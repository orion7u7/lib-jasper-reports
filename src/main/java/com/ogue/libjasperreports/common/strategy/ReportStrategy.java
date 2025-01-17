package com.ogue.libjasperreports.common.strategy;

import net.sf.jasperreports.engine.JRException;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ReportStrategy {
    byte[] generateReport(String reportType, Map<String, Object> params) throws JRException, IOException;
    byte[] combineReports(List<byte[]> reports) throws IOException;
}