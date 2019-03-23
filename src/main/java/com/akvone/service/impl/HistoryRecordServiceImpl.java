package com.akvone.service.impl;

import com.akvone.dao.HistoryRecordDAO;
import com.akvone.dto.LocationStatistics;
import com.akvone.entity.HistoryRecord;
import com.akvone.entity.Location;
import com.akvone.entity.Song;
import com.akvone.entity.User;
import com.akvone.service.HistoryRecordService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class HistoryRecordServiceImpl implements HistoryRecordService {

  private final HistoryRecordDAO historyRecordDAO;

  @Override
  public HistoryRecord add(User user, Song song, Location location) {
    if (!historyRecordDAO.exists(user, song)) {
      HistoryRecord historyRecord = new HistoryRecord();
      historyRecord.setUser(user);
      historyRecord.setSong(song);
      historyRecord.setLocation(location);
      historyRecordDAO.save(historyRecord);
    }
    return historyRecordDAO.getByUserAndSong(user, song);
  }

  @Override
  @Transactional
  public LocationStatistics getLocationStatistics(long locationId) {
    Long userCount = historyRecordDAO.getUserCountByLocationId(locationId);
    List<String> topStyles = historyRecordDAO.getTopStylesByLocation(locationId);

    return new LocationStatistics(userCount, topStyles);
  }

}
