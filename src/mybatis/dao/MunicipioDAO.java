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
import empenofacil.model.Municipio;
import java.util.List;
import mybatis.MyBatisUtil;
import mybatis.idao.IMunicipioDAO;
import org.apache.ibatis.session.SqlSession;


public class MunicipioDAO implements IMunicipioDAO {

    @Override
    public List<Municipio> obtenerMunicipiosPorEstado(Integer idEstado) {
        List<Municipio> list = null;
        SqlSession conn = MyBatisUtil.getSession();
        try {
            IMunicipioDAO municipioDAO = conn.getMapper(IMunicipioDAO.class);
            list = municipioDAO.obtenerMunicipiosPorEstado(idEstado);
        } catch (Exception e) {
            Util.excepcion(e);
        } finally {
            conn.close();
        }
        return list;
    }
    
    @Override
    public Municipio obtenerMunicipio(Integer idMunicipio) {
        Municipio municipio = null;
        SqlSession conn = MyBatisUtil.getSession();
        try {
            IMunicipioDAO municipioDAO = conn.getMapper(IMunicipioDAO.class);
            municipio = municipioDAO.obtenerMunicipio(idMunicipio);
        } catch (Exception e) {
            Util.excepcion(e);
        } finally {
            conn.close();
        }
        return municipio;
    }
}