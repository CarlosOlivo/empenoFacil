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

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Carlos
 */
public class Domicilio {
    private Integer idDomicilio;
    private final StringProperty calle;
    private final StringProperty numero;
    private final StringProperty colonia;
    private final IntegerProperty codigoPostal;
    private final StringProperty localidad;
    private Integer idMunicipio;

    public Domicilio() {
        calle = new SimpleStringProperty();
        numero = new SimpleStringProperty();
        colonia = new SimpleStringProperty();
        codigoPostal = new SimpleIntegerProperty();
        localidad = new SimpleStringProperty();
    }

    public Domicilio(Integer idDomicilio, String calle, String numero, String colonia, Integer codigoPostal, String localidad, Integer idMunicipio) {
        this.idDomicilio = idDomicilio;
        this.calle = new SimpleStringProperty(calle);
        this.numero = new SimpleStringProperty(numero);
        this.colonia = new SimpleStringProperty(colonia);
        if(codigoPostal != null) {
            this.codigoPostal = new SimpleIntegerProperty(codigoPostal);
        } else {
            this.codigoPostal = new SimpleIntegerProperty();
        }
        this.localidad = new SimpleStringProperty(localidad);
        this.idMunicipio = idMunicipio;
    }

    public Integer getIdDomicilio() {
        return idDomicilio;
    }

    public void setIdDomicilio(Integer idDomicilio) {
        this.idDomicilio = idDomicilio;
    }
    
    public StringProperty getCalleProperty() {
        return calle;
    }

    public String getCalle() {
        return calle.get();
    }

    public void setCalle(String calle) {
        this.calle.set(calle);
    }

    public StringProperty getNumeroProperty() {
        return numero;
    }
    
    public String getNumero() {
        return numero.get();
    }

    public void setNumero(String numero) {
        this.numero.set(numero);
    }

    public StringProperty getColoniaProperty() {
        return colonia;
    }
    
    public String getColonia() {
        return colonia.get();
    }

    public void setColonia(String colonia) {
        this.colonia.set(colonia);
    }

    public IntegerProperty getCodigoPostalProperty() {
        return codigoPostal;
    }
    
    public Integer getCodigoPostal() {
        return codigoPostal.get();
    }

    public void setCodigoPostal(Integer codigoPostal) {
        this.codigoPostal.set(codigoPostal);
    }

    public StringProperty getLocalidadProperty() {
        return localidad;
    }
    
    public String getLocalidad() {
        return localidad.get();
    }

    public void setLocalidad(String localidad) {
        this.localidad.set(localidad);
    }

    public Integer getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(Integer idMunicipio) {
        this.idMunicipio = idMunicipio;
    }
}