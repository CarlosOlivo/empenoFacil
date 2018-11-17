/*
 * Copyright (C) 2018 lunix
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package empenofacil.reportes;

import empenofacil.reportes.db.Connect;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

public class Reportes {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private static String ds[] = {"localhost", "3306", "empenofacil", "admin_ef", "123456"};

    public static String generarReporteJasper(String jasper, HashMap parametros) {
        String basepath = System.getProperty("user.dir") + "/jasper/";
        basepath = basepath.replaceAll("\\\\", "/");
        parametros.put("p_path", basepath);
        //Salida
        String salida = basepath + "/salida/";
        String token = "" + new Date().getTime();
        try {
            JasperPrint jasperPrint = Reportes.getPrint(basepath, jasper, parametros);
            String file = String.format("%s%s_$s.pdf", createPath(salida), jasper, token);
            JasperExportManager.exportReportToPdfFile(jasperPrint, file);
            return file;
        } catch (Exception e) {
            System.out.println("Exception" + e.getMessage());
        }
        return null;
    }

    public static String generarPDFReciboVenta(String folio) {
        String basepath = System.getProperty("user.dir") + "/jasper/";
        basepath = basepath.replaceAll("\\\\", "/");
        HashMap parameters = new HashMap();
        parameters.put("p_folio", folio);
        parameters.put("p_path", basepath);
        //-----------OUTPUT----------------//
        String outpath = basepath + "/salida/";
        String token = "" + new Date().getTime();
        String jasper = "ReciboVenta";
        try {
            JasperPrint po = Reportes.getPrint(basepath, jasper, parameters);
            String file = String.format("%s%s_%s.pdf", createPath(outpath), jasper, token);
            JasperExportManager.exportReportToPdfFile(po, file);
            return file;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String generarEtiquetaVenta(String folio) {
        String basepath = System.getProperty("user.dir") + "/jasper/";
        basepath = basepath.replaceAll("\\\\", "/");
        HashMap parameters = new HashMap();
        parameters.put("p_folio", folio);
        parameters.put("p_path", basepath);
        //-----------OUTPUT----------------//
        String outpath = basepath + "/salida/";
        String token = "" + new Date().getTime();
        String jasper = "ReciboVenta";
        try {
            JasperPrint po = Reportes.getPrint(basepath, jasper, parameters);
            String file = String.format("%s%s_%s.pdf", createPath(outpath), jasper, token);
            JasperExportManager.exportReportToPdfFile(po, file);
            return file;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static JasperPrint getPrint(String basepath, String reporte, HashMap param) {
        JasperPrint result = null;
        try {
            String jasper = basepath + reporte + ".jrxml";
            Connect conn = new Connect(Connect.MYSQL, ds[0], Integer.parseInt(ds[1]), ds[2], ds[3], ds[4]);
            JasperReport report = JasperCompileManager.compileReport(jasper);
            result = JasperFillManager.fillReport(report, param, conn.connection());
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    private static String createPath(String path) {
        File f = new File(path);
        if (!f.exists()) {
            f.mkdir();
        }
        return path;
    }

}
