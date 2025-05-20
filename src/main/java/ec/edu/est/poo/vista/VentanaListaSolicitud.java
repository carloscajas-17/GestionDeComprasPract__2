package ec.edu.est.poo.vista;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import ec.edu.est.poo.modelos.SolicitudCompra;

public class VentanaListaSolicitud extends Frame {
    private TextArea areaSolicitudes;
    private Button btnCerrar;

    public VentanaListaSolicitud(List<SolicitudCompra> solicitudes) {
        setTitle("Listado de Solicitudes");
        setLayout(new BorderLayout());

        areaSolicitudes = new TextArea(20, 60);
        areaSolicitudes.setEditable(false);

        for (SolicitudCompra solicitud : solicitudes) {
            areaSolicitudes.append(solicitud.toString() + "\n\n");
        }

        btnCerrar = new Button("Cerrar");
        btnCerrar.addActionListener(e -> dispose());

        add(areaSolicitudes, BorderLayout.CENTER);
        add(btnCerrar, BorderLayout.SOUTH);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        pack();
        setVisible(true);
    }
}
