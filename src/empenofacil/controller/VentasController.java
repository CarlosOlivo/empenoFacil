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
import static empenofacil.controller.ContratosController.openPDF;
import empenofacil.model.Articulo;
import empenofacil.model.Venta;
import empenofacil.model.Venta_Detalle;
import empenofacil.reportes.Reportes;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import mybatis.dao.ArticuloDAO;
import mybatis.dao.VentaDAO;
import mybatis.dao.Venta_DetalleDAO;

/**
 * FXML Controller class
 *
 * @author lunix
 */
public class VentasController implements Initializable {

    private final ToggleGroup radioGroup;

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

    @FXML
    private RadioButton rbOferta;

    @FXML
    private RadioButton rdNormal;

    @FXML
    private TableColumn<Articulo, Number> descuentocol;
    private Double subtotal;
    private Double iva;
    private Double total;
    private Double descuento;
    private final ArticuloDAO articuloDAO;
    private final VentaDAO ventaDAO;
    private final Venta_DetalleDAO venta_DetalleDAO;

    public VentasController() {
        rbOferta = new RadioButton();
        rdNormal = new RadioButton();
        radioGroup = new ToggleGroup();
        ventaDAO = new VentaDAO();
        venta_DetalleDAO = new Venta_DetalleDAO();
        articuloDAO = new ArticuloDAO();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rdNormal.setSelected(true);
        rbOferta.setToggleGroup(radioGroup);
        rdNormal.setToggleGroup(radioGroup);
        radioGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                if (newValue.equals(rbOferta)) {
                    descuentof.setVisible(true);
                }
                if (newValue.equals(rdNormal)) {
                    descuentof.setVisible(false);
                }
            }
        });
        descuentol.visibleProperty().bind(descuentof.visibleProperty());
        descuentol.managedProperty().bind(descuentol.visibleProperty());
        descuentof.managedProperty().bind(descuentof.visibleProperty());
        descuentof.setVisible(false);
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
            SeleccionarArticulosController controller = loader.getController();
            controller.setArticulos(artciculosT.getItems());
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Seleccione Articulo");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initStyle(StageStyle.UTILITY);
            dialogStage.initOwner(EmpenoFacil.getStage());
            Scene scene = new Scene(root);
            dialogStage.setScene(scene);
            dialogStage.showAndWait();
            artciculosT.getItems().addAll(controller.getArticulosNuevos());
            calcularSubtoal();
        } catch (IOException ioEx) {
            Util.excepcion(ioEx);
        }
    }

    public void calcularSubtoal() {
        ObservableList<Articulo> listaArticulos = artciculosT.getItems();
        if (listaArticulos.size() > 0) {
            subtotal = 0d;
            for (int i = 0; i < listaArticulos.size(); i++) {
                subtotal += listaArticulos.get(i).getPrecio();
            }
            stid.setText(String.format("%10.2f", subtotal));
            iva = subtotal * MenuController.getParametrosPredeterminados().getIva();
            ivalbl.setText(String.format("%10.2f", iva));
            total = subtotal + iva;
            tlbl.setText(String.format("%10.2f", total));
        } else {
            stid.setText("0.00");
        }

    }

    @FXML
    public void realizarVenta() {
        if (rbOferta.isSelected()) {
            realizarVentaOferta();
        } else if (rdNormal.isSelected()) {
            realizarVentaNormal();
        }
    }

    @FXML
    public void restablecer() {
        artciculosT.getItems().clear();
        stid.setText("");
        subtotal = 0d;
        ivalbl.setText("");
        iva = 0d;
        tlbl.setText("");
        total = 0d;
    }
    
    private boolean validarDEscuento() {
        try {
            Double.parseDouble(descuentof.getText());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    private void realizarVentaNormal() {
        if (artciculosT.getItems().isEmpty()) {
            Util.dialogo(Alert.AlertType.ERROR, "Tabla vacia");
        } else {
            Venta venta = new Venta();
            venta.setIdEmpleado(MenuController.getEmpleado().getIdDomicilio());
            venta.setIdSucursal(1);
            venta.setTiempoCreacion(new Date());
            venta.setDescuento(0d);
            venta.setSubtotal(subtotal);
            venta.setIva(iva);
            venta.setTotal(total);

            if (ventaDAO.crearVenta(venta) != 0) {
                for (Articulo articulo : artciculosT.getItems()) {
                    venta_DetalleDAO.crearVentaDetalle(new Venta_Detalle(venta.getIdVenta(), articulo.getIdArticulo()));
                    articulo.setIdEstadoArticulo(Articulo.ESTADO_ARTICULO.VENDIDO.ordinal());
                    articuloDAO.editarArticulo(articulo);
                }
                Util.dialogo(Alert.AlertType.INFORMATION, "Venta realizado correctamente");
                restablecer();
 
                imprimirTicketVenta(venta.getIdVenta());
            }
        }
    }

    private void realizarVentaOferta() {
        if (artciculosT.getItems().isEmpty()) {
            Util.dialogo(Alert.AlertType.ERROR, "Tabla vacia");
        } else {
            if(descuentof.getText().trim().isEmpty() || !validarDEscuento()){
                Util.dialogo(Alert.AlertType.ERROR, "Descuento no valido");
            }
            Venta venta = new Venta();
            venta.setIdEmpleado(MenuController.getEmpleado().getIdDomicilio());
            venta.setIdSucursal(1);
            venta.setTiempoCreacion(new Date());
            venta.setDescuento(Double.parseDouble(descuentof.getText()));
            venta.setSubtotal(subtotal);
            venta.setIva(iva);
            venta.setTotal(total - venta.getDescuento());

            if (ventaDAO.crearVenta(venta) != 0) {
                for (Articulo articulo : artciculosT.getItems()) {
                    venta_DetalleDAO.crearVentaDetalle(new Venta_Detalle(venta.getIdVenta(), articulo.getIdArticulo()));
                    articulo.setIdEstadoArticulo(Articulo.ESTADO_ARTICULO.VENDIDO.ordinal());
                    articuloDAO.editarArticulo(articulo);
                }
                Util.dialogo(Alert.AlertType.INFORMATION, "Venta realizado correctamente");
                restablecer();
                imprimirTicketVenta(venta.getIdVenta());
            }
        }
    }

    private void imprimirTicketVenta(Integer idVenta) {
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("p_idVenta", idVenta);
        String path = Reportes.generarEtiquetaVenta("TicketdeVenta", parametros);
        openPDF(path);

    }
}
