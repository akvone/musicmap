package com.akvone.service.impl;

import com.akvone.dao.LocationDAO;
import com.akvone.entity.Location;
import com.akvone.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

  private final LocationDAO locationDAO;

  @Override
  public Location getById(Long id) {
    return locationDAO.getById(id);
  }
}
