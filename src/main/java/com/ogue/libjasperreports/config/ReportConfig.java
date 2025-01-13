package com.ogue.libjasperreports.config;

import com.ogue.strategy.ReportStrategy;
import com.ogue.strategy.ReportStrategyContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReportConfig {

    @Bean
    public ReportStrategy reportStrategy() {
        return new ReportStrategyContext();
    }
}
