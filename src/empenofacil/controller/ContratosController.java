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

import empenofacil.model.Contrato;
import empenofacil.model.Prenda;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import mybatis.dao.ContratoDAO;

/**
 * FXML Controller class
 *
 * @author Carlos
 */
public class ContratosController implements Initializable {
    
    private final ContratoDAO contratoDAO;
    
    @FXML
    private VBox contratosV;
    @FXML
    private VBox contratoV;
    @FXML
    private TextField buscar;
    @FXML
    private TableView<Contrato> contratos;
    @FXML
    private TableColumn<Contrato, Number> folio;
    @FXML
    private TableColumn<Contrato, String> cliente;
    @FXML
    private TableColumn<Contrato, String> estadoContrato;
    @FXML
    private TableColumn<Contrato, Number> total;
    @FXML
    private TableView<Prenda> prendas;
    @FXML
    private TableColumn<Prenda, String> nombre;
    @FXML
    private TableColumn<Prenda, Number> precio;
    @FXML
    private TableColumn<Prenda, Number> descripcion;

    public ContratosController() {
        contratoDAO = new ContratoDAO();
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        contratos.setPlaceholder(new Text("No hay empeños en el sistema..."));
        prendas.setPlaceholder(new Text("No hay prendas, agrega al menos una..."));
        contratos.getItems().setAll(contratoDAO.obtenerContratos());
        contratoV.visibleProperty().bind(contratosV.visibleProperty().not());
    }
    
    @FXML
    private void nuevo() {
        contratosV.setVisible(false);
    }
    
    @FXML
    private void regresar() {
        contratosV.setVisible(true);
    }
}
