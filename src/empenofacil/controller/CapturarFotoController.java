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

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import java.awt.event.ActionEvent;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderImage;
import javafx.scene.layout.Pane;


public class CapturarFotoController implements Initializable {

    @FXML
    private ComboBox<String> seleccionaCamara;

    @FXML
    private Pane panelFoto;

    @FXML
    private Button tomarFoto;

    @FXML
    private Button fotoguardar;

    private Webcam webcam;
    private BufferedImage image;
    private boolean ready = false;
    private String NAMECAM = null;
    private WebcamPanel panel = null;

    public void cargarWebcams() {
        List<Webcam> list = Webcam.getWebcams();
        if (list != null && list.size() > 0) {
            NAMECAM = list.get(0).getName();
            System.out.println("*1* " + NAMECAM);
            for (Webcam w : list) {
                this.seleccionaCamara.getItems().add(w.getName());
                NAMECAM = w.getName();
            }
            System.out.println("*2* " + NAMECAM);
            seleccionaCamara.getSelectionModel().getSelectedItem();
        }
    }

    private void showPicture() {
        if (image != null) {
            this.panelFoto.getChildren().removeAll();
            Label picture = new Label();
            picture.setGraphic(picture);
            this.panelFoto.getChildren().add(picture);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tomarFoto.setOnAction((javafx.event.ActionEvent event) -> {
            image = webcam.getImage();
            tomarFoto.setVisible(false);
            fotoguardar.setVisible(true);
            showPicture();
        });
        fotoguardar.setOnAction((javafx.event.ActionEvent event) -> {
        });
    }

}
