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
import empenofacil.model.Venta_Detalle;
import java.util.List;
import mybatis.MyBatisUtil;
import mybatis.idao.IVenta_DetalleDAO;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author lunix
 */
public class Venta_DetalleDAO implements IVenta_DetalleDAO {

    @Override
    public List<Venta_Detalle> obtenerVentasDetalle() {
        List<Venta_Detalle> list = null;
        SqlSession conn = MyBatisUtil.getSession();
        try {
            IVenta_DetalleDAO ventaDDao = conn.getMapper(IVenta_DetalleDAO.class);
            list = ventaDDao.obtenerVentasDetalle();
        } catch (Exception e) {
            Util.excepcion(e);
        } finally {
            conn.close();
        }
        return list;
    }

    @Override
    public Venta_Detalle obtenerVentaDetalle(Integer idVenta) {
        Venta_Detalle venta_Detalle = null;
        SqlSession conn = MyBatisUtil.getSession();
        try {
            IVenta_DetalleDAO venta_DetalleDAO = conn.getMapper(IVenta_DetalleDAO.class);
            venta_Detalle = venta_DetalleDAO.obtenerVentaDetalle(idVenta);
        } catch (Exception e) {
            Util.excepcion(e);
        } finally {
            conn.close();
        }
        return venta_Detalle;
    }

    @Override
    public int crearVentaDetalle(Venta_Detalle venta_Detalle) {
        int rows = 0;
        SqlSession conn = MyBatisUtil.getSession();
        try {
            IVenta_DetalleDAO venta_DetalleDAO = conn.getMapper(IVenta_DetalleDAO.class);
            rows = venta_DetalleDAO.crearVentaDetalle(venta_Detalle);
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
    public int editarVentaDetalle(Venta_Detalle venta_Detalle) {
        int rows = 0;
        SqlSession conn = MyBatisUtil.getSession();
        try {
            IVenta_DetalleDAO venta_DetalleDAO = conn.getMapper(IVenta_DetalleDAO.class);
            rows = venta_DetalleDAO.editarVentaDetalle(venta_Detalle);
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
    public List<Venta_Detalle> buscarVentaDetalle(String busqueda) {
        List<Venta_Detalle> list = null;
        SqlSession conn = MyBatisUtil.getSession();
        try {
            IVenta_DetalleDAO venta_DetalleDAO = conn.getMapper(IVenta_DetalleDAO.class);
            list = venta_DetalleDAO.buscarVentaDetalle("%" + busqueda + "%");
        } catch (Exception e) {
            Util.excepcion(e);
        } finally {
            conn.close();
        }
        return list;
    }
}
