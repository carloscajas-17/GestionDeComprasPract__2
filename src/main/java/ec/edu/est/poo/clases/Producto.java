package ec.edu.est.poo.clases;

import ec.edu.est.poo.interfaces.Calculable;

import java.util.Objects;

public class Producto implements Calculable {
    private int codigo;
    private String nombre;
    private String descripcion;
    private double precio;
    public Producto(int codigo, String nombre, String descripcion, double precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }
    public Producto() {
    }
    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    @Override
    public double calcularSubtotal() {
        return precio;
    }
    @Override
    public double calcularIVA() {
        return calcularSubtotal() * 0.15;
    }
    @Override
    public double calcularTotal() {
        return calcularSubtotal() + calcularIVA();
    }
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return codigo == producto.codigo && Double.compare(precio, producto.precio) == 0 && Objects.equals(nombre, producto.nombre) && Objects.equals(descripcion, producto.descripcion);
    }
    @Override
    public int hashCode() {
        return Objects.hash(codigo, nombre, descripcion, precio);
    }
    @Override
    public String toString() {
        return "Producto{" +
                "Código: " + codigo +
                ", Nombre: '" + nombre + '\'' +
                ", Descripción: '" + descripcion + '\'' +
                ", Precio: " + precio +
                '}';
    }
}
