package ec.edu.est.poo.vista;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VentanaProveedor extends Frame implements ActionListener {
    private Panel pSuperior;
    private Panel pCentral;
    private Panel pInferior;
    private Panel pAgregar;
    private Panel pListado;

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

    private CardLayout cardLayout;

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
        pAgregar = new Panel(new GridLayout(6, 2, 10, 15));
        pListado = new Panel(new BorderLayout());
        pInferior = new Panel(cardLayout);

        titulo = new Label("MENU DE PROVEEDORES", Label.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 28));
        titulo.setForeground(new Color(50, 50, 50));

        btnAgregar = new Button("Agregar Proveedor");
        btnGuardar = new Button("Guardar");
        btnListar = new Button("Listar Proveedores");

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

        pListado.add(new Label("Aquí se mostrará a los proveedores"));
        pListado.add(txtMostrar, BorderLayout.CENTER);

        pSuperior.add(titulo);

        pCentral.add(btnAgregar);
        pCentral.add(btnListar);

        pAgregar.add(lbId);
        pAgregar.add(txtId);
        pAgregar.add(lbNombre);
        pAgregar.add(txtNombre);
        pAgregar.add(lbDireccion);
        pAgregar.add(txtDireccion);
        pAgregar.add(lbTelefono);
        pAgregar.add(txtTelefono);
        pAgregar.add(lbProductos);
        pAgregar.add(txtProductos);
        pAgregar.add(btnGuardar);
        pAgregar.add(new Label(""));

        pInferior.add(pAgregar, "Agregar");
        pInferior.add(pListado, "Listado");

        pGeneral.add(pSuperior, BorderLayout.NORTH);
        pGeneral.add(pCentral, BorderLayout.CENTER);
        pGeneral.add(pInferior, BorderLayout.SOUTH);

        add(pGeneral, BorderLayout.CENTER);

        setVisible(true);

        btnAgregar.addActionListener(this);
        btnListar.addActionListener(this);

        cardLayout.show(pInferior, "Formulario");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAgregar) {
            cardLayout.show(pInferior, "Agregar");
            txtId.setText("");
            txtNombre.setText("");
            txtDireccion.setText("");
            txtTelefono.setText("");
            txtProductos.setText("");
        } else if (e.getSource() == btnListar) {
            cardLayout.show(pInferior, "Listado");
        }
    }
}
