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
import empenofacil.model.Prenda;
import java.util.List;
import mybatis.MyBatisUtil;
import mybatis.idao.IPrendaDAO;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author lunix
 */
public class PrendaDao implements IPrendaDAO {

    @Override
    public List<Prenda> buscarPrenda(String busqueda) {
        List<Prenda> listaPrendas = null;
        SqlSession conn = MyBatisUtil.getSession();
        try {
            IPrendaDAO prendaDAO = conn.getMapper(IPrendaDAO.class);
            listaPrendas = prendaDAO.buscarPrenda("%" + busqueda + "%");

        } catch (Exception e) {
            Util.excepcion(e);
        } finally {
            conn.close();
        }
        return listaPrendas;
    }

    @Override
    public List<Prenda> obtenerPrendas() {
        List<Prenda> list = null;
        SqlSession conn = MyBatisUtil.getSession();
        try {
            IPrendaDAO prendaDAO = conn.getMapper(IPrendaDAO.class);
            list = prendaDAO.obtenerPrendas();
        } catch (Exception e) {
            Util.excepcion(e);
        } finally {
            conn.close();
        }
        return list;
    }

    @Override
    public Prenda obtenerPrenda(Integer idPrenda) {
        Prenda prenda = null;
        SqlSession conn = MyBatisUtil.getSession();
        try {
            IPrendaDAO prendaDAO = conn.getMapper(PrendaDao.class);
            prenda = prendaDAO.obtenerPrenda(idPrenda);
        } catch (Exception e) {
            Util.excepcion(e);
        } finally {
            conn.close();
        }
        return prenda;
    }

    @Override
    public int crearPrenda(Prenda prenda) {
        int rows = 0;
        SqlSession conn = MyBatisUtil.getSession();
        try {
            IPrendaDAO prendaDAO = conn.getMapper(IPrendaDAO.class);
            rows = prendaDAO.crearPrenda(prenda);
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
    public int editarPrenda(Prenda prenda) {
        int rows = 0;
        SqlSession conn = MyBatisUtil.getSession();
        try {
            IPrendaDAO prendaDAO  = conn.getMapper(IPrendaDAO.class);
            rows = prendaDAO.editarPrenda(prenda);
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
