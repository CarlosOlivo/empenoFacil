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
package mybatis.idao;

import empenofacil.model.Empleado;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IEmpleadoDAO {
    public List<Empleado> buscarEmpleados(String busqueda);
    public List<Empleado> obtenerEmpleados();
    public Empleado obtenerEmpleado(@Param("usuario") String usuario, @Param("contrasenia") String contrasenia);
    public Empleado obtenerEmpleadoPorUsuario(String usuario);
    public int crearEmpleado(Empleado empleado);
    public int editarEmpleado(Empleado empleado);
}
