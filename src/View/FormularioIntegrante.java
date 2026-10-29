
package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

        public class FormularioIntegrante {

            // Contenedor principal
            private JFrame ventana;

            // Componentes del Formulario
            private JTextField txtNombres;
            private JTextField txtApellidos;
            private JTextField txtRepositorio;
            private JRadioButton rbNuevo;
            private JRadioButton rbAntiguo;
            private ButtonGroup bgTipoEstudiante;
            private JComboBox<String> cbProyectoEducativo;
            private JButton btnRegistrar;

            // Componentes para la Consulta
            private JTable tablaIntegrantes;
            private DefaultTableModel modeloTabla;

            public FormularioIntegrante() {
                iniciarVentana();
            }

            public void iniciarVentana() {
                ventana = new JFrame("Registro de Integrantes - MVC");
                ventana.setSize(750, 550);
                ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                ventana.setLocationRelativeTo(null);
                ventana.setLayout(new BorderLayout(10, 10));

                // Panel Principal con margen
                JPanel panelContenido = new JPanel(new BorderLayout(10, 10));
                panelContenido.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

                // 1. PANEL FORMULARIO (Norte / Arriba)
                JPanel panelFormulario = new JPanel(new GridLayout(6, 2, 8, 8));
                panelFormulario.setBorder(BorderFactory.createTitledBorder("Datos del Integrante"));

                // Campos de Texto
                panelFormulario.add(new JLabel("Nombres:"));
                txtNombres = new JTextField();
                panelFormulario.add(txtNombres);

                panelFormulario.add(new JLabel("Apellidos:"));
                txtApellidos = new JTextField();
                panelFormulario.add(txtApellidos);

                panelFormulario.add(new JLabel("Repositorio GitHub:"));
                txtRepositorio = new JTextField();
                panelFormulario.add(txtRepositorio);

                // Selección Única: Tipo de Estudiante
                panelFormulario.add(new JLabel("Tipo de Estudiante:"));
                JPanel panelRadio = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
                rbNuevo = new JRadioButton("Nuevo");
                rbAntiguo = new JRadioButton("Antiguo");
                bgTipoEstudiante = new ButtonGroup();
                bgTipoEstudiante.add(rbNuevo);
                bgTipoEstudiante.add(rbAntiguo);
                panelRadio.add(rbNuevo);
                panelRadio.add(rbAntiguo);
                panelFormulario.add(panelRadio);

                // ComboBox: Proyecto Educativo
                panelFormulario.add(new JLabel("Proyecto Educativo:"));
                String[] opcionesProyectos = {
                        "Ingeniería de Sistemas",
                        "Ingeniería Electrónica",
                        "Ingeniería Industrial",
                        "Ingeniería Civil"
                };
                cbProyectoEducativo = new JComboBox<>(opcionesProyectos);
                panelFormulario.add(cbProyectoEducativo);

                // Botón Registrar
                btnRegistrar = new JButton("Registrar Integrante");
                panelFormulario.add(new JLabel()); // Espacio vacío para alineación
                panelFormulario.add(btnRegistrar);

                // 2. PANEL TABLA / CONSULTA (Centro)
                JPanel panelTabla = new JPanel(new BorderLayout());
                panelTabla.setBorder(BorderFactory.createTitledBorder("Integrantes Registrados"));

                String[] columnas = {"Nombres", "Apellidos", "Repositorio", "Tipo", "Proyecto"};
                modeloTabla = new DefaultTableModel(columnas, 0) {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false; // Hacer la tabla de solo lectura
                    }
                };
                tablaIntegrantes = new JTable(modeloTabla);
                JScrollPane scrollTabla = new JScrollPane(tablaIntegrantes);
                panelTabla.add(scrollTabla, BorderLayout.CENTER);

                // Ensamblado
                panelContenido.add(panelFormulario, BorderLayout.NORTH);
                panelContenido.add(panelTabla, BorderLayout.CENTER);
                ventana.add(panelContenido);

                ventana.setVisible(true);
            }

            //  Métodos Getters para los datos del formulario
            public String getNombres() {
                return txtNombres.getText().trim();
            }

            public String getApellidos() {
                return txtApellidos.getText().trim();
            }

            public String getRepositorio() {
                return txtRepositorio.getText().trim();
            }

            /**
             * Retorna true si es Nuevo, false si es Antiguo o null si no seleccionó ninguno.
             */
            public Boolean isNuevoEstudiante() {
                if (rbNuevo.isSelected()) return true;
                if (rbAntiguo.isSelected()) return false;
                return null;
            }

            public String getProyectoSeleccionado() {
                return (String) cbProyectoEducativo.getSelectedItem();
            }

            public JButton getBtnRegistrar() {
                return btnRegistrar;
            }

            public DefaultTableModel getModeloTabla() {
                return modeloTabla;
            }

            // --- Métodos auxiliares de la interfaz ---

            public void limpiarCampos() {
                txtNombres.setText("");
                txtApellidos.setText("");
                txtRepositorio.setText("");
                bgTipoEstudiante.clearSelection();
                cbProyectoEducativo.setSelectedIndex(0);
            }

            public void mostrarMensaje(String mensaje) {
                JOptionPane.showMessageDialog(ventana, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
            }

            public void mostrarError(String mensaje) {
                JOptionPane.showMessageDialog(ventana, mensaje, "Error de Validación", JOptionPane.ERROR_MESSAGE);
            }

}
