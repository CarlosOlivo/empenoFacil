/*
 * Copyright (C) 2018 Carlos
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
package empenofacil.controller;

import empenofacil.Util;
import empenofacil.model.Empleado;
import empenofacil.model.Parametros;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import mybatis.dao.ParametrosDAO;

/**
 * FXML Controller class
 *
 * @author Carlos
 */
public class MenuController implements Initializable {
    private static Empleado empleado;
    private static final Parametros PARAMETROS = new Parametros();
    private static final ParametrosDAO PARAMETROS_DAO = new ParametrosDAO();
    
    @FXML
    private Label usuario;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        PARAMETROS.setFolio(PARAMETROS_DAO.obtenerParametroEntero("folio"));
        PARAMETROS.setIva(PARAMETROS_DAO.obtenerParametroDoble("iva"));
        PARAMETROS.setNumPeriodos(PARAMETROS_DAO.obtenerParametroEntero("numPeriodos"));
        PARAMETROS.setDiasPorPeriodo(PARAMETROS_DAO.obtenerParametroEntero("diasPorPeriodo"));
        PARAMETROS.setDiasEnTotal(PARAMETROS_DAO.obtenerParametroEntero("diasEnTotal"));
        PARAMETROS.setNumPeriodosExtension(PARAMETROS_DAO.obtenerParametroEntero("numPeriodosExtension"));
        PARAMETROS.setDiasPorPeriodoExtension(PARAMETROS_DAO.obtenerParametroEntero("diasPorPeriodoExtension"));
        PARAMETROS.setDiasEnTotalExtension(PARAMETROS_DAO.obtenerParametroEntero("diasEnTotalExtension"));
        PARAMETROS.setDiasParaCancelarContrato(PARAMETROS_DAO.obtenerParametroEntero("diasParaCancelarContrato"));
        PARAMETROS.setPorcentajePrestamo(PARAMETROS_DAO.obtenerParametroDoble("porcentajePrestamo"));
        PARAMETROS.setInteresPorPeriodo(PARAMETROS_DAO.obtenerParametroDoble("interesPorPeriodo"));
        PARAMETROS.setComercializacion(PARAMETROS_DAO.obtenerParametroDoble("comercializacion"));
        PARAMETROS.setTasaInteres(PARAMETROS_DAO.obtenerParametroDoble("tasaInteres"));
        PARAMETROS.setCostoAnualTotal(PARAMETROS_DAO.obtenerParametroDoble("costoAnualTotal"));
        PARAMETROS.setCostoMensual(PARAMETROS_DAO.obtenerParametroDoble("costoMensual"));
        PARAMETROS.setCostoDiario(PARAMETROS_DAO.obtenerParametroDoble("costoDiario"));
     }
    
    public void inicializar() {
        usuario.setText("Bienvenid@ " + getEmpleado().getNombre() + " " + getEmpleado().getApellidoPaterno());
    }
    
    public static Parametros getParametrosPredeterminados() {
        return PARAMETROS;
    }

    public static Empleado getEmpleado() {
        return empleado;
    }

    public static void setEmpleado(Empleado empleado) {
        MenuController.empleado = empleado;
    }
    
    @FXML
    private void cerrarSesion() {
        Util.login();
    }
}