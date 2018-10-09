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
public class TipoFoto {
    private Integer idTipoFoto;
    private String tipoFoto;

    public TipoFoto() {}

    public TipoFoto(Integer idTipoFoto, String tipoFoto) {
        this.idTipoFoto = idTipoFoto;
        this.tipoFoto = tipoFoto;
    }

    public Integer getIdTipoFoto() {
        return idTipoFoto;
    }

    public void setIdTipoFoto(Integer idTipoFoto) {
        this.idTipoFoto = idTipoFoto;
    }

    public String getTipoFoto() {
        return tipoFoto;
    }

    public void setTipoFoto(String tipoFoto) {
        this.tipoFoto = tipoFoto;
    }

    @Override
    public String toString() {
        return getTipoFoto();
    }
}