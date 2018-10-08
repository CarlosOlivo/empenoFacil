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
public class FotografiaCliente {

    private Integer idFotoCliente;
    private Integer idTipoFoto;
    private Integer idCliente;
    private Object foto;

    public FotografiaCliente() {
    }

    public FotografiaCliente(Integer idFotoCliente, Integer idTipoFoto, Integer idCliente, Object foto) {
        this.idFotoCliente = idFotoCliente;
        this.idTipoFoto = idTipoFoto;
        this.idCliente = idCliente;
        this.foto = foto;
    }

    public Integer getIdFotoCliente() {
        return idFotoCliente;
    }

    public void setIdFotoCliente(Integer idFotoCliente) {
        this.idFotoCliente = idFotoCliente;
    }

    public Integer getIdTipoFoto() {
        return idTipoFoto;
    }

    public void setIdTipoFoto(Integer idTipoFoto) {
        this.idTipoFoto = idTipoFoto;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Object getFoto() {
        return foto;
    }

    public void setFoto(Object foto) {
        this.foto = foto;
    }

}
