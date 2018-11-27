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
import empenofacil.model.Contrato;
import java.util.List;
import mybatis.MyBatisUtil;
import mybatis.idao.IContratoDAO;
import org.apache.ibatis.session.SqlSession;


public class ContratoDAO implements IContratoDAO {

    @Override
    public List<Contrato> obtenerContratos() {
        List<Contrato> list = null;
        SqlSession conn = MyBatisUtil.getSession();
        try {
            IContratoDAO contratoDAO = conn.getMapper(IContratoDAO.class);
            list = contratoDAO.obtenerContratos();
        } catch (Exception e) {
            Util.excepcion(e);
        } finally {
            conn.close();
        }
        return list;
    }

    @Override
    public Contrato obtenerContrato(Integer folio) {
        Contrato contrato = null;
        SqlSession conn = MyBatisUtil.getSession();
        try {
            IContratoDAO contratoDAO = conn.getMapper(IContratoDAO.class);
            contrato = contratoDAO.obtenerContrato(folio);
        } catch (Exception e) {
            Util.excepcion(e);
        } finally {
            conn.close();
        }
        return contrato;
    }

    @Override
    public int crearContrato(Contrato contrato) {
        int rows = 0;
        SqlSession conn = MyBatisUtil.getSession();
        try {
            IContratoDAO contratoDAO = conn.getMapper(IContratoDAO.class);
            rows = contratoDAO.crearContrato(contrato);
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
    public int editarContrato(Contrato contrato) {
        int rows = 0;
        SqlSession conn = MyBatisUtil.getSession();
        try {
            IContratoDAO contratoDAO = conn.getMapper(IContratoDAO.class);
            rows = contratoDAO.editarContrato(contrato);
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
    public List<Contrato> buscarContratoPorNombre(String busqueda) {
        List<Contrato> list = null;
        SqlSession conn = MyBatisUtil.getSession();
        try {
            IContratoDAO contratoDAO = conn.getMapper(IContratoDAO.class);
            list = contratoDAO.buscarContratoPorNombre("%"+busqueda+"%");
        } catch (Exception e) {
            Util.excepcion(e);
        } finally {
            conn.close();
        }
        return list;
    }

    @Override
    public List<Contrato> buscarContratoPorPrenda(String busqueda) {
        List<Contrato> list = null;
        SqlSession conn = MyBatisUtil.getSession();
        try {
            IContratoDAO contratoDAO = conn.getMapper(IContratoDAO.class);
            list = contratoDAO.buscarContratoPorPrenda("%"+busqueda+"%");
        } catch (Exception e) {
            Util.excepcion(e);
        } finally {
            conn.close();
        }
        return list;
    }
}