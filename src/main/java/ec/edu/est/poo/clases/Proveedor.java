package ec.edu.est.poo.clases;

import ec.edu.est.poo.abstracts.Persona;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Proveedor extends Persona {
    private List<Producto> producto;
    public Proveedor() {

    }

    public Proveedor(int id, String nombre, String direccion, String telefono) {
        super(id, nombre, direccion, telefono);
        this.producto = new ArrayList<>();
    }

    public void registrarProducto(int codigo, String nombre, String descripcion, double precio) {
        Producto newProducto = new Producto(codigo, nombre, descripcion, precio);
        producto.add(newProducto);
        System.out.println("Producto Registrado: " + newProducto);
    }
    @Override
    public void mostrarInfo() {
        System.out.println("Proveedor:");
        System.out.println("ID: " + getId());
        System.out.println("Nombre: " + getNombre());
        System.out.println("Dirección: " + getDireccion());
        System.out.println("Teléfono: " + getTelefono());
        if (producto != null) {
            System.out.println("Producto que ofrece: " + producto.getNombre());
        } else {
            System.out.println("Producto que ofrece: No especificado");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Proveedor proveedor = (Proveedor) o;
        return Objects.equals(producto, proveedor.producto);
    }
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), producto);
    }

    @Override
    public String toString() {
        return "Proveedor{" +
                "Productos: " + producto +
                '}';
    }


}
