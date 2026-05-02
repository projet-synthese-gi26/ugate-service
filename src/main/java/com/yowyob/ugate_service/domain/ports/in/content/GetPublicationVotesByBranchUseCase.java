package com.yowyob.ugate_service.domain.ports.in.content;

import com.yowyob.ugate_service.domain.model.PublicationVoteModel;
import reactor.core.publisher.Flux;

import java.util.UUID;

public interface GetPublicationVotesByBranchUseCase {
    Flux<PublicationVoteModel> getVotesByBranch(UUID branchId);
}