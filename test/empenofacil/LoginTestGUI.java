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

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DialogPane;
import javafx.stage.Stage;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit.ApplicationTest;

public class LoginTestGUI extends ApplicationTest {
    
    @BeforeClass
    public static void inicializar() {
        Util.setJUnit(true);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(EmpenoFacil.class.getResource("view/Login.fxml"));
        Parent root = (Parent) loader.load();
        Scene scene = new Scene(root);
        stage.hide();
        stage.setScene(scene);
        stage.setTitle("Empeño fácil - Inicio de sesión");
        stage.setResizable(false);
        stage.setMaximized(false);
        stage.sizeToScene();
        stage.show();
    }
    
    @Test
    public void loginVacio() {
        FxRobot bot = new FxRobot();
        bot.clickOn("#login");
        final Stage actualAlertDialog = UtilTest.getTopModalStage(bot.robotContext());
        assertNotNull(actualAlertDialog);
        final DialogPane dialogPane = (DialogPane) actualAlertDialog.getScene().getRoot();
        assertEquals("El campo usuario esta vacio", dialogPane.getContentText());
        bot.clickOn("#OK");
    }
    
    @Test
    public void loginPassVacio() {
        FxRobot bot = new FxRobot();
        bot.clickOn("#user");
        bot.write("test");
        bot.clickOn("#login");
        final Stage actualAlertDialog = UtilTest.getTopModalStage(bot.robotContext());
        assertNotNull(actualAlertDialog);
        final DialogPane dialogPane = (DialogPane) actualAlertDialog.getScene().getRoot();
        assertEquals("El campo contraseña esta vacio", dialogPane.getContentText());
        bot.clickOn("#OK");
    }
    
    @Test
    public void loginIncorrecto() {
        FxRobot bot = new FxRobot();
        bot.clickOn("#user");
        bot.write("test");
        bot.clickOn("#pass");
        bot.write("test2");
        bot.clickOn("#login");
        final Stage actualAlertDialog = UtilTest.getTopModalStage(bot.robotContext());
        assertNotNull(actualAlertDialog);
        final DialogPane dialogPane = (DialogPane) actualAlertDialog.getScene().getRoot();
        assertEquals("Usuario y/o contraseña invalidos", dialogPane.getContentText());
        bot.clickOn("#OK");
    }
    
    @Test
    public void loginCorecto() {
        FxRobot bot = new FxRobot();
        bot.clickOn("#user");
        bot.write("test");
        bot.clickOn("#pass");
        bot.write("test");
        bot.clickOn("#login");
        final Stage actualAlertDialog = UtilTest.getTopModalStage(bot.robotContext());
        assertNotNull(actualAlertDialog);
        final DialogPane dialogPane = (DialogPane) actualAlertDialog.getScene().getRoot();
        assertEquals("Menú", dialogPane.getContentText());
        bot.clickOn("#OK");
    }
}
