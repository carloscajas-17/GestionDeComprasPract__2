package ec.edu.est.poo.clases;

import ec.edu.est.poo.enums.EstadoSolicitud;

import java.util.List;
import java.util.Objects;

public class SolicitudCompra {
    private int id;
    private String departamento;
    private EstadoSolicitud estado;
    private List<DetalleCompra> productos;
    public SolicitudCompra() {
    }
    public SolicitudCompra(int id, String departamento, EstadoSolicitud estado, List<DetalleCompra> productos) {
        this.id = id;
        this.departamento = departamento;
        this.estado = estado;
        this.productos = productos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public EstadoSolicitud getEstado() {
        return estado;
    }

    public void setEstado(EstadoSolicitud estado) {
        this.estado = estado;
    }

    public List<DetalleCompra> getProductos() {
        return productos;
    }

    public void setProductos(List<DetalleCompra> productos) {
        this.productos = productos;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SolicitudCompra that = (SolicitudCompra) o;
        return id == that.id && Objects.equals(departamento, that.departamento) && estado == that.estado && Objects.equals(productos, that.productos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, departamento, estado, productos);
    }

    @Override
    public String toString() {
        return "SolicitudCompra{" +
                "Id: " + id +
                ", Departamento: '" + departamento + '\'' +
                ", Estado: " + estado +
                ", Productos: " + productos +
                '}';
    }
}
