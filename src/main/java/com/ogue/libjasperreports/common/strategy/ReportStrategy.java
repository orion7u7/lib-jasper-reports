package com.ogue.libjasperreports.common.strategy;

import net.sf.jasperreports.engine.JRException;

import java.io.IOException;
import java.util.Map;

public interface ReportStrategy {
    byte[] generateReport(String reportType, Map<String, Object> params) throws JRException, IOException;
}