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

/**
 *
 * @author Carlos
 */
public class Parametros {
    private Integer folio;
    private Double iva;
    private Integer numPeriodos;
    private Integer diasPorPeriodo;
    private Integer diasEnTotal;
    private Integer numPeriodosExtension;
    private Integer diasPorPeriodoExtension;
    private Integer diasEnTotalExtension;
    private Integer diasParaCancelarContrato;
    private Double porcentajePrestamo;
    private Double interesPorPeriodo;
    private Double comercializacion;
    private Double tasaInteres;
    private Double costoAnualTotal;
    private Double costoMensual;
    private Double costoDiario;

    public Parametros() {
    }

    public Parametros(Integer folio, Double iva, Integer numPeriodos, Integer diasPorPeriodo, Integer diasEnTotal, Integer numPeriodosExtension, Integer diasPorPeriodoExtension, Integer diasEnTotalExtension, Integer diasParaCancelarContrato, Double porcentajePrestamo, Double interesPorPeriodo, Double comercializacion, Double tasaInteres, Double costoAnualTotal, Double costoMensual, Double costoDiario) {
        this.folio = folio;
        this.iva = iva;
        this.numPeriodos = numPeriodos;
        this.diasPorPeriodo = diasPorPeriodo;
        this.diasEnTotal = diasEnTotal;
        this.numPeriodosExtension = numPeriodosExtension;
        this.diasPorPeriodoExtension = diasPorPeriodoExtension;
        this.diasEnTotalExtension = diasEnTotalExtension;
        this.diasParaCancelarContrato = diasParaCancelarContrato;
        this.porcentajePrestamo = porcentajePrestamo;
        this.interesPorPeriodo = interesPorPeriodo;
        this.comercializacion = comercializacion;
        this.tasaInteres = tasaInteres;
        this.costoAnualTotal = costoAnualTotal;
        this.costoMensual = costoMensual;
        this.costoDiario = costoDiario;
    }

    public Integer getFolio() {
        return folio;
    }

    public void setFolio(Integer folio) {
        this.folio = folio;
    }

    public Double getIva() {
        return iva;
    }

    public void setIva(Double iva) {
        this.iva = iva;
    }

    public Integer getNumPeriodos() {
        return numPeriodos;
    }

    public void setNumPeriodos(Integer numPeriodos) {
        this.numPeriodos = numPeriodos;
    }

    public Integer getDiasPorPeriodo() {
        return diasPorPeriodo;
    }

    public void setDiasPorPeriodo(Integer diasPorPeriodo) {
        this.diasPorPeriodo = diasPorPeriodo;
    }

    public Integer getDiasEnTotal() {
        return diasEnTotal;
    }

    public void setDiasEnTotal(Integer diasEnTotal) {
        this.diasEnTotal = diasEnTotal;
    }

    public Integer getNumPeriodosExtension() {
        return numPeriodosExtension;
    }

    public void setNumPeriodosExtension(Integer numPeriodosExtension) {
        this.numPeriodosExtension = numPeriodosExtension;
    }

    public Integer getDiasPorPeriodoExtension() {
        return diasPorPeriodoExtension;
    }

    public void setDiasPorPeriodoExtension(Integer diasPorPeriodoExtension) {
        this.diasPorPeriodoExtension = diasPorPeriodoExtension;
    }

    public Integer getDiasEnTotalExtension() {
        return diasEnTotalExtension;
    }

    public void setDiasEnTotalExtension(Integer diasEnTotalExtension) {
        this.diasEnTotalExtension = diasEnTotalExtension;
    }

    public Integer getDiasParaCancelarContrato() {
        return diasParaCancelarContrato;
    }

    public void setDiasParaCancelarContrato(Integer diasParaCancelarContrato) {
        this.diasParaCancelarContrato = diasParaCancelarContrato;
    }

    public Double getPorcentajePrestamo() {
        return porcentajePrestamo;
    }

    public void setPorcentajePrestamo(Double porcentajePrestamo) {
        this.porcentajePrestamo = porcentajePrestamo;
    }

    public Double getInteresPorPeriodo() {
        return interesPorPeriodo;
    }

    public void setInteresPorPeriodo(Double interesPorPeriodo) {
        this.interesPorPeriodo = interesPorPeriodo;
    }

    public Double getComercializacion() {
        return comercializacion;
    }

    public void setComercializacion(Double comercializacion) {
        this.comercializacion = comercializacion;
    }

    public Double getTasaInteres() {
        return tasaInteres;
    }

    public void setTasaInteres(Double tasaInteres) {
        this.tasaInteres = tasaInteres;
    }

    public Double getCostoAnualTotal() {
        return costoAnualTotal;
    }

    public void setCostoAnualTotal(Double costoAnualTotal) {
        this.costoAnualTotal = costoAnualTotal;
    }

    public Double getCostoMensual() {
        return costoMensual;
    }

    public void setCostoMensual(Double costoMensual) {
        this.costoMensual = costoMensual;
    }

    public Double getCostoDiario() {
        return costoDiario;
    }

    public void setCostoDiario(Double costoDiario) {
        this.costoDiario = costoDiario;
    }
}