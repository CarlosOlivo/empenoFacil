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
import empenofacil.model.FotografiaCliente;
import mybatis.idao.IFotoClienteDAO;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author lunix
 */
public class FotoClienteDAO implements IFotoClienteDAO{

    @Override
    public FotografiaCliente obtenerFotografiaCliente(Integer idFotoCliente) {
        FotografiaCliente fotografiaCliente = null;
        SqlSession conn = MyBatisUtil.getSession();
        try {
            IFotoClienteDAO fotografiaDAO = conn.getMapper(IFotoClienteDAO.class);
            fotografiaCliente = fotografiaDAO.obtenerFotografiaCliente(idFotoCliente);
        } catch (Exception e) {
            Util.excepcion(e);
        } finally {
            conn.close();
        }
        return fotografiaCliente;
    }

    @Override
    public int tomarFoto(FotografiaCliente fotoCliente) {
        int rows = 0;
        SqlSession conn = MyBatisUtil.getSession();
        try {
            IFotoClienteDAO fotograClienteDAO = conn.getMapper(IFotoClienteDAO.class);
            rows = fotograClienteDAO.tomarFoto(fotoCliente);
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
