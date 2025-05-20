package ec.edu.est.poo.vista;


import java.awt.*;
import java.awt.event.*;
import java.util.List;
import ec.edu.est.poo.modelos.SolicitudCompra;

public class VentanaListaSolicitudes  extends Frame{
    private Frame frame;
    private TextArea areaSolicitudes;
    private Button btnCerrar;

    public VentanaListaSolicitudes(List<SolicitudCompra> solicitudes) {
        frame = new Frame("Listado de Solicitudes");
        frame.setLayout(new BorderLayout());

        areaSolicitudes = new TextArea(20, 60);
        areaSolicitudes.setEditable(false);

        for (SolicitudCompra solicitud : solicitudes) {
            areaSolicitudes.append(solicitud.toString() + "\n\n");
        }

        btnCerrar = new Button("Cerrar");
        btnCerrar.addActionListener(e -> frame.dispose());

        frame.add(areaSolicitudes, BorderLayout.CENTER);
        frame.add(btnCerrar, BorderLayout.SOUTH);

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                frame.dispose();
            }
        });

        frame.pack();
        frame.setVisible(true);
    }
}
