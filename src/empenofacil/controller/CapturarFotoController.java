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
import empenofacil.Util;
import empenofacil.model.Cliente;
import empenofacil.model.FotoCliente;
import empenofacil.model.FotoPrenda;
import empenofacil.model.Prenda;
import empenofacil.model.TipoFoto;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import mybatis.dao.FotoClienteDAO;
import mybatis.dao.FotoPrendaDAO;
import mybatis.dao.TipoFotoDAO;

public class CapturarFotoController implements Initializable {

    private Stage dialogStage;
    private final SwingNode webCamPanel;
    private WebcamPanel panel;
    private String NAMECAM;
    private Webcam webcam;
    private BufferedImage img;
    private boolean ready;
    private Cliente cliente;
    private Prenda prenda;
    private final TipoFotoDAO tipoFotoDAO;
    private final FotoClienteDAO fotoClienteDAO;
    private final FotoPrendaDAO fotoPrendaDAO;

    @FXML
    private ChoiceBox<String> seleccionaCamara;
    @FXML
    private Pane panelFoto;
    @FXML
    private Button tomarFoto;
    @FXML
    private Button tomarFotoDeNuevo;
    @FXML
    private Button guardarFoto;
    @FXML
    private VBox panelOpciones;
    @FXML
    private VBox clienteV;
    @FXML
    private ChoiceBox<TipoFoto> clienteC;
    @FXML
    private VBox prendaV;

    public CapturarFotoController() {
        webCamPanel = new SwingNode();
        ready = false;
        cliente = new Cliente();
        prenda = new Prenda();
        tipoFotoDAO = new TipoFotoDAO();
        fotoClienteDAO = new FotoClienteDAO();
        fotoPrendaDAO = new FotoPrendaDAO();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarWebcams();
        cargarCanvasWebcam();
        seleccionaCamara.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (ready && (newValue == null ? oldValue != null : !newValue.equals(oldValue)) && newValue != null) {
                NAMECAM = newValue;
            }
        });
        tomarFoto.managedProperty().bind(tomarFoto.visibleProperty());
        tomarFotoDeNuevo.managedProperty().bind(tomarFotoDeNuevo.visibleProperty());
        tomarFotoDeNuevo.visibleProperty().bind(tomarFoto.visibleProperty().not());
        guardarFoto.visibleProperty().bind(tomarFotoDeNuevo.visibleProperty());
        ready = true;
    }

    public void cargarWebcams() {
        List<Webcam> list = Webcam.getWebcams();
        if (list != null && list.size() > 0) {
            NAMECAM = list.get(0).getName();
            for (Webcam w : list) {
                this.seleccionaCamara.getItems().add(w.getName());
                NAMECAM = w.getName();
            }
            seleccionaCamara.getSelectionModel().select(NAMECAM);
        }
    }

    private void cargarCanvasWebcam() {
        if (NAMECAM != null) {
            if (webcam != null && webcam.isOpen()) {
                webcam.close();
            }
            webcam = Webcam.getWebcamByName(NAMECAM);
            webcam.setViewSize(new Dimension(640, 480));
            panel = new WebcamPanel(webcam);
            webCamPanel.setContent(panel);
            panelFoto.getChildren().setAll(webCamPanel);
        }
    }

    @FXML
    private void tomarFoto() {
        img = webcam.getImage();
        tomarFoto.setVisible(false);
        mostrarFoto();
    }

    @FXML
    private void tomarFotoDeNuevo() {
        tomarFoto.setVisible(true);
        webCamPanel.setContent(panel);
    }

    private void mostrarFoto() {
        if (img != null) {
            JLabel foto = new JLabel(new ImageIcon(img));
            webCamPanel.setContent(foto);
        }
    }

    @FXML
    private void guardarFoto() {
        if (cliente != null) {
            tomarFotoCliente();
        } else {
            tomarFotoPrenda();

        }
    }

    @FXML
    private void cerrar() {
        dialogStage.close();
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
        panelOpciones.getChildren().remove(prendaV);
        cargarTiposFotoCliente();
    }
    
    public void setPrenda(Prenda prenda) {
        this.prenda =prenda;
        panelOpciones.getChildren().remove(clienteV);
    }

    private void cargarTiposFotoCliente() {
        List<TipoFoto> tiposFoto = tipoFotoDAO.obtenerTiposFoto();
        if (tiposFoto != null && !tiposFoto.isEmpty()) {
            clienteC.getItems().setAll(tiposFoto);
        } else {
            Util.dialogo(Alert.AlertType.ERROR, "No hay tipos de fotos en el sistema");
        }
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
        salir();
    }

    private void salir() {
        dialogStage.setOnHiding((event) -> {
            webcam.close();
        });
    }

    private void tomarFotoCliente() {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(img, "jpg", baos);
            baos.flush();
            byte[] imgBytes = baos.toByteArray();
            baos.close();
            TipoFoto tipoFoto = clienteC.getValue();
            if (tipoFoto == null) {
                Util.dialogo(Alert.AlertType.ERROR, "Selecciona un tipo de foto");
                return;
            }
            FotoCliente fotoCliente = fotoClienteDAO.obtenerFotoCliente(cliente.getIdCliente(), tipoFoto.getIdTipoFoto());
            if (fotoCliente == null) {
                if (fotoClienteDAO.crearFotoCliente(new FotoCliente(null, tipoFoto.getIdTipoFoto(), cliente.getIdCliente(), imgBytes)) == 0) {
                    Util.dialogo(Alert.AlertType.ERROR, "Ocurrio un error al guardar la foto");
                } else {
                    Util.dialogo(Alert.AlertType.INFORMATION, "Foto guardada correctamente");
                }
            } else {
                Optional<ButtonType> opcion = Util.confirmacion("Foto duplicada", "Ya existe una foto, ¿Deseas reemplazarla?");
                if (opcion.isPresent() && opcion.get() == ButtonType.YES) {
                    fotoCliente.setFoto(imgBytes);
                    if (fotoClienteDAO.editarFotoCliente(fotoCliente) == 0) {
                        Util.dialogo(Alert.AlertType.ERROR, "Ocurrio un error al reemplazar la foto");
                    } else {
                        Util.dialogo(Alert.AlertType.INFORMATION, "Foto reemplazaada correctamente");
                    }
                } else {
                    Util.dialogo(Alert.AlertType.INFORMATION, "Foto descartada");
                }
            }
            tomarFotoDeNuevo();
            clienteC.getSelectionModel().clearSelection();
        } catch (IOException ex) {
            Util.excepcion(ex);
        }
    }

    private void tomarFotoPrenda() {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(img, "jpg", baos);
            baos.flush();
            byte[] imgBytes = baos.toByteArray();
            baos.close();

            FotoPrenda fotoPrenda = fotoPrendaDAO.obtenerFotoPrenda(prenda.getIdPrenda());
            if (fotoPrenda == null) {
                if (fotoPrendaDAO.tomarFotografia(fotoPrenda) == 0) {
                    Util.dialogo(Alert.AlertType.ERROR, "Ocurrio un error al guardar la foto");
                } else {
                    Util.dialogo(Alert.AlertType.INFORMATION, "Foto guardada correctamente");
                }
            }
            tomarFotoDeNuevo();
        } catch (IOException ex) {
            Util.excepcion(ex);
        }
    }

 
}
