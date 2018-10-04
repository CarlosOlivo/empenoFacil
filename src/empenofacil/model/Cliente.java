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

import java.io.File;
import java.util.Date;
import javafx.beans.property.StringProperty;

/**
 * Clase de la creacion de un objeo de tipo de Cliente.
 *
 * @version 1.0
 * @author lunix
 * @since 2018-09-29
 */
public class Cliente {

    private Integer idcliente;
    private Integer id_domicilio;
    private Integer idocupacion;
    private StringProperty nombre;
    private StringProperty apellidoPaterno;
    private StringProperty apellidoMaterno;
    private StringProperty telefono;
    private StringProperty celular;
    private StringProperty curp;
    private StringProperty rfc;
    private Boolean listaNegra;
    private Date fechaNacimiento;
    private File huellaCliete;

    public Cliente() {
    }

    public Cliente(Integer idcliente, Integer id_domicilio, Integer idocupacion, StringProperty nombre, StringProperty apellidoPaterno, StringProperty apellidoMaterno, StringProperty telefono, StringProperty celular, StringProperty curp, StringProperty rfc, Boolean listaNegra, Date fechaNacimiento, File huellaCliete) {
        this.idcliente = idcliente;
        this.id_domicilio = id_domicilio;
        this.idocupacion = idocupacion;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.telefono = telefono;
        this.celular = celular;
        this.curp = curp;
        this.rfc = rfc;
        this.listaNegra = listaNegra;
        this.fechaNacimiento = fechaNacimiento;
        this.huellaCliete = huellaCliete;
    }

    public Integer getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Integer idcliente) {
        this.idcliente = idcliente;
    }

    public Integer getId_domicilio() {
        return id_domicilio;
    }

    public void setId_domicilio(Integer id_domicilio) {
        this.id_domicilio = id_domicilio;
    }

    public Integer getIdocupacion() {
        return idocupacion;
    }

    public void setIdocupacion(Integer idocupacion) {
        this.idocupacion = idocupacion;
    }

    public StringProperty getNombre() {
        return nombre;
    }

    public void setNombre(StringProperty nombre) {
        this.nombre = nombre;
    }

    public StringProperty getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(StringProperty apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public StringProperty getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(StringProperty apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public StringProperty getTelefono() {
        return telefono;
    }

    public void setTelefono(StringProperty telefono) {
        this.telefono = telefono;
    }

    public StringProperty getCelular() {
        return celular;
    }

    public void setCelular(StringProperty celular) {
        this.celular = celular;
    }

    public StringProperty getCurp() {
        return curp;
    }

    public void setCurp(StringProperty curp) {
        this.curp = curp;
    }

    public StringProperty getRfc() {
        return rfc;
    }

    public void setRfc(StringProperty rfc) {
        this.rfc = rfc;
    }

    public Boolean getListaNegra() {
        return listaNegra;
    }

    public void setListaNegra(Boolean listaNegra) {
        this.listaNegra = listaNegra;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public File getHuellaCliete() {
        return huellaCliete;
    }

    public void setHuellaCliete(File huellaCliete) {
        this.huellaCliete = huellaCliete;
    }
}
