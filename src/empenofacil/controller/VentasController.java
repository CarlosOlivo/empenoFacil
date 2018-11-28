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

import empenofacil.EmpenoFacil;
import empenofacil.Util;
import empenofacil.model.Articulo;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
    private Label nombrel;

    @FXML
    private TextField direccionf;

    @FXML
    private TableColumn<Articulo, String> cdescripcion;

    @FXML
    private Label stid;

    @FXML
    private TableView<Articulo> artciculosT;

    @FXML
    private Label direccionl;

    @FXML
    private Label descuentol;

    @FXML
    private Label lbclave;

    @FXML
    private TextField busquedaTxt;

    @FXML
    private TableColumn<Articulo, Number> cpeso;

    @FXML
    private Label subtotall;

    @FXML
    private Button btnBusqueda1;

    @FXML
    private Label ival;

    @FXML
    private TableColumn<Articulo, Number> ctamanio;

    @FXML
    private Label totall;

    @FXML
    private TextField clavef;

    @FXML
    private TableColumn<Articulo, Number> cprecio;

    @FXML
    private TextField descuentof;

    @FXML
    private Label tlbl;

    @FXML
    private TextField nombref;

    @FXML
    private Label ivalbl;

    @FXML
    private Label labelBusqueda;

    @FXML
    private TableColumn<Articulo, String> cNombre;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cNombre.setCellValueFactory(data -> data.getValue().getNombreProperty());
        cprecio.setCellValueFactory(data -> data.getValue().getPrecioProperty());
        cpeso.setCellValueFactory(data -> data.getValue().getPesoProperty());
        ctamanio.setCellValueFactory(data -> data.getValue().getTamanioProperty());
        cdescripcion.setCellValueFactory(data -> data.getValue().getDescripcionProperty());
    }

    @FXML
    public void abrirSeccionArticulos() {
        try {
            FXMLLoader loader = new FXMLLoader(EmpenoFacil.class.getResource("view/SeleccionarArticulos.fxml"));
            Parent root = (Parent) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Seleccione Articulo");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initStyle(StageStyle.UTILITY);
            dialogStage.initOwner(EmpenoFacil.getStage());
            Scene scene = new Scene(root);
            dialogStage.setScene(scene);
            dialogStage.showAndWait();
        } catch (IOException ioEx) {
            Util.excepcion(ioEx);
        }
    }

    @FXML
    public void realizarVenta() {

    }
}
