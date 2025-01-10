package com.ogue.libjasperreports.mock;

import com.ogue.libjasperreports.model.Assigment;
import com.ogue.libjasperreports.model.DatosCarga;
import com.ogue.libjasperreports.model.DatosEmpresa;
import com.ogue.libjasperreports.model.DatosPersona;

import java.util.List;

public class AssigmentMock {

    public static Assigment assigmentMock (){
        DatosPersona datosPersona = new DatosPersona("12345678", "9", "Juan", "Perez", "Lopez", "Calle Falsa 123", "Comuna 1", "Ciudad 1", "Region 1", "2222222", "9999999", "juan.perez@example.com", "2023-01-01", "2023-12-31");
        DatosEmpresa datosEmpresa = new DatosEmpresa("87654321", "K", "Empresa SA", "Calle Verdadera 456", "Comuna 2", "Ciudad 2", "Region 2", "3333333", "8888888", "empresa@example.com");
        DatosCarga carga1 = new DatosCarga("11111111", "1", "Ana", "Lopez", "F", "BEN1", "CAU1", "01", "01", "2023");
        DatosCarga carga2 = new DatosCarga("22222222", "2", "Luis", "Martinez", "M", "BEN2", "CAU2", "02", "02", "2023");
        DatosCarga carga3 = new DatosCarga("33333333", "3", "Maria", "Gonzalez", "F", "BEN3", "CAU3", "03", "03", "2023");
        DatosCarga carga4 = new DatosCarga("44444444", "4", "Pedro", "Rodriguez", "M", "BEN4", "CAU4", "04", "04", "2023");

        Assigment assigment = new Assigment(datosPersona, datosEmpresa, List.of(carga1, carga2, carga3, carga4));

        return assigment;
    }


}
