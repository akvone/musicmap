package com.akvone.service.impl;

import com.akvone.dao.StyleDAO;
import com.akvone.entity.Style;
import com.akvone.service.StyleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StyleServiceImpl implements StyleService {

  private final StyleDAO styleDAO;

  @Override
  public Style getById(Long id) {
    if (styleDAO.exists(id)) {
      return styleDAO.getById(id);
    } else {
      System.out.println();
      System.out.println("Style have not been found in database (styleID = " + id + ")");
      return null;
    }
  }

}
