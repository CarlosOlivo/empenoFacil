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
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import mybatis.dao.ArticuloDAO;

/**
 * FXML Controller class
 *
 * @author lunix
 */
public class SeleccionarArticulosController implements Initializable {

    private final ArticuloDAO articuloDAO;

    @FXML
    private TableColumn<Articulo, String> cDescripcion;

    @FXML
    private TableColumn<Articulo, Number> cTamanio;

    @FXML
    private Button aceptarbn;

    @FXML
    private TableColumn<Articulo, Void> seleccionar;

    @FXML
    private TableView<Articulo> artciuloTable;

    @FXML
    private TableColumn<Articulo, Number> cPrecio;

    @FXML
    private TableColumn<Articulo, Number> cPeso;

    @FXML
    private TableColumn<Articulo, String> cNombre;

    @FXML
    private Button cancelarbn;

    public SeleccionarArticulosController() {
        articuloDAO = new ArticuloDAO();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        artciuloTable.getItems().setAll(articuloDAO.obtenerArticulos());
        cNombre.setCellValueFactory(data -> data.getValue().getNombreProperty());
        cPrecio.setCellValueFactory(data -> data.getValue().getPrecioProperty());
        cPeso.setCellValueFactory(data -> data.getValue().getPesoProperty());
        cTamanio.setCellValueFactory(data -> data.getValue().getTamanioProperty());
        cDescripcion.setCellValueFactory(data -> data.getValue().getDescripcionProperty());
        seleccionar.setCellFactory(param -> new Opciones());

    }

    private class Opciones extends TableCell<Articulo, Void> {

        private final CheckBox cb = new CheckBox();

        private final HBox hb = new HBox(cb);

        public Opciones() {
            hb.setSpacing(5);
            hb.setPadding(new Insets(-3.5d, 0d, 0d, 0d));
            hb.setAlignment(Pos.TOP_LEFT);

        }

        @Override
        protected void updateItem(Void item, boolean empty) {
            super.updateItem(item, empty);
            setGraphic(empty ? null : hb);
        }
    }

}
