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

import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author lunix
 */
public final class Contrato {
    private Integer folio;
    private Integer idCliente;
    private Integer idEstadoContrato;
    private Integer idSucursal;
    private Integer idEmpleado;
    private final IntegerProperty numBolsa;
    private Date fechaInicioContrato;
    private Date fechaFinContrato;
    private final StringProperty cotitular;
    private final DoubleProperty totalAvaluo;
    private final DoubleProperty totalPrestamo;

    public Contrato() {
        numBolsa = new SimpleIntegerProperty();
        cotitular = new SimpleStringProperty();
        totalAvaluo = new SimpleDoubleProperty();
        totalPrestamo = new SimpleDoubleProperty();
    }

    public Contrato(Integer folio, Integer idCliente, Integer idEstadoContrato, Integer idSucursal, Integer idEmpleado, Integer numBolsa, Date fechaInicioContrato, Date fechaFinContrato, String cotitular, Double totalAvaluo, Double totalPrestamo) {
        this.folio = folio;
        this.idCliente = idCliente;
        this.idEstadoContrato = idEstadoContrato;
        this.idSucursal = idSucursal;
        this.idEmpleado = idEmpleado;
        this.numBolsa = new SimpleIntegerProperty(numBolsa);
        setFechaInicioContrato(fechaInicioContrato);
        setFechaFinContrato(fechaFinContrato);
        this.cotitular = new SimpleStringProperty(cotitular);
        this.totalAvaluo = new SimpleDoubleProperty(totalAvaluo);
        this.totalPrestamo = new SimpleDoubleProperty(totalPrestamo);
    }
    
    public enum ESTADO_CONTRATO {
        INVALIDO, ACTIVO, CANCELADO, PRORROGA, EXPIRADO, COMERCIALIZADO
    }
    
    public StringProperty folioPropertyFormato() {
        return new SimpleStringProperty("#" + getFolio());
    }

    public Integer getFolio() {
        return folio;
    }

    public void setFolio(Integer folio) {
        this.folio = folio;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }
    
    public StringProperty estadoContratoPropertyFormato() {
        return new SimpleStringProperty(Contrato.ESTADO_CONTRATO.values()[getIdEstadoContrato()].toString());
    }

    public Integer getIdEstadoContrato() {
        return idEstadoContrato;
    }

    public void setIdEstadoContrato(Integer idEstadoContrato) {
        this.idEstadoContrato = idEstadoContrato;
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

    public Integer getNumBolsa() {
        return numBolsa.get();
    }

    public void setNumBolsa(Integer numBolsa) {
        this.numBolsa.set(numBolsa);
    }
    
    public StringProperty fechaInicioContratoFormato() {
        return new SimpleStringProperty(new SimpleDateFormat("dd/MM/yyyy").format(getFechaInicioContrato()));
    }

    public Date getFechaInicioContrato() {
        return new Date(fechaInicioContrato.getTime());
    }
    
    public void setFechaInicioContrato(Date fechaInicioContrato) {
        if(fechaInicioContrato != null) {
            this.fechaInicioContrato = new Date(fechaInicioContrato.getTime());
        } else {
            this.fechaInicioContrato = new Date();
        }
    }
    
    public StringProperty fechaFinContratoFormato() {
        return new SimpleStringProperty(new SimpleDateFormat("dd/MM/yyyy").format(getFechaFinContrato()));
    }

    public Date getFechaFinContrato() {
        return new Date(fechaFinContrato.getTime());
    }

    public void setFechaFinContrato(Date fechaFinContrato) {
        if(fechaFinContrato != null) {
            this.fechaFinContrato = new Date(fechaFinContrato.getTime());
        } else {
            this.fechaFinContrato = new Date();
        }
    }

    public String getCotitular() {
        return cotitular.get();
    }

    public void setCotitular(String cotitular) {
        this.cotitular.set(cotitular);
    }
    
    public StringProperty totalAvaluoPropertyFormato() {
        return new SimpleStringProperty(String.format("$%.2f", getTotalAvaluo()));
    }

    public Double getTotalAvaluo() {
        return totalAvaluo.get();
    }

    public void setTotalAvaluo(Double totalAvaluo) {
        this.totalAvaluo.set(totalAvaluo);
    }
    
    public StringProperty totalPrestamoPropertyFormato() {
        return new SimpleStringProperty(String.format("$%.2f", getTotalPrestamo()));
    }

    public Double getTotalPrestamo() {
        return totalPrestamo.get();
    }

    public void setTotalPrestamo(Double totalPrestamo) {
        this.totalPrestamo.set(totalPrestamo);
    }
}