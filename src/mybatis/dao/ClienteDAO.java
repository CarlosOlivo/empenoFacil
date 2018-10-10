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
import empenofacil.model.Cliente;
import java.util.List;
import mybatis.MyBatisUtil;
import mybatis.idao.IClienteDAO;
import org.apache.ibatis.session.SqlSession;


public class ClienteDAO implements IClienteDAO {
    
    @Override
    public List<Cliente> buscarClientes(String busqueda) {
        List<Cliente> list = null;
        SqlSession conn = MyBatisUtil.getSession();
        try {
            IClienteDAO clienteDAO = conn.getMapper(IClienteDAO.class);
            list = clienteDAO.buscarClientes("%"+busqueda+"%");
        } catch (Exception e) {
            Util.excepcion(e);
        } finally {
            conn.close();
        }
        return list;
    }
    
    @Override
    public List<Cliente> obtenerClientes() {
        List<Cliente> list = null;
        SqlSession conn = MyBatisUtil.getSession();
        try {
            IClienteDAO clienteDAO = conn.getMapper(IClienteDAO.class);
            list = clienteDAO.obtenerClientes();
        } catch (Exception e) {
            Util.excepcion(e);
        } finally {
            conn.close();
        }
        return list;
    }

    @Override
    public Cliente obtenerCliente(Integer idCliente) {
        Cliente cliente = null;
        SqlSession conn = MyBatisUtil.getSession();
        try {
            IClienteDAO clienteDAO = conn.getMapper(IClienteDAO.class);
            cliente = clienteDAO.obtenerCliente(idCliente);
        } catch (Exception e) {
            Util.excepcion(e);
        } finally {
            conn.close();
        }
        return cliente;
    }

    @Override
    public int crearCliente(Cliente cliente) {
        int rows = 0;
        SqlSession conn = MyBatisUtil.getSession();
        try {
            IClienteDAO clienteDAO = conn.getMapper(IClienteDAO.class);
            rows = clienteDAO.crearCliente(cliente);
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
    public int editarCliente(Cliente cliente) {
        int rows = 0;
        SqlSession conn = MyBatisUtil.getSession();
        try {
            IClienteDAO clienteDAO = conn.getMapper(IClienteDAO.class);
            rows = clienteDAO.editarCliente(cliente);
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
    public int editarListaNegraCliente(Cliente cliente) {
        int rows = 0;
        SqlSession conn = MyBatisUtil.getSession();
        try {
            IClienteDAO clienteDAO = conn.getMapper(IClienteDAO.class);
            rows = clienteDAO.editarListaNegraCliente(cliente);
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