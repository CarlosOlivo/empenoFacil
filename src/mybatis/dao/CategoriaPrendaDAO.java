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
import empenofacil.model.CategoriaPrenda;
import java.util.List;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import mybatis.idao.ICategoriaPrendaDAO;

/**
 *
 * @author lunix
 */
public class CategoriaPrendaDAO implements ICategoriaPrendaDAO{

    @Override
    public List<CategoriaPrenda> obtenerCategoriasPrenda(Integer idTipoPrenda) {
        List<CategoriaPrenda> list = null;
        SqlSession conn = MyBatisUtil.getSession();
        try {
            ICategoriaPrendaDAO categoriaPrenda = conn.getMapper(ICategoriaPrendaDAO.class);
            list = categoriaPrenda.obtenerCategoriasPrenda(idTipoPrenda);
        } catch (Exception e) {
            Util.excepcion(e);
        } finally {
            conn.close();
        }
        return list;
    }   
}