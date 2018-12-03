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
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author Carlos
 */
public final class Periodo {
    private Integer folio;
    private final IntegerProperty numPeriodo;
    private final DoubleProperty prestamo;
    private final DoubleProperty interes;
    private final DoubleProperty iva;
    private final DoubleProperty refrendo;
    private final DoubleProperty finiquito;
    private Date fechaInicioPeriodo;
    private Date fechaFinPeriodo;
    
    public Periodo() {
        numPeriodo = new SimpleIntegerProperty();
        prestamo = new SimpleDoubleProperty();
        interes = new SimpleDoubleProperty();
        iva = new SimpleDoubleProperty();
        refrendo = new SimpleDoubleProperty();
        finiquito = new SimpleDoubleProperty();
    }

    public Periodo(Integer folio, Integer numPeriodo, Double prestamo, Double interes, Double iva, Double refrendo, Double finiquito, Date fechaInicioPeriodo, Date fechaFinPeriodo) {
        this.folio = folio;
        this.numPeriodo = new SimpleIntegerProperty(numPeriodo);
        this.prestamo = new SimpleDoubleProperty(prestamo);
        this.interes = new SimpleDoubleProperty(interes);
        this.iva = new SimpleDoubleProperty(iva);
        this.refrendo = new SimpleDoubleProperty(refrendo);
        this.finiquito = new SimpleDoubleProperty(finiquito);
        setFechaInicioPeriodo(fechaInicioPeriodo);
        setFechaFinPeriodo(fechaFinPeriodo);
    }

    public Integer getFolio() {
        return folio;
    }

    public void setFolio(Integer folio) {
        this.folio = folio;
    }
    
    public IntegerProperty numPeriodoProperty() {
        return numPeriodo;
    }
    
    public Integer getNumPeriodo() {
        return numPeriodo.get();
    }
    
    public void setNumPeriodo(Integer numPeriodo) {
        this.numPeriodo.set(numPeriodo);
    }
    
    public DoubleProperty prestamoProperty() {
        return prestamo;
    }
    
    public Double getPrestamo() {
        return prestamo.get();
    }
    
    public void setPrestamo(Double prestamo) {
        this.prestamo.set(prestamo);
    }
    
    public DoubleProperty interesProperty() {
        return interes;
    }
    
    public Double getInteres() {
        return interes.get();
    }
    
    public void setInteres(Double interes) {
        this.interes.set(interes);
    }
    
    public DoubleProperty ivaProperty() {
        return iva;
    }
    
    public Double getIva() {
        return iva.get();
    }
    
    public void setIva(Double iva) {
        this.iva.set(iva);
    }
    
    public DoubleProperty refrendoProperty() {
        return refrendo;
    }
    
    public Double getRefrendo() {
        return refrendo.get();
    }
    
    public void setRefrendo(Double refrendo) {
        this.refrendo.set(refrendo);
    }
    
    public DoubleProperty finiquitoProperty() {
        return finiquito;
    }
    
    public Double getFiniquito() {
        return finiquito.get();
    }
    
    public void setFiniquito(Double finiquito) {
        this.finiquito.set(finiquito);
    }

    public Date getFechaInicioPeriodo() {
        return new Date(fechaInicioPeriodo.getTime());
    }

    public void setFechaInicioPeriodo(Date fechaInicioPeriodo) {
        if(fechaInicioPeriodo != null) {
            this.fechaInicioPeriodo = new Date(fechaInicioPeriodo.getTime());
        } else {
            this.fechaInicioPeriodo = new Date();
        }
    }

    public Date getFechaFinPeriodo() {
        return new Date(fechaFinPeriodo.getTime());
    }

    public void setFechaFinPeriodo(Date fechaFinPeriodo) {
        if(fechaFinPeriodo != null) {
            this.fechaFinPeriodo = new Date(fechaFinPeriodo.getTime());
        } else {
            this.fechaFinPeriodo = new Date();
        }
    }
}