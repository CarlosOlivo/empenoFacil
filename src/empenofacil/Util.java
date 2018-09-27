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

import empenofacil.controller.LoginController;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class Util {
    public static void dialogo(Alert.AlertType tipo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.show();
    }
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
    public static void login(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(EmpenoFacil.class.getResource("view/Login.fxml"));
            Parent root = (Parent) loader.load();
            LoginController controller = (LoginController) loader.getController();
            controller.setStage(stage);
            Scene scene = new Scene(root);
            stage.hide();
            stage.setScene(scene);
            stage.setTitle("Empeño fácil - Inicio de sesión");
            stage.setResizable(false);
            stage.setMaximized(false);
            stage.sizeToScene();
            stage.show();
        } catch (IOException ioEx) {
            Util.excepcion(ioEx);
        }
    }
}
