package ec.edu.est.poo.vista;

import ec.edu.est.poo.modelos.Producto;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class VentanaProducto extends Frame implements ActionListener {
    private Panel pSuperior, pCentral, pInferior;
    private Panel pAgregar, pListar, pBuscar;
    private CardLayout cardLayout;

    private Button btnAgregar, btnGuardar, btnListar, btnIrBuscar, btnBuscar, btnLimpiar, btnSalir;
    private TextField txtCodigo, txtNombre, txtDescripcion, txtPrecio, txtCriterio;
    private TextArea txtAreaProductos, txtAreaBusqueda;

    private List<Producto> listaProductos;

    public VentanaProducto(List<Producto> productos) {
        this.listaProductos = productos;

        setTitle("Gestión de Productos");
        setSize(600, 500);
        setLayout(new BorderLayout());
        setBackground(new Color(224, 255, 255));
        setLocationRelativeTo(null);

        // Ventana cierre
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        // Título
        pSuperior = new Panel();
        Label lblTitulo = new Label("MENÚ DE PRODUCTOS", Label.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 28));
        lblTitulo.setForeground(new Color(0, 80, 180));
        pSuperior.add(lblTitulo);

        // Botones principales
        pCentral = new Panel(new FlowLayout());
        btnAgregar = new Button("Agregar Producto");
        btnListar = new Button("Listar Productos");
        btnIrBuscar = new Button("Buscar Producto");

        pCentral.add(btnAgregar);
        pCentral.add(btnListar);
        pCentral.add(btnIrBuscar);

        // Panel inferior con CardLayout
        cardLayout = new CardLayout();
        pInferior = new Panel(cardLayout);

        // Panel AGREGAR
        pAgregar = new Panel(new GridLayout(6, 2, 10, 10));
        txtCodigo = new TextField(15);
        txtNombre = new TextField(15);
        txtDescripcion = new TextField(15);
        txtPrecio = new TextField(15);
        btnGuardar = new Button("Guardar");
        btnLimpiar = new Button("Limpiar");

        pAgregar.add(new Label("Código:"));
        pAgregar.add(txtCodigo);
        pAgregar.add(new Label("Nombre:"));
        pAgregar.add(txtNombre);
        pAgregar.add(new Label("Descripción:"));
        pAgregar.add(txtDescripcion);
        pAgregar.add(new Label("Precio:"));
        pAgregar.add(txtPrecio);
        pAgregar.add(btnGuardar);
        pAgregar.add(btnLimpiar);

        // Panel LISTAR
        pListar = new Panel(new BorderLayout());
        txtAreaProductos = new TextArea("Aquí se mostrarán los productos...", 10, 40);
        txtAreaProductos.setEditable(false);
        pListar.add(txtAreaProductos, BorderLayout.CENTER);

        // Panel BUSCAR
        pBuscar = new Panel(new GridLayout(4, 1, 10, 10));
        txtCriterio = new TextField(15);
        btnBuscar = new Button("Buscar");
        txtAreaBusqueda = new TextArea("Resultado de búsqueda...", 5, 30);
        txtAreaBusqueda.setEditable(false);

        pBuscar.add(new Label("Código del producto a buscar:"));
        pBuscar.add(txtCriterio);
        pBuscar.add(btnBuscar);
        pBuscar.add(txtAreaBusqueda);

        // Agregar los 3 paneles al cardLayout
        pInferior.add(pAgregar, "Agregar");
        pInferior.add(pListar, "Listar");
        pInferior.add(pBuscar, "Buscar");

        add(pSuperior, BorderLayout.NORTH);
        add(pCentral, BorderLayout.CENTER);
        add(pInferior, BorderLayout.SOUTH);

        setVisible(true);

        // Eventos
        btnAgregar.addActionListener(this);
        btnListar.addActionListener(this);
        btnIrBuscar.addActionListener(this);
        btnGuardar.addActionListener(this);
        btnLimpiar.addActionListener(this);
        btnBuscar.addActionListener(this);

        cardLayout.show(pInferior, "Agregar");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAgregar) {
            cardLayout.show(pInferior, "Agregar");
            limpiarFormulario();
        } else if (e.getSource() == btnListar) {
            cardLayout.show(pInferior, "Listar");
            mostrarProductos();
        } else if (e.getSource() == btnIrBuscar) {
            cardLayout.show(pInferior, "Buscar");
            txtCriterio.setText("");
            txtAreaBusqueda.setText("");
        } else if (e.getSource() == btnGuardar) {
            guardarProducto();
        } else if (e.getSource() == btnLimpiar) {
            limpiarFormulario();
        } else if (e.getSource() == btnBuscar) {
            buscarProducto();
        }
    }

    private void guardarProducto() {
        try {
            int codigo = Integer.parseInt(txtCodigo.getText());
            String nombre = txtNombre.getText().trim();
            String descripcion = txtDescripcion.getText().trim();
            double precio = Double.parseDouble(txtPrecio.getText());

            // Verificar duplicados
            for (Producto p : listaProductos) {
                if (p.getCodigo() == codigo) {
                    mostrarMensaje("Ya existe un producto con ese código.");
                    return;
                }
            }

            Producto nuevo = new Producto(codigo, nombre, descripcion, precio);
            listaProductos.add(nuevo);
            mostrarMensaje("Producto agregado correctamente.");
            limpiarFormulario();
        } catch (NumberFormatException ex) {
            mostrarMensaje("Error: código y precio deben ser numéricos.");
        }
    }

    private void mostrarProductos() {
        StringBuilder sb = new StringBuilder();
        if (listaProductos.isEmpty()) {
            sb.append("No hay productos registrados.");
        } else {
            for (Producto p : listaProductos) {
                sb.append(p.toString()).append("\n--------------------\n");
            }
        }
        txtAreaProductos.setText(sb.toString());
    }

    private void buscarProducto() {
        String criterio = txtCriterio.getText().trim();
        if (criterio.isEmpty()) {
            txtAreaBusqueda.setText("Ingrese un código.");
            return;
        }

        try {
            int codigoBuscado = Integer.parseInt(criterio);
            for (Producto p : listaProductos) {
                if (p.getCodigo() == codigoBuscado) {
                    txtAreaBusqueda.setText(p.toString());
                    return;
                }
            }
            txtAreaBusqueda.setText("Producto no encontrado.");
        } catch (NumberFormatException ex) {
            txtAreaBusqueda.setText("Código inválido.");
        }
    }

    private void limpiarFormulario() {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtDescripcion.setText("");
        txtPrecio.setText("");
    }

    private void mostrarMensaje(String mensaje) {
        Dialog d = new Dialog(this, "Mensaje", true);
        d.setLayout(new FlowLayout());
        d.add(new Label(mensaje));
        Button b = new Button("OK");
        b.addActionListener(e -> d.setVisible(false));
        d.add(b);
        d.setSize(300, 100);
        d.setLocationRelativeTo(this);
        d.setVisible(true);
    }
}
