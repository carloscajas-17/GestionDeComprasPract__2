package ec.edu.est.poo.vista;

import java.awt.*;

public class VentanaProveedor extends Frame {
    private Panel pSuperior;
    private Panel pCentral;
    private Panel pInferior;

    private Button btnAgregar;
    private Button btnGuardar;
    private Button btnListar;

    private Label titulo;
    private Label lbId;
    private Label lbNombre;
    private Label lbDireccion;
    private Label lbTelefono;
    private Label lbProductos;

    private TextField txtMostrar;
    private TextField txtId;
    private TextField txtNombre;
    private TextField txtDireccion;
    private TextField txtTelefono;
    private TextField txtProductos;

    public VentanaProveedor() {
        setTitle("Ventana Proveedor");
        setSize(500, 600);
        setLayout(new BorderLayout());
        setBackground(Color.LIGHT_GRAY);
        setLocationRelativeTo(null);

        Panel pGeneral = new Panel();

        pSuperior = new Panel();
        pCentral = new Panel(new GridLayout(8 , 2));
        pInferior = new Panel(new GridLayout(2, 1));

        titulo = new Label("MENU DE PROVEEDORES", Label.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 28));
        titulo.setForeground(new Color(50, 50, 50));

        btnAgregar = new Button("Agregar");
        btnGuardar = new Button("Guardar");
        btnListar = new Button("Listar");

        lbId = new Label("Id: ");
        lbNombre = new Label("Nombre: ");
        lbDireccion = new Label("Dirección: ");
        lbTelefono = new Label("Teléfono: ");
        lbProductos = new Label("Productos: ");

        txtId = new TextField(15);
        txtNombre = new TextField(15);
        txtDireccion = new TextField(15);
        txtTelefono = new TextField(15);
        txtProductos = new TextField(15);
        txtMostrar = new TextField("Aquí se mostrara la lista de proveedores...");
        txtMostrar.setEditable(false);

        pSuperior.add(titulo);

        pCentral.add(lbId);
        pCentral.add(txtId);
        pCentral.add(lbNombre);
        pCentral.add(txtNombre);
        pCentral.add(lbDireccion);
        pCentral.add(txtDireccion);
        pCentral.add(lbTelefono);
        pCentral.add(txtTelefono);
        pCentral.add(lbProductos);
        pCentral.add(txtProductos);
        pCentral.add(btnAgregar);
        pCentral.add(btnGuardar);

        pInferior.add(btnListar);
        pInferior.add(txtMostrar);

        pGeneral.add(pSuperior, BorderLayout.NORTH);
        pGeneral.add(pCentral, BorderLayout.CENTER);
        pGeneral.add(pInferior, BorderLayout.SOUTH);

        add(pGeneral, BorderLayout.CENTER);

        setVisible(true);

    }
}
