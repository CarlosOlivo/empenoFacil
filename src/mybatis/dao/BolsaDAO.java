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
import empenofacil.model.Bolsa;
import java.util.List;
import mybatis.MyBatisUtil;
import mybatis.idao.IBolsaDAO;
import org.apache.ibatis.session.SqlSession;


public class BolsaDAO implements IBolsaDAO {

    @Override
    public List<Bolsa> obtenerBolsa(Integer folio) {
        List<Bolsa> list = null;
        SqlSession conn = MyBatisUtil.getSession();
        try {
            IBolsaDAO bolsaDAO = conn.getMapper(IBolsaDAO.class);
            list = bolsaDAO.obtenerBolsa(folio);
        } catch (Exception e) {
            Util.excepcion(e);
        } finally {
            conn.close();
        }
        return list;
    }

    @Override
    public int crearBolsa(Bolsa bolsa) {
        int rows = 0;
        SqlSession conn = MyBatisUtil.getSession();
        try {
            IBolsaDAO bolsaDAO = conn.getMapper(IBolsaDAO.class);
            rows = bolsaDAO.crearBolsa(bolsa);
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