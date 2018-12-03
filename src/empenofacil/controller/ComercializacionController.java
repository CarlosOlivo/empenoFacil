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
import empenofacil.model.Articulo;
import empenofacil.model.Prenda;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import mybatis.dao.ArticuloDAO;
import mybatis.dao.PrendaDAO;

/**
 * FXML Controller class
 *
 * @author Carlos
 */
public class ComercializacionController implements Initializable {

    @FXML
    private TableView<Articulo> tablaComercializacion;

    @FXML
    private TableColumn<Articulo, Number> prestamoid;

    @FXML
    private TableColumn<Articulo, Number> avaluoid;

    @FXML
    private TableColumn<Articulo, String> descripcionid;

    @FXML
    private TableColumn<Articulo, String> nombreid;

    @FXML
    private TableColumn<Articulo, Number> comerid;

    @FXML
    private TextArea txtid;

    @FXML
    private Button guardarid;

    private final ArticuloDAO articuloDAO;

    public ComercializacionController() {
        articuloDAO = new ArticuloDAO();

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tablaComercializacion.getItems().setAll(articuloDAO.obtenerArticulos());
        descripcionid.setCellValueFactory(data -> data.getValue().getDescripcionProperty());
        nombreid.setCellValueFactory(data -> data.getValue().getNombreProperty());
        comerid.setCellValueFactory(data -> data.getValue().getPrecioProperty());
        tablaComercializacion.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                txtid.setText(newValue.getDescripcion());
            }
        });

    }
    
    @FXML
    private void guardarDescripcion() {
        if(txtid.getText().trim().isEmpty()){
            Util.dialogo(Alert.AlertType.ERROR, "Escribe una descripción");
        } else {
            Articulo articulo = tablaComercializacion.getSelectionModel().getSelectedItem();
            articulo.setDescripcion(txtid.getText());
            if (articuloDAO.editarArticulo(articulo) != 0) {
                Util.dialogo(Alert.AlertType.INFORMATION, "Descripción editada correctamente");
            } else {
                Util.dialogo(Alert.AlertType.ERROR, "Descripción no editAda");
            }
        }
    }

}
