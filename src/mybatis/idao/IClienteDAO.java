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
package mybatis.idao;

import empenofacil.model.Cliente;
import java.util.List;

/**
 *
 * @author lunix
 */
public interface IClienteDAO {
    public List<Cliente> buscarClientes(String busqueda);
    public List<Cliente> obtenerClientes();
    public Cliente obtenerCliente(Integer idCliente);
    public int crearCliente(Cliente cliente);
    public int editarCliente(Cliente cliente);
    public int editarListaNegraCliente(Cliente cliente);
}
