package ec.edu.est.poo.vista;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VentanaSolicitud extends Frame implements ActionListener {
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
    private Label lbDepartamento;
    private Label lbEstado;
    private Label lbProducto;
    private Label lbCantidad;
    private Label lbPrecioU;
    private Label lbIva;
    private Label lbTotal;

    private TextField txtId;
    private TextField txtDepartamento;
    private TextField txtEstado;
    private TextField txtProducto;
    private TextField txtCantidad;
    private TextField txtPrecioU;
    private TextField txtIva;
    private TextField txtTotal;
    private TextField txtMostrar;

    private CardLayout cardLayout;

    public VentanaSolicitud() {
        setTitle("Menú de Solicitudes");
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
        pInferior = new Panel(cardLayout);

        titulo = new Label("MENU DE SOLICITUDES", Label.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 28));
        titulo.setForeground(new Color(50, 50, 50));

        btnAgregar = new Button("Agregar Solicitud");
        btnListar = new Button("Listar Solicitudes");
        btnGuardar = new Button("Guardar");

        lbId = new Label("Id: ");
        txtId = new TextField(15);
        lbDepartamento = new Label("Departamento: ");
        txtDepartamento = new TextField(15);
        lbEstado = new Label("Estado de solicitud: ");
        txtEstado = new TextField(15);
        lbProducto = new Label("Producto: ");
        txtProducto = new TextField(15);
        lbCantidad = new Label("Cantidad: ");
        txtCantidad = new TextField(15);
        lbPrecioU = new Label("Precio unitario: ");
        txtPrecioU = new TextField(15);
        lbIva = new Label("Iva: ");
        txtIva = new TextField(15);
        lbTotal = new Label("Precio total: ");
        txtTotal = new TextField(15);
        txtTotal.setEditable(false);

        txtMostrar = new TextField("Aquí se mostrará la lista de solicitudes...");
        txtMostrar.setEditable(false);

        pListado.add(new Label("Aquí se mostrará la lista de solicitudes...", Label.CENTER));
        pListado.add(txtMostrar, BorderLayout.CENTER);

        pSuperior.add(titulo);

        pCentral.add(btnAgregar);
        pCentral.add(btnListar);

        pAgregar.add(lbId);
        pAgregar.add(txtId);
        pAgregar.add(lbDepartamento);
        pAgregar.add(txtDepartamento);
        pAgregar.add(lbEstado);
        pAgregar.add(txtEstado);
        pAgregar.add(lbProducto);
        pAgregar.add(txtProducto);
        pAgregar.add(lbCantidad);
        pAgregar.add(txtCantidad);
        pAgregar.add(lbPrecioU);
        pAgregar.add(txtPrecioU);
        pAgregar.add(lbIva);
        pAgregar.add(txtIva);
        pAgregar.add(lbTotal);
        pAgregar.add(txtTotal);
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


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAgregar) {
            cardLayout.show(pInferior, "Agregar");
            txtId.setText("");
            txtDepartamento.setText("");
            txtEstado.setText("");
            txtProducto.setText("");
            txtCantidad.setText("");
            txtPrecioU.setText("");
            txtIva.setText("");
            txtTotal.setText("");
        } else if (e.getSource() == btnListar) {
            cardLayout.show(pInferior, "Listado");
        }
    }
}
