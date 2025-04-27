package ec.edu.est.poo.controlador;

import ec.edu.est.poo.clases.*;
import ec.edu.est.poo.enums.EstadoSolicitud;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private final Scanner scanner = new Scanner(System.in);
    private final List<Empleado> empleados = new ArrayList<>();
    private final List<Proveedor> proveedores = new ArrayList<>();
    private final List<Producto> productos = new ArrayList<>();
    private final List<SolicitudCompra> solicitudes = new ArrayList<>();

    private int leerEntero() {
        while (!scanner.hasNextInt()) {
            System.out.println("Por favor, ingrese un número válido.");
            scanner.next();
        }
        int numero = scanner.nextInt();
        scanner.nextLine();
        return numero;
    }

    public void menuPrincipal() {
        int opcion;
        do {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. Gestionar Empleados");
            System.out.println("2. Gestionar Proveedores");
            System.out.println("3. Gestionar Productos");
            System.out.println("4. Gestionar Solicitudes de Compra");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = leerEntero();
            switch (opcion) {
                case 1 -> menuEmpleados();
                case 2 -> menuProveedores();
                case 3 -> menuProductos();
                case 4 -> menuSolicitudes();
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    private void menuEmpleados() {
        int opcion;
        do {
            System.out.println("\n--- GESTIÓN DE EMPLEADOS ---");
            System.out.println("1. Registrar Empleado");
            System.out.println("2. Listar Empleados");
            System.out.println("0. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            opcion = leerEntero();
            switch (opcion) {
                case 1 -> registrarEmpleado();
                case 2 -> listarEmpleados();
                case 0 -> System.out.println("Regresando al menú principal...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    private void registrarEmpleado() {
        System.out.print("ID: ");
        int id = leerEntero();
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Dirección: ");
        String direccion = scanner.nextLine();
        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine();
        System.out.print("Cargo: ");
        String cargo = scanner.nextLine();
        System.out.print("Departamento: ");
        String departamento = scanner.nextLine();

        Empleado empleado = new Empleado(id, nombre, direccion, telefono, cargo, departamento);
        empleados.add(empleado);
        System.out.println("Empleado registrado exitosamente.");
    }

    private void listarEmpleados() {
        if (empleados.isEmpty()) {
            System.out.println("No hay empleados registrados.");
        } else {
            for (Empleado e : empleados) {
                e.mostrarInfo();
                System.out.println();
            }
        }
    }

    private void menuProveedores() {
        int opcion;
        do {
            System.out.println("\n--- GESTIÓN DE PROVEEDORES ---");
            System.out.println("1. Registrar Proveedor");
            System.out.println("2. Listar Proveedores");
            System.out.println("0. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            opcion = leerEntero();
            switch (opcion) {
                case 1 -> registrarProveedor();
                case 2 -> listarProveedores();
                case 0 -> System.out.println("Regresando al menú principal...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    private void registrarProveedor() {
        System.out.print("ID: ");
        int id = leerEntero();
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Dirección: ");
        String direccion = scanner.nextLine();
        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine();

        System.out.println("Ahora registre el producto que ofrece el proveedor:");
        Producto producto = crearProducto();

        Proveedor proveedor = new Proveedor(id, nombre, direccion, telefono, producto);
        proveedores.add(proveedor);
        System.out.println("Proveedor registrado exitosamente.");
    }

    private void listarProveedores() {
        if (proveedores.isEmpty()) {
            System.out.println("No hay proveedores registrados.");
        } else {
            for (Proveedor p : proveedores) {
                p.mostrarInfo();
                System.out.println();
            }
        }
    }

    private void menuProductos() {
        int opcion;
        do {
            System.out.println("\n--- GESTIÓN DE PRODUCTOS ---");
            System.out.println("1. Registrar Producto");
            System.out.println("2. Listar Productos");
            System.out.println("0. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            opcion = leerEntero();
            switch (opcion) {
                case 1 -> registrarProducto();
                case 2 -> listarProductos();
                case 0 -> System.out.println("Regresando al menú principal...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    private void registrarProducto() {
        Producto producto = crearProducto();
        productos.add(producto);
        System.out.println("Producto registrado exitosamente.");
    }

    private Producto crearProducto() {
        System.out.print("Código: ");
        int codigo = leerEntero();
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Descripción: ");
        String descripcion = scanner.nextLine();
        System.out.print("Precio: ");
        double precio = scanner.nextDouble();
        scanner.nextLine();

        return new Producto(codigo, nombre, descripcion, precio);
    }

    private void listarProductos() {
        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados.");
        } else {
            for (Producto p : productos) {
                System.out.println(p);
                System.out.println();
            }
        }
    }

    private void menuSolicitudes() {
        int opcion;
        do {
            System.out.println("\n--- GESTIÓN DE SOLICITUDES DE COMPRA ---");
            System.out.println("1. Registrar Solicitud");
            System.out.println("2. Listar Solicitudes");
            System.out.println("0. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            opcion = leerEntero();
            switch (opcion) {
                case 1 -> registrarSolicitud();
                case 2 -> listarSolicitudes();
                case 0 -> System.out.println("Regresando al menú principal...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    private void registrarSolicitud() {
        System.out.print("ID de la solicitud: ");
        int id = leerEntero();
        System.out.print("Departamento solicitante: ");
        String departamento = scanner.nextLine();
        EstadoSolicitud estado = EstadoSolicitud.EN_REVISION;

        List<DetalleCompra> detalles = new ArrayList<>();

        String continuar;
        do {
            System.out.println("\nAñadir producto a la solicitud:");
            Producto producto = crearProducto();
            System.out.print("Cantidad: ");
            int cantidad = leerEntero();
            detalles.add(new DetalleCompra(producto, cantidad));
            System.out.print("¿Desea agregar otro producto? (S/N): ");
            continuar = scanner.nextLine();
        } while (continuar.equalsIgnoreCase("S"));

        SolicitudCompra solicitud = new SolicitudCompra(id, departamento, estado, detalles);
        solicitudes.add(solicitud);
        System.out.println("Solicitud registrada exitosamente.");
    }

    private void listarSolicitudes() {
        if (solicitudes.isEmpty()) {
            System.out.println("No hay solicitudes registradas.");
        } else {
            for (SolicitudCompra s : solicitudes) {
                System.out.println(s);
                System.out.println();
            }
        }
    }
}
