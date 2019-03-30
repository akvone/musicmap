package com.akvone.service.impl;

import com.akvone.dao.LocationRepository;
import com.akvone.entity.LocationEntity;
import com.akvone.service.LocationService;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

  private final LocationRepository locationRepository;

  @Override
  @NotNull
  public LocationEntity get(Long id) {
    return locationRepository.getOne(id);
  }
}
