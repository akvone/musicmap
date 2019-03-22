package com.akvone.controller;

import com.akvone.dto.JSONUserData;
import com.akvone.service.HistoryRecordService;
import com.akvone.service.RouterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
  @ResponseBody
  public String search(@RequestParam("locationId") Long locationId) {
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("userCount", historyRecordService.getUserCountByLocationId(locationId));
    JSONArray jsonArray = new JSONArray();
    for (Object s : historyRecordService.getStyleTop(locationId).toArray()) {
      jsonArray.add(s.toString());
    }
    jsonObject.put("topStyles", jsonArray);
    return jsonObject.toJSONString();
  }

}