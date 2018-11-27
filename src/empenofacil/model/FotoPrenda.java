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

/**
 *
 * @author lunix
 */
public class FotoPrenda {
    private Integer idFotoPrenda;
    private Integer idPrenda;
    private Object foto;

    public FotoPrenda() {
    }

    public FotoPrenda(Integer idFotoPrenda, Integer idPrenda, Object foto) {
        this.idFotoPrenda = idFotoPrenda;
        this.idPrenda = idPrenda;
        this.foto = foto;
    }

    public Integer getIdFotoPrenda() {
        return idFotoPrenda;
    }

    public void setIdFotoPrenda(Integer idFotoPrenda) {
        this.idFotoPrenda = idFotoPrenda;
    }

    public Integer getIdPrenda() {
        return idPrenda;
    }

    public void setIdPrenda(Integer idPrenda) {
        this.idPrenda = idPrenda;
    }

    public Object getFoto() {
        return foto;
    }

    public void setFoto(Object foto) {
        this.foto = foto;
    }   
}
