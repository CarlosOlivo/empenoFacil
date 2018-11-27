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

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author lunix
 */
public class Venta_Detalle {

    private Integer idVenta;
    private Integer idPrenda;
    private StringProperty concepto;
    private double precio;
    private double descuento;
    private double tasaiva;
    private double iva;
    private double subtotal;
    private double total;

    public Venta_Detalle() {
        this.concepto = new SimpleStringProperty();
    }

    public Venta_Detalle(Integer idVenta, Integer idPrenda, String concepto, double precio, double descuento, double tasaiva, double iva, double subtotal, double total) {
        this.idVenta = idVenta;
        this.idPrenda = idPrenda;
        this.concepto = new SimpleStringProperty(concepto);
        this.precio = precio;
        this.descuento = descuento;
        this.tasaiva = tasaiva;
        this.iva = iva;
        this.subtotal = subtotal;
        this.total = total;
    }

    public Integer getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Integer idVenta) {
        this.idVenta = idVenta;
    }

    public Integer getIdPrenda() {
        return idPrenda;
    }

    public void setIdPrenda(Integer idPrenda) {
        this.idPrenda = idPrenda;
    }

    public String getConcepto() {
        return concepto.get();
    }

    public void setConcepto(String concepto) {
        this.concepto.set(concepto);
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public double getTasaiva() {
        return tasaiva;
    }

    public void setTasaiva(double tasaiva) {
        this.tasaiva = tasaiva;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
