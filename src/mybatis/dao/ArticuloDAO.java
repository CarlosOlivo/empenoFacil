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
import empenofacil.model.Articulo;
import java.util.List;
import mybatis.MyBatisUtil;
import mybatis.idao.IArticuloDAO;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author lunix
 */
public class ArticuloDAO implements IArticuloDAO {

    @Override
    public List<Articulo> buscarArticulo(String articulo) {
        List<Articulo> list = null;
        SqlSession conn = MyBatisUtil.getSession();
        try {
            IArticuloDAO articuloDAO = conn.getMapper(IArticuloDAO.class);
            list = articuloDAO.buscarArticulo("%" + articulo + "%");
        } catch (Exception e) {
            Util.excepcion(e);
        }
        return list;
    }

    @Override
    public List<Articulo> obtenerArticulos() {
        List<Articulo> list = null;
        SqlSession conn = MyBatisUtil.getSession();
        try {
            IArticuloDAO articuloDAO = conn.getMapper(IArticuloDAO.class);
            list = articuloDAO.obtenerArticulos();
        } catch (Exception e) {
            Util.excepcion(e);
        }
        return list;
    }

    @Override
    public Articulo obtenerArticulo(Integer idArticulo) {
        Articulo articulo = null;
        SqlSession conn = MyBatisUtil.getSession();
        try {
            IArticuloDAO articuloDAO = conn.getMapper(IArticuloDAO.class);
            articulo = articuloDAO.obtenerArticulo(idArticulo);
        } catch (Exception e) {
            Util.excepcion(e);
        } finally {
            conn.close();
        }
        return articulo;
    }

    @Override
    public int crearArticulo(Articulo articulo) {
        int rows = 0;
        SqlSession conn = MyBatisUtil.getSession();
        try {
            IArticuloDAO articuloDAO = conn.getMapper(IArticuloDAO.class);
            rows = articuloDAO.crearArticulo(articulo);
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
    public int editarArticulo(Articulo articulo) {
        int rows = 0;
        SqlSession conn = MyBatisUtil.getSession();
        try {
            IArticuloDAO articuloDAO = conn.getMapper(IArticuloDAO.class);
            rows = articuloDAO.editarArticulo(articulo);
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
