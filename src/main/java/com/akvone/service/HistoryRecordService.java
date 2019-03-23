package com.akvone.service;

import com.akvone.dto.LocationStatistics;
import com.akvone.entity.HistoryRecord;
import com.akvone.entity.Location;
import com.akvone.entity.Song;
import com.akvone.entity.User;

public interface HistoryRecordService {

  HistoryRecord add(User user, Song song, Location location);

  LocationStatistics getLocationStatistics(long locationId);

}
