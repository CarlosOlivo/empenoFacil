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

import com.dlsc.formsfx.model.structure.Field;
import com.dlsc.formsfx.model.structure.Form;
import com.dlsc.formsfx.model.structure.NodeElement;
import com.dlsc.formsfx.model.structure.Section;
import com.dlsc.formsfx.model.util.BindingMode;
import com.dlsc.formsfx.model.validators.DoubleRangeValidator;
import com.dlsc.formsfx.model.validators.StringLengthValidator;
import com.dlsc.formsfx.view.renderer.FormRenderer;
import com.dlsc.formsfx.view.util.ColSpan;
import empenofacil.Util;
import empenofacil.model.CategoriaPrenda;
import empenofacil.model.Cliente;
import empenofacil.model.Contrato;
import empenofacil.model.Prenda;
import empenofacil.model.TipoPrenda;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import mybatis.dao.CategoriaPrendaDAO;
import mybatis.dao.ClienteDAO;
import mybatis.dao.ContratoDAO;
import mybatis.dao.TipoPrendaDAO;

/**
 * FXML Controller class
 *
 * @author Carlos
 */
public class ContratosController implements Initializable {

    private final ContratoDAO contratoDAO;
    private final ClienteDAO clienteDAO;
    private final TipoPrendaDAO tipoPrendaDAO;
    private final CategoriaPrendaDAO categoriaPrendaDAO;
    private final Prenda prenda;
    private final ChoiceBox<CategoriaPrenda> categorias;
    private final ChoiceBox<TipoPrenda> tipos;
    private final Form formulario;
    private final ObservableList<Prenda> lista_prendas;

    @FXML
    private VBox contratosV;
    @FXML
    private VBox prendasV;
    @FXML
    private VBox formV;
    @FXML
    private VBox form;
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
    private ChoiceBox<Cliente> clientesC;
    @FXML
    private TableView<Prenda> prendas;
    @FXML
    private TableColumn<Prenda, String> nombre;
    @FXML
    private TableColumn<Prenda, Number> precio;
    @FXML
    private TableColumn<Prenda, String> descripcion;
    @FXML
    private Button guardarPrenda;

    public ContratosController() {
        contratoDAO = new ContratoDAO();
        clienteDAO = new ClienteDAO();
        tipoPrendaDAO = new TipoPrendaDAO();
        categoriaPrendaDAO = new CategoriaPrendaDAO();
        prenda = new Prenda(null, null, null, "", 0d, 0d, 0d, "");
        tipos = new ChoiceBox<>();
        categorias = new ChoiceBox<>();
        formulario = Form.of(
                Section.of(
                        NodeElement.of(Util.contenedor("Tipo", tipos))
                                .span(ColSpan.HALF),
                        NodeElement.of(Util.contenedor("Categoria", categorias))
                                .span(ColSpan.HALF),
                        Field.ofStringType(prenda.getNombreProperty())
                                .label("Nombre")
                                .validate(StringLengthValidator.atLeast(3, "Introduce un nombre valido."))
                                .span(ColSpan.HALF)
                                .required(true),
                        Field.ofDoubleType(prenda.getTamanioProperty())
                                .label("Tamaño")
                                .span(ColSpan.HALF),
                        Field.ofDoubleType(prenda.getPrecioProperty())
                                .label("Precio")
                                .span(ColSpan.HALF)
                                .validate(DoubleRangeValidator.atLeast(0.1d, "Introduce un precio"))
                                .required(true),
                        Field.ofDoubleType(prenda.getPesoProperty())
                                .label("Peso")
                                .span(ColSpan.HALF),
                        Field.ofStringType(prenda.getDescripcionProperty())
                                .label("Descripción")
                                .multiline(true)
                                .validate(StringLengthValidator.atLeast(3, "Introduce una descripción valida."))
                                .required(true)
                ).title("Datos prenda").collapsible(false)
        );
        lista_prendas = FXCollections.observableArrayList();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        contratos.setPlaceholder(new Text("No hay empeños en el sistema..."));
        contratos.getItems().setAll(contratoDAO.obtenerContratos());
        formulario.binding(BindingMode.CONTINUOUS);
        form.getChildren().setAll(new FormRenderer(formulario));
        guardarPrenda.disableProperty().bind(formulario.validProperty().not());
        tipos.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                categorias.setDisable(true);
                cargarCategorias(newValue.getIdTipoPrenda());
                categorias.setDisable(false);
            }
        });
        prendas.setPlaceholder(new Text("No hay prendas, agrega al menos una..."));
        nombre.setCellValueFactory(data -> data.getValue().getNombreProperty());
        precio.setCellValueFactory(data -> data.getValue().getPrecioProperty());
        descripcion.setCellValueFactory(data -> data.getValue().getDescripcionProperty());
        prendas.setItems(lista_prendas);
    }

    @FXML
    private void nuevoContrato() {
        cargarClientes();
        contratosV.setVisible(false);
        prendasV.setVisible(true);
    }

    @FXML
    private void nuevaPrenda() {
        if(lista_prendas.size() > 5) {
            Util.dialogo(Alert.AlertType.ERROR, "Solo puedes empeñar 5 prendas a la vez.");
            return;
        }
        cargarTipos();
        prendasV.setVisible(false);
        formV.setVisible(true);
    }

    @FXML
    private void regresarContratos() {
        prendasV.setVisible(false);
        contratosV.setVisible(true);
    }

    @FXML
    private void guardarContrato() {
        // TODO
    }

    @FXML
    private void guardarPrenda() {
        TipoPrenda tipoPrendaTMP = tipos.getValue();
        if(tipoPrendaTMP == null) {
            Util.dialogo(Alert.AlertType.ERROR, "Selecciona el tipo de prenda antes de continuar");
            return;
        }
        prenda.setIdTipoPrenda(tipoPrendaTMP.getIdTipoPrenda());
        CategoriaPrenda categoriaPrendaTMP = categorias.getValue();
        if(categoriaPrendaTMP == null) {
            Util.dialogo(Alert.AlertType.ERROR, "Selecciona la categoria de la prenda antes de continuar");
            return;
        }
        prenda.setIdCategoriaPrenda(categoriaPrendaTMP.getIdCategoriaPrenda());
        agregarPrenda();
        regresarPrendas();
    }
    
    private void agregarPrenda() {
        lista_prendas.add(new Prenda(prenda.getIdPrenda(), prenda.getIdCategoriaPrenda(), prenda.getIdTipoPrenda(), prenda.getNombre(), prenda.getTamanio(), prenda.getPrecio(), prenda.getPeso(), prenda.getDescripcion()));
        prenda.setIdPrenda(null);
        prenda.setIdCategoriaPrenda(null);
        prenda.setIdTipoPrenda(null);
        prenda.setNombre("");
        prenda.setTamanio(0d);
        prenda.setPrecio(0d);
        prenda.setPeso(0d);
        prenda.setDescripcion("");
    }

    @FXML
    private void regresarPrendas() {
        formV.setVisible(false);
        prendasV.setVisible(true);
    }

    private void cargarClientes() {
        List<Cliente> clientes = clienteDAO.obtenerClientes();
        if (clientes != null && !clientes.isEmpty()) {
            clientesC.getItems().setAll(clientes);
        } else {
            regresarContratos();
            Util.dialogo(Alert.AlertType.ERROR, "No hay clientes en el sistema");
        }
    }
    
    private void cargarTipos() {
        categorias.setDisable(true);
        List<TipoPrenda> clientes = tipoPrendaDAO.obtenerTiposPrenda();
        if (clientes != null && !clientes.isEmpty()) {
            categorias.getSelectionModel().clearSelection();
            tipos.getItems().setAll(clientes);
        } else {
            regresarContratos();
            Util.dialogo(Alert.AlertType.ERROR, "No hay tipos de prenda en el sistema");
        }
    }
    
    private void cargarCategorias(int idTipoPrenda) {
        List<CategoriaPrenda> municipios = categoriaPrendaDAO.obtenerCategoriasPrenda(idTipoPrenda);
        if (municipios != null && !municipios.isEmpty()) {
            categorias.getItems().setAll(municipios);
            categorias.setDisable(false);
        } else {
            Util.dialogo(Alert.AlertType.ERROR, "No hay categorias en el sistema");
        }
    }
}
