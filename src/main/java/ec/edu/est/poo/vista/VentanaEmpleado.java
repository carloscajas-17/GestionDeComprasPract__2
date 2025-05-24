package ec.edu.est.poo.vista;

import ec.edu.est.poo.modelos.Departamento;
import ec.edu.est.poo.modelos.Empleado;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class VentanaEmpleado extends Frame implements ActionListener, ItemListener {
    private Panel pSuperior;
    private Panel pCentral;
    private Panel pInferior;
    private Panel pAgregar;
    private Panel pListado;
    private Panel pBuscar;

    private Button btnAgregar;
    private Button btnGuardar;
    private Button btnListar;
    private Button btnBuscarDep;
    private Button btnIrBuscar;
    private Button btnBuscar;

    private Label titulo;
    private Label lbId;
    private Label lbNombre;
    private Label lbDireccion;
    private Label lbTelefono;
    private Label lbCargo;
    private Label lbIdDep;
    private Label lbDepartamento;

    private Label lbCriterio;

    private TextArea txtMostrar;
    private TextArea txtBusqueda;

    private TextField txtId;
    private TextField txtNombre;
    private TextField txtDireccion;
    private TextField txtTelefono;
    private TextField txtCargo;
    private TextField txtDepartamento;

    private Choice chDepartamento;

    private TextField txtCriterio;

    private CardLayout cardLayout;

    private List<Empleado> listaEmpleados = new ArrayList<>();
    private List<Departamento> departamentoList = new ArrayList<>();

    public VentanaEmpleado() {
        setTitle("Menú de Empleados");
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
        pAgregar = new Panel(new GridLayout(8, 2, 10, 15));
        pListado = new Panel(new BorderLayout());
        pBuscar = new Panel(new GridLayout(4, 1, 10, 15));
        pInferior = new Panel(cardLayout);

        pSuperior.setBackground(new Color(247, 249, 249));

        titulo = new Label("MENU DE EMPLEADOS", Label.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 28));
        titulo.setForeground(new Color(50, 50, 50));

        btnAgregar = new Button("Agregar Empleado");
        btnListar = new Button("Listar Empleados");
        btnBuscar = new Button("Buscar");

        btnGuardar = new Button("Guardar");

        btnIrBuscar = new Button("Buscar Empleado");

        lbId = new Label("Id: ");
        lbNombre = new Label("Nombre: ");
        lbDireccion = new Label("Dirección: ");
        lbTelefono = new Label("Teléfono: ");
        lbCargo = new Label("Cargo: ");
        lbIdDep = new Label("Departamento: ");

        lbCriterio = new Label("ID a buscar: ");

        txtId = new TextField(15);
        txtNombre = new TextField(15);
        txtDireccion = new TextField(15);
        txtTelefono = new TextField(15);
        txtCargo = new TextField(15);

        chDepartamento = new Choice();

        txtCriterio = new TextField(15);

        txtMostrar = new TextArea("Aquí se mostrará la lista de empleados...", 5, 30);
        txtMostrar.setEditable(false);

        txtBusqueda = new TextArea("Resultado de las búsqueda...", 5, 30);
        txtBusqueda.setEditable(false);

        pListado.add(new Label("Aquí se mostrará a los empleados", Label.CENTER));
        pListado.add(txtMostrar, BorderLayout.CENTER);

        pCentral.add(btnAgregar);
        pCentral.add(btnListar);
        pCentral.add(btnIrBuscar);

        pSuperior.add(titulo);

        pAgregar.add(lbId);
        pAgregar.add(txtId);
        pAgregar.add(lbNombre);
        pAgregar.add(txtNombre);
        pAgregar.add(lbDireccion);
        pAgregar.add(txtDireccion);
        pAgregar.add(lbTelefono);
        pAgregar.add(txtTelefono);
        pAgregar.add(lbCargo);
        pAgregar.add(txtCargo);
        pAgregar.add(lbIdDep);
        pAgregar.add(chDepartamento);
        pAgregar.add(new Label(""));
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
        chDepartamento.addItemListener(this);

        cargarDepartamentos();
        poblarChoices();

        cardLayout.show(pInferior, "Formulario");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAgregar) {
            cardLayout.show(pInferior, "Agregar");
            limpiarFormulario();
        } else if (e.getSource() == btnListar) {
            cardLayout.show(pInferior, "Listado");
            mostrarLista();
        } else if (e.getSource() == btnGuardar) {
            guardarEmpleado();
        } else if (e.getSource() == btnIrBuscar) {
            cardLayout.show(pInferior, "Buscar");
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == chDepartamento) {

        }
    }

    private void limpiarFormulario() {
        txtId.setText("");
        txtNombre.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
        txtCargo.setText("");
        if (chDepartamento.getItemCount() > 0) {
            chDepartamento.select(0);
        }
    }

    private void mostrarLista() {
        StringBuilder sb = new StringBuilder();
        if (listaEmpleados.isEmpty()) {
            sb.append("No hay empleados...");
        } else {
            for (Empleado empleado : listaEmpleados) {
                sb.append(empleado.toString());
                sb.append("\n----------\n");
            }
            txtMostrar.setText(sb.toString());
        }
    }

    private void guardarEmpleado() {
        try {
            int id = Integer.parseInt(txtId.getText());
            String nombre = txtNombre.getText();
            String direccion = txtDireccion.getText();
            String telefono = txtTelefono.getText();
            String cargo = txtCargo.getText();
            if (departamentoList.isEmpty() || chDepartamento.getSelectedIndex() == -1) {
                JOptionPane.showMessageDialog(this, "Error");
                return;
            }
            String idDepartamentoSt = chDepartamento.getSelectedItem();
            int idDepartamentoInt = Integer.parseInt(idDepartamentoSt);
            Departamento departamentoSeleccionado = null;
            for (Departamento dep : departamentoList) {
                if (dep.getId() == idDepartamentoInt) {
                    departamentoSeleccionado = dep;
                    break;
                }
            }
            if (departamentoSeleccionado == null) {
                JOptionPane.showMessageDialog(this, "Error", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            boolean idDuplicado = false;
            for (int i = 0; i < listaEmpleados.size(); i++) {
                Empleado emp = listaEmpleados.get(i);
                if (emp.getId() == id) {
                    idDuplicado = true;
                    break;
                }
            }
            if (idDuplicado) {
                JOptionPane.showMessageDialog(this, "ERROR", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Empleado nuevoEmpleado = new Empleado(id, nombre, direccion, telefono, cargo, departamentoSeleccionado);
            listaEmpleados.add(nuevoEmpleado);
            JOptionPane.showMessageDialog(this, "Empleado guardado con éxito.",
                    "Guardar", JOptionPane.INFORMATION_MESSAGE);
            limpiarFormulario();
            cardLayout.show(pInferior, "Listado");
            mostrarLista();

        } catch (NumberFormatException error) {
            JOptionPane.showMessageDialog(this, "Error: Ingresa un ID válido.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarDepartamentos() {
        departamentoList.add(new Departamento(1, "IT"));
        departamentoList.add(new Departamento(2, "DP"));
        departamentoList.add(new Departamento(3, "XD"));
    }

    private void poblarChoices() {
        chDepartamento.removeAll();
        if (departamentoList.isEmpty()) {
            chDepartamento.add("No hay ID's");
            chDepartamento.setEnabled(false);
        } else {
            chDepartamento.setEnabled(true);
            for (Departamento dep : departamentoList) {
                chDepartamento.add(String.valueOf(dep.getId()));
            }
            chDepartamento.select(0);
        }
    }
}