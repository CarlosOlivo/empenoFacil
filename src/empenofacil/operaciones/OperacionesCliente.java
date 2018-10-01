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
package empenofacil.operaciones;

import empenofacil.model.Cliente;
import java.util.List;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author lunix
 */
public class OperacionesCliente {
    
    public void getAllClientes(){
        List<Cliente> list = null;
        SqlSession conn = MyBatisUtil.getSession();
        if(conn != null){
            try {
                list = conn.selectList("Cliente.getAllClientes");                
            } catch (Exception e) {
                e.printStackTrace();
            }finally{
                conn.close();
            }
        }
        if(list != null){
            this.imprimirClientes(list);
        }else{
            System.out.println("Lista no inicializada........................");
        }
    }
    
    private void imprimirClientes(List<Cliente> clientes){
        for(Cliente cliente : clientes){
            System.out.println("ID del Cliente: "+cliente.getIdcliente()+" Nombre(s): "
                    +cliente.getNombre()+" Apellidos: "+cliente.getApellidoPaterno()
                    +" Fecha de Nacimiento: "+cliente.getFechaNacimiento());
        }
    }
    
    public void getClienteById(Integer idCliente){
        Cliente resp = null;
        SqlSession conn = MyBatisUtil.getSession();
        if(conn != null){
            try {
                resp = conn.selectOne("Cliente.getClienteById", idCliente);
            } catch (Exception e) {
                e.printStackTrace();
            }finally{
                conn.close();
            }
            if(resp != null){
                System.out.println("ID del Cliente: "+resp.getIdcliente()+" Nombre(s): "
                    +resp.getNombre()+" Apellidos: "+resp.getApellidoPaterno()
                    +" Fecha de Nacimiento: "+resp.getFechaNacimiento());
            }else{
                System.out.println("Registro no encontrado.....................");
            }
        }           
    }
    
    public void guardarCliente(Cliente clientes){
        SqlSession conn = MyBatisUtil.getSession();
        try {
            int result = conn.insert("Cliente.registrarCliente", clientes);
            conn.commit();
            if(result > 0){
                System.out.println("Cliente guardado...............");
            }else{
                System.out.println("Cliente no registrado..........");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            conn.close();
        }
    }
    
    public void actualizarCliente(Cliente cliente){
        SqlSession conn = MyBatisUtil.getSession();
        try {
            int result = conn.update("Cliente.actualizarCliente", cliente);
            conn.commit();
            if(result > 0){
                System.out.println("cliente actualizado......................");
            }else{
                System.out.println("Cliente no actualizado...................");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            conn.close();
        }
    }
    
    public void eliminarCliente(Integer idCliente){
        SqlSession conn = MyBatisUtil.getSession();
        try{
            int result = conn.delete("Cliente.eliminarCliente", idCliente);
            conn.commit();
            if(result > 0){
                System.out.println("Cliente eliminad0...............");
            }else{
                System.out.println("Cliente no eliminado............");
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            conn.close();
        }
    }
    
}
