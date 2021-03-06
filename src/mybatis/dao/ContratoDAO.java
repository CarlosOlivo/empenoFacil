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
package mybatis.dao;

import empenofacil.Util;
import empenofacil.model.Articulo;
import empenofacil.model.Contrato;
import empenofacil.model.Parametros;
import empenofacil.model.Prenda;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import mybatis.MyBatisUtil;
import mybatis.idao.IContratoDAO;
import org.apache.ibatis.session.SqlSession;

public class ContratoDAO implements IContratoDAO {

    @Override
    public List<Contrato> obtenerContratos() {
        List<Contrato> list = null;
        SqlSession conn = MyBatisUtil.getSession();
        try {
            IContratoDAO contratoDAO = conn.getMapper(IContratoDAO.class);
            list = procesarContratos(contratoDAO.obtenerContratos());
        } catch (Exception e) {
            Util.excepcion(e);
        } finally {
            conn.close();
        }
        return list;
    }

    private List<Contrato> procesarContratos(List<Contrato> contratos) {
        contratos.forEach((contrato) -> {
            procesarContrato(contrato);
        });
        return contratos;
    }

    @Override
    public Contrato obtenerContrato(Integer folio) {
        Contrato contrato = null;
        SqlSession conn = MyBatisUtil.getSession();
        try {
            IContratoDAO contratoDAO = conn.getMapper(IContratoDAO.class);
            contrato = procesarContrato(contratoDAO.obtenerContrato(folio));
        } catch (Exception e) {
            Util.excepcion(e);
        } finally {
            conn.close();
        }
        return contrato;
    }

    @Override
    public int crearContrato(Contrato contrato) {
        int rows = 0;
        SqlSession conn = MyBatisUtil.getSession();
        try {
            IContratoDAO contratoDAO = conn.getMapper(IContratoDAO.class);
            rows = contratoDAO.crearContrato(contrato);
            conn.commit();
        } catch (Exception e) {
            conn.rollback();
            Util.excepcion(e);
        } finally {
            conn.close();
        }
        return rows;
    }

    @Override
    public int editarContrato(Contrato contrato) {
        int rows = 0;
        SqlSession conn = MyBatisUtil.getSession();
        try {
            IContratoDAO contratoDAO = conn.getMapper(IContratoDAO.class);
            rows = contratoDAO.editarContrato(contrato);
            conn.commit();
        } catch (Exception e) {
            conn.rollback();
            Util.excepcion(e);
        } finally {
            conn.close();
        }
        return rows;
    }

    @Override
    public List<Contrato> buscarContratoPorNombre(String busqueda) {
        List<Contrato> list = null;
        SqlSession conn = MyBatisUtil.getSession();
        try {
            IContratoDAO contratoDAO = conn.getMapper(IContratoDAO.class);
            list = procesarContratos(contratoDAO.buscarContratoPorNombre("%" + busqueda + "%"));
        } catch (Exception e) {
            Util.excepcion(e);
        } finally {
            conn.close();
        }
        return list;
    }

    @Override
    public List<Contrato> buscarContratoPorPrenda(String busqueda) {
        List<Contrato> list = null;
        SqlSession conn = MyBatisUtil.getSession();
        try {
            IContratoDAO contratoDAO = conn.getMapper(IContratoDAO.class);
            list = procesarContratos(contratoDAO.buscarContratoPorPrenda("%" + busqueda + "%"));
        } catch (Exception e) {
            Util.excepcion(e);
        } finally {
            conn.close();
        }
        return list;
    }

    private Contrato procesarContrato(Contrato contrato) {
        if (contrato != null) {
            switch (Contrato.ESTADO_CONTRATO.values()[contrato.getIdEstadoContrato()]) {
                case ACTIVO:
                    contrato = verificarContratoActivo(contrato);
                    break;
                case CANCELADO:
                    break;
                case PRORROGA:
                    contrato = verificarContratoProrroga(contrato);
                    break;
                case EXPIRADO:
                    contrato = comercializarContrato(contrato);
                    break;
                case COMERCIALIZADO:
                    break;
                case FINIQUITADO:
                    break;
                case REFRENDADO:
                    break;
                default:
                    System.err.println("Contrato invalido");
                    break;
            }
        }
        return contrato;
    }

    private Contrato verificarContratoActivo(Contrato contrato) {
        Date fechaHoy = Util.obtenerFechaSinTiempo(new Date());
        if (contrato != null && fechaHoy.after(contrato.getFechaFinContrato())) {
            contrato.setIdEstadoContrato(Contrato.ESTADO_CONTRATO.PRORROGA.ordinal());
            System.err.println(String.format("Contrato #%d: ACTIVO -> PRORROGA", contrato.getFolio()));
            if (editarContrato(contrato) != 0) {
                contrato = procesarContrato(contrato);
            } else {
                System.err.println(String.format("Ocurrio un error al actualizar el estado del contrato #%d", contrato.getFolio()));
                contrato.setIdEstadoContrato(Contrato.ESTADO_CONTRATO.ACTIVO.ordinal());
                System.err.println(String.format("Contrato #%d: ACTIVO <- PRORROGA", contrato.getFolio()));
            }
        }
        return contrato;
    }

    private Contrato verificarContratoProrroga(Contrato contrato) {
        if (contrato != null) {
            Parametros parametros = new ParametrosDAO().obtenerParametros(contrato.getFolio());
            Date fechaHoy = Util.obtenerFechaSinTiempo(new Date());
            Date fechaProrroga = Util.agregarDiasFecha(contrato.getFechaFinContrato(), parametros.getDiasEnTotalExtension());
            if (fechaHoy.after(fechaProrroga)) {
                contrato.setIdEstadoContrato(Contrato.ESTADO_CONTRATO.EXPIRADO.ordinal());
                System.err.println(String.format("Contrato #%d: PRORROGA -> EXPIRADO", contrato.getFolio()));
                if (editarContrato(contrato) != 0) {
                    contrato = procesarContrato(contrato);
                } else {
                    System.err.println(String.format("Ocurrio un error al actualizar el estado del contrato #%d", contrato.getFolio()));
                    contrato.setIdEstadoContrato(Contrato.ESTADO_CONTRATO.PRORROGA.ordinal());
                    System.err.println(String.format("Contrato #%d: PRORROGA <- EXPIRADO", contrato.getFolio()));
                }
            }
        }
        return contrato;
    }

    private Contrato comercializarContrato(Contrato contrato) {
        if (contrato != null) {
            boolean error = false;
            Parametros parametros = new ParametrosDAO().obtenerParametros(contrato.getFolio());
            List<Prenda> prendasContrato = new PrendaDAO().obtenerPrendasContrato(contrato.getFolio());
            List<Articulo> articulos = new ArrayList<>();
            prendasContrato.forEach((prenda) -> {
                Articulo articulo = new Articulo();
                articulo.setIdCategoriaPrenda(prenda.getIdCategoriaPrenda());
                articulo.setIdTipoPrenda(prenda.getIdTipoPrenda());
                articulo.setIdEstadoArticulo(Articulo.ESTADO_ARTICULO.DISPONIBLE.ordinal());
                articulo.setNombre(prenda.getNombre());
                articulo.setTamanio(prenda.getTamanio());
                articulo.setPeso(prenda.getPeso());
                articulo.setDescripcion(prenda.getDescripcion());
                articulo.setPrecio(prenda.getPrestamo() * (1 + parametros.getComercializacion()));
                articulos.add(articulo);
            });
            for (Articulo articulo : articulos) {
                if (new ArticuloDAO().crearArticulo(articulo) != 0) {
                    System.err.println(String.format("NUEVO ARTICULO #%d", articulo.getIdArticulo()));
                } else {
                    error = true;
                    System.err.println(String.format("Ocurrio un error al comercializar el articulo #%d", articulo.getIdArticulo()));
                }
            }
            if (!error) {
                contrato.setIdEstadoContrato(Contrato.ESTADO_CONTRATO.COMERCIALIZADO.ordinal());
                System.err.println(String.format("Contrato #%d: EXPIRADO -> COMERCIALIZADO", contrato.getFolio()));
                if (editarContrato(contrato) != 0) {
                    contrato = procesarContrato(contrato);
                } else {
                    System.err.println(String.format("Ocurrio un error al actualizar el estado del contrato #%d", contrato.getFolio()));
                    contrato.setIdEstadoContrato(Contrato.ESTADO_CONTRATO.EXPIRADO.ordinal());
                    System.err.println(String.format("Contrato #%d: EXPIRADO <- COMERCIALIZADO", contrato.getFolio()));
                }
            }
        }
        return contrato;
    }
}
