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
package empenofacil;

import empenofacil.controller.MenuController;
import empenofacil.model.Empleado;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

/**
 * Clase utilitaria
 * @author Carlos
 */
public class Util {

    /**
     * Despliega un un dialogo JavaFX
     * @param tipo Tipo del dialogo
     * @param mensaje Mensaje a desplegar en el dialogo
     */
    public static void dialogo(Alert.AlertType tipo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.show();
    }

    /**
     * Despliiega el stack trace de una excepción en dialogo JavaFX
     * @param exception Excepción a desplegar
     */
    public static void excepcion(Exception exception) {
        if(exception.getMessage().contains("Communications link failure")) {
            Util.dialogo(Alert.AlertType.ERROR, "No se puede establecer una conexión con la base de datos, intente más tarde");
            return;
        }
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Excepción");
        alert.setHeaderText(null);
        alert.setContentText(exception.getLocalizedMessage());
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        exception.printStackTrace(pw);
        String exceptionText = sw.toString();
        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);
        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);
        alert.getDialogPane().setExpandableContent(textArea);
        alert.showAndWait();
    }

    /**
     * Muestra la ventana de inicio de sesión
     */
    public static void login() {
        try {
            FXMLLoader loader = new FXMLLoader(EmpenoFacil.class.getResource("view/Login.fxml"));
            Parent root = (Parent) loader.load();
            Scene scene = new Scene(root);
            EmpenoFacil.getStage().hide();
            EmpenoFacil.getStage().setScene(scene);
            EmpenoFacil.getStage().setTitle("Empeño fácil - Inicio de sesión");
            EmpenoFacil.getStage().setResizable(false);
            EmpenoFacil.getStage().setMaximized(false);
            EmpenoFacil.getStage().sizeToScene();
            EmpenoFacil.getStage().show();
        } catch (IOException ioEx) {
            Util.excepcion(ioEx);
        }
    }
    
    /**
     * Muestra la ventana de registro
     */
    public static void registro() {
        try {
            FXMLLoader loader = new FXMLLoader(EmpenoFacil.class.getResource("view/Registro.fxml"));
            Parent root = (Parent) loader.load();
            Scene scene = new Scene(root);
            EmpenoFacil.getStage().hide();
            EmpenoFacil.getStage().setScene(scene);
            EmpenoFacil.getStage().setTitle("Empeño fácil - Registo de usuario");
            EmpenoFacil.getStage().setResizable(false);
            EmpenoFacil.getStage().setMaximized(false);
            EmpenoFacil.getStage().sizeToScene();
            EmpenoFacil.getStage().show();
        } catch (IOException ioEx) {
            Util.excepcion(ioEx);
        }
    }

    /**
     * Muestra el menú principal
     * @param empleado Empleado del sistema
     */
    public static void menu(Empleado empleado) {
        try {
            FXMLLoader loader = new FXMLLoader(EmpenoFacil.class.getResource("view/Menu.fxml"));
            Parent root = (Parent) loader.load();
            MenuController controller = (MenuController) loader.getController();
            controller.setEmpleado(empleado);
            Scene scene = new Scene(root);
            EmpenoFacil.getStage().hide();
            EmpenoFacil.getStage().setScene(scene);
            EmpenoFacil.getStage().setTitle("Empeño fácil - Menu principal");
            EmpenoFacil.getStage().setResizable(true);
            EmpenoFacil.getStage().setMaximized(false);
            EmpenoFacil.getStage().sizeToScene();
            EmpenoFacil.getStage().show();
        } catch (IOException ioEx) {
            Util.excepcion(ioEx);
        }
    }

    /**
     * Crea un campo personalizado para un formulario FormFX
     * @param label Etiqueta del campo
     * @param control Control personalizado
     * @return GridPane con un campo de formulario
     */
    public static GridPane contenedor(String label, Control control) {
        GridPane gridPane = new GridPane();
        AnchorPane anchorPane = new AnchorPane(control);
        AnchorPane.setLeftAnchor(control, 0d);
        AnchorPane.setRightAnchor(control, 0d);
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(33.35);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(66.65);
        gridPane.getColumnConstraints().setAll(column1, column2);
        gridPane.add(new Label(label), 0, 0, 1, 1);
        gridPane.add(anchorPane, 1, 0, 1, 1);
        return gridPane;
    }
}
