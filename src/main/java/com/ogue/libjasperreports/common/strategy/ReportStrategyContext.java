package com.ogue.libjasperreports.common.strategy;

import net.sf.jasperreports.engine.*;
import org.apache.pdfbox.multipdf.PDFMergerUtility;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class ReportStrategyContext implements ReportStrategy {


    @Override
    public byte[] generateReport(String reportType, Map<String, Object> params) throws JRException {
        try {
            InputStream reportStream = this.getClass().getResourceAsStream("/reports/" + reportType);
            if (reportStream == null) {
                throw new IllegalArgumentException("Template file not found: " + reportType);
            }

            JasperPrint jasperPrint = JasperFillManager.fillReport(reportStream, params, new JREmptyDataSource());
            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (JRException e) {
            throw new JRException("Error al generar el reporte", e);
        }
    }

    @Override
    public byte[] combineReports(List<byte[]> reports) throws IOException {
        PDFMergerUtility pdfMerger = new PDFMergerUtility();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        for (byte[] report : reports) {
            pdfMerger.addSource(new ByteArrayInputStream(report));
        }
        pdfMerger.setDestinationStream(outputStream);
        pdfMerger.mergeDocuments(null);

        return outputStream.toByteArray();
    }
}
