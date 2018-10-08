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

import com.sun.javafx.util.Utils;
import empenofacil.Util;
import empenofacil.model.FotografiaPrenda;
import mybatis.MyBatisUtil;
import mybatis.idao.IFotoPrendaDAO;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author lunix
 */
public class FotoPrendaDAO implements IFotoPrendaDAO{

    @Override
    public FotografiaPrenda obtenerFotoPrenda(Integer idFotoPrenda) {
        FotografiaPrenda fotoPrenda = null;
        SqlSession conn = MyBatisUtil.getSession();
        try {
            IFotoPrendaDAO prendaDAO = conn.getMapper(IFotoPrendaDAO.class);
            fotoPrenda = prendaDAO.obtenerFotoPrenda(idFotoPrenda);
        } catch (Exception e) {
            Util.excepcion(e);
        } finally{
            conn.close();
        }
        return fotoPrenda;
    }

    @Override
    public int tomarFotografia(FotografiaPrenda fotoPrenda) {
        int rows = 0;
        SqlSession conn = MyBatisUtil.getSession();
        try {
            IFotoPrendaDAO prendaDAO = conn.getMapper(IFotoPrendaDAO.class);
            rows = prendaDAO.tomarFotografia(fotoPrenda);
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
