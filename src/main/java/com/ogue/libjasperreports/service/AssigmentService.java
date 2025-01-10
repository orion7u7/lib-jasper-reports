package com.ogue.libjasperreports.service;

import com.ogue.strategy.ReportStrategyContext;
import com.ogue.libjasperreports.model.Assigment;
import com.ogue.libjasperreports.model.DatosCarga;
import com.ogue.libjasperreports.model.DatosEmpresa;
import com.ogue.libjasperreports.model.DatosPersona;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Service
public class AssigmentService {

    public Map<String, Object> createAssigment(Assigment assigment) throws IOException {

        Map<String, Object> resultMap = new HashMap<>();

        InputStream imageFile = Objects.requireNonNull(ReportStrategyContext.class.getResourceAsStream("/POC/logo_cla.png"), "No se pudo encontrar la imagen en el directorio de recursos.");
        BufferedImage image = Objects.requireNonNull(ImageIO.read(imageFile), "Error al cargar la imagen.");
        resultMap.put("logo", image);

        // Mapear datosPersona como beneficiario
        if (assigment.getDatosPersona() != null) {
            DatosPersona datosPersona = assigment.getDatosPersona();
            resultMap.put("beneficiario_run", datosPersona.getRutpersona() + "-" + datosPersona.getDvpersona());
            resultMap.put("beneficiario_apePat", datosPersona.getApPaterno());
            resultMap.put("beneficiario_apeMat", datosPersona.getApMaterno());
            resultMap.put("beneficiario_nombres", datosPersona.getNombre());
            resultMap.put("beneficiario_domiCalle", datosPersona.getDomicilio());
            resultMap.put("beneficiario_n", ""); // Campo no especificado
            resultMap.put("beneficiario_deptoComuna", datosPersona.getComuna());
            resultMap.put("beneficiario_ciudad", datosPersona.getCiudad());
            resultMap.put("beneficiario_region", datosPersona.getRegion());
            resultMap.put("beneficiario_fonoFijo", datosPersona.getFonoFijo());
            resultMap.put("beneficiario_fonoMovil", datosPersona.getFonoCel());
            resultMap.put("beneficiario_correoElectronico", datosPersona.getEmail());
        }

        // Mapear datosEmpresa como empleador
        if (assigment.getDatosEmpresa() != null) {
            DatosEmpresa datosEmpresa = assigment.getDatosEmpresa();
            resultMap.put("empleador_rut", datosEmpresa.getRutempresa() + "-" + datosEmpresa.getDvempresa());
            resultMap.put("empleador_razonSocial", datosEmpresa.getRazonSoc());
            resultMap.put("empleador_fechaInicioContrato", ""); // Campo no mapeado desde Assigment
            resultMap.put("empleador_fechaTerminoContrato", ""); // Campo no mapeado desde Assigment
            resultMap.put("empleador_domiCalle", datosEmpresa.getDomicilio());
            resultMap.put("empleador_n", ""); // Campo no especificado
            resultMap.put("empleador_deptoComuna", datosEmpresa.getComuna());
            resultMap.put("empleador_ciudad", datosEmpresa.getCiudad());
            resultMap.put("empleador_region", datosEmpresa.getRegion());
            resultMap.put("empleador_fonoFijo", datosEmpresa.getFonoFijo());
            resultMap.put("empleador_fonoMovil", datosEmpresa.getFonoCel());
            resultMap.put("empleador_correoElectronico", datosEmpresa.getEmail());
            resultMap.put("empleador_sucursal", ""); // Campo no especificado
        }

        List<Map<String, String>> causantesList = new ArrayList<>();
        if (assigment.getListaCausantes() != null && !assigment.getListaCausantes().isEmpty()) {
            List<DatosCarga> datosCargaList = assigment.getListaCausantes();
            for (DatosCarga datosCarga : datosCargaList) {
                Map<String, String> causanteMap = new HashMap<>();
                causanteMap.put("causanteRun", datosCarga.getRutcarga() + "-" + datosCarga.getDvcarga());
                causanteMap.put("nombreCausante", datosCarga.getNombres());
                causanteMap.put("causanteCodSexo", datosCarga.getCodSex());
                causanteMap.put("causanteCodTipoBeneficio", datosCarga.getCodBen());
                causanteMap.put("causanteCodTipoCausante", datosCarga.getCodCau());
                causanteMap.put("diaFechaNacimiento", datosCarga.getFecDia());
                causanteMap.put("mesFechaNacimiento", datosCarga.getFecMes());
                causanteMap.put("anioFechaNacimiento", datosCarga.getFecAo());
                causanteMap.put("diaFechaInicioBeneficio", "");
                causanteMap.put("mesFechaInicioBeneficio", "");
                causanteMap.put("anioFechaInicioBeneficio", "");
                causanteMap.put("firmaDeclaracion", "");
                causanteMap.put("usoExclusivo", "");
                causantesList.add(causanteMap);
            }
            resultMap.put("listaCausantes", new JRBeanArrayDataSource(causantesList.toArray()));
        }

        return resultMap;
    }

}
