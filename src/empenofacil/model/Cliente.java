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

import java.util.Date;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Clase entidad Cliente.
 * @version 1.0
 * @author lunix
 * @since 2018-09-29
 */
public class Cliente {
    private Integer idCliente;
    private Integer idOcupacion;
    private Integer idDomicilio;
    private final SimpleStringProperty nombre;
    private final SimpleStringProperty apellidoPaterno;
    private final SimpleStringProperty apellidoMaterno;
    private final SimpleStringProperty telefono;
    private final SimpleStringProperty celular;
    private Date fechaNacimiento;
    private final SimpleStringProperty curp;
    private final SimpleStringProperty rfc;
    private Object huellaCliete;
    private Boolean listaNegra;
    
    public Cliente() {
        nombre = new SimpleStringProperty();
        apellidoPaterno = new SimpleStringProperty();
        apellidoMaterno = new SimpleStringProperty();
        telefono = new SimpleStringProperty();
        celular = new SimpleStringProperty();
        curp = new SimpleStringProperty();
        rfc = new SimpleStringProperty();
    }

    public Cliente(Integer idCliente, Integer idOcupacion, Integer idDomicilio, String nombre, String apellidoPaterno, String apellidoMaterno, String telefono, String celular, Date fechaNacimiento, String curp, String rfc, Object huellaCliete, Boolean listaNegra) {
        this.idCliente = idCliente;
        this.idOcupacion = idOcupacion;
        this.idDomicilio = idDomicilio;
        this.nombre = new SimpleStringProperty(nombre);
        this.apellidoPaterno = new SimpleStringProperty(apellidoPaterno);
        this.apellidoMaterno = new SimpleStringProperty(apellidoMaterno);
        this.telefono = new SimpleStringProperty(telefono);
        this.celular = new SimpleStringProperty(celular);
        if(fechaNacimiento != null) {
            this.fechaNacimiento = new Date(fechaNacimiento.getTime());
        } else {
            this.fechaNacimiento = new Date();
        }
        this.curp = new SimpleStringProperty(curp);
        this.rfc = new SimpleStringProperty(rfc);
        this.huellaCliete = huellaCliete;
        this.listaNegra = listaNegra;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getIdOcupacion() {
        return idOcupacion;
    }

    public void setIdOcupacion(Integer idOcupacion) {
        this.idOcupacion = idOcupacion;
    }

    public Integer getIdDomicilio() {
        return idDomicilio;
    }

    public void setIdDomicilio(Integer idDomicilio) {
        this.idDomicilio = idDomicilio;
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
    
    public StringProperty getApellidoPaternoProperty() {
        return apellidoPaterno;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno.get();
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno.set(apellidoPaterno);
    }
    
    public StringProperty getApellidoMaternoProperty() {
        return apellidoMaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno.get();
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno.set(apellidoMaterno);
    }

    public StringProperty getTelefonoProperty() {
        return telefono;
    }
    
    public String getTelefono() {
        return telefono.get();
    }

    public void setTelefono(String telefono) {
        this.telefono.set(telefono);
    }
    
    public StringProperty getCelularProperty() {
        return celular;
    }

    public String getCelular() {
        return celular.get();
    }

    public void setCelular(String celular) {
        this.celular.set(celular);
    }

    public Date getFechaNacimiento() {
        return new Date(fechaNacimiento.getTime());
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        if(fechaNacimiento != null) {
            this.fechaNacimiento = new Date(fechaNacimiento.getTime());
        } else {
            this.fechaNacimiento = new Date();
        }
    }
    
    public StringProperty getCurpProperty() {
        return curp;
    }

    public String getCurp() {
        return curp.get();
    }

    public void setCurp(String CURP) {
        this.curp.set(CURP);
    }
    
    public StringProperty getrfcProperty() {
        return rfc;
    }

    public String getRfc() {
        return rfc.get();
    }

    public void setRfc(String RFC) {
        this.rfc.set(RFC);
    }

    public Object getHuellaCliete() {
        return huellaCliete;
    }

    public void setHuellaCliete(Object huellaCliete) {
        this.huellaCliete = huellaCliete;
    }

    public Boolean getListaNegra() {
        return listaNegra;
    }

    public void setListaNegra(Boolean listaNegra) {
        this.listaNegra = listaNegra;
    }
}