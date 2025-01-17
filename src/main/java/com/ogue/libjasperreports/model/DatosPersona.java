package com.ogue.libjasperreports.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DatosPersona {
    private String rutpersona;
    private String dvpersona;
    private String nombre;
    private String ap_paterno;
    private String ap_materno;
    private String domicilio;
    private String comuna;
    private String ciudad;
    private String region;
    private String fono_fijo;
    private String fono_cel;
    private String email;
    private String fecha_ini_cont;
    private String fecha_ter_cont;
}
