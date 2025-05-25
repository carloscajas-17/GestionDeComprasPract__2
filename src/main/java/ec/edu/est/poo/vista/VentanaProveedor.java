package ec.edu.est.poo.vista;

import ec.edu.est.poo.modelos.Producto;
import ec.edu.est.poo.modelos.Proveedor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class VentanaProveedor extends Frame implements ActionListener {
    private Panel pSuperior;
    private Panel pCentral;
    private Panel pInferior;
    private Panel pAgregar;
    private Panel pListado;
    private Panel pBuscar;

    private Button btnAgregar;
    private Button btnGuardar;
    private Button btnListar;
    private Button btnIrBuscar;
    private Button btnBuscar;

    private Label titulo;
    private Label lbId;
    private Label lbNombre;
    private Label lbDireccion;
    private Label lbTelefono;
    private Label lbCodeProduto;
    private Label lbProducto;
    private Label lbDescripcion;
    private Label lbPrecio;
    private Label lbCriterio;

    private TextArea txtMostrar;
    private TextArea txtBusqueda;

    private TextField txtId;
    private TextField txtNombre;
    private TextField txtDireccion;
    private TextField txtTelefono;
    private TextField txtCodeProducto;
    private TextField txtProducto;
    private TextField txtDescripcion;
    private TextField txtPrecio;
    private TextField txtCriterio;

    private CardLayout cardLayout;
    private List<Proveedor> listaProveedores = new ArrayList<>();

    public VentanaProveedor() {
        setTitle("Ventana Proveedor");
        setSize(500, 600);
        setLayout(new BorderLayout());
        setBackground(new Color(174, 214, 241));
        setLocationRelativeTo(null);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        Panel pGeneral = new Panel();

        cardLayout = new CardLayout();

        pSuperior = new Panel();
        pCentral = new Panel(new FlowLayout(FlowLayout.CENTER));
        pAgregar = new Panel(new GridLayout(9, 2, 10, 15));
        pListado = new Panel(new BorderLayout());
        pBuscar = new Panel(new GridLayout(4, 1, 10, 15));
        pInferior = new Panel(cardLayout);

        pSuperior.setBackground(new Color(247, 249, 249));

        titulo = new Label("MENU DE PROVEEDORES", Label.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 28));
        titulo.setForeground(new Color(50, 50, 50));

        btnAgregar = new Button("Agregar Proveedor");
        btnGuardar = new Button("Guardar");
        btnListar = new Button("Listar Proveedores");
        btnBuscar = new Button("Buscar");
        btnIrBuscar = new Button("Buscar proveedor");

        lbId = new Label("Id: ");
        lbNombre = new Label("Nombre: ");
        lbDireccion = new Label("Dirección: ");
        lbTelefono = new Label("Teléfono: ");
        lbCodeProduto = new Label("Código producto: ");
        lbProducto = new Label("Productos: ");
        lbDescripcion = new Label("Descripción: ");
        lbPrecio = new Label("Precio: ");

        txtId = new TextField(15);
        txtNombre = new TextField(15);
        txtDireccion = new TextField(15);
        txtTelefono = new TextField(15);
        txtCodeProducto = new TextField(15);
        txtProducto = new TextField(15);
        txtDescripcion = new TextField(15);
        txtPrecio = new TextField(15);

        txtMostrar = new TextArea("Aquí se mostrara la lista de proveedores...", 5, 30);
        txtMostrar.setEditable(false);

        lbCriterio = new Label("Id a buscar: ");
        txtCriterio = new TextField(15);

        txtBusqueda = new TextArea("Resultado de la búsqueda...", 5, 30);
        txtBusqueda.setEditable(false);

        pListado.add(new Label("Aquí se mostrará a los proveedores"));
        pListado.add(txtMostrar, BorderLayout.CENTER);

        pSuperior.add(titulo);

        pCentral.add(btnAgregar);
        pCentral.add(btnListar);
        pCentral.add(btnIrBuscar);

        pAgregar.add(lbId);
        pAgregar.add(txtId);
        pAgregar.add(lbNombre);
        pAgregar.add(txtNombre);
        pAgregar.add(lbDireccion);
        pAgregar.add(txtDireccion);
        pAgregar.add(lbTelefono);
        pAgregar.add(txtTelefono);
        pAgregar.add(lbCodeProduto);
        pAgregar.add(txtCodeProducto);
        pAgregar.add(lbProducto);
        pAgregar.add(txtProducto);
        pAgregar.add(lbDescripcion);
        pAgregar.add(txtDescripcion);
        pAgregar.add(lbPrecio);
        pAgregar.add(txtPrecio);
        pAgregar.add(btnGuardar);
        pAgregar.add(new Label(""));

        pBuscar.add(lbCriterio);
        pBuscar.add(txtCriterio);
        pBuscar.add(btnBuscar);
        pBuscar.add(txtBusqueda);

        pInferior.add(pAgregar, "Agregar");
        pInferior.add(pListado, "Listado");
        pInferior.add(pBuscar, "Buscar");

        pGeneral.add(pSuperior, BorderLayout.NORTH);
        pGeneral.add(pCentral, BorderLayout.CENTER);
        pGeneral.add(pInferior, BorderLayout.SOUTH);

        add(pGeneral, BorderLayout.CENTER);

        setVisible(true);

        btnAgregar.addActionListener(this);
        btnListar.addActionListener(this);
        btnGuardar.addActionListener(this);
        btnIrBuscar.addActionListener(this);
        btnBuscar.addActionListener(this);

        cardLayout.show(pInferior, "Formulario");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAgregar) {
            cardLayout.show(pInferior, "Agregar");
            limpiar();
        } else if (e.getSource() == btnGuardar) {
            guardarProveedor();
        } else if(e.getSource() == btnListar) {
            cardLayout.show(pInferior, "Listado");
            mostrarProveedores();
        } else if (e.getSource() == btnIrBuscar) {
            cardLayout.show(pInferior, "Buscar");
        } else if (e.getSource() == btnBuscar) {
            buscarProveedor();
        }
    }

    private void guardarProveedor() {
        try {
            int id = Integer.parseInt(txtId.getText());
            String nombre = txtNombre.getText();
            String direccion = txtDireccion.getText();
            String telefono = txtTelefono.getText();
            int codigo = Integer.parseInt(txtCodeProducto.getText());
            String nombreProducto = txtProducto.getText();
            String descripcionProducto = txtDescripcion.getText();
            double precioProducto = Double.parseDouble(txtPrecio.getText());

            Producto producto = new Producto(codigo, nombreProducto, descripcionProducto, precioProducto);
            Proveedor newProveedor = new Proveedor(id, nombre, direccion, telefono, producto);

            newProveedor.registrarProducto(producto.getCodigo(), producto.getNombre(), producto.getDescripcion(), producto.getPrecio());

            listaProveedores.add(newProveedor);
            JOptionPane.showMessageDialog(this, "Proveedor agregado",
                    "Guardar", JOptionPane.INFORMATION_MESSAGE);
            limpiar();
            cardLayout.show(pInferior, "Listado");
            mostrarProveedores();
        } catch (NumberFormatException error) {
            JOptionPane.showMessageDialog(this, "Error",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void mostrarProveedores() {
        StringBuilder sb = new StringBuilder();
        if (listaProveedores.isEmpty()) {
            sb.append("No hay proveedores registrados.");
        } else {
            for (Proveedor proveedor : listaProveedores) {
                sb.append(proveedor.toString());
                sb.append("\n");
            }
            txtMostrar.setText(sb.toString());
        }
    }

    private void limpiar() {
        txtId.setText("");
        txtNombre.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
        txtCodeProducto.setText("");
        txtProducto.setText("");
        txtDescripcion.setText("");
        txtPrecio.setText("");
    }

    private void buscarProveedor() {
        txtBusqueda.setText("");
        String criterio = txtCriterio.getText().trim();
        if (criterio.isEmpty()) {
            txtBusqueda.setText("Por favor, ingrese un Id...");
            return;
        }
        List<Proveedor> resultados = new ArrayList<>();
        for (Proveedor proveedor : listaProveedores) {
            if (proveedor.coincideCon(criterio)) {
                resultados.add(proveedor);
                break;
            }
        }
        if (resultados.isEmpty()) {
            txtBusqueda.setText("No se encontraron proveedores con el Id: " + criterio);
        } else {
            StringBuilder sbResult = new StringBuilder();
            sbResult.append("Resultado de la búsqueda");
            for (Proveedor proveedor : resultados) {
                sbResult.append(proveedor.toString());
                sbResult.append("\n----------\n");
            }
            txtBusqueda.setText(sbResult.toString());
        }
    }
}
