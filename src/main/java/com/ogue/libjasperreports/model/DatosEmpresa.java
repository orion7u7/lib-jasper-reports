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
    private String razon_soc;
    private String domicilio;
    private String comuna;
    private String ciudad;
    private String region;
    private String fono_fijo;
    private String fono_cel;
    private String email;
}
