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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DialogPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.junit.Assert;
import org.junit.Ignore;
import org.testfx.api.FxRobotContext;

/**
 * Clase utilitaria de pruebas
 * 
 * @author Carlos
 */
@Ignore
public class UtilTest {

    /**
     * Inicializa un Stage principal
     * @param stage Stage principal a inicializar
     * @param fxml Archivo .FXML a cargar
     * @throws Exception Exepción
     */
    public static void cargarStage(Stage stage, String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader(EmpenoFacil.class.getResource(fxml));
        Parent root = (Parent) loader.load();
        Scene scene = new Scene(root);
        stage.hide();
        stage.setScene(scene);
        stage.setTitle("Empeño fácil");
        stage.setResizable(false);
        stage.setMaximized(false);
        stage.sizeToScene();
        stage.show();
        stage.toFront();
    }

    /**
     * Obtiene el Stage del primer dialogo modal que encuentre al frente
     * 
     * @param robotContext Contexto
     * @return Stage del dialogo modal
     */
    public static Stage getTopModalStage(final FxRobotContext robotContext) {
        final List<Window> allWindows = new ArrayList<>(robotContext.getWindowFinder().listWindows());
        Collections.reverse(allWindows);

        return (Stage) allWindows
                .stream()
                .filter(window -> window instanceof javafx.stage.Stage)
                .filter(window -> ((javafx.stage.Stage) window).getModality() == Modality.APPLICATION_MODAL)
                .findFirst()
                .orElse(null);
    }
    
    /**
     * Obtiene el primer DialogPane que se encuentre al frente
     * 
     * @param fxRobotContext Contexto
     * @return DialogPane encontrado
     */
    public static DialogPane getTopDialogPane(final FxRobotContext fxRobotContext) {
        final Stage actualAlertDialog = UtilTest.getTopModalStage(fxRobotContext);
        Assert.assertNotNull(actualAlertDialog);
        return (DialogPane) actualAlertDialog.getScene().getRoot();
    }
}
