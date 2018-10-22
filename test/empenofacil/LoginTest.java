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

import de.saxsys.javafx.test.JfxRunner;
import de.saxsys.javafx.test.TestInJfxThread;
import empenofacil.model.Empleado;
import javafx.application.Application;
import javafx.stage.Stage;
import mybatis.dao.EmpleadoDAO;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

@RunWith(JfxRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LoginTest extends Application {
    
    private static EmpleadoDAO empleadoDAO;
    private static Empleado empleado;

    public static Empleado getEmpleado() {
        return empleado;
    }

    public static void setEmpleado(Empleado empleado) {
        LoginTest.empleado = empleado;
    }
    
    @BeforeClass
    public static void preparar() {
        empleadoDAO = new EmpleadoDAO();
    }
    
    @Test
    @TestInJfxThread
    public void test_1_login() {
        setEmpleado(empleadoDAO.obtenerEmpleado("test", "test"));
        Assert.assertNotNull("No existe empleado", empleado);
    }
    
    @Test
    public void test_2_esUsuario() {
        Assert.assertEquals("Usuarion invalido", "test", getEmpleado().getUsuario());
    }
        
    @Override
    public void start(Stage primaryStage) throws Exception {
        // NOTHING
    }
}
