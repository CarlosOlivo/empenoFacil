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
public class CategoriaPrenda {
    private Integer idCategoriaPrenda;
    private Integer idTipoPrenda;
    private SimpleStringProperty categoriaPrenda;
    
    public CategoriaPrenda(){
        this.categoriaPrenda = new SimpleStringProperty();
    }

    public CategoriaPrenda(Integer idCategoriaPrenda, Integer idTipoPrenda, String categoriaPrenda) {
        this.idCategoriaPrenda = idCategoriaPrenda;
        this.idTipoPrenda = idTipoPrenda;
        this.categoriaPrenda = new SimpleStringProperty(categoriaPrenda);
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
    
    public SimpleStringProperty getCategoriaPrendaProperty() {
        return categoriaPrenda;
    }
    
    public String getCategoriaPrenda() {
        return categoriaPrenda.get();
    }

    public void setCategoriaPrenda(String categoriaPrenda) {
        this.categoriaPrenda.set(categoriaPrenda);
    }

    @Override
    public String toString() {
        return getCategoriaPrenda();
    }
}
