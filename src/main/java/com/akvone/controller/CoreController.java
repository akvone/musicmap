package com.akvone.controller;

import com.akvone.dto.UserDataDto;
import com.akvone.dto.LocationStatisticsDto;
import com.akvone.service.HistoryRecordService;
import com.akvone.service.CoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.RuntimeService;
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
public class CoreController {

  private final HistoryRecordService historyRecordService;
  private final CoreService coreService;
  private final RuntimeService runtimeService;

  @GetMapping("/start")
  public String sendStartPage() {
    runtimeService.startProcessInstanceByKey("sendApplicant");
    return "start";
  }

  @PostMapping("/add_user")
  public ResponseEntity<Object> postUserData(@RequestBody UserDataDto userDataDto) {
    log.debug("Received from client: {}", userDataDto);

    coreService.handleUserData(userDataDto);

    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping("/regStat")
  public ResponseEntity<LocationStatisticsDto> getLocationStatistics(@RequestParam("locationId") Long locationId) {
    log.debug("Get request on info for {} location", locationId);

    LocationStatisticsDto stat = historyRecordService.getLocationStatistics(locationId);

    return new ResponseEntity<>(stat, HttpStatus.OK);
  }

}