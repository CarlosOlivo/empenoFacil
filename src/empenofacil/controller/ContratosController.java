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
import empenofacil.model.Bolsa;
import empenofacil.model.CategoriaPrenda;
import empenofacil.model.Cliente;
import empenofacil.model.Contrato;
import empenofacil.model.Parametros;
import empenofacil.model.Periodo;
import empenofacil.model.Prenda;
import empenofacil.model.TipoPrenda;
import empenofacil.reportes.Reportes;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import mybatis.dao.BolsaDAO;
import mybatis.dao.CategoriaPrendaDAO;
import mybatis.dao.ClienteDAO;
import mybatis.dao.ContratoDAO;
import mybatis.dao.ParametrosDAO;
import mybatis.dao.PeriodoDAO;
import mybatis.dao.PrendaDAO;
import mybatis.dao.TipoPrendaDAO;

/**
 * FXML Controller class
 *
 * @author Carlos
 */
public class ContratosController implements Initializable {

    private final BolsaDAO bolsaDAO;
    private final ContratoDAO contratoDAO;
    private final ClienteDAO clienteDAO;
    private final TipoPrendaDAO tipoPrendaDAO;
    private final CategoriaPrendaDAO categoriaPrendaDAO;
    private final ParametrosDAO parametrosDAO;
    private final PeriodoDAO periodoDAO;
    private final PrendaDAO prendaDAO;
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
    private ChoiceBox<String> buscarC;
    @FXML
    private TextField buscarT;
    @FXML
    private TableView<Contrato> contratos;
    @FXML
    private TableColumn<Contrato, String> folio;
    @FXML
    private TableColumn<Contrato, String> estadoContrato;
    @FXML
    private TableColumn<Contrato, String> avaluoContrato;
    @FXML
    private TableColumn<Contrato, String> prestamoContrato;
    @FXML
    private TableColumn<Contrato, String> fechaInicioContrato;
    @FXML
    private TableColumn<Contrato, String> fechaFinContrato;
    @FXML
    private ChoiceBox<Cliente> clientesC;
    @FXML
    private TableView<Prenda> prendas;
    @FXML
    private TableColumn<Prenda, String> nombre;
    @FXML
    private TableColumn<Prenda, String> avaluo;
    @FXML
    private TableColumn<Prenda, String> prestamo;
    @FXML
    private TableColumn<Prenda, String> descripcion;
    @FXML
    private TableColumn<Prenda, Void> colAcciones;
    @FXML
    private Button guardarPrenda;
    @FXML
    private TextField cotitular;
    @FXML
    private Label porcentajePrestamo;
    @FXML
    private HBox opcionesContrato;
    @FXML
    private Label opcionesContratoL;

    public ContratosController() {
        bolsaDAO = new BolsaDAO();
        contratoDAO = new ContratoDAO();
        clienteDAO = new ClienteDAO();
        tipoPrendaDAO = new TipoPrendaDAO();
        categoriaPrendaDAO = new CategoriaPrendaDAO();
        parametrosDAO = new ParametrosDAO();
        periodoDAO = new PeriodoDAO();
        prendaDAO = new PrendaDAO();
        prenda = new Prenda(null, null, null, "", 0d, 0d, "", 0d, 0d);
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
                        Field.ofDoubleType(prenda.prestamoProperty())
                                .label("Prestamo")
                                .span(ColSpan.HALF)
                                .validate(DoubleRangeValidator.atLeast(0.1d, "Introduce la cantidad a prestar."))
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
        buscarC.getItems().setAll("Folio", "Cliente", "Prenda");
        contratos.setPlaceholder(new Text("No hay empeños en el sistema..."));
        folio.setCellValueFactory(data -> data.getValue().folioPropertyFormato());
        estadoContrato.setCellValueFactory(data -> data.getValue().estadoContratoPropertyFormato());
        avaluoContrato.setCellValueFactory(data -> data.getValue().totalAvaluoPropertyFormato());
        prestamoContrato.setCellValueFactory(data -> data.getValue().totalPrestamoPropertyFormato());
        fechaInicioContrato.setCellValueFactory(data -> data.getValue().fechaInicioContratoFormato());
        fechaFinContrato.setCellValueFactory(data -> data.getValue().fechaFinContratoFormato());
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
        avaluo.setCellValueFactory(data -> data.getValue().avaluoPropertyFormato());
        prestamo.setCellValueFactory(data -> data.getValue().prestamoPropertyFormato());
        descripcion.setCellValueFactory(data -> data.getValue().getDescripcionProperty());
        colAcciones.setCellFactory(param -> new Opciones());
        prendas.setItems(lista_prendas);
        contratos.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                opcionesContrato.setDisable(false);
                opcionesContratoL.setText("Empeño #" + newValue.getFolio());
            }
        });
    }

    @FXML
    private void nuevoContrato() {
        clientesC.getSelectionModel().clearSelection();
        clientesC.getItems().clear();
        cargarClientes();
        porcentajePrestamo.setText(String.format("Porcentaje de prestamo: %.2f%%", MenuController.getParametrosPredeterminados().getPorcentajePrestamo() * 100));
        contratosV.setVisible(false);
        prendasV.setVisible(true);
        if (clientesC.getItems().isEmpty()) {
            regresarContratos();
            Util.dialogo(Alert.AlertType.ERROR, "No hay clientes en el sistema");
        }
    }

    @FXML
    private void nuevaPrenda() {
        if (lista_prendas.size() >= 5) {
            Util.dialogo(Alert.AlertType.ERROR, "Solo puedes empeñar 5 prendas a la vez.");
            return;
        }
        cargarTipos();
        prendasV.setVisible(false);
        formV.setVisible(true);
        if (tipos.getItems().isEmpty()) {
            regresarPrendas();
            Util.dialogo(Alert.AlertType.ERROR, "No hay tipos de prenda en el sistema");
        }
    }

    @FXML
    private void regresarContratos() {
        if (!lista_prendas.isEmpty()) {
            Optional<ButtonType> confirmacion = Util.confirmacion("Nuevo empleño", "Se descartara el empeño, ¿Deseas continuar?");
            if (confirmacion.isPresent() && confirmacion.get() == ButtonType.YES) {
                lista_prendas.clear();
                cotitular.clear();
            }
            if (confirmacion.isPresent() && confirmacion.get() == ButtonType.NO) {
                return;
            }
        }
        prendasV.setVisible(false);
        contratosV.setVisible(true);
    }

    @FXML
    private void guardarContrato() {
        if (esContratoValido()) {
            Double totalAvaluo = 0d;
            Double totalPrestamo = 0d;
            Contrato contrato = new Contrato(
                    null, // folio
                    clientesC.getSelectionModel().getSelectedItem().getIdCliente(), // idCliente
                    Contrato.ESTADO_CONTRATO.ACTIVO.ordinal(), //idEstadoContrato
                    1, // idSucursal
                    MenuController.getEmpleado().getIdEmpleado(), // idEmpleado
                    0, // numBolsa
                    new Date(), // FechaInicioContrato
                    Util.agregarDiasFecha(new Date(), MenuController.getParametrosPredeterminados().getDiasEnTotal()), // FechaFinContrato
                    cotitular.getText().trim(), //cotitular
                    totalAvaluo, //totalAvaluo
                    totalPrestamo //totalPrestamo
            );
            if (contratoDAO.crearContrato(contrato) == 0) {
                Util.dialogo(Alert.AlertType.ERROR, "Ocurrio un error al crear el contrato");
                return;
            }
            if (parametrosDAO.crearParametros(contrato.getFolio()) == 0) {
                Util.dialogo(Alert.AlertType.ERROR, "Ocurrio un error al crear los parametros del contrato");
                return;
            }
            for (Prenda prendaTMP : lista_prendas) {
                totalAvaluo += prendaTMP.getAvaluo();
                totalPrestamo += prendaTMP.getPrestamo();
                if (prendaDAO.crearPrenda(prendaTMP) == 0) {
                    Util.dialogo(Alert.AlertType.ERROR, "Ocurrio un error al crear la prenda");
                    return;
                }
                if (bolsaDAO.crearBolsa(new Bolsa(contrato.getFolio(), prendaTMP.getIdPrenda())) == 0) {
                    Util.dialogo(Alert.AlertType.ERROR, "Ocurrio un error al agregar la prenda a la bolsa");
                    return;
                }
            }
            contrato.setNumBolsa(contrato.getFolio());
            contrato.setTotalAvaluo(totalAvaluo);
            contrato.setTotalPrestamo(totalPrestamo);
            if (contratoDAO.editarContrato(contrato) == 0) {
                Util.dialogo(Alert.AlertType.ERROR, "Ocurrio un error al editar el contrato");
            } else {
                Util.dialogo(Alert.AlertType.INFORMATION, "Contrato creado correctamente");
                generarPeriodosDePago(contrato);
                lista_prendas.clear();
                cotitular.clear();
                actualizarContratos();
                regresarContratos();
            }
        }
    }

    private boolean esContratoValido() {
        if (clientesC.getSelectionModel().getSelectedItem() == null) {
            Util.dialogo(Alert.AlertType.ERROR, "Elige un cliente");
            return false;
        }
        if (lista_prendas.isEmpty()) {
            Util.dialogo(Alert.AlertType.ERROR, "Agrega al menos una prenda");
            return false;
        }
        if (cotitular.getText().trim().isEmpty()) {
            Util.dialogo(Alert.AlertType.ERROR, "Introduce el nombre del cotitular");
            return false;
        }
        return true;
    }

    private boolean esPrendaValida() {
        if (tipos.getValue() == null) {
            Util.dialogo(Alert.AlertType.ERROR, "Selecciona el tipo de prenda antes de continuar");
            return false;
        }
        if (categorias.getValue() == null) {
            Util.dialogo(Alert.AlertType.ERROR, "Selecciona la categoria de la prenda antes de continuar");
            return false;
        }
        return true;
    }

    @FXML
    private void guardarPrenda() {
        if (esPrendaValida()) {
            prenda.setIdTipoPrenda(tipos.getValue().getIdTipoPrenda());
            prenda.setIdCategoriaPrenda(categorias.getValue().getIdCategoriaPrenda());
            lista_prendas.add(
                    new Prenda(
                            prenda.getIdPrenda(),
                            prenda.getIdCategoriaPrenda(),
                            prenda.getIdTipoPrenda(),
                            prenda.getNombre(),
                            prenda.getTamanio(),
                            prenda.getPeso(),
                            prenda.getDescripcion(),
                            prenda.getPrestamo() / MenuController.getParametrosPredeterminados().getPorcentajePrestamo(),
                            prenda.getPrestamo()
                    )
            );
            descartarPrenda();
            regresarPrendas();
        }
    }

    private void descartarPrenda() {
        prenda.setIdPrenda(null);
        prenda.setIdCategoriaPrenda(null);
        prenda.setIdTipoPrenda(null);
        prenda.setNombre("");
        prenda.setTamanio(0d);
        prenda.setPeso(0d);
        prenda.setDescripcion("");
        prenda.setAvaluo(0d);
        prenda.setPrestamo(0d);
    }

    @FXML
    private void regresarPrendas() {
        descartarPrenda();
        formV.setVisible(false);
        prendasV.setVisible(true);
    }

    private void cargarClientes() {
        List<Cliente> clientes = clienteDAO.obtenerClientes();
        if (clientes != null && !clientes.isEmpty()) {
            clientesC.getItems().setAll(clientes);
        }
    }

    private void cargarTipos() {
        categorias.setDisable(true);
        List<TipoPrenda> tiposPrenda = tipoPrendaDAO.obtenerTiposPrenda();
        if (tiposPrenda != null && !tiposPrenda.isEmpty()) {
            categorias.getSelectionModel().clearSelection();
            tipos.getItems().setAll(tiposPrenda);
        } else {
            regresarContratos();
            Util.dialogo(Alert.AlertType.ERROR, "No hay tipos de prenda en el sistema");
        }
    }

    private void cargarCategorias(int idTipoPrenda) {
        List<CategoriaPrenda> categoriasPrenda = categoriaPrendaDAO.obtenerCategoriasPrenda(idTipoPrenda);
        if (categoriasPrenda != null && !categoriasPrenda.isEmpty()) {
            categorias.getItems().setAll(categoriasPrenda);
            categorias.setDisable(false);
        } else {
            Util.dialogo(Alert.AlertType.ERROR, "No hay categorias en el sistema");
        }
    }

    @FXML
    public void actualizarContratos() {
        buscarC.getSelectionModel().clearSelection();
        buscarT.clear();
        contratos.getItems().clear();
        opcionesContrato.setDisable(true);
        opcionesContratoL.setText("Empeño #?");
        contratos.getItems().setAll(contratoDAO.obtenerContratos());
    }

    @FXML
    private void cancelarContrato() {
        Contrato contratoTMP = contratos.getSelectionModel().getSelectedItem();
        long diasDesdeContrato = ChronoUnit.DAYS.between(contratoTMP.getFechaInicioContrato().toInstant(), new Date().toInstant());
        Parametros parametrosTMP = parametrosDAO.obtenerParametros(contratoTMP.getFolio());
        if (contratoTMP.getIdEstadoContrato() == Contrato.ESTADO_CONTRATO.CANCELADO.ordinal()) {
            Util.dialogo(Alert.AlertType.WARNING, "El empeño ya se encuentra cancelado");
            return;
        }
        if (contratoTMP.getFechaInicioContrato().getTime() > new Date().getTime()) {
            Util.dialogo(Alert.AlertType.WARNING, "Fecha de inicio de contrato invalida");
            return;
        }
        if (diasDesdeContrato < parametrosTMP.getDiasParaCancelarContrato()) {
            Optional<ButtonType> confirmacion = Util.confirmacion("Cancelar empeño", String.format("¿Desea cancelar el empeño #%d?", contratoTMP.getFolio()));
            if (confirmacion.isPresent() && confirmacion.get() == ButtonType.YES) {
                contratoTMP.setIdEstadoContrato(Contrato.ESTADO_CONTRATO.CANCELADO.ordinal());
                if (contratoDAO.editarContrato(contratoTMP) > 0) {
                    Util.dialogo(Alert.AlertType.INFORMATION, "Empeño cancelado correctamente");
                    actualizarContratos();
                } else {
                    Util.dialogo(Alert.AlertType.ERROR, "Ocurrio un error al cancelar el empeño");
                }
            }
        } else {
            Util.dialogo(Alert.AlertType.ERROR, String.format("No se puede cancelar un contrato después de %d día(s), el límite es de < %d día(s)", diasDesdeContrato, parametrosTMP.getDiasParaCancelarContrato()));
        }
    }

    @FXML
    public void clonarContrato() {
        // TODO - Reempeño
    }

    @FXML
    public void buscarContrato() {
        String busqueda;
        List<Contrato> contratosTMP;
        if (buscarT.getText().trim().isEmpty()) {
            Util.dialogo(Alert.AlertType.ERROR, "Introduce el valor a buscar antes de continuar");
            return;
        }
        if (buscarC.getSelectionModel().getSelectedItem() == null) {
            Util.dialogo(Alert.AlertType.ERROR, "Selecciona el tipo de busqueda que deseas realizar antes de continuar");
            return;
        }
        switch (buscarC.getSelectionModel().getSelectedItem()) {
            case "Folio":
                try {
                    int folioTMP = Integer.parseInt(buscarT.getText().trim());
                    Contrato contratoTMP = contratoDAO.obtenerContrato(folioTMP);
                    if (contratoTMP == null) {
                        contratos.getItems().clear();
                    } else {
                        contratos.getItems().setAll(contratoTMP);
                    }
                } catch (NumberFormatException nfEx) {
                    Util.dialogo(Alert.AlertType.ERROR, "El Folio no es un número entero valido");
                }
                break;
            case "Cliente":
                busqueda = buscarT.getText().trim();
                contratosTMP = contratoDAO.buscarContratoPorNombre(busqueda);
                if (contratosTMP == null || contratosTMP.isEmpty()) {
                    contratos.getItems().clear();
                } else {
                    contratos.getItems().setAll(contratosTMP);
                }
                break;
            case "Prenda":
                busqueda = buscarT.getText().trim();
                contratosTMP = contratoDAO.buscarContratoPorPrenda(busqueda);
                if (contratosTMP == null || contratosTMP.isEmpty()) {
                    contratos.getItems().clear();
                } else {
                    contratos.getItems().setAll(contratosTMP);
                }
                break;
            default:
                Util.dialogo(Alert.AlertType.ERROR, "Opción invalida");
                actualizarContratos();
                break;
        }
    }
    
    private void generarPeriodosDePago(Contrato contrato) {
        Parametros parametros = new ParametrosDAO().obtenerParametros(contrato.getFolio());
        int numPeriodos = parametros.getNumPeriodos();
        int diasPorPeriodo = parametros.getDiasPorPeriodo();
        List<Periodo> periodos = new ArrayList<>(numPeriodos);
        Date fechaTMP = contrato.getFechaInicioContrato();
        for(int numPeriodo = 0; numPeriodo < numPeriodos; numPeriodo++) {
            Periodo periodo = new Periodo();
            periodo.setFolio(contrato.getFolio());
            periodo.setNumPeriodo(numPeriodo + 1);
            periodo.setPrestamo(contrato.getTotalPrestamo());
            periodo.setInteres(periodo.getPrestamo()*parametros.getInteresPorPeriodo()*periodo.getNumPeriodo());
            periodo.setIva(periodo.getInteres()*parametros.getIva());
            periodo.setRefrendo(periodo.getInteres()+periodo.getIva());
            periodo.setFiniquito(periodo.getPrestamo()+periodo.getRefrendo());
            periodo.setFechaInicioPeriodo(fechaTMP);
            if(numPeriodo == 0) { 
                fechaTMP = Util.agregarDiasFecha(fechaTMP, diasPorPeriodo);
            } else {
                fechaTMP = Util.agregarDiasFecha(fechaTMP, diasPorPeriodo - 1);
            }
            periodo.setFechaFinPeriodo(fechaTMP);
            if(numPeriodo != numPeriodos - 1) {
                fechaTMP = Util.agregarDiasFecha(fechaTMP, 1);
            }
            periodos.add(periodo);
        }
        periodos.forEach((periodo) -> {
            if(periodoDAO.crearPeriodo(periodo) == 0) {
                Util.dialogo(Alert.AlertType.ERROR, "Ocurrio un error al guardar el periodo de pago");
            }
        });
    }

    @FXML
    public void generarContrato() {
        Integer numFolio = contratos.getSelectionModel().getSelectedItem().getFolio();//obtien el indice del registro en la tabla
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("folio", numFolio);
        String path = Reportes.generarPDFContratoJasper("Contrato", parametros);
        openPDF(path);

    }
    
    @FXML
    public void imprimirEtiquetas() {
        Integer numFolio = contratos.getSelectionModel().getSelectedItem().getFolio();//obtien el indice del registro en la tabla
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("folio", numFolio);
        String path = Reportes.generarEtiquetaVenta("Etiquetadecomercializacion", parametros);
        openPDF(path);
    }

    public static void openPDF(String url) {
        if (Desktop.isDesktopSupported()) {
            try {
                File myFile = new File(url);
                Desktop.getDesktop().open(myFile);
            } catch (IOException ex) {
                Util.excepcion(ex);
            }
        }
    }

    private class Opciones extends TableCell<Prenda, Void> {

        private final Hyperlink eliminar = new Hyperlink("❌ Eliminar prenda");
        private final Hyperlink tomarFoto = new Hyperlink("Tomar foto");

        private final HBox hb = new HBox(eliminar, tomarFoto);
        

        public Opciones() {
            hb.setSpacing(5);
            hb.setPadding(new Insets(-3.5d, 0d, 0d, 0d));
            hb.setAlignment(Pos.TOP_LEFT);
            eliminar.setTooltip(new Tooltip("Eliminar prenda"));
            eliminar.setOnAction(event -> {
                lista_prendas.remove(getIndex());
            });
            
            tomarFoto.setOnAction((event) -> {
                Util.capturarFotoPrenda(prenda);
            });
        }

        @Override
        protected void updateItem(Void item, boolean empty) {
            super.updateItem(item, empty);
            setGraphic(empty ? null : hb);
        }
    }
}
