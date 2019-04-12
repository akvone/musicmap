package com.akvone.controller;

import com.akvone.dto.UserDataDto;
import com.akvone.dto.LocationStatisticsDto;
import com.akvone.jasper.SimpleReportExporter;
import com.akvone.jasper.SimpleReportFiller;
import com.akvone.service.HistoryRecordService;
import com.akvone.service.CoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("")
@Validated
@RequiredArgsConstructor
public class CoreController {

  private final HistoryRecordService historyRecordService;
  private final CoreService coreService;

  @GetMapping("/start")
  public String sendStartPage() {
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


  @Autowired
  private DataSource dataSource;

  @GetMapping("/get")
  public void getRpt1(HttpServletResponse response) throws JRException, IOException, SQLException {

//    Connection con = dataSource.getConnection();
//    Statement stmt = con.createStatement();
//    ResultSet rs = stmt.executeQuery("SELECT * FROM locations");
//    while(rs.next()){
//      System.out.println("Location ID="+rs.getInt("id")+", Name="+rs.getString("name"));
//    }

    SimpleReportFiller simpleReportFiller = new SimpleReportFiller();
    simpleReportFiller.setDataSource(dataSource);

    /* SUB reports */
    simpleReportFiller.setReportFileName("locationNameReport.jrxml");
    simpleReportFiller.compileReport();
    simpleReportFiller.setReportFileName("votesNumberReport.jrxml");
    simpleReportFiller.compileReport();
    simpleReportFiller.setReportFileName("employeeEmailReport.jrxml");
    simpleReportFiller.compileReport();

    /* ROOT report */
    simpleReportFiller.setReportFileName("employeeReport.jrxml");
    simpleReportFiller.compileReport();

    Map<String, Object> parameters = new HashMap<>();
    parameters.put("title", "ALL COUNTY STATISTIC");

    simpleReportFiller.setParameters(parameters);
    simpleReportFiller.fillReport();

    SimpleReportExporter simpleExporter = new SimpleReportExporter();
    simpleExporter.setJasperPrint(simpleReportFiller.getJasperPrint());


    simpleExporter.exportToXlsx("employeeReport.xlsx", "Employee Data");
    simpleExporter.exportToCsv("employeeReport.csv");
    simpleExporter.exportToHtml("employeeReport.html");


    response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
    response.setHeader("Content-disposition", "inline; filename=helloWorldReport12345.docx");
    final OutputStream outStream = response.getOutputStream();
    simpleExporter.exportToPdf("employeeReport.pdf", "baeldung", outStream);
//    simpleExporter.exportToDocx("helloWorldReport12345.docx", "baeldung", outStream);




//    InputStream jasperStream = this.getClass().getResourceAsStream("/jasperreports/HelloWorld1.jasper");
//    Map<String,Object> params = new HashMap<>();
//    JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
//    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());
//
//    response.setContentType("application/x-pdf");
//    response.setHeader("Content-disposition", "inline; filename=helloWorldReport.pdf");
//
//    final OutputStream outStream = response.getOutputStream();
//    JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
  }

  @GetMapping("/docx")
  public void getDocx(HttpServletResponse response) throws JRException, IOException, SQLException {

//    Connection con = dataSource.getConnection();
//    Statement stmt = con.createStatement();
//    ResultSet rs = stmt.executeQuery("SELECT * FROM locations");
//    while(rs.next()){
//      System.out.println("Location ID="+rs.getInt("id")+", Name="+rs.getString("name"));
//    }

    SimpleReportFiller simpleReportFiller = new SimpleReportFiller();
    simpleReportFiller.setDataSource(dataSource);

    /* SUB reports */
    simpleReportFiller.setReportFileName("locationNameReport.jrxml");
    simpleReportFiller.compileReport();
    simpleReportFiller.setReportFileName("votesNumberReport.jrxml");
    simpleReportFiller.compileReport();
    simpleReportFiller.setReportFileName("employeeEmailReport.jrxml");
    simpleReportFiller.compileReport();

    /* ROOT report */
    simpleReportFiller.setReportFileName("employeeReport.jrxml");
    simpleReportFiller.compileReport();

    Map<String, Object> parameters = new HashMap<>();
    parameters.put("title", "ALL COUNTY STATISTIC");

    simpleReportFiller.setParameters(parameters);
    simpleReportFiller.fillReport();

    SimpleReportExporter simpleExporter = new SimpleReportExporter();
    simpleExporter.setJasperPrint(simpleReportFiller.getJasperPrint());


    simpleExporter.exportToXlsx("employeeReport.xlsx", "Employee Data");
    simpleExporter.exportToCsv("employeeReport.csv");
    simpleExporter.exportToHtml("employeeReport.html");


    response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
    response.setHeader("Content-disposition", "inline; filename=SpbVKGroupStatistic.docx");
    final OutputStream outStream = response.getOutputStream();
    simpleExporter.exportToDocx("SpbVKGroupStatistic.docx", "baeldung", outStream);



//    InputStream jasperStream = this.getClass().getResourceAsStream("/jasperreports/HelloWorld1.jasper");
//    Map<String,Object> params = new HashMap<>();
//    JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
//    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());
//
//    response.setContentType("application/x-pdf");
//    response.setHeader("Content-disposition", "inline; filename=helloWorldReport.pdf");
//
//    final OutputStream outStream = response.getOutputStream();
//    JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
  }
}