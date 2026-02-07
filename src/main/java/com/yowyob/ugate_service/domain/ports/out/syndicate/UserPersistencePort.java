package com.yowyob.ugate_service.domain.ports.out.syndicate;

import java.util.UUID;

import com.yowyob.ugate_service.domain.model.UserModel;

import reactor.core.publisher.Flux;

public interface UserPersistencePort {
  public Flux<UserModel> findUsersByBranchId(UUID branchId);
}
