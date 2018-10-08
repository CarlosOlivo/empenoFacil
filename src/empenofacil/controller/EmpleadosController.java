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
import empenofacil.model.Domicilio;
import empenofacil.model.Empleado;
import empenofacil.model.Estado;
import empenofacil.model.Municipio;
import empenofacil.model.Rol;
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
import mybatis.dao.DomicilioDAO;
import mybatis.dao.EmpleadoDAO;
import mybatis.dao.EstadoDAO;
import mybatis.dao.MunicipioDAO;
import mybatis.dao.RolDAO;

/**
 * FXML Controller class
 *
 * @author Carlos
 */
public class EmpleadosController implements Initializable {

    private final EmpleadoDAO empleadoDAO;
    private final DomicilioDAO domicilioDAO;
    private final EstadoDAO estadoDAO;
    private final MunicipioDAO municipioDAO;
    private final RolDAO rolDAO;
    private final Empleado empleado;
    private final Domicilio domicilio;
    private final Form formulario;
    private final DatePicker fechaNacimiento;
    private final ChoiceBox<Estado> estado;
    private final ChoiceBox<Municipio> municipio;
    private final ChoiceBox<Rol> rol;

    @FXML
    private VBox form;
    @FXML
    private HBox acciones;
    @FXML
    private Button guardar;
    @FXML
    private TextField buscar;
    @FXML
    private TableView<Empleado> empleados;
    @FXML
    private TableColumn<Empleado, String> nombre;
    @FXML
    private TableColumn<Empleado, String> apellidoP;
    @FXML
    private TableColumn<Empleado, String> curp;
    @FXML
    private TableColumn<Empleado, String> rfc;

    public EmpleadosController() {
        empleadoDAO = new EmpleadoDAO();
        domicilioDAO = new DomicilioDAO();
        estadoDAO = new EstadoDAO();
        municipioDAO = new MunicipioDAO();
        rolDAO = new RolDAO();
        empleado = new Empleado(null, null, null, "", "", "", "", "", "", "", "", "", null, null);
        domicilio = new Domicilio(null, "", "", "", null, "", null);
        estado = new ChoiceBox<>();
        fechaNacimiento = new DatePicker();
        municipio = new ChoiceBox<>();
        rol = new ChoiceBox<>();
        formulario = Form.of(
                Section.of(
                        Field.ofStringType(empleado.getUsuarioProperty())
                                .label("Usuario")
                                .span(ColSpan.HALF)
                                .validate(StringLengthValidator.atLeast(3, "Introduce un usuario valido de al menos 3 caracteres."))
                                .required(true),
                        Field.ofPasswordType(empleado.getContraseniaProperty())
                                .label("Contraseña")
                                .span(ColSpan.HALF)
                                .validate(StringLengthValidator.atLeast(3, "Introduce una contraseña valida de al menos 3 caracteres."))
                                .required(true),
                        Field.ofStringType(empleado.getNombreProperty())
                                .label("Nombe")
                                .validate(RegexValidator.forPattern("(?![\\W])^[\\p{L} .'-]{3,}$", "Introduce un nombre valido."))
                                .required(true),
                        Field.ofStringType(empleado.getApellidoPaternoProperty())
                                .label("Apellido paterno")
                                .span(ColSpan.HALF)
                                .validate(RegexValidator.forPattern("(?![\\W])^[\\p{L} .'-]{3,}$", "Introduce un apellido paterno valido."))
                                .required(true),
                        Field.ofStringType(empleado.getApellidoMaternoProperty())
                                .label("Apellido materno")
                                .span(ColSpan.HALF)
                                .validate(RegexValidator.forPattern("^\\.{0}$|(?![\\W])^[\\p{L} .'-]{3,}$", "Introduce un apellido materno valido.")),
                        Field.ofStringType(empleado.getTelefonoProperty())
                                .label("Télefono")
                                .span(ColSpan.HALF)
                                .validate(RegexValidator.forPattern("^\\.{0}$|\\d{10}", "Introduce un número de telefono de 10 digitos.")),
                        Field.ofStringType(empleado.getCelularProperty())
                                .label("Celular")
                                .span(ColSpan.HALF)
                                .validate(RegexValidator.forPattern("^\\.{0}$|\\d{10}", "Introduce un número de telefono de 10 digitos.")),
                        NodeElement.of(Util.contenedor("Rol", rol))
                                .span(ColSpan.HALF),
                        NodeElement.of(Util.contenedor("Fecha de nacimiento", fechaNacimiento))
                                .span(ColSpan.HALF),
                        Field.ofStringType(empleado.getCurpProperty())
                                .label("CURP")
                                .span(ColSpan.HALF)
                                .validate(RegexValidator.forPattern("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{18}$", "Introduce una CURP de 18 caracteres."))
                                .required(true),
                        Field.ofStringType(empleado.getRfcProperty())
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
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        empleados.setPlaceholder(new Text("No hay empleados en el sistema..."));
        empleados.getItems().setAll(empleadoDAO.obtenerEmpleados());
        empleados.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                guardar.setText("Actualizar");
                cargarEmpleado(newValue);
                fechaNacimiento.setValue(empleado.getFechaNacimiento().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                form.setDisable(false);
            }
        });
        nombre.setCellValueFactory(data -> data.getValue().getNombreProperty());
        apellidoP.setCellValueFactory(data -> data.getValue().getApellidoPaternoProperty());
        curp.setCellValueFactory(data -> data.getValue().getCurpProperty());
        rfc.setCellValueFactory(data -> data.getValue().getRfcProperty());
        fechaNacimiento.setConverter(new DateFormat());
        fechaNacimiento.setPromptText("dd/MM/yyyy");
        formulario.binding(BindingMode.CONTINUOUS);
        form.getChildren().add(new FormRenderer(formulario));
        acciones.disableProperty().bind(form.disableProperty());
        guardar.disableProperty().bind(formulario.validProperty().not());
        form.setDisable(true);
        buscar.textProperty().addListener((observable, oldValue, newValue) -> {
            empleados.getItems().setAll(empleadoDAO.buscarEmpleados(newValue));
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
                    Util.dialogo(Alert.AlertType.ERROR, "Ocurrio un error al crear el domicilio del empleado");
                    return;
                }
            } else {
                if (domicilioDAO.editarDomicilio(domicilio) == 0) {
                    Util.dialogo(Alert.AlertType.ERROR, "Ocurrio un error al editar el domicilio del empleado");
                    return;
                }
            }
            empleado.setIdRol(rol.getValue().getIdRol());
            empleado.setIdDomicilio(domicilio.getIdDomicilio());
            empleado.setFechaNacimiento(Date.valueOf(fechaNacimiento.getValue()));
            if (empleado.getIdEmpleado() == null) {
                if (empleadoDAO.crearEmpleado(empleado) == 0) {
                    Util.dialogo(Alert.AlertType.ERROR, "Ocurrio un error al crear el empleado");
                } else {
                    Util.dialogo(Alert.AlertType.INFORMATION, "Empleado creado correctamente");
                    actualizarEmpleados();
                    cancelar();
                }
            } else {
                if (empleadoDAO.editarEmpleado(empleado) == 0) {
                    Util.dialogo(Alert.AlertType.ERROR, "Ocurrio un error al editar el empleado");
                } else {
                    Util.dialogo(Alert.AlertType.INFORMATION, "Empleado editado correctamente");
                    actualizarEmpleados();
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
        empleados.getSelectionModel().clearSelection();
        fechaNacimiento.setValue(null);
        rol.getSelectionModel().clearSelection();
        rol.getItems().clear();
        estado.getSelectionModel().clearSelection();
        estado.getItems().clear();
        municipio.getSelectionModel().clearSelection();
        municipio.getItems().clear();
        Empleado empleadoNuevo = new Empleado(null, null, null, "", "", "", "", "", "", "", "", "", null, null);
        Domicilio domicilioNuevo = new Domicilio(null, "", "", "", null, "", null);
        cargarEmpleado(empleadoNuevo, domicilioNuevo);
    }

    private boolean esFormularioValido() {
        if (empleado.getIdEmpleado() == null && empleadoDAO.obtenerEmpleadoPorUsuario(empleado.getUsuario()) != null) {
            System.out.println(empleado.getIdEmpleado());
            Util.dialogo(Alert.AlertType.ERROR, "El nombre de usuario ya no esta disponible, elige otro");
            empleado.setUsuario("");
            return false;
        }
        if (rol.getValue() == null) {
            Util.dialogo(Alert.AlertType.ERROR, "Elige un rol");
            return false;
        }
        if (fechaNacimiento.getValue() == null) {
            Util.dialogo(Alert.AlertType.ERROR, "Selecciona una fecha de nacimiento");
            return false;
        }
        if (Period.between(fechaNacimiento.getValue(), LocalDate.now()).getYears() < 18) {
            Util.dialogo(Alert.AlertType.ERROR, "Empleado es menor de edad");
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

    private void cargarRoles() {
        List<Rol> roles = rolDAO.obtenerRoles();
        if (roles != null && !roles.isEmpty()) {
            rol.getItems().setAll(roles);
            if (empleado.getIdRol()!= null) {
                rol.getSelectionModel().select(rolDAO.obtenerRol(empleado.getIdRol()));
            }
        } else {
            Util.dialogo(Alert.AlertType.ERROR, "No hay roles en el sistema");
        }
    }

    private void actualizarEmpleados() {
        empleados.getItems().clear();
        if (buscar.getText().trim().isEmpty()) {
            empleados.getItems().setAll(empleadoDAO.obtenerEmpleados());
        } else {
            empleados.getItems().setAll(empleadoDAO.buscarEmpleados(buscar.getText()));
        }
    }

    private void cargarEmpleado(Empleado empleado) {
        cargarEmpleado(empleado, domicilioDAO.obtenerDomicilio(empleado.getIdDomicilio()));
    }

    private void cargarEmpleado(Empleado empleado, Domicilio domicilio) {
        this.empleado.setIdEmpleado(empleado.getIdEmpleado());
        this.empleado.setIdRol(empleado.getIdRol());
        this.empleado.setIdDomicilio(empleado.getIdDomicilio());
        this.empleado.setUsuario(empleado.getUsuario());
        this.empleado.setContrasenia("");
        this.empleado.setNombre(empleado.getNombre());
        this.empleado.setApellidoPaterno(empleado.getApellidoPaterno());
        this.empleado.setApellidoMaterno(Objects.toString(empleado.getApellidoMaterno(), ""));
        this.empleado.setTelefono(Objects.toString(empleado.getTelefono(), ""));
        this.empleado.setCelular(Objects.toString(empleado.getCelular(), ""));
        this.empleado.setCurp(empleado.getCurp());
        this.empleado.setRfc(empleado.getRfc());
        this.empleado.setFechaNacimiento(empleado.getFechaNacimiento());
        this.empleado.setHuellaEmpleado(empleado.getHuellaEmpleado());
        this.domicilio.setIdDomicilio(domicilio.getIdDomicilio());
        this.domicilio.setCalle(domicilio.getCalle());
        this.domicilio.setNumero(domicilio.getNumero());
        this.domicilio.setColonia(domicilio.getColonia());
        this.domicilio.setCodigoPostal(domicilio.getCodigoPostal());
        this.domicilio.setLocalidad(domicilio.getLocalidad());
        this.domicilio.setIdMunicipio(domicilio.getIdMunicipio());
        cargarEstados();
        cargarRoles();
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
