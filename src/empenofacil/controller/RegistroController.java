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
import empenofacil.model.Estado;
import empenofacil.model.Municipio;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import mybatis.MyBatisUtil;
import mybatis.dao.EstadoDAO;
import mybatis.dao.MunicipioDAO;
import org.apache.ibatis.session.SqlSession;

/**
 * FXML Controller class
 *
 * @author Carlos
 */
public class RegistroController implements Initializable {
    private Stage stage;
    
    @FXML
    private ChoiceBox<Estado> estadoC;
    @FXML
    private ChoiceBox<Municipio> municipioC;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarEstados();
        estadoC.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null) {
                municipioC.setDisable(true);
                cargarMunicipios(newValue.getId_estado());
                municipioC.setDisable(false);
            }
        });
    }
    
    private void cargarEstados() {
        List<Estado> estados = obtenerEstados();
        if(estados != null && !estados.isEmpty()) {
            estadoC.setItems(FXCollections.observableArrayList(estados));
        } else {
            Util.dialogo(Alert.AlertType.ERROR, "No hay estados en el sistema");
        }
    }
    
    private void cargarMunicipios(int estado) {
        List<Municipio> municipios = obtenerMunicipios(estado);
        if(municipios != null && !municipios.isEmpty()) {
            municipioC.setItems(FXCollections.observableArrayList(municipios));
        } else {
            Util.dialogo(Alert.AlertType.ERROR, "No hay municipios en el sistema");
        }
    }
    
    @FXML
    private void login() {
        Util.login(getStage());
    }
    
    @FXML
    private void registro() {
        // TODO
    }
    
    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    private List obtenerEstados() {
        List<Estado> list = null;
        SqlSession conn = MyBatisUtil.getSession();
        try {
            EstadoDAO estadoDAO = conn.getMapper(EstadoDAO.class);
            list = estadoDAO.obtenerEstados();
        } catch (Exception e) {
            Util.excepcion(e);
        } finally {
            conn.close();
        }
        return list;
    }
    
    private List obtenerMunicipios(int estado) {
        List<Municipio> list = null;
        SqlSession conn = MyBatisUtil.getSession();
        try {
            MunicipioDAO municipioDAO = conn.getMapper(MunicipioDAO.class);
            list = municipioDAO.obtenerMunicipiosPorEstado(estado);
        } catch (Exception e) {
            Util.excepcion(e);
        } finally {
            conn.close();
        }
        return list;
    }
}
