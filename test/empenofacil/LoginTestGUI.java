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

import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;

public class LoginTestGUI extends ApplicationTest {
    
    private static FxRobot bot;

    @BeforeClass
    public static void inicializar() throws Exception {
        FxToolkit.registerPrimaryStage();
        bot = new FxRobot();
        Util.setDebug(true);
    }
    
    @Before
    public void antes() {
        FxAssert.verifyThat("#login", NodeMatchers.isNotNull());
        FxAssert.verifyThat("#login", NodeMatchers.isVisible());
        FxAssert.verifyThat("#login", NodeMatchers.isEnabled());
    }

    @After
    public void restablecer() throws Exception {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }

    @Override
    public void start(Stage stage) throws Exception {
        UtilTest.cargarStage(stage, "view/Login.fxml");
    }

    @Test
    public void loginVacio() {
        bot.clickOn("#login");
        Assert.assertEquals("El campo usuario esta vacio", UtilTest.getTopDialogPane(bot.robotContext()).getContentText());
        bot.clickOn("#OK");
    }

    @Test
    public void loginPassVacio() {
        bot
                .clickOn("#user")
                .write("test")
                .clickOn("#login");
        Assert.assertEquals("El campo contraseña esta vacio", UtilTest.getTopDialogPane(bot.robotContext()).getContentText());
        bot.clickOn("#OK");
    }

    @Test
    public void loginIncorrecto() {
        bot
                .clickOn("#user")
                .write("test")
                .clickOn("#pass")
                .write("test2")
                .clickOn("#login");
        Assert.assertEquals("Usuario y/o contraseña invalidos", UtilTest.getTopDialogPane(bot.robotContext()).getContentText());
        bot.clickOn("#OK");
    }

    @Test
    public void loginCorecto() {
        bot
                .clickOn("#user")
                .write("test")
                .clickOn("#pass")
                .write("test")
                .clickOn("#login");
        Assert.assertEquals("Menú", UtilTest.getTopDialogPane(bot.robotContext()).getContentText());
        bot.clickOn("#OK");
    }
    
    @Test
    public void loginSalir() {
        bot.clickOn("#salir");
        Assert.assertEquals("Salir", UtilTest.getTopDialogPane(bot.robotContext()).getContentText());
        bot.clickOn("#OK");
    }
}
