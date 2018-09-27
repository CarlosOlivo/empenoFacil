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

public class Empleado {
    private Integer id_empleado;
    private Integer id_rol;
    private Integer id_domicilio;
    private String usuario;
    private String contrasenia;
    private final StringProperty nombre;
    private final StringProperty apellidoMaterno;
    private final StringProperty apellidoPaterno;
    private final StringProperty telefono;
    private final StringProperty celular;
    private final StringProperty rfc;
    private final StringProperty curp;
    private Date fecha_nacimiento;
    private Object huella_empleado;

    public Empleado() {
        this.id_empleado = null;
        this.id_rol = null;
        this.id_domicilio = null;
        this.usuario = null;
        this.contrasenia = null;
        this.nombre = new SimpleStringProperty();
        this.apellidoMaterno = new SimpleStringProperty();
        this.apellidoPaterno = new SimpleStringProperty();
        this.telefono = new SimpleStringProperty();
        this.celular = new SimpleStringProperty();
        this.rfc = new SimpleStringProperty();
        this.curp = new SimpleStringProperty();
        this.fecha_nacimiento = new Date();
        this.huella_empleado = null;
    }
    
    public Empleado(Integer id_empleado, Integer id_rol, Integer id_domicilio, String usuario, String contrasenia, String nombre, String apellidoMaterno, String apellidoPaterno, String telefono, String celular, String rfc, String curp, Date fecha_nacimiento, Object huella_empleado) {
        this.id_empleado = id_empleado;
        this.id_rol = id_rol;
        this.id_domicilio = id_domicilio;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.nombre = new SimpleStringProperty(nombre);
        this.apellidoMaterno = new SimpleStringProperty(apellidoMaterno);
        this.apellidoPaterno = new SimpleStringProperty(apellidoPaterno);
        this.telefono = new SimpleStringProperty(telefono);
        this.celular = new SimpleStringProperty(celular);
        this.rfc = new SimpleStringProperty(rfc);
        this.curp = new SimpleStringProperty(curp);
        if(fecha_nacimiento != null) {
            this.fecha_nacimiento = new Date(fecha_nacimiento.getTime());
        } else {
            this.fecha_nacimiento = new Date();
        }
        this.huella_empleado = huella_empleado;
    }
    
    public Integer getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(Integer id_empleado) {
        this.id_empleado = id_empleado;
    }

    public Integer getId_rol() {
        return id_rol;
    }

    public void setId_rol(Integer id_rol) {
        this.id_rol = id_rol;
    }

    public Integer getId_domicilio() {
        return id_domicilio;
    }

    public void setId_domicilio(Integer id_domicilio) {
        this.id_domicilio = id_domicilio;
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
    
    public StringProperty getNombreProperty() {
        return nombre;
    }
    
    public String getNombre() {
        return nombre.getValue();
    }

    public void setNombre(String nombre) {
        this.nombre.setValue(nombre);
    }
    
    public StringProperty getApellidoPaternoProperty() {
        return apellidoPaterno;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno.getValue();
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno.setValue(apellidoPaterno);
    }
    
    public StringProperty getApellidoMaternoProperty() {
        return apellidoMaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno.getValue();
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno.setValue(apellidoMaterno);
    }
    
    public StringProperty getTelefonoProperty() {
        return telefono;
    }

    public String getTelefono() {
        return telefono.getValue();
    }

    public void setTelefono(String telefono) {
        this.telefono.setValue(telefono);
    }
    
    public StringProperty getCelularProperty() {
        return celular;
    }

    public String getCelular() {
        return celular.getValue();
    }

    public void setCelular(String celular) {
        this.celular.setValue(celular);
    }
    
    public StringProperty getRFCProperty() {
        return rfc;
    }

    public String getRFC() {
        return rfc.getValue();
    }

    public void setRFC(String rfc) {
        this.rfc.setValue(rfc);
    }
    
    public StringProperty getCURPProperty() {
        return curp;
    }

    public String getCURP() {
        return curp.getValue();
    }

    public void setCURP(String curp) {
        this.curp.setValue(curp);
    }
    
    public Date getFechaNacimiento() {
        return new Date(fecha_nacimiento.getTime());
    }

    public void setFechaNacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = new Date(fecha_nacimiento.getTime());
    }

    public Object getHuellaEmpleado() {
        return huella_empleado;
    }

    public void setHuellaEmpleado(Object huella_empleado) {
        this.huella_empleado = huella_empleado;
    }
}