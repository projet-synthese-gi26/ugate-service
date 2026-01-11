package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response;

import com.yowyob.ugate_service.domain.model.PublicationVoteModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublicationVoteWithResultsDTO {
    private UUID id;
    private String title;
    private String description;
    private Instant closingAt;
    private String type;
    private long totalVotes;
    private List<VoteResultDTO> results;
    private boolean hasVoted;

    public static PublicationVoteWithResultsDTO from(PublicationVoteModel model, long totalVotes, List<VoteResultDTO> results, boolean hasVoted) {
        return new PublicationVoteWithResultsDTO(
            model.getId(),
            model.getTitle(),
            model.getDescription(),
            model.getClosingAt(),
            model.getType(),
            totalVotes,
            results,
            hasVoted
        );
    }
}
