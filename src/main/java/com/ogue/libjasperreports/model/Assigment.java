package com.ogue.libjasperreports.model;

import lombok.*;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Assigment {
    private DatosPersona datosPersona;
    private DatosEmpresa datosEmpresa;
    private List<DatosCarga> listaCausantes;
}

