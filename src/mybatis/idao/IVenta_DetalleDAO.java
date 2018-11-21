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

import empenofacil.model.Venta_Detalle;
import java.util.List;

/**
 *
 * @author lunix
 */
public interface IVenta_DetalleDAO {

    public List<Venta_Detalle> buscarVentaDetalle(String busqueda);

    public List<Venta_Detalle> obtenerVentasDetalle();

    public Venta_Detalle obtenerVentaDetalle(Integer idVenta);

    public int crearVentaDetalle(Venta_Detalle venta_Detalle);

    public int editarVentaDetalle(Venta_Detalle venta_Detalle);
}
