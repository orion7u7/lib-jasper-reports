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
    private String apPaterno;
    private String apMaterno;
    private String domicilio;
    private String comuna;
    private String ciudad;
    private String region;
    private String fonoFijo;
    private String fonoCel;
    private String email;
    private String fechaIniCont;
    private String fechaTerCont;
}
