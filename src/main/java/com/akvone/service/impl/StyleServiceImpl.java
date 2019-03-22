package com.akvone.service.impl;

import com.akvone.dao.StyleDAO;
import com.akvone.entity.Style;
import com.akvone.service.StyleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class StyleServiceImpl implements StyleService {

  private final StyleDAO styleDAO;

  @Override
  public Style getById(Long id) {
    if (styleDAO.exists(id)) {
      return styleDAO.getById(id);
    } else {
      log.warn("Style have not been found in database. styleID = {} ", id);
      return null;
    }
  }

}
