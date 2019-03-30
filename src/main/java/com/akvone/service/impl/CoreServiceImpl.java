package com.akvone.service.impl;

import com.akvone.dto.GroupDto;
import com.akvone.dto.UserDataDto;
import com.akvone.entity.GroupEntity;
import com.akvone.entity.LocationEntity;
import com.akvone.entity.UserEntity;
import com.akvone.service.CoreService;
import com.akvone.service.GroupService;
import com.akvone.service.HistoryRecordService;
import com.akvone.service.LocationService;
import com.akvone.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CoreServiceImpl implements CoreService {

  private final UserService userService;
  private final LocationService locationService;
  private final GroupService groupService;
  private final HistoryRecordService historyRecordService;

  @Transactional
  public void handleUserData(UserDataDto userData) {
    UserEntity userEntity = userService.createOrUpdate(userData.getVkId(), userData.getCoordinates());
    LocationEntity locationEntity = locationService.get(userData.getLocationId());

    for (GroupDto groupDto : userData.getGroupDtos()) {
      GroupEntity groupEntity = groupService.createOrUpdate(groupDto);

      historyRecordService.addIfNotExist(userEntity, groupEntity, locationEntity);
    }
  }
}