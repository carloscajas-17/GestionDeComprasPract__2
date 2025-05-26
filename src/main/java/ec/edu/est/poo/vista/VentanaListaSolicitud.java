package ec.edu.est.poo.vista;

import ec.edu.est.poo.modelos.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class VentanaListaSolicitud extends Frame implements ActionListener {
    private TextField txtIdEmpleado, txtDepartamento, txtCodigoProducto;
    private Choice chEstado;
    private TextArea areaDetalle;
    private Button btnVerProducto, btnAgregarProducto, btnGuardar;

    private List<SolicitudCompra> solicitudes = new ArrayList<>();
    private List<Producto> productos = new ArrayList<>();
    private List<Empleado> empleados = new ArrayList<>();
    private SolicitudCompra solicitudActual;

    public VentanaListaSolicitud() {
        setTitle("Ventana Lista Solicitud");
        setSize(600, 600);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        // Parte superior
        Panel panelTop = new Panel(new GridLayout(4, 2, 10, 10));
        panelTop.setBackground(new Color(220, 240, 255));

        panelTop.add(new Label("ID Empleado:"));
        txtIdEmpleado = new TextField();
        panelTop.add(txtIdEmpleado);

        panelTop.add(new Label("Departamento:"));
        txtDepartamento = new TextField();
        txtDepartamento.setEditable(false);
        panelTop.add(txtDepartamento);

        panelTop.add(new Label("Estado:"));
        chEstado = new Choice();
        for (EstadoSolicitud estado : EstadoSolicitud.values()) {
            chEstado.add(estado.name());
        }
        panelTop.add(chEstado);

        panelTop.add(new Label("Código Producto:"));
        txtCodigoProducto = new TextField();
        panelTop.add(txtCodigoProducto);

        add(panelTop, BorderLayout.NORTH);

        // Centro
        areaDetalle = new TextArea();
        areaDetalle.setEditable(false);
        add(areaDetalle, BorderLayout.CENTER);

        // Inferior
        Panel panelBotones = new Panel();
        btnVerProducto = new Button("Ver Info");
        btnAgregarProducto = new Button("Agregar Producto");
        btnGuardar = new Button("Guardar");

        panelBotones.add(btnVerProducto);
        panelBotones.add(btnAgregarProducto);
        panelBotones.add(btnGuardar);
        add(panelBotones, BorderLayout.SOUTH);

        // Eventos
        txtIdEmpleado.addActionListener(e -> cargarEmpleado());
        btnVerProducto.addActionListener(this);
        btnAgregarProducto.addActionListener(this);
        btnGuardar.addActionListener(this);

        precargarDatos();
        setVisible(true);
    }

    private void precargarDatos() {
        // Productos
        productos.add(new Producto(100, "Mouse", "Mouse óptico", 10.0));
        productos.add(new Producto(101, "Teclado", "Teclado mecánico", 25.5));

        // Departamento y empleado
        Departamento dep = new Departamento(1, "IT");
        Empleado emp = new Empleado(1, "Andres Cajas", "Loja", "0987654321", "Ingeniero", dep);
        empleados.add(emp);

        // Solicitud precargada con productos
        SolicitudCompra solicitud = new SolicitudCompra(emp);
        solicitud.setProductos(new ArrayList<>());
        solicitud.setEstado(EstadoSolicitud.SOLICITADA);
        solicitud.getProductos().add(new DetalleCompra(productos.get(0), 2));
        solicitud.getProductos().add(new DetalleCompra(productos.get(1), 1));
        solicitudes.add(solicitud);
    }

    private void cargarEmpleado() {
        String idStr = txtIdEmpleado.getText().trim();
        for (Empleado emp : empleados) {
            if (String.valueOf(emp.getId()).equals(idStr)) {
                txtDepartamento.setText(emp.getDepartamento().getNombre());

                // Verificar si ya existe la solicitud
                for (SolicitudCompra sc : solicitudes) {
                    if (sc.getEmpleado() != null && sc.getEmpleado().getId() == emp.getId()) {
                        solicitudActual = sc;
                        chEstado.select(solicitudActual.getEstado().name());
                        mostrarDetalle();
                        return;
                    }
                }

                // Si no existe, se crea
                solicitudActual = new SolicitudCompra(emp);
                solicitudActual.setProductos(new ArrayList<>());
                solicitudActual.setEstado(EstadoSolicitud.SOLICITADA);
                solicitudes.add(solicitudActual);
                chEstado.select(solicitudActual.getEstado().name());
                mostrarDetalle();
                return;
            }
        }
        areaDetalle.setText("Empleado no encontrado.");
    }

    private void mostrarDetalle() {
        if (solicitudActual == null) return;

        StringBuilder sb = new StringBuilder();
        sb.append("Empleado: ").append(solicitudActual.getEmpleado().getNombre()).append("\n");
        sb.append("Departamento: ").append(solicitudActual.getDepartamento().getNombre()).append("\n");
        sb.append("Estado: ").append(solicitudActual.getEstado()).append("\n\n");
        sb.append("Productos:\n");

        for (DetalleCompra d : solicitudActual.getProductos()) {
            Producto p = d.getProducto();
            sb.append("- ").append(p.getNombre()).append(", Precio: $")
                    .append(p.getPrecio()).append(", Cantidad: ").append(d.getCantidad()).append("\n");
        }

        sb.append("\nSubtotal: $").append(solicitudActual.calcularSubtotal());
        sb.append("\nIVA: $").append(solicitudActual.calcularIVA());
        sb.append("\nTotal: $").append(solicitudActual.calcularTotal());

        areaDetalle.setText(sb.toString());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnVerProducto) {
            try {
                int codigo = Integer.parseInt(txtCodigoProducto.getText());
                for (Producto p : productos) {
                    if (p.getCodigo() == codigo) {
                        areaDetalle.setText(p.toString());
                        return;
                    }
                }
                areaDetalle.setText("Producto no encontrado.");
            } catch (NumberFormatException ex) {
                areaDetalle.setText("Código inválido.");
            }
        } else if (e.getSource() == btnAgregarProducto) {
            try {
                int codigo = Integer.parseInt(txtCodigoProducto.getText());
                for (Producto p : productos) {
                    if (p.getCodigo() == codigo) {
                        if (solicitudActual != null) {
                            solicitudActual.getProductos().add(new DetalleCompra(p, 1));
                            mostrarDetalle();
                        }
                        return;
                    }
                }
                areaDetalle.setText("Producto no encontrado.");
            } catch (NumberFormatException ex) {
                areaDetalle.setText("Código inválido.");
            }
        } else if (e.getSource() == btnGuardar) {
            if (solicitudActual != null) {
                solicitudActual.setEstado(EstadoSolicitud.valueOf(chEstado.getSelectedItem()));
                mostrarDetalle();
                areaDetalle.append("\n\nEstado actualizado correctamente.");
            }
        }
    }
}
