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
package empenofacil.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author lunix
 */
public class VentasController implements Initializable {

    @FXML
    private Button agregarab;

    @FXML
    private Button cncelarb;

    @FXML
    private TextField ivaf;

    @FXML
    private Label nombrel;

    @FXML
    private Label subtotall;

    @FXML
    private TextField totalf;

    @FXML
    private TextField direccionf;

    @FXML
    private Button btnBusqueda1;

    @FXML
    private Label ival;

    @FXML
    private TextField acomuladof;

    @FXML
    private CheckBox notacb;

    @FXML
    private Label totall;

    @FXML
    private TextField subtotalf;

    @FXML
    private Label direccionl;

    @FXML
    private Label descuentol;

    @FXML
    private Label lbclave;

    @FXML
    private TextField clavef;

    @FXML
    private TextField busquedaTxt;

    @FXML
    private TableColumn<?, ?> articulotabla;

    @FXML
    private TextField descuentof;

    @FXML
    private Label lblAcumlado;

    @FXML
    private TextField nombref;

    @FXML
    private Label labelBusqueda;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        agregarab.setOnAction((ActionEvent event) -> {
            abrirSeccionArticulos();
        });
    }

    public void abrirSeccionArticulos() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../empenofacil/view/SeleccionarArticulos.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            System.out.println("Exception" + e.getMessage());
        }
    }
}
