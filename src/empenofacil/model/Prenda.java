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

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author lunix
 */
public class Prenda {

    private Integer idPrenda;
    private Integer idCategoriaPrenda;
    private Integer idTipoPrenda;
    private final StringProperty nombre;
    private final DoubleProperty tamanio;
    private final DoubleProperty precio;
    private final DoubleProperty peso;
    private final StringProperty descripcion;
    
    public Prenda() {
        this.nombre = new SimpleStringProperty();
        this.tamanio = new SimpleDoubleProperty();
        this.precio = new SimpleDoubleProperty();
        this.peso = new SimpleDoubleProperty();
        this.descripcion = new SimpleStringProperty();
    }
    
    public Prenda(Integer idPrenda, Integer idCategoriaPrenda, Integer idTipoPrenda, String nombre, Double tamanio, Double precio, Double peso, String descripcion) {
        this.idPrenda = idPrenda;
        this.idCategoriaPrenda = idCategoriaPrenda;
        this.idTipoPrenda = idTipoPrenda;
        this.nombre = new SimpleStringProperty(nombre);
        this.tamanio = new SimpleDoubleProperty(tamanio);
        this.precio = new SimpleDoubleProperty(precio);
        this.peso = new SimpleDoubleProperty(peso);
        this.descripcion = new SimpleStringProperty(descripcion);
    }
    
    public Integer getIdPrenda() {
        return idPrenda;
    }
    
    public void setIdPrenda(Integer idPrenda) {
        this.idPrenda = idPrenda;
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
    
    public StringProperty getNombreProperty() {
        return nombre;
    }
    
    public String getNombre() {
        return nombre.get();
    }
    
    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }
    
    public DoubleProperty getTamanioProperty() {
        return tamanio;
    }
    
    public Double getTamanio() {
        return tamanio.getValue();
    }
    
    public void setTamanio(Double tamanio) {
        this.tamanio.set(tamanio);
    }
    
    public DoubleProperty getPrecioProperty() {
        return precio;
    }
    
    public Double getPrecio() {
        return precio.get();
    }
    
    public void setPrecio(Double precio) {
        this.precio.set(precio);
    }
    
    public DoubleProperty getPesoProperty() {
        return peso;
    }
    
    public Double getPeso() {
        return peso.get();
    }
    
    public void setPeso(Double peso) {
        this.peso.set(peso);
    }
    
    public StringProperty getDescripcionProperty() {
        return descripcion;
    }
    
    public String getDescripcion() {
        return descripcion.get();
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion.set(descripcion);
    }    
}
