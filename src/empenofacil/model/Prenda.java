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

/**
 *
 * @author lunix
 */
public class Prenda {

    private Integer idPrenda;
    private Integer idCategoriaprenda;
    private Integer idTipoPrenda;
    private SimpleStringProperty nombre;
    private double tamanio;
    private double precio;
    private double peso;
    private SimpleStringProperty descripcion;
    
    public Prenda() {
        this.nombre = new SimpleStringProperty();
        this.descripcion = new SimpleStringProperty();
    }
    
    public Prenda(Integer idPrenda, Integer idCategoriaprenda, Integer idTipoPrenda, String nombre, double tamanio, double precio, double peso, String descripcion) {
        this.idPrenda = idPrenda;
        this.idCategoriaprenda = idCategoriaprenda;
        this.idTipoPrenda = idTipoPrenda;
        this.nombre = new SimpleStringProperty(nombre);
        this.tamanio = tamanio;
        this.precio = precio;
        this.peso = peso;
        this.descripcion = new SimpleStringProperty(descripcion);
    }
    
    public Integer getIdPrenda() {
        return idPrenda;
    }
    
    public void setIdPrenda(Integer idPrenda) {
        this.idPrenda = idPrenda;
    }
    
    public Integer getIdCategoriaprenda() {
        return idCategoriaprenda;
    }
    
    public void setIdCategoriaprenda(Integer idCategoriaprenda) {
        this.idCategoriaprenda = idCategoriaprenda;
    }
    
    public Integer getIdTipoPrenda() {
        return idTipoPrenda;
    }
    
    public void setIdTipoPrenda(Integer idTipoPrenda) {
        this.idTipoPrenda = idTipoPrenda;
    }
    
    public SimpleStringProperty getNombre() {
        return nombre;
    }
    
    public void setNombre(SimpleStringProperty nombre) {
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
    
    public SimpleStringProperty getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(SimpleStringProperty descripcion) {
        this.descripcion = descripcion;
    }    
}
