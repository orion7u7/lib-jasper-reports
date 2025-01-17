package com.ogue.libjasperreports.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DatosCarga {
    private String rutcarga;
    private String dvcargga;
    private String nombres;
    private String apellidos;
    private String cod_sex;
    private String cod_ben;
    private String cod_cau;
    private String fec_dia;
    private String fec_mes;
    private String fec_año;
}
