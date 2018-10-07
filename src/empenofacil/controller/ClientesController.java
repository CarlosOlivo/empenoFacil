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
import com.dlsc.formsfx.model.validators.IntegerRangeValidator;
import com.dlsc.formsfx.model.validators.RegexValidator;
import com.dlsc.formsfx.model.validators.StringLengthValidator;
import com.dlsc.formsfx.view.renderer.FormRenderer;
import com.dlsc.formsfx.view.util.ColSpan;
import empenofacil.Util;
import empenofacil.model.Cliente;
import empenofacil.model.Domicilio;
import empenofacil.model.Estado;
import empenofacil.model.Municipio;
import empenofacil.model.Ocupacion;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.StringConverter;
import mybatis.dao.ClienteDAO;
import mybatis.dao.DomicilioDAO;
import mybatis.dao.EstadoDAO;
import mybatis.dao.MunicipioDAO;
import mybatis.dao.OcupacionDAO;

/**
 * FXML Controller class
 *
 * @author Carlos
 */
public class ClientesController implements Initializable {

    private final ClienteDAO clienteDAO;
    private final DomicilioDAO domicilioDAO;
    private final EstadoDAO estadoDAO;
    private final MunicipioDAO municipioDAO;
    private final OcupacionDAO ocupacionDAO;
    private final Cliente cliente;
    private final Domicilio domicilio;
    private final Form formulario;
    private final DatePicker fechaNacimiento;
    private final ChoiceBox<Estado> estado;
    private final ChoiceBox<Municipio> municipio;
    private final ChoiceBox<Ocupacion> ocupacion;

    @FXML
    private VBox form;
    @FXML
    private HBox acciones;
    @FXML
    private Button guardar;
    @FXML
    private TextField buscar;
    @FXML
    private TableView<Cliente> clientes;
    @FXML
    private TableColumn<Cliente, String> nombre;
    @FXML
    private TableColumn<Cliente, String> apellidoP;
    @FXML
    private TableColumn<Cliente, String> curp;
    @FXML
    private TableColumn<Cliente, String> rfc;

    public ClientesController() {
        clienteDAO = new ClienteDAO();
        domicilioDAO = new DomicilioDAO();
        estadoDAO = new EstadoDAO();
        municipioDAO = new MunicipioDAO();
        ocupacionDAO = new OcupacionDAO();
        cliente = new Cliente(null, null, null, "", "", "", "", "", null, "", "", null, null);
        domicilio = new Domicilio(null, "", "", "", null, "", null);
        estado = new ChoiceBox<>();
        fechaNacimiento = new DatePicker();
        municipio = new ChoiceBox<>();
        ocupacion = new ChoiceBox<>();
        formulario = Form.of(
                Section.of(
                        Field.ofStringType(cliente.getNombreProperty())
                                .label("Nombe")
                                .validate(RegexValidator.forPattern("(?![\\W])^[\\p{L} .'-]{3,}$", "Introduce un nombre valido."))
                                .required(true),
                        Field.ofStringType(cliente.getApellidoPaternoProperty())
                                .label("Apellido paterno")
                                .span(ColSpan.HALF)
                                .validate(RegexValidator.forPattern("(?![\\W])^[\\p{L} .'-]{3,}$", "Introduce un apellido paterno valido."))
                                .required(true),
                        Field.ofStringType(cliente.getApellidoMaternoProperty())
                                .label("Apellido materno")
                                .span(ColSpan.HALF)
                                .validate(RegexValidator.forPattern("^\\.{0}$|(?![\\W])^[\\p{L} .'-]{3,}$", "Introduce un apellido materno valido.")),
                        Field.ofStringType(cliente.getTelefonoProperty())
                                .label("Télefono")
                                .span(ColSpan.HALF)
                                .validate(RegexValidator.forPattern("^\\.{0}$|\\d{10}", "Introduce un número de telefono de 10 digitos.")),
                        Field.ofStringType(cliente.getCelularProperty())
                                .label("Celular")
                                .span(ColSpan.HALF)
                                .validate(RegexValidator.forPattern("^\\.{0}$|\\d{10}", "Introduce un número de telefono de 10 digitos.")),
                        NodeElement.of(Util.contenedor("Ocupación", ocupacion))
                                .span(ColSpan.HALF),
                        NodeElement.of(Util.contenedor("Fecha de nacimiento", fechaNacimiento))
                                .span(ColSpan.HALF),
                        Field.ofStringType(cliente.getCurpProperty())
                                .label("CURP")
                                .span(ColSpan.HALF)
                                .validate(RegexValidator.forPattern("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{18}$", "Introduce una CURP de 18 caracteres."))
                                .required(true),
                        Field.ofStringType(cliente.getrfcProperty())
                                .label("RFC")
                                .span(ColSpan.HALF)
                                .validate(RegexValidator.forPattern("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{13}$", "Introduce el RFC de 13 caracteres."))
                                .required(true)
                ).title("Datos personales").collapsible(false),
                Section.of(
                        Field.ofStringType(domicilio.getCalleProperty())
                                .label("Calle")
                                .span(9)
                                .validate(StringLengthValidator.atLeast(1, "Introduce la calle de tu domicilio."))
                                .required(true),
                        Field.ofStringType(domicilio.getNumeroProperty())
                                .label("Número")
                                .span(3)
                                .validate(StringLengthValidator.between(1, 5, "Introduce el número de tu domicilio, maximo 5 caracteres."))
                                .required(true),
                        Field.ofStringType(domicilio.getColoniaProperty())
                                .label("Colonia")
                                .span(5)
                                .validate(StringLengthValidator.atLeast(1, "Introduce la colonia de tu domicilio."))
                                .required(true),
                        Field.ofStringType(domicilio.getLocalidadProperty())
                                .label("Localidad")
                                .span(4)
                                .validate(StringLengthValidator.atLeast(1, "Introduce la localidad de tu domicilio."))
                                .required(true),
                        Field.ofIntegerType(domicilio.getCodigoPostalProperty())
                                .label("Codigo postal")
                                .span(3)
                                .validate(IntegerRangeValidator.between(10000, 99999, "Introduce un codigo postal valido de 5 digitos."))
                                .required(true),
                        NodeElement.of(Util.contenedor("Estado", estado))
                                .span(ColSpan.HALF),
                        NodeElement.of(Util.contenedor("Municipio", municipio))
                                .span(ColSpan.HALF)
                ).title("Domicilio").collapsible(false)
        );
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clientes.setPlaceholder(new Text("No hay clientes en el sistema..."));
        clientes.getItems().setAll(clienteDAO.obtenerClientes());
        clientes.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                guardar.setText("Actualizar");
                cargarCliente(newValue);
                fechaNacimiento.setValue(cliente.getFechaNacimiento().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                form.setDisable(false);
            }
        });
        nombre.setCellValueFactory(data -> data.getValue().getNombreProperty());
        apellidoP.setCellValueFactory(data -> data.getValue().getApellidoPaternoProperty());
        curp.setCellValueFactory(data -> data.getValue().getCurpProperty());
        rfc.setCellValueFactory(data -> data.getValue().getrfcProperty());
        fechaNacimiento.setConverter(new DateFormat());
        fechaNacimiento.setPromptText("dd/MM/yyyy");
        formulario.binding(BindingMode.CONTINUOUS);
        form.getChildren().add(new FormRenderer(formulario));
        acciones.disableProperty().bind(form.disableProperty());
        guardar.disableProperty().bind(formulario.validProperty().not());
        form.setDisable(true);
        buscar.textProperty().addListener((observable, oldValue, newValue) -> {
            clientes.getItems().setAll(clienteDAO.buscarClientes(newValue));
        });
        buscar.setText("");
        estado.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                municipio.setDisable(true);
                cargarMunicipios(newValue.getIdEstado());
                municipio.setDisable(false);
            }
        });
    }
    
    @FXML
    private void nuevo() {
        limpiarFormulario();
        form.setDisable(false);
    }
    
    @FXML
    private void guardar() {
        if (esFormularioValido()) {
            domicilio.setIdMunicipio(municipio.getValue().getIdMunicipio());
            if (domicilio.getIdDomicilio() == null) {
                if (domicilioDAO.crearDomicilio(domicilio) == 0) {
                    Util.dialogo(Alert.AlertType.ERROR, "Ocurrio un error al crear el domicilio del cliente");
                    return;
                }
            } else {
                if (domicilioDAO.editarDomicilio(domicilio) == 0) {
                    Util.dialogo(Alert.AlertType.ERROR, "Ocurrio un error al editar el domicilio del cliente");
                    return;
                }
            }
            cliente.setIdOcupacion(ocupacion.getValue().getIdOcupacion());
            cliente.setIdDomicilio(domicilio.getIdDomicilio());
            cliente.setFechaNacimiento(Date.valueOf(fechaNacimiento.getValue()));
            if (cliente.getIdCliente() == null) {
                if (clienteDAO.crearCliente(cliente) == 0) {
                    Util.dialogo(Alert.AlertType.ERROR, "Ocurrio un error al crear el cliente");
                } else {
                    Util.dialogo(Alert.AlertType.INFORMATION, "Cliente creado correctamente");
                    actualizarClientes();
                    cancelar();
                }
            } else {
                if (clienteDAO.editarCliente(cliente) == 0) {
                    Util.dialogo(Alert.AlertType.ERROR, "Ocurrio un error al editar el cliente");
                } else {
                    Util.dialogo(Alert.AlertType.INFORMATION, "Cliente editado correctamente");
                    actualizarClientes();
                    cancelar();
                }
            }
        }
    }

    @FXML
    private void cancelar() {
        limpiarFormulario();
        form.setDisable(true);
    }
    
    private void limpiarFormulario() {
        guardar.setText("Guardar");
        clientes.getSelectionModel().clearSelection();
        fechaNacimiento.setValue(null);
        ocupacion.getSelectionModel().clearSelection();
        ocupacion.getItems().clear();
        estado.getSelectionModel().clearSelection();
        estado.getItems().clear();
        municipio.getSelectionModel().clearSelection();
        municipio.getItems().clear();
        Cliente clienteNuevo = new Cliente(null, null, null, "", "", "", "", "", null, "", "", null, null);
        Domicilio domicilioNuevo = new Domicilio(null, "", "", "", null, "", null);
        cargarCliente(clienteNuevo, domicilioNuevo);
    }
    
    private boolean esFormularioValido() {
        if (ocupacion.getValue() == null) {
            Util.dialogo(Alert.AlertType.ERROR, "Elige una ocupación");
            return false;
        }
        if (fechaNacimiento.getValue() == null) {
            Util.dialogo(Alert.AlertType.ERROR, "Selecciona una fecha de nacimiento");
            return false;
        }
        if (Period.between(fechaNacimiento.getValue(), LocalDate.now()).getYears() < 18) {
            Util.dialogo(Alert.AlertType.ERROR, "Cliente es menor de edad");
            return false;
        }
        if (estado.getValue() == null) {
            Util.dialogo(Alert.AlertType.ERROR, "Elige un estado");
            return false;
        }
        if (municipio.getValue() == null) {
            Util.dialogo(Alert.AlertType.ERROR, "Elige un municipio");
            return false;
        }
        return true;
    }
    
    private void cargarEstados() {
        municipio.setDisable(true);
        List<Estado> estados = estadoDAO.obtenerEstados();
        if (estados != null && !estados.isEmpty()) {
            municipio.getSelectionModel().clearSelection();
            estado.getItems().setAll(estados);
            if (domicilio.getIdMunicipio() != null) {
                estado.getSelectionModel().select(estadoDAO.obtenerEstado(municipioDAO.obtenerMunicipio(domicilio.getIdMunicipio()).getIdEstado()));
            }
        } else {
            Util.dialogo(Alert.AlertType.ERROR, "No hay estados en el sistema");
        }
    }

    private void cargarMunicipios(int estado) {
        List<Municipio> municipios = municipioDAO.obtenerMunicipiosPorEstado(estado);
        if (municipios != null && !municipios.isEmpty()) {
            municipio.getItems().setAll(municipios);
            if (domicilio.getIdMunicipio() != null) {
                municipio.getSelectionModel().select(municipioDAO.obtenerMunicipio(domicilio.getIdMunicipio()));
            }
            municipio.setDisable(false);
        } else {
            Util.dialogo(Alert.AlertType.ERROR, "No hay municipios en el sistema");
        }
    }

    private void cargarOcupaciones() {
        List<Ocupacion> ocupaciones = ocupacionDAO.obtenerOcupaciones();
        if (ocupaciones != null && !ocupaciones.isEmpty()) {
            ocupacion.getItems().setAll(ocupaciones);
            if (cliente.getIdOcupacion() != null) {
                ocupacion.getSelectionModel().select(ocupacionDAO.obtenerOcupacion(cliente.getIdOcupacion()));
            }
        } else {
            Util.dialogo(Alert.AlertType.ERROR, "No hay ocupaciones en el sistema");
        }
    }

    private void actualizarClientes() {
        clientes.getItems().clear();
        if (buscar.getText().trim().isEmpty()) {
            clientes.getItems().setAll(clienteDAO.obtenerClientes());
        } else {
            clientes.getItems().setAll(clienteDAO.buscarClientes(buscar.getText()));
        }
    }
    
    private void cargarCliente(Cliente cliente) {
        cargarCliente(cliente, domicilioDAO.obtenerDomicilio(cliente.getIdDomicilio()));
    }

    private void cargarCliente(Cliente cliente, Domicilio domicilio) {
        this.cliente.setIdCliente(cliente.getIdCliente());
        this.cliente.setIdOcupacion(cliente.getIdOcupacion());
        this.cliente.setIdDomicilio(cliente.getIdDomicilio());
        this.cliente.setNombre(cliente.getNombre());
        this.cliente.setApellidoPaterno(cliente.getApellidoPaterno());
        this.cliente.setApellidoMaterno(Objects.toString(cliente.getApellidoMaterno(), ""));
        this.cliente.setTelefono(Objects.toString(cliente.getTelefono(), ""));
        this.cliente.setCelular(Objects.toString(cliente.getCelular(), ""));
        this.cliente.setFechaNacimiento(cliente.getFechaNacimiento());
        this.cliente.setCurp(cliente.getCurp());
        this.cliente.setRfc(cliente.getRfc());
        this.cliente.setHuellaCliete(cliente.getHuellaCliete());
        this.cliente.setListaNegra(cliente.getListaNegra());
        this.domicilio.setIdDomicilio(domicilio.getIdDomicilio());
        this.domicilio.setCalle(domicilio.getCalle());
        this.domicilio.setNumero(domicilio.getNumero());
        this.domicilio.setColonia(domicilio.getColonia());
        this.domicilio.setCodigoPostal(domicilio.getCodigoPostal());
        this.domicilio.setLocalidad(domicilio.getLocalidad());
        this.domicilio.setIdMunicipio(domicilio.getIdMunicipio());
        cargarEstados();
        cargarOcupaciones();
    }

    private static class DateFormat extends StringConverter<LocalDate> {

        private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        @Override
        public String toString(LocalDate localDate) {
            if (localDate == null) {
                return "";
            }
            return dateTimeFormatter.format(localDate);
        }

        @Override
        public LocalDate fromString(String dateString) {
            if (dateString == null || dateString.trim().isEmpty()) {
                return null;
            }
            return LocalDate.parse(dateString, dateTimeFormatter);
        }
    }
}
