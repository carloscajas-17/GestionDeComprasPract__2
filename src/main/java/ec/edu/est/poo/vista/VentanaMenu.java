package ec.edu.est.poo.vista;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VentanaMenu extends Frame implements ActionListener {
    private Panel general;
    private Panel pSuperior;
    private Panel pCentral;
    private Panel pInferior;
    private Label titulo;
    private Button btnEmpleado;
    private Button btnProveedor;
    private Button btnSolicitud;
    private Button btnSalir;

    public VentanaMenu() {
        setTitle("Gestión de Compras");
        setSize(400, 400);
        setLayout(new BorderLayout());
        setBackground(Color.LIGHT_GRAY);
        setLocationRelativeTo(null);

        general = new Panel(new BorderLayout());
        pSuperior = new Panel();
        pCentral = new Panel();
        pCentral.setLayout(new GridLayout(0,1,0,10));
        pInferior = new Panel();


        btnEmpleado = new Button("Empleado");
        btnEmpleado.setFont(new Font("SansSerif", Font.PLAIN, 18));

        btnProveedor = new Button("Proveedor");
        btnProveedor.setFont(new Font("SansSerif", Font.PLAIN, 18));

        btnSolicitud = new Button("Solicitud");
        btnSolicitud.setFont(new Font("SansSerif", Font.PLAIN, 18));

        btnSalir = new Button("Cerrar");
        btnSalir.setFont(new Font("SansSerif", Font.PLAIN, 18));
        btnSalir.addActionListener(this);

        titulo = new Label("GESTION DE COMPRAS", Label.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 28));
        titulo.setForeground(new Color(50, 50, 50));

        pSuperior.add(titulo);

        pCentral.add(new Label(""));
        pCentral.add(btnEmpleado);
        pCentral.add(btnProveedor);
        pCentral.add(btnSolicitud);
        pCentral.add(new Label(""));

        pInferior.add(btnSalir);

        general.add(pSuperior, BorderLayout.NORTH);
        general.add(pCentral, BorderLayout.CENTER);
        general.add(pInferior, BorderLayout.SOUTH);

        add(general, BorderLayout.CENTER);

        setVisible(true);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evento) {
                System.exit(0);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSalir) {
            System.exit(0);
        }
    }
}

