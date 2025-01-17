package com.ogue.libjasperreports.service;

import com.ogue.libjasperreports.common.strategy.ReportStrategy;
import com.ogue.libjasperreports.common.strategy.ReportStrategyContext;
import com.ogue.libjasperreports.model.Assigment;
import com.ogue.libjasperreports.model.DatosCarga;
import com.ogue.libjasperreports.model.DatosEmpresa;
import com.ogue.libjasperreports.model.DatosPersona;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Service
public class AssigmentService {

    private final ReportStrategy reportStrategy;


    public AssigmentService(ReportStrategy reportStrategy) {
        this.reportStrategy = reportStrategy;
    }

    public byte[] createAssigment(String templateName, Assigment assigment) throws JRException, IOException {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            resultMap.put("logo", loadImage());
            mapBeneficiaryData(assigment, resultMap);
            mapEmployerData(assigment, resultMap);
        } catch (IOException e) {
            throw new RuntimeException("Error al procesar la carga de recursos: " + e.getMessage(), e);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Error al mapear los datos: " + e.getMessage(), e);
        }

        List<byte[]> reportPages = new ArrayList<>();
        List<DatosCarga> datosCargaList = assigment.getListaCausantes();
        int pageSize = 4;
        for (int i = 0; i < datosCargaList.size(); i += pageSize) {
            List<DatosCarga> subList = datosCargaList.subList(i, Math.min(i + pageSize, datosCargaList.size()));
            mapCauses(subList, resultMap);
            reportPages.add(reportStrategy.generateReport(templateName, resultMap));
        }

        byte[] report2 = reportStrategy.generateReport("Cert_AFyMAT_Pg2.jasper", null);
        reportPages.add(report2);

        return reportStrategy.combineReports(reportPages);
    }

    private BufferedImage loadImage() throws IOException {
        try {
            InputStream imageFile = Objects.requireNonNull(AssigmentService.class.getResourceAsStream("/POC/logo_cla.png"), "No se pudo encontrar la imagen en el directorio de recursos.");
            return Objects.requireNonNull(ImageIO.read(imageFile), "Error al cargar la imagen.");
        } catch (NullPointerException | IOException e) {
            throw new IOException("Error al cargar la imagen: " + e.getMessage(), e);
        }
    }

    private void mapBeneficiaryData(Assigment assigment, Map<String, Object> resultMap) {
        if (assigment.getDatosPersona() != null) {
            DatosPersona datosPersona = assigment.getDatosPersona();
            resultMap.put("beneficiario_run", datosPersona.getRutpersona() + "-" + datosPersona.getDvpersona());
            resultMap.put("beneficiario_apePat", datosPersona.getAp_paterno());
            resultMap.put("beneficiario_apeMat", datosPersona.getAp_materno());
            resultMap.put("beneficiario_nombres", datosPersona.getNombre());
            resultMap.put("beneficiario_domiCalle", datosPersona.getDomicilio());
            resultMap.put("beneficiario_n", ""); // Campo no especificado
            resultMap.put("beneficiario_deptoComuna", datosPersona.getComuna());
            resultMap.put("beneficiario_ciudad", datosPersona.getCiudad());
            resultMap.put("beneficiario_region", datosPersona.getRegion());
            resultMap.put("beneficiario_fonoFijo", datosPersona.getFono_fijo());
            resultMap.put("beneficiario_fonoMovil", datosPersona.getFono_cel());
            resultMap.put("beneficiario_correoElectronico", datosPersona.getEmail());
        }
    }

    private void mapEmployerData(Assigment assigment, Map<String, Object> resultMap) {
        if (assigment.getDatosEmpresa() != null) {
            DatosEmpresa datosEmpresa = assigment.getDatosEmpresa();
            resultMap.put("empleador_rut", datosEmpresa.getRutempresa() + "-" + datosEmpresa.getDvempresa());
            resultMap.put("empleador_razonSocial", datosEmpresa.getRazon_soc());
            resultMap.put("empleador_fechaInicioContrato", ""); // Campo no mapeado desde Assigment
            resultMap.put("empleador_fechaTerminoContrato", ""); // Campo no mapeado desde Assigment
            resultMap.put("empleador_domiCalle", datosEmpresa.getDomicilio());
            resultMap.put("empleador_n", ""); // Campo no especificado
            resultMap.put("empleador_deptoComuna", datosEmpresa.getComuna());
            resultMap.put("empleador_ciudad", datosEmpresa.getCiudad());
            resultMap.put("empleador_region", datosEmpresa.getRegion());
            resultMap.put("empleador_fonoFijo", datosEmpresa.getFono_fijo());
            resultMap.put("empleador_fonoMovil", datosEmpresa.getFono_cel());
            resultMap.put("empleador_correoElectronico", datosEmpresa.getEmail());
            resultMap.put("empleador_sucursal", ""); // Campo no especificado
        }
    }

    private void mapCauses(List<DatosCarga> datosCargaList, Map<String, Object> resultMap) {
        List<Map<String, String>> causantesList = new ArrayList<>();
        for (DatosCarga datosCarga : datosCargaList) {
            Map<String, String> causanteMap = new HashMap<>();
            causanteMap.put("causanteRun", datosCarga.getRutcarga() + "-" + datosCarga.getDvcargga());
            causanteMap.put("nombreCausante", datosCarga.getNombres());
            causanteMap.put("causanteCodSexo", datosCarga.getCod_sex());
            causanteMap.put("causanteCodTipoBeneficio", datosCarga.getCod_ben());
            causanteMap.put("causanteCodTipoCausante", datosCarga.getCod_cau());
            causanteMap.put("diaFechaNacimiento", datosCarga.getFec_dia());
            causanteMap.put("mesFechaNacimiento", datosCarga.getFec_mes());
            causanteMap.put("anioFechaNacimiento", datosCarga.getFec_año());
            causanteMap.put("diaFechaInicioBeneficio", "");
            causanteMap.put("mesFechaInicioBeneficio", "");
            causanteMap.put("anioFechaInicioBeneficio", "");
            causanteMap.put("firmaDeclaracion", "");
            causanteMap.put("usoExclusivo", "");
            causantesList.add(causanteMap);
        }
        resultMap.put("listaCausantes", new JRBeanArrayDataSource(causantesList.toArray()));
    }
}