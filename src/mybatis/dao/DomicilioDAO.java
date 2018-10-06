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
import empenofacil.model.Domicilio;
import mybatis.MyBatisUtil;
import mybatis.idao.IDomicilioDAO;
import org.apache.ibatis.session.SqlSession;


public class DomicilioDAO implements IDomicilioDAO {

    @Override
    public Domicilio obtenerDomicilio(Integer idDomicilio) {
        Domicilio domicilio = null;
        SqlSession conn = MyBatisUtil.getSession();
        try {
            IDomicilioDAO domicilioDAO = conn.getMapper(IDomicilioDAO.class);
            domicilio = domicilioDAO.obtenerDomicilio(idDomicilio);
        } catch (Exception e) {
            Util.excepcion(e);
        } finally {
            conn.close();
        }
        return domicilio;
    }

    @Override
    public int crearDomicilio(Domicilio domicilio) {
        int rows = 0;
        SqlSession conn = MyBatisUtil.getSession();
        try {
            IDomicilioDAO domicilioDAO = conn.getMapper(IDomicilioDAO.class);
            rows = domicilioDAO.crearDomicilio(domicilio);
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
    public int editarDomicilio(Domicilio domicilio) {
        int rows = 0;
        SqlSession conn = MyBatisUtil.getSession();
        try {
            IDomicilioDAO domicilioDAO = conn.getMapper(IDomicilioDAO.class);
            rows = domicilioDAO.editarDomicilio(domicilio);
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