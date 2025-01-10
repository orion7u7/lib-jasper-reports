package com.ogue.libjasperreports.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DatosEmpresa {
    private String rutempresa;
    private String dvempresa;
    private String razonSoc;
    private String domicilio;
    private String comuna;
    private String ciudad;
    private String region;
    private String fonoFijo;
    private String fonoCel;
    private String email;
}
