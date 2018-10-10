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
public class TipoPrenda {

    private Integer idTipoPrenda;
    private SimpleStringProperty tipoPrenda;

    public TipoPrenda() {
        this.tipoPrenda = new SimpleStringProperty();
    }

    public TipoPrenda(Integer idTipoPrenda, String tipoPrenda) {
        this.idTipoPrenda = idTipoPrenda;
        this.tipoPrenda = new SimpleStringProperty(tipoPrenda);
    }

    public Integer getIdTipoPrenda() {
        return idTipoPrenda;
    }

    public void setIdTipoPrenda(Integer idTipoPrenda) {
        this.idTipoPrenda = idTipoPrenda;
    }
    
    public SimpleStringProperty getTipoPrendaProperty() {
        return tipoPrenda;
    }

    public String getTipoPrenda() {
        return tipoPrenda.get();
    }

    public void setTipoPrenda(String tipoPrenda) {
        this.tipoPrenda.set(tipoPrenda);
    }

    @Override
    public String toString() {
        return getTipoPrenda();
    }
}
