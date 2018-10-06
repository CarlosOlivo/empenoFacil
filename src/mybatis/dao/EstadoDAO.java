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
import empenofacil.model.Estado;
import java.util.List;
import mybatis.MyBatisUtil;
import mybatis.idao.IEstadoDAO;
import org.apache.ibatis.session.SqlSession;


public class EstadoDAO implements IEstadoDAO {

    @Override
    public List<Estado> obtenerEstados() {
        List<Estado> list = null;
        SqlSession conn = MyBatisUtil.getSession();
        try {
            IEstadoDAO estadoDAO = conn.getMapper(IEstadoDAO.class);
            list = estadoDAO.obtenerEstados();
        } catch (Exception e) {
            Util.excepcion(e);
        } finally {
            conn.close();
        }
        return list;
    }
    
    @Override
    public Estado obtenerEstado(Integer idEstado) {
        Estado estado = null;
        SqlSession conn = MyBatisUtil.getSession();
        try {
            IEstadoDAO estadoDAO = conn.getMapper(IEstadoDAO.class);
            estado = estadoDAO.obtenerEstado(idEstado);
        } catch (Exception e) {
            Util.excepcion(e);
        } finally {
            conn.close();
        }
        return estado;
    }
}