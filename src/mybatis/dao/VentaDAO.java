/*
 * Copyright (C) 2018 lunix
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
import empenofacil.model.Venta;
import java.util.List;
import mybatis.MyBatisUtil;
import mybatis.idao.IVentaDAO;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author lunix
 */
public class VentaDAO implements IVentaDAO {

    @Override
    public List<Venta> buscarVenta(String busqueda) {
        List<Venta> list = null;
        SqlSession conn = MyBatisUtil.getSession();
        try {
            IVentaDAO ventaDAO = conn.getMapper(IVentaDAO.class);
            list = ventaDAO.buscarVenta("%" + busqueda + "%");
        } catch (Exception e) {
            Util.excepcion(e);
        } finally {
            conn.close();
        }
        return list;
    }

    @Override
    public List<Venta> obtenerVentas() {
        List<Venta> list = null;
        SqlSession conn = MyBatisUtil.getSession();
        try {
            IVentaDAO ventaDAO = conn.getMapper(IVentaDAO.class);
            list = ventaDAO.obtenerVentas();
        } catch (Exception e) {
            Util.excepcion(e);
        } finally {
            conn.close();
        }
        return list;
    }

    @Override
    public Venta obtenerVenta(Integer idVenta) {
        Venta venta = null;
        SqlSession conn = MyBatisUtil.getSession();
        try {
            IVentaDAO ventaDAO = conn.getMapper(IVentaDAO.class);
            venta = ventaDAO.obtenerVenta(idVenta);
        } catch (Exception e) {
            Util.excepcion(e);
        } finally {
            conn.close();
        }
        return venta;
    }

    @Override
    public int crearVenta(Venta venta) {
        int rows = 0;
        SqlSession conn = MyBatisUtil.getSession();
        try {
            IVentaDAO ventaDAO = conn.getMapper(IVentaDAO.class);
            rows = ventaDAO.crearVenta(venta);
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
    public int editarVenta(Venta venta) {
        int rows = 0;
        SqlSession conn = MyBatisUtil.getSession();
        try {
            IVentaDAO ventaDAO = conn.getMapper(IVentaDAO.class);
            rows = ventaDAO.editarVenta(venta);
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
