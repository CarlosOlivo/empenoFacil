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

import empenofacil.model.Articulo;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;

/**
 * FXML Controller class
 *
 * @author lunix
 */
public class SeleccionarArticulosController implements Initializable {

    @FXML
    private TableColumn<Articulo, String> cDescripcion;

    @FXML
    private TableColumn<Articulo, Number> cTamanio;

    @FXML
    private Button aceptarbn;

    @FXML
    private TableColumn<Articulo, Number> cPrecio;

    @FXML
    private TableColumn<Articulo, Number> cPeso;

    @FXML
    private TableColumn<Articulo, String> cNombre;

    @FXML
    private Button cancelarbn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cNombre.setCellValueFactory(data -> data.getValue().getNombreProperty());
        cPrecio.setCellValueFactory(data -> data.getValue().getPrecioProperty());
        cPeso.setCellValueFactory(data -> data.getValue().getPesoProperty());
        cTamanio.setCellValueFactory(data -> data.getValue().getTamanioProperty());
        cDescripcion.setCellValueFactory(data -> data.getValue().getDescripcionProperty());
    }

}
