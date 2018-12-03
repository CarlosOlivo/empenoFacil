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

import java.util.Objects;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
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
    private final StringProperty nombre;
    private final DoubleProperty tamanio;
    private final DoubleProperty precio;
    private final DoubleProperty peso;
    private final StringProperty descripcion;

    public Articulo() {
        nombre = new SimpleStringProperty();
        descripcion = new SimpleStringProperty();
        tamanio = new SimpleDoubleProperty();
        precio = new SimpleDoubleProperty();
        peso = new SimpleDoubleProperty();
    }
    
    public enum ESTADO_ARTICULO {
        INVALIDO, DISPONIBLE, VENDIDO   
    }

    public Articulo(Integer idArticulo, Integer idCategoriaPrenda, Integer idTipoPrenda, Integer idEstadoArticulo, String nombre, Double tamanio, Double precio, Double peso, String descripcion) {
        this.idArticulo = idArticulo;
        this.idCategoriaPrenda = idCategoriaPrenda;
        this.idTipoPrenda = idTipoPrenda;
        this.idEstadoArticulo = idEstadoArticulo;
        this.nombre = new SimpleStringProperty(nombre);
        this.tamanio = new SimpleDoubleProperty(tamanio);
        this.precio = new SimpleDoubleProperty(precio);
        this.peso = new SimpleDoubleProperty(peso);
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

    public StringProperty getNombreProperty() {
        return nombre;
    }

    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public Double getTamanio() {
        return tamanio.get();
    }

    public DoubleProperty getTamanioProperty() {
        return tamanio;
    }

    public void setTamanio(Double tamanio) {
        this.tamanio.set(tamanio);
    }

    public Double getPrecio() {
        return precio.get();
    }

    public DoubleProperty getPrecioProperty() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio.set(precio);
    }

    public Double getPeso() {
        return peso.get();
    }
    public DoubleProperty getPesoProperty() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso.set(peso);
    }
    
    public String getDescripcion() {
        return descripcion.get();
    }
    
    public StringProperty getDescripcionProperty() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion.set(descripcion);
    }
    
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Articulo other = (Articulo) obj;
        return Objects.equals(this.idArticulo, other.idArticulo);
    }
}
