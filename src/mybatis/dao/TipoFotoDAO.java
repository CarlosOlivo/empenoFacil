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
import empenofacil.model.TipoFoto;
import java.util.List;
import mybatis.MyBatisUtil;
import mybatis.idao.ITipoDAO;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author lunix
 */
public class TipoFotoDAO implements ITipoDAO {

    @Override
    public List<TipoFoto> obtenerTiposFoto() {
        List<TipoFoto> list = null;
        SqlSession conn = MyBatisUtil.getSession();
        try {
            ITipoDAO tipofotoDAo = conn.getMapper(ITipoDAO.class);
            list = tipofotoDAo.obtenerTiposFoto();
        } catch (Exception e) {
            Util.excepcion(e);
        } finally {
            conn.close();
        }
        return list;
    }

    @Override
    public TipoFoto obtenerTipoFoto(Integer idTipoFoto) {
        TipoFoto tipoFoto = null;
        SqlSession conn = MyBatisUtil.getSession();   
        try {
            ITipoDAO tipoDao = conn.getMapper(ITipoDAO.class);
            tipoFoto = tipoDao.obtenerTipoFoto(idTipoFoto);
        } catch (Exception e) {
            Util.excepcion(e);
        } finally {
            conn.close();
        }
        return tipoFoto;
    }
}
