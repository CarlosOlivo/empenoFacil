/*
 * Copyright (C) 2018 Carlos
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

import java.util.Date;

/**
 *
 * @author Carlos
 */
public final class Pago {
    private Integer idPago;
    private Integer folio;
    private Integer numPeriodo;
    private Integer idSucursal;
    private Integer idEmpleado;
    private Integer idTipoPago;
    private Date fechaHora;

    public Pago() {
    }

    public Pago(Integer idPago, Integer folio, Integer numPeriodo, Integer idSucursal, Integer idEmpleado, Integer idTipoPago, Date fechaHora) {
        this.idPago = idPago;
        this.folio = folio;
        this.numPeriodo = numPeriodo;
        this.idSucursal = idSucursal;
        this.idEmpleado = idEmpleado;
        this.idTipoPago = idTipoPago;
        setFechaHora(fechaHora);
    }
    
    public enum TIPO_PAGO {
        INVALIDO, FINIQUITO, REFRENDO
    }

    public Integer getIdPago() {
        return idPago;
    }

    public void setIdPago(Integer idPago) {
        this.idPago = idPago;
    }

    public Integer getFolio() {
        return folio;
    }

    public void setFolio(Integer folio) {
        this.folio = folio;
    }

    public Integer getNumPeriodo() {
        return numPeriodo;
    }

    public void setNumPeriodo(Integer numPeriodo) {
        this.numPeriodo = numPeriodo;
    }

    public Integer getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Integer idSucursal) {
        this.idSucursal = idSucursal;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }
    
    public Integer getIdTipoPago() {
        return idTipoPago;
    }

    public void setIdTipoPago(Integer idTipoPago) {
        this.idTipoPago = idTipoPago;
    }

    public Date getFechaHora() {
        return new Date(fechaHora.getTime());
    }

    public void setFechaHora(Date fechaHora) {
        if(fechaHora != null) {
            this.fechaHora = new Date(fechaHora.getTime());
        } else {
            this.fechaHora = new Date();
        }
    }
}
