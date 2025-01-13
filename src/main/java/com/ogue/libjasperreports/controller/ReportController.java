package com.ogue.libjasperreports.controller;


import com.ogue.libjasperreports.common.LoadFiles;
import com.ogue.strategy.ReportStrategy;
import com.ogue.strategy.ReportStrategyContext;
import com.ogue.libjasperreports.mock.AssigmentMock;
import com.ogue.libjasperreports.service.AssigmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportStrategy reportStrategy;

    //private final ReportStrategyContext reportStrategyContext = new ReportStrategyContext();
    private final LoadFiles loadFiles;
    private final AssigmentService assigmentService;


    public ReportController( ReportStrategy reportStrategy,LoadFiles loadFiles, AssigmentService assigmentService) {
        this.reportStrategy = reportStrategy;
        this.loadFiles = loadFiles;
        this.assigmentService = assigmentService;
    }

    @GetMapping("/{reportType}")
    public ResponseEntity<byte[]> generateReport(@PathVariable() String reportType) {
        try {
            String templateName = loadFiles.load().get(reportType);

            byte[] report = reportStrategy.generateReport(templateName, assigmentService.createAssigment(AssigmentMock.assigmentMock()));
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("inline", reportType + ".pdf");

            return new ResponseEntity<>(report, headers, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}