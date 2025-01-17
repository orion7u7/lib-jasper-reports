package com.ogue.libjasperreports.controller;


import com.ogue.libjasperreports.common.LoadFiles;
import com.ogue.libjasperreports.model.Assigment;
import com.ogue.libjasperreports.service.AssigmentService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/asignaciones-familiares/v1/solicitudes")
public class ReportController {


    private final LoadFiles loadFilesConfig;
    private final AssigmentService assigmentService;

    public ReportController(LoadFiles loadFilesConfig, AssigmentService assigmentService) {
        this.loadFilesConfig = loadFilesConfig;
        this.assigmentService = assigmentService;
    }

    @PostMapping("/procesar")
    public ResponseEntity<byte[]> generateReport(@RequestBody Assigment body) {
        try {
            String templateName = loadFilesConfig.load().get("reporte1");
            if (templateName == null || templateName.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }

            byte[] report = assigmentService.createAssigment(templateName,body);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("inline", "Cert_AFyMAT_Pg1" + ".pdf");

            return new ResponseEntity<>(report, headers, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}