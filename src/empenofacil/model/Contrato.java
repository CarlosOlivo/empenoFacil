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

/**
 *
 * @author lunix
 */
public class Contrato {
    private Integer folio;
    private Integer id_estado;
    private String rfc_empresa;
    private Integer id_cliente;
    private Integer num_bolsa;
    private Date fecha_inicio_contrato;
    private Date fecha_fin_contrato;
    private double iva;
    private double subtotal;
    private double total;
    private String cotitular;

    public Contrato() {
    }

    public Contrato(Integer folio, Integer id_estado, String rfc_empresa, Integer id_cliente, Integer num_bolsa, Date fecha_inicio_contrato, Date fecha_fin_contrato, double iva, double subtotal, double total, String cotitular) {
        this.folio = folio;
        this.id_estado = id_estado;
        this.rfc_empresa = rfc_empresa;
        this.id_cliente = id_cliente;
        this.num_bolsa = num_bolsa;
        this.fecha_inicio_contrato = fecha_inicio_contrato;
        this.fecha_fin_contrato = fecha_fin_contrato;
        this.iva = iva;
        this.subtotal = subtotal;
        this.total = total;
        this.cotitular = cotitular;
    }

    public Integer getFolio() {
        return folio;
    }

    public void setFolio(Integer folio) {
        this.folio = folio;
    }

    public Integer getId_estado() {
        return id_estado;
    }

    public void setId_estado(Integer id_estado) {
        this.id_estado = id_estado;
    }

    public String getRfc_empresa() {
        return rfc_empresa;
    }

    public void setRfc_empresa(String rfc_empresa) {
        this.rfc_empresa = rfc_empresa;
    }

    public Integer getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Integer id_cliente) {
        this.id_cliente = id_cliente;
    }

    public Integer getNum_bolsa() {
        return num_bolsa;
    }

    public void setNum_bolsa(Integer num_bolsa) {
        this.num_bolsa = num_bolsa;
    }

    public Date getFecha_inicio_contrato() {
        return fecha_inicio_contrato;
    }

    public void setFecha_inicio_contrato(Date fecha_inicio_contrato) {
        this.fecha_inicio_contrato = fecha_inicio_contrato;
    }

    public Date getFecha_fin_contrato() {
        return fecha_fin_contrato;
    }

    public void setFecha_fin_contrato(Date fecha_fin_contrato) {
        this.fecha_fin_contrato = fecha_fin_contrato;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getCotitular() {
        return cotitular;
    }

    public void setCotitular(String cotitular) {
        this.cotitular = cotitular;
    }

    @Override
    public String toString() {
        return "Contrato{" + "folio=" + folio + ", id_estado=" + id_estado + ", "
                + "rfc_empresa=" + rfc_empresa + ", id_cliente=" + id_cliente + ","
                + " num_bolsa=" + num_bolsa + ", fecha_inicio_contrato=" + fecha_inicio_contrato + ","
                + " fecha_fin_contrato=" + fecha_fin_contrato + ", iva=" + iva + ", subtotal=" + subtotal + ","
                + " total=" + total + ", cotitular=" + cotitular + '}';
    }
}
