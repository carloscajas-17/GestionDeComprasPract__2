package ec.edu.est.poo.modelos;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Departamento {
    private int id;
    private String nombre;
    private List<Empleado> empleados;

    public Departamento(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.empleados = new ArrayList<>();
    }

    public Departamento() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void addEmpleado(Empleado empleado) {
        empleados.add(empleado);
        System.out.println("Empleado agregado al departamento: " + empleado.getNombre());
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Departamento that = (Departamento) o;
        return id == that.id && Objects.equals(nombre, that.nombre) && Objects.equals(empleados, that.empleados);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, empleados);
    }

    @Override
    public String toString() {
        return "Departamento{" +
                "Id: " + id +
                ", Nombre: '" + nombre + '\'' +
                ", Empleados: " + empleados +
                '}';
    }
}
