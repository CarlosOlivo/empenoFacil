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

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author lunix
 */
public class SeleccionarArticulosController implements Initializable {

    @FXML
    private Label prendasl;

    @FXML
    private Button aceptarbn;

    @FXML
    private Button buscarbn;

    @FXML
    private TableColumn<?, ?> articulostable;

    @FXML
    private RadioButton seriebolsar;

    @FXML
    private DatePicker fincalendar;

    @FXML
    private RadioButton ororb;

    @FXML
    private Label finl;

    @FXML
    private Button cancelarbn;

    @FXML
    private Button limpiarbn;

    @FXML
    private RadioButton fechar;

    @FXML
    private HBox iniciol;

    @FXML
    private RadioButton aparatosrb;

    @FXML
    private DatePicker iniciocalendar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
