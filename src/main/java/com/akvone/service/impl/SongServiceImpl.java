package com.akvone.service.impl;

import com.akvone.dao.SongDAO;
import com.akvone.entity.Singer;
import com.akvone.entity.Song;
import com.akvone.entity.Style;
import com.akvone.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SongServiceImpl implements SongService {

  private final SongDAO songDAO;

  @Override
  public Song add(Long vkId, Singer singer, Style style) {
    Song song;
    if (songDAO.exists(vkId)) {
      song = songDAO.getByVkId(vkId);
    } else {
      song = new Song();
      song.setVkId(vkId);
      song.setSinger(singer);
      song.setStyle(style);
      songDAO.save(song);
    }
    return song;
  }
}
