package com.akvone.service;

import com.akvone.dto.GroupDto;
import com.akvone.entity.GroupEntity;

public interface GroupService {

  GroupEntity createOrUpdate(GroupDto groupDto);

}
