package ec.edu.est.poo.clases;

import ec.edu.est.poo.abstracts.Persona;
import ec.edu.est.poo.enums.EstadoSolicitud;
import ec.edu.est.poo.interfaces.Buscable;

import java.util.List;
import java.util.Objects;

public class Empleado extends Persona implements Buscable {
    private  String cargo;
    private String departamento;
    public Empleado(int id, String nombre, String direccion, String telefono, String cargo, String departamento) {
        super(id, nombre, direccion, telefono);
        this.cargo = cargo;
        this.departamento = departamento;
    }
    public Empleado() {

    }

    public String getCargo() {
        return cargo;
    }
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    public String getDepartamento() {
        return departamento;
    }
    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
    public SolicitudCompra realizarSolicitudCompra(int id, String departamento, List<DetalleCompra> productos) {
        SolicitudCompra solicitudCompra = new SolicitudCompra(id, departamento, EstadoSolicitud.SOLICITADA, productos);
        System.out.println("Solicitud de compra creada: " + solicitudCompra);
        return solicitudCompra;
    }

    @Override
    public void mostrarInfo() {
        super.mostrarInfo();
        System.out.println("Cargo: " + cargo);
        System.out.println("Departamento: " + departamento);
    }

    public boolean coincideCon(String criterio) {
        return getNombre().equalsIgnoreCase(criterio);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Empleado empleado = (Empleado) o;
        return Objects.equals(cargo, empleado.cargo) && Objects.equals(departamento, empleado.departamento);
    }
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cargo, departamento);
    }

    @Override
    public String toString() {
        return super.toString() +  "\nEmpleado{" +
                "cargo='" + cargo + '\'' +
                ", departamento='" + departamento + '\'' +
                '}';
    }
}
