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
public class Articulo {

    private Integer idArticulo;
    private Integer idCategoriaPrenda;
    private Integer idTipoPrenda;
    private Integer idEstadoArticulo;
    private StringProperty nombre;
    private double tamanio;
    private double precio;
    private double peso;
    private StringProperty descripcion;

    public Articulo() {
        nombre = new SimpleStringProperty();
        descripcion = new SimpleStringProperty();
    }

    public Articulo(Integer idArticulo, Integer idCategoriaPrenda, Integer idTipoPrenda, Integer idEstadoArticulo, String nombre, double tamanio, double precio, double peso, String descripcion) {
        this.idArticulo = idArticulo;
        this.idCategoriaPrenda = idCategoriaPrenda;
        this.idTipoPrenda = idTipoPrenda;
        this.idEstadoArticulo = idEstadoArticulo;
        this.nombre = new SimpleStringProperty(nombre);
        this.tamanio = tamanio;
        this.precio = precio;
        this.peso = peso;
        this.descripcion = new SimpleStringProperty(descripcion);
    }

    public Integer getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(Integer idArticulo) {
        this.idArticulo = idArticulo;
    }

    public Integer getIdCategoriaPrenda() {
        return idCategoriaPrenda;
    }

    public void setIdCategoriaPrenda(Integer idCategoriaPrenda) {
        this.idCategoriaPrenda = idCategoriaPrenda;
    }

    public Integer getIdTipoPrenda() {
        return idTipoPrenda;
    }

    public void setIdTipoPrenda(Integer idTipoPrenda) {
        this.idTipoPrenda = idTipoPrenda;
    }

    public Integer getIdEstadoArticulo() {
        return idEstadoArticulo;
    }

    public void setIdEstadoArticulo(Integer idEstadoArticulo) {
        this.idEstadoArticulo = idEstadoArticulo;
    }

    public StringProperty getNombre() {
        return nombre;
    }

    public void setNombre(StringProperty nombre) {
        this.nombre = nombre;
    }

    public double getTamanio() {
        return tamanio;
    }

    public void setTamanio(double tamanio) {
        this.tamanio = tamanio;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public StringProperty getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(StringProperty descripcion) {
        this.descripcion = descripcion;
    }
}
