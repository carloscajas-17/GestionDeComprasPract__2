package ec.edu.est.poo.vista;

import java.awt.*;

public class VentanaEmpleado extends Frame {

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
    private Label lbCargo;
    private Label lbDepartamento;

    private TextField txtMostrar;
    private TextField txtId;
    private TextField txtNombre;
    private TextField txtDireccion;
    private TextField txtTelefono;
    private TextField txtCargo;
    private TextField txtDepartamento;

    public VentanaEmpleado() {
        setTitle("Menú de Empleados");
        setSize(500, 600);
        setLayout(new BorderLayout());
        setBackground(Color.LIGHT_GRAY);
        setLocationRelativeTo(null);

        Panel pGeneral = new Panel();

        pSuperior = new Panel();
        pCentral = new Panel(new GridLayout(8, 2));
        pInferior = new Panel();

        titulo = new Label("MENU DE EMPLEADOS", Label.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 28));
        titulo.setForeground(new Color(50, 50, 50));

        btnAgregar = new Button("Agregar Empleado");
        btnGuardar = new Button("Guardar Empleado");
        btnListar = new Button("Listar Empleados");

        btnAgregar.setFont(new Font("SansSerif", Font.PLAIN, 16));
        btnGuardar.setFont(new Font("SansSerif", Font.PLAIN, 16));
        btnListar.setFont(new Font("SansSerif", Font.PLAIN, 16));

        lbId = new Label("Id: ");
        lbNombre = new Label("Nombre: ");
        lbDireccion = new Label("Dirección: ");
        lbTelefono = new Label("Teléfono: ");
        lbCargo = new Label("Cargo: ");
        lbDepartamento = new Label("Departamento: ");

        txtId = new TextField(15);
        txtNombre = new TextField(15);
        txtDireccion = new TextField(15);
        txtTelefono = new TextField(15);
        txtCargo = new TextField(15);
        txtDepartamento = new TextField(15);
        txtMostrar = new TextField("Aquí se mostrará la lista de empleados...");
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
        pCentral.add(lbCargo);
        pCentral.add(txtCargo);
        pCentral.add(lbDepartamento);
        pCentral.add(txtDepartamento);
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
