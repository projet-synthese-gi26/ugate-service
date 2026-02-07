package com.yowyob.ugate_service.application.service.content;

import com.yowyob.ugate_service.domain.model.PublicationVoteModel;
import com.yowyob.ugate_service.domain.model.VoteModel;
import com.yowyob.ugate_service.domain.ports.in.content.CastVoteUseCase;
import com.yowyob.ugate_service.domain.ports.in.content.CreatePublicationVoteUseCase;
import com.yowyob.ugate_service.domain.ports.in.content.GetPublicationVoteResultsUseCase;
import com.yowyob.ugate_service.domain.ports.out.syndicate.PublicationVotePersistencePort;
import com.yowyob.ugate_service.domain.ports.out.syndicate.VotePersistencePort;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.PublicationVoteWithResultsDTO;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.VoteResultDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import reactor.core.publisher.Mono;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PublicationVoteService
        implements CreatePublicationVoteUseCase, CastVoteUseCase, GetPublicationVoteResultsUseCase {

    private final PublicationVotePersistencePort publicationVotePersistencePort;
    private final VotePersistencePort votePersistencePort;

    @Override
    public Mono<Void> createPublicationVote(PublicationVoteModel publicationVoteModel) {

        return publicationVotePersistencePort.save(publicationVoteModel).then();
    }

    @Override
    public Mono<Void> castVote(UUID userId, UUID publicationVoteId, String choiceLabel) {
        return publicationVotePersistencePort.findById(publicationVoteId)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Poll not found")))
                .flatMap(poll -> {
                    if (poll.getClosingAt() != null && poll.getClosingAt().isBefore(Instant.now())) {
                        return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "This poll is closed."));
                    }
                    VoteModel vote = new VoteModel(null, userId, publicationVoteId, choiceLabel);
                    return votePersistencePort.save(vote);
                });
    }

    @Override
    public Mono<PublicationVoteWithResultsDTO> getPublicationVoteResults(UUID publicationVoteId, UUID userId) {
        Mono<PublicationVoteModel> pollMono = publicationVotePersistencePort.findById(publicationVoteId)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Poll not found")));

        Mono<List<VoteModel>> votesMono = votePersistencePort.findByPublicationVoteId(publicationVoteId).collectList();

        return Mono.zip(pollMono, votesMono)
                .map(tuple -> {
                    PublicationVoteModel poll = tuple.getT1();
                    List<VoteModel> votes = tuple.getT2();

                    long totalVotes = votes.size();
                    boolean hasVoted = votes.stream().anyMatch(v -> v.getUserId().equals(userId));

                    List<VoteResultDTO> results = votes.stream()
                            .collect(Collectors.groupingBy(VoteModel::getLabel, Collectors.counting()))
                            .entrySet().stream()
                            .map(entry -> new VoteResultDTO(entry.getKey(), entry.getValue()))
                            .collect(Collectors.toList());

                    return PublicationVoteWithResultsDTO.from(poll, totalVotes, results, hasVoted);
                });
    }
}