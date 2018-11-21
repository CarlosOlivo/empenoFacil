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
import empenofacil.model.Parametros;
import mybatis.MyBatisUtil;
import mybatis.idao.IParametrosDAO;
import org.apache.ibatis.session.SqlSession;


public class ParametrosDAO implements IParametrosDAO {

    @Override
    public Integer obtenerParametroEntero(String parametro) {
        Integer param = null;
        SqlSession conn = MyBatisUtil.getSession();
        try {
            IParametrosDAO parametrosDAO = conn.getMapper(IParametrosDAO.class);
            param = parametrosDAO.obtenerParametroEntero(parametro);
        } catch (Exception e) {
            Util.excepcion(e);
        } finally {
            conn.close();
        }
        return param;
    }

    @Override
    public Double obtenerParametroDoble(String parametro) {
        Double param = null;
        SqlSession conn = MyBatisUtil.getSession();
        try {
            IParametrosDAO parametrosDAO = conn.getMapper(IParametrosDAO.class);
            param = parametrosDAO.obtenerParametroDoble(parametro);
        } catch (Exception e) {
            Util.excepcion(e);
        } finally {
            conn.close();
        }
        return param;
    }

    @Override
    public Parametros obtenerParametros(String folio) {
        Parametros param = null;
        SqlSession conn = MyBatisUtil.getSession();
        try {
            IParametrosDAO parametrosDAO = conn.getMapper(IParametrosDAO.class);
            param = parametrosDAO.obtenerParametros(folio);
        } catch (Exception e) {
            Util.excepcion(e);
        } finally {
            conn.close();
        }
        return param;
    }

    @Override
    public int crearParametros(Integer folio) {
        int rows = 0;
        SqlSession conn = MyBatisUtil.getSession();
        try {
            IParametrosDAO parametrosDAO = conn.getMapper(IParametrosDAO.class);
            rows = parametrosDAO.crearParametros(folio);
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
