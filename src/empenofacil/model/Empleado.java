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

public class Empleado {
    private Integer idEmpleado;
    private Integer idRol;
    private Integer idDomicilio;
    private String usuario;
    private String contrasenia;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String telefono;
    private String celular;
    private String rfc;
    private String curp;
    private Date fechaNacimiento;
    private Object huellaEmpleado;

    public Empleado() {}

    public Empleado(Integer idEmpleado, Integer idRol, Integer idDomicilio, String usuario, String contrasenia, String nombre, String apellidoPaterno, String apellidoMaterno, String telefono, String celular, String rfc, String curp, Date fechaNacimiento, Object huellaEmpleado) {
        this.idEmpleado = idEmpleado;
        this.idRol = idRol;
        this.idDomicilio = idDomicilio;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.telefono = telefono;
        this.celular = celular;
        this.rfc = rfc;
        this.curp = curp;
        if(fechaNacimiento != null) {
            this.fechaNacimiento = new Date(fechaNacimiento.getTime());
        } else {
            this.fechaNacimiento = new Date();
        }
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

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
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