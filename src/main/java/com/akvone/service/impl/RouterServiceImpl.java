package com.akvone.service.impl;

import com.akvone.entity.HistoryRecord;
import com.akvone.entity.JSONSong;
import com.akvone.entity.JSONUserData;
import com.akvone.entity.Location;
import com.akvone.entity.Singer;
import com.akvone.entity.Song;
import com.akvone.entity.Style;
import com.akvone.entity.User;
import com.akvone.service.HistoryRecordService;
import com.akvone.service.LocationService;
import com.akvone.service.RouterService;
import com.akvone.service.SingerService;
import com.akvone.service.SongService;
import com.akvone.service.StyleService;
import com.akvone.service.UserService;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RouterServiceImpl implements RouterService {

  @Autowired
  private UserService userService;

  @Autowired
  private LocationService locationService;

  @Autowired
  private StyleService styleService;

  @Autowired
  private SingerService singerService;

  @Autowired
  private SongService songService;

  @Autowired
  private HistoryRecordService historyRecordService;

  @Transactional
  public Set<HistoryRecord> route(JSONUserData jsonUserData) {

    User user;
    if ((!jsonUserData.getVkIdLine().equals("")) && (jsonUserData.getVkIdLine() != null)) {
      user = userService.add(Long.parseLong(jsonUserData.getVkIdLine()), jsonUserData.getX(), jsonUserData.getY());
    } else {
      return null;
    }

    Location location = locationService.getById(jsonUserData.getLocationId());

    if (location == null) {
      return null;
    }

    List<JSONSong> songs = jsonUserData.getSongs();
    Set<HistoryRecord> historyRecords = new HashSet<HistoryRecord>();
    for (JSONSong jsonSong : songs) {
      Style style;
      if (jsonSong.getStyleName() != null) {
        style = styleService.getById(Long.parseLong(jsonSong.getStyleName()));
      } else {
        continue;
      }
      if (style == null) {
        continue;
      }

      Singer singer;
      if ((jsonSong.getSinger() == null) || (jsonSong.getSinger().equals(""))) {
        singer = singerService.add("Unknown artist");
      } else {
        singer = singerService.add(jsonSong.getSinger());
      }

      Song song;
      if (jsonSong.getId() == null) {
        continue;
      } else {
        song = songService.add(jsonSong.getId(), singer, style);
      }

      HistoryRecord historyRecord = historyRecordService.add(user, song, location);

      historyRecords.add(historyRecord);
    }
    return historyRecords;
  }
}