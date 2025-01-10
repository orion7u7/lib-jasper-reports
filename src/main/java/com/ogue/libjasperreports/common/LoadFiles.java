package com.ogue.libjasperreports.common;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class LoadFiles {

    @Bean
    public  Map<String, String> load() {
        Map<String, String> templateMap = new HashMap<>();
        templateMap.put("reporte1", "Cert_AFyMAT_Pg1.jasper");
        templateMap.put("reporte2", "prueba_reporte.jasper");

        return templateMap;
    }
}
