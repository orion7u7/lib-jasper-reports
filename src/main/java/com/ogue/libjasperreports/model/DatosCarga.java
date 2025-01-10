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
    private String dvcarga;
    private String nombres;
    private String apellidos;
    private String codSex;
    private String codBen;
    private String codCau;
    private String fecDia;
    private String fecMes;
    private String fecAo;
}
