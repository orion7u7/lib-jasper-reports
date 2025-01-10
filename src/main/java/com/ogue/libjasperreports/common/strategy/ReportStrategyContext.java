package com.ogue.libjasperreports.common.strategy;

import com.ogue.libjasperreports.common.LoadFiles;
import net.sf.jasperreports.engine.*;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Map;

@Service
public class ReportStrategyContext implements ReportStrategy {

    private final LoadFiles loadFiles;

    public ReportStrategyContext(LoadFiles loadFiles) {
        this.loadFiles = loadFiles;
    }

    @Override
    public byte[] generateReport(String reportType, Map<String, Object> params) throws JRException {
        try {
            String templateName = loadFiles.load().get(reportType);

            if (templateName == null) {
                throw new IllegalArgumentException("No template found for report type: " + reportType);
            }

            InputStream reportStream = this.getClass().getResourceAsStream("/reports/" + templateName);
            if (reportStream == null) {
                throw new IllegalArgumentException("Template file not found: " + templateName);
            }


            JasperPrint jasperPrint = JasperFillManager.fillReport(reportStream, params, new JREmptyDataSource());
            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (Exception e) {
            throw new JRException("Error al generar el reporte", e);
        }

    }

}