package com.akvone.service;

import com.akvone.dto.LocationStatisticsDto;
import com.akvone.entity.GroupEntity;
import com.akvone.entity.LocationEntity;
import com.akvone.entity.UserEntity;

public interface HistoryRecordService {

  void addIfNotExist(UserEntity userEntity, GroupEntity group, LocationEntity locationEntity);

  LocationStatisticsDto getLocationStatistics(long locationId);

}
