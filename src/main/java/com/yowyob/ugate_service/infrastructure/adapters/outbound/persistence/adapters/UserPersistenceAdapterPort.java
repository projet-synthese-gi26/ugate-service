package com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.adapters;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.yowyob.ugate_service.domain.model.UserModel;
import com.yowyob.ugate_service.domain.ports.out.syndicate.UserPersistencePort;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.SyndicatMemberRepository;
import com.yowyob.ugate_service.infrastructure.adapters.outbound.persistence.repository.UserRepository;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;

@Component
@AllArgsConstructor
public class UserPersistenceAdapterPort implements UserPersistencePort {

  private final UserRepository userRepository;
  private final SyndicatMemberRepository syndicatMemberRepository;

  @Override
  public Flux<UserModel> findUsersByBranchId(UUID branchId) {
    return syndicatMemberRepository.findByBranchId(branchId)
        .flatMap(member -> userRepository.findById(member.userId()))
        .map(userEntity -> {
          UserModel userModel = new UserModel();
          userModel.setId(userEntity.getId().toString());
          userModel.setEmail(userEntity.email());
          userModel.setName(userEntity.name());
          userModel.setPhoneNumber(userEntity.phoneNumber());
          return userModel;
        });

  }

}
