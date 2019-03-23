package com.akvone.controller;

import com.akvone.dto.JSONUserData;
import com.akvone.dto.LocationStatistics;
import com.akvone.service.HistoryRecordService;
import com.akvone.service.RouterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequestMapping("")
@Validated
@RequiredArgsConstructor
public class SpringController {

  private final HistoryRecordService historyRecordService;
  private final RouterService routerService;

  @GetMapping("/start")
  public String sendStartPage() {
    return "start";
  }

  @PostMapping("/add_user")
  public void receiveJSONUserData(@RequestBody JSONUserData jsonUserData) {
    log.debug("Received from client: {}", jsonUserData);
    routerService.route(jsonUserData);
  }

  @GetMapping("/regStat")
  public ResponseEntity<LocationStatistics> getLocationStatistics(@RequestParam("locationId") Long locationId) {
    log.debug("Get request on info by {} location", locationId);

    LocationStatistics stat = historyRecordService.getLocationStatistics(locationId);

    return new ResponseEntity<>(stat, HttpStatus.OK);
  }

}