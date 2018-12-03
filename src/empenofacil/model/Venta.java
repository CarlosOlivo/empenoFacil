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
package empenofacil.model;

import java.util.Date;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 *
 * @author lunix
 */
public final class Venta {

    private Integer idVenta;
    private Integer idEmpleado;
    private Integer idSucursal;
    private Date tiempoCreacion;

    private final DoubleProperty descuento;
    private final DoubleProperty subtotal;
    private final DoubleProperty iva;
    private final DoubleProperty total;

    public Venta() {

        descuento = new SimpleDoubleProperty();
        subtotal = new SimpleDoubleProperty();
        iva = new SimpleDoubleProperty();
        total = new SimpleDoubleProperty();
    }

    public Venta(Integer idVenta, Integer idEmpleado, Integer idSucursal, Date tiempoCreacion, double descuento, double subtotal, double iva, double total) {
        this.idVenta = idVenta;
        this.idEmpleado = idEmpleado;
        this.idSucursal = idSucursal;
        setTiempoCreacion(tiempoCreacion);

        this.descuento = new SimpleDoubleProperty(descuento);
        this.subtotal = new SimpleDoubleProperty(subtotal);
        this.iva = new SimpleDoubleProperty(iva);
        this.total = new SimpleDoubleProperty(total);
    }

    public Integer getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Integer idVenta) {
        this.idVenta = idVenta;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Integer getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Integer idSucursal) {
        this.idSucursal = idSucursal;
    }

    public Date getTiempoCreacion() {
        return new Date(tiempoCreacion.getTime());
    }

    public void setTiempoCreacion(Date tiempoCreacion) {
        if(tiempoCreacion != null) {
            this.tiempoCreacion = new Date(tiempoCreacion.getTime());
        } else {
            this.tiempoCreacion = new Date();
        }
    }

    public Double getDescuento() {
        return descuento.get();
    }

    public void setDescuento(Double descuento) {
        this.descuento.set(descuento);
    }

    public Double getSubtotal() {
        return subtotal.get();
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal.set(subtotal);
    }

    public Double getIva() {
        return iva.get();
    }

    public void setIva(Double iva) {
        this.iva.set(iva);
    }

    public Double getTotal() {
        return total.get();
    }

    public void setTotal(Double total) {
        this.total.set(total);
    }
}
