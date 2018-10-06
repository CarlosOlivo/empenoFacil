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
package mybatis.dao;

import empenofacil.Util;
import empenofacil.model.Empleado;
import mybatis.MyBatisUtil;
import mybatis.idao.IEmpleadoDAO;
import org.apache.ibatis.session.SqlSession;


public class EmpleadoDAO implements IEmpleadoDAO {

    @Override
    public Empleado obtenerEmpleado(String usuario, String contrasenia) {
        Empleado empleado = null;
        SqlSession conn = MyBatisUtil.getSession();
        try {
            IEmpleadoDAO empleadoDAO = conn.getMapper(IEmpleadoDAO.class);
            empleado = empleadoDAO.obtenerEmpleado(usuario, contrasenia);
        } catch (Exception e) {
            Util.excepcion(e);
        } finally {
            conn.close();
        }
        return empleado;
    }
}