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

import javafx.scene.control.ChoiceBox;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;

public class ClienteTestGUI extends ApplicationTest {

    private static FxRobot bot;

    @BeforeClass
    public static void inicializar() throws Exception {
        FxToolkit.registerPrimaryStage();
        bot = new FxRobot();
        Util.setDebug(true);
    }

    @After
    public void restablecer() throws Exception {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }

    @Override
    public void start(Stage stage) throws Exception {
        UtilTest.cargarStage(stage, "view/Clientes.fxml");
    }

    @Test
    public void nuevoClienteCancelar() {
        FxAssert.verifyThat("#cancelar", NodeMatchers.isDisabled());
        bot.clickOn("#nuevo");
        FxAssert.verifyThat("#cancelar", NodeMatchers.isEnabled());
        bot.clickOn("#cancelar");
    }

    @Test
    public void nuevoClienteGuardar() {
        FxAssert.verifyThat("#guardar", NodeMatchers.isDisabled());
        bot.clickOn("#nuevo");
        FxAssert.verifyThat("#guardar", NodeMatchers.isDisabled());
        bot
                .clickOn("#nombreF")
                .write("Nombre Cliente")
                .clickOn("#apellidoPaternoF")
                .write("Apellido Paterno")
                .clickOn("#telefonoF")
                .write("0123456789")
                .clickOn("#ocupacionC")
                .press(KeyCode.TAB).press(KeyCode.ENTER)
                .clickOn("#fechaNacimientoF")
                .write("03/05/1997")
                .clickOn("#CURPF")
                .write("TEST01234567890123")
                .clickOn("#RFCF")
                .write("TEST123456789")
                .clickOn("#calleF")
                .write("Conocido")
                .clickOn(offset("#numeroF", 75, 0))
                .write("N/A")
                .clickOn("#coloniaF")
                .write("Conocido")
                .clickOn(offset("#localidadF", 75, 0))
                .write("Conocido")
                .clickOn(offset("#codigoPostalF", 75, 0))
                .press(KeyCode.BACK_SPACE).write("12345")
                .clickOn("#estadoC").clickOn()
                .clickOn("#municipioC").clickOn();
        ChoiceBox ocupacionC = bot.fromAll().lookup("#ocupacionC").query();
        Assert.assertNotNull(ocupacionC.getSelectionModel().getSelectedItem());
        ChoiceBox estadoC = bot.fromAll().lookup("#estadoC").query();
        Assert.assertNotNull(estadoC.getSelectionModel().getSelectedItem());
        ChoiceBox municipioC = bot.fromAll().lookup("#municipioC").query();
        Assert.assertNotNull(municipioC.getSelectionModel().getSelectedItem());
        FxAssert.verifyThat("#guardar", NodeMatchers.isEnabled());
        bot.clickOn("#guardar");
        Assert.assertEquals("Nuevo domicilio", UtilTest.getTopDialogPane(bot.robotContext()).getContentText());
        bot.clickOn("#OK");
        Assert.assertEquals("Nuevo cliente", UtilTest.getTopDialogPane(bot.robotContext()).getContentText());
        bot.clickOn("#OK");
    }
}
