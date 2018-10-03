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
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mybatis.MyBatisUtil;
import mybatis.dao.EmpleadoDAO;
import org.apache.ibatis.session.SqlSession;

public class LoginController implements Initializable {
    private Stage stage;
    @FXML
    private TextField user;
    @FXML
    private TextField pass;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    private void login() {
        if(user.getText().isEmpty()) {
            Util.dialogo(Alert.AlertType.ERROR, "El campo usuario esta vacio");
            return;
        }
        if(pass.getText().isEmpty()) {
            Util.dialogo(Alert.AlertType.ERROR, "El campo contraseña esta vacio");
            return;
        }
        SqlSession conn = MyBatisUtil.getSession();
        try {
            EmpleadoDAO empleadoDAO = conn.getMapper(EmpleadoDAO.class);
            Empleado empleado = empleadoDAO.obtenerEmpleado(user.getText(), pass.getText());
            if(empleado != null) {
                Util.menu(getStage(), empleado);
            } else {
                Util.dialogo(Alert.AlertType.ERROR, "Usuario y/o contraseña invalidos");
            }
        } catch(Exception e) {
            Util.excepcion(e);
        }
    }
    
    @FXML
    private void registro() {
        Util.registro(getStage());
    }
        
    @FXML
    private void salir() {
        Platform.exit();
    }
    
    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}