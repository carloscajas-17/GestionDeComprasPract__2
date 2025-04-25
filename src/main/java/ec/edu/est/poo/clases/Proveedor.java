package ec.edu.est.poo.clases;

import ec.edu.est.poo.abstracts.Persona;

import java.util.Objects;

public class Proveedor extends Persona {
    private Producto producto;
    public Proveedor() {

    }

    public Proveedor(int id, String nombre, String direccion, String telefono, Producto producto) {
        super(id, nombre, direccion, telefono);
        this.producto = producto;
    }
    public Producto getProducto() {
        return producto;
    }
    public void setProducto(Producto producto) {
        this.producto = producto;
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
