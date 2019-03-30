package com.akvone.service;

import com.akvone.entity.UserEntity;

public interface UserService {

  UserEntity createOrUpdate(Long vkId, double[] coordinates);
}