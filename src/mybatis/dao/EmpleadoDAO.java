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
import java.util.List;
import mybatis.MyBatisUtil;
import mybatis.idao.IEmpleadoDAO;
import org.apache.ibatis.session.SqlSession;


public class EmpleadoDAO implements IEmpleadoDAO {

    @Override
    public List<Empleado> buscarEmpleados(String busqueda) {
        List<Empleado> list = null;
        SqlSession conn = MyBatisUtil.getSession();
        try {
            IEmpleadoDAO empleadoDAO = conn.getMapper(IEmpleadoDAO.class);
            list = empleadoDAO.buscarEmpleados("%"+busqueda+"%");
        } catch (Exception e) {
            Util.excepcion(e);
        } finally {
            conn.close();
        }
        return list;
    }
    
    @Override
    public List<Empleado> obtenerEmpleados() {
        List<Empleado> list = null;
        SqlSession conn = MyBatisUtil.getSession();
        try {
            IEmpleadoDAO empleadoDAO = conn.getMapper(IEmpleadoDAO.class);
            list = empleadoDAO.obtenerEmpleados();
        } catch (Exception e) {
            Util.excepcion(e);
        } finally {
            conn.close();
        }
        return list;
    }
    
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
    
    @Override
    public Empleado obtenerEmpleadoPorUsuario(String usuario) {
        Empleado empleado = null;
        SqlSession conn = MyBatisUtil.getSession();
        try {
            IEmpleadoDAO empleadoDAO = conn.getMapper(IEmpleadoDAO.class);
            empleado = empleadoDAO.obtenerEmpleadoPorUsuario(usuario);
        } catch (Exception e) {
            Util.excepcion(e);
        } finally {
            conn.close();
        }
        return empleado;
    }
    
    @Override
    public int crearEmpleado(Empleado empleado) {
        int rows = 0;
        SqlSession conn = MyBatisUtil.getSession();
        try {
            IEmpleadoDAO empleadoDAO = conn.getMapper(IEmpleadoDAO.class);
            rows = empleadoDAO.crearEmpleado(empleado);
            conn.commit();
        } catch (Exception e) {
            conn.rollback();
            Util.excepcion(e);
        } finally {
            conn.close();
        }
        return rows;
    }
    
    @Override
    public int editarEmpleado(Empleado empleado) {
        int rows = 0;
        SqlSession conn = MyBatisUtil.getSession();
        try {
            IEmpleadoDAO empleadoDAO = conn.getMapper(IEmpleadoDAO.class);
            rows = empleadoDAO.editarEmpleado(empleado);
            conn.commit();
        } catch (Exception e) {
            conn.rollback();
            Util.excepcion(e);
        } finally {
            conn.close();
        }
        return rows;
    }
}