package ec.edu.est.poo.vista;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import ec.edu.est.poo.modelos.*;

public class VentanaSolicitud extends Frame {

    private Frame frame;
    private TextField txtId, txtDepartamento, txtCodigoProducto, txtCantidad;
    private TextField txtSubtotal, txtIVA, txtTotal;

    private Button btnAgregarProducto, btnRegistrar, btnAceptar, btnRechazar, btnLimpiar, btnSalir;

    private List<Producto> productosDisponibles;
    private List<SolicitudCompra> solicitudes;
    private List<DetalleCompra> productosSeleccionados;

    public VentanaSolicitud(List<Producto> productosDisponibles, List<SolicitudCompra> solicitudes) {
        this.productosDisponibles = productosDisponibles;
        this.solicitudes = solicitudes;
        this.productosSeleccionados = new ArrayList<>();
        construirUI();
    }

    private void construirUI() {
        frame = new Frame("Registrar Solicitud de Compra");
        frame.setLayout(new GridLayout(10, 2));

        frame.add(new Label("ID Solicitud:"));
        txtId = new TextField();
        frame.add(txtId);

        frame.add(new Label("Departamento:"));
        txtDepartamento = new TextField();
        frame.add(txtDepartamento);

        frame.add(new Label("Código Producto:"));
        txtCodigoProducto = new TextField();
        frame.add(txtCodigoProducto);

        frame.add(new Label("Cantidad:"));
        txtCantidad = new TextField();
        frame.add(txtCantidad);

        btnAgregarProducto = new Button("Agregar Producto");
        frame.add(btnAgregarProducto);
        frame.add(new Label()); // espacio vacío

        frame.add(new Label("Subtotal:"));
        txtSubtotal = new TextField();
        txtSubtotal.setEditable(false);
        frame.add(txtSubtotal);

        frame.add(new Label("IVA (15%):"));
        txtIVA = new TextField();
        txtIVA.setEditable(false);
        frame.add(txtIVA);

        frame.add(new Label("Total:"));
        txtTotal = new TextField();
        txtTotal.setEditable(false);
        frame.add(txtTotal);

        btnRegistrar = new Button("Registrar Solicitud");
        btnAceptar = new Button("Aceptar");
        btnRechazar = new Button("Rechazar");
        btnLimpiar = new Button("Limpiar");
        btnSalir = new Button("Salir");

        frame.add(btnRegistrar);
        frame.add(btnAceptar);
        frame.add(btnRechazar);
        frame.add(btnLimpiar);
        frame.add(btnSalir);

        // Eventos
        btnAgregarProducto.addActionListener(e -> agregarProducto());
        btnRegistrar.addActionListener(e -> registrarSolicitud());
        btnAceptar.addActionListener(e -> cambiarEstadoUltima(EstadoSolicitud.APROBADA));
        btnRechazar.addActionListener(e -> cambiarEstadoUltima(EstadoSolicitud.RECHAZADA));
        btnLimpiar.addActionListener(e -> limpiarCampos());
        btnSalir.addActionListener(e -> frame.dispose());

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                frame.dispose();
            }
        });

        frame.setSize(500, 500);
        frame.setVisible(true);
    }

    private void agregarProducto() {
        try {
            int codigo = Integer.parseInt(txtCodigoProducto.getText().trim());
            int cantidad = Integer.parseInt(txtCantidad.getText().trim());

            Producto encontrado = null;
            for (Producto p : productosDisponibles) {
                if (p.getCodigo() == codigo) {
                    encontrado = p;
                    break;
                }
            }

            if (encontrado != null) {
                DetalleCompra detalle = new DetalleCompra(encontrado, cantidad);
                productosSeleccionados.add(detalle);
                System.out.println("Producto agregado: " + detalle);
                calcularTotales();
            } else {
                System.out.println("Producto no encontrado.");
            }

        } catch (NumberFormatException ex) {
            System.out.println("Código o cantidad inválidos.");
        }
    }

    private void registrarSolicitud() {
        try {
            int id = Integer.parseInt(txtId.getText().trim());
            String nombreDepartamento = txtDepartamento.getText().trim();

            Departamento departamento = new Departamento(nombreDepartamento);
            SolicitudCompra solicitud = new SolicitudCompra(id, departamento, EstadoSolicitud.SOLICITADA, productosSeleccionados);
            solicitudes.add(solicitud);

            calcularTotales();

            System.out.println("Solicitud registrada: " + solicitud);
        } catch (NumberFormatException ex) {
            System.out.println("ID inválido.");
        }
    }

    private void calcularTotales() {
        SolicitudCompra temporal = new SolicitudCompra(0, new Departamento("temp"), EstadoSolicitud.SOLICITADA, productosSeleccionados);
        double sub = temporal.calcularSubtotal();
        double iva = temporal.calcularIVA();
        double total = temporal.calcularTotal();

        txtSubtotal.setText(String.format("%.2f", sub));
        txtIVA.setText(String.format("%.2f", iva));
        txtTotal.setText(String.format("%.2f", total));
    }

    private void cambiarEstadoUltima(EstadoSolicitud nuevoEstado) {
        if (!solicitudes.isEmpty()) {
            SolicitudCompra ultima = solicitudes.get(solicitudes.size() - 1);
            ultima.setEstado(nuevoEstado);
            System.out.println("Estado cambiado a: " + nuevoEstado);
        }
    }

    private void limpiarCampos() {
        txtId.setText("");
        txtDepartamento.setText("");
        txtCodigoProducto.setText("");
        txtCantidad.setText("");
        txtSubtotal.setText("");
        txtIVA.setText("");
        txtTotal.setText("");
        productosSeleccionados.clear();
    }
}
