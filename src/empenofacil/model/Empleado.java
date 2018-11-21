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
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public final class Empleado {
    private Integer idEmpleado;
    private Integer idRol;
    private Integer idDomicilio;
    private final StringProperty usuario;
    private final StringProperty contrasenia;
    private final StringProperty nombre;
    private final StringProperty apellidoPaterno;
    private final StringProperty apellidoMaterno;
    private final StringProperty telefono;
    private final StringProperty celular;
    private final StringProperty curp;
    private final StringProperty rfc;
    private Date fechaNacimiento;
    private Object huellaEmpleado;

    public Empleado() {
        usuario = new SimpleStringProperty();
        contrasenia = new SimpleStringProperty();
        nombre = new SimpleStringProperty();
        apellidoPaterno = new SimpleStringProperty();
        apellidoMaterno = new SimpleStringProperty();
        telefono = new SimpleStringProperty();
        celular = new SimpleStringProperty();
        curp = new SimpleStringProperty();
        rfc = new SimpleStringProperty();
    }

    public Empleado(Integer idEmpleado, Integer idRol, Integer idDomicilio, String usuario, String contrasenia, String nombre, String apellidoPaterno, String apellidoMaterno, String telefono, String celular, String curp, String rfc, Date fechaNacimiento, Object huellaEmpleado) {
        this.idEmpleado = idEmpleado;
        this.idRol = idRol;
        this.idDomicilio = idDomicilio;
        this.usuario = new SimpleStringProperty(usuario);
        this.contrasenia = new SimpleStringProperty(contrasenia);
        this.nombre = new SimpleStringProperty(nombre);
        this.apellidoPaterno = new SimpleStringProperty(apellidoPaterno);
        this.apellidoMaterno = new SimpleStringProperty(apellidoMaterno);
        this.telefono = new SimpleStringProperty(telefono);
        this.celular = new SimpleStringProperty(celular);
        this.curp = new SimpleStringProperty(curp);
        this.rfc = new SimpleStringProperty(rfc);
        setFechaNacimiento(fechaNacimiento);
        this.huellaEmpleado = huellaEmpleado;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public Integer getIdDomicilio() {
        return idDomicilio;
    }

    public void setIdDomicilio(Integer idDomicilio) {
        this.idDomicilio = idDomicilio;
    }
    
    public StringProperty getUsuarioProperty() {
        return usuario;
    }

    public String getUsuario() {
        return usuario.get();
    }

    public void setUsuario(String usuario) {
        this.usuario.set(usuario);
    }
    
    public StringProperty getContraseniaProperty() {
        return contrasenia;
    }

    public String getContrasenia() {
        return contrasenia.get();
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia.set(contrasenia);
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
        return apellidoMaterno.getValueSafe();
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno.set(apellidoMaterno);
    }
    
    public StringProperty getTelefonoProperty() {
        return telefono;
    }

    public String getTelefono() {
        return telefono.getValueSafe();
    }

    public void setTelefono(String telefono) {
        this.telefono.set(telefono);
    }
    
    public StringProperty getCelularProperty() {
        return celular;
    }

    public String getCelular() {
        return celular.getValueSafe();
    }

    public void setCelular(String celular) {
        this.celular.set(celular);
    }
    
    public StringProperty getCurpProperty() {
        return curp;
    }
    
    public String getCurp() {
        return curp.get();
    }

    public void setCurp(String curp) {
        this.curp.set(curp);
    }
    
    public StringProperty getRfcProperty() {
        return rfc;
    }

    public String getRfc() {
        return rfc.get();
    }

    public void setRfc(String rfc) {
        this.rfc.set(rfc);
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

    public Object getHuellaEmpleado() {
        return huellaEmpleado;
    }

    public void setHuellaEmpleado(Object huellaEmpleado) {
        this.huellaEmpleado = huellaEmpleado;
    }
}