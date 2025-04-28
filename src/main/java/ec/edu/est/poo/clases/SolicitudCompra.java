package ec.edu.est.poo.clases;

import ec.edu.est.poo.enums.EstadoSolicitud;
import ec.edu.est.poo.interfaces.Buscable;
import ec.edu.est.poo.interfaces.Calculable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SolicitudCompra implements Calculable, Buscable {
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
        this.productos = productos != null ? productos : new ArrayList<>();
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

    public void addDetalle(Producto producto, int cantidad) {
        DetalleCompra detalleCompra = new DetalleCompra(producto, cantidad);
        productos.add(detalleCompra);
        System.out.println("Detalle de compra agregado: " + detalleCompra);
    }

    public void aceptarSolicitud() {
        this.estado = EstadoSolicitud.APROBADA;
        System.out.println("La solicitud de compra ha sido aceptada.");
    }

    public void rechazarSolicitud() {
        this.estado = EstadoSolicitud.RECHAZADA;
        System.out.println("La solicitud de compra ha sido rechazada.");
    }

    public boolean coincideCon(String criterio) {
        return String.valueOf(this.id).equals(criterio);
    }

    @Override
    public double calcularSubtotal() {
        double subtotal = 0.0;
        for(DetalleCompra detalleCompra : productos) {
            subtotal += detalleCompra.getProducto().getPrecio() * detalleCompra.getCantidad();
        }
        return subtotal;
    }

    @Override
    public double calcularIVA() {
        return calcularSubtotal() * 0.15;
    }

    @Override
    public double calcularTotal() {
        double subTotal = calcularSubtotal();
        double iva = calcularIVA();
        return subTotal + iva;
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
