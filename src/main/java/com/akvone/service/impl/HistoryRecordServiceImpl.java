package com.akvone.service.impl;

import static java.util.stream.Collectors.toList;

import com.akvone.dao.HistoryRecordRepository;
import com.akvone.dto.LocationStatisticsDto;
import com.akvone.entity.GroupEntity;
import com.akvone.entity.HistoryRecordEntity;
import com.akvone.entity.LocationEntity;
import com.akvone.entity.UserEntity;
import com.akvone.service.HistoryRecordService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class HistoryRecordServiceImpl implements HistoryRecordService {

  private final HistoryRecordRepository historyRecordRepository;

  @Override
  public void addIfNotExist(UserEntity userEntity, GroupEntity groupEntity, LocationEntity locationEntity) {
    if (!historyRecordRepository.existsByUserAndGroup(userEntity, groupEntity)) {
      save(userEntity, groupEntity, locationEntity);
    }
  }

  private void save(UserEntity userEntity, GroupEntity groupEntity, LocationEntity locationEntity) {
    HistoryRecordEntity historyRecord = new HistoryRecordEntity();
    historyRecord.setUser(userEntity);
    historyRecord.setGroup(groupEntity);
    historyRecord.setLocation(locationEntity);

    historyRecordRepository.save(historyRecord);
  }

  @Override
  @Transactional
  public LocationStatisticsDto getLocationStatistics(long locationId) {
    int userCount = historyRecordRepository.countUsersByLocation(locationId);
    List<HistoryRecordEntity> topStyles = historyRecordRepository.findByLocationId(locationId);

    List<String> groupNames = topStyles.stream()
        .map(e -> e.getGroup().getName())
        .collect(toList());

    return new LocationStatisticsDto(userCount, groupNames);
  }

}
