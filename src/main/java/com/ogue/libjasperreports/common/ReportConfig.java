package com.ogue.libjasperreports.common;

import com.ogue.libjasperreports.common.strategy.ReportStrategy;
import com.ogue.libjasperreports.common.strategy.ReportStrategyContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ReportConfig {

    @Bean
    public ReportStrategy reportStrategy() {
        return new ReportStrategyContext();
    }
}
