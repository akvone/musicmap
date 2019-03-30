package com.akvone.service.impl;

import com.akvone.dao.UserRepository;
import com.akvone.entity.UserEntity;
import com.akvone.service.UserService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Override
  public UserEntity createOrUpdate(Long vkId, double[] coordinates) {
    UserEntity userEntity;
    Optional<UserEntity> userOpt = userRepository.findById(vkId);

    if (!userOpt.isPresent()) {
      userEntity = new UserEntity();
      userEntity.setId(vkId);
    } else {
      userEntity = userOpt.get();
    }

    userEntity.setX(coordinates[0]);
    userEntity.setY(coordinates[1]);

    return userRepository.save(userEntity);
  }
}
