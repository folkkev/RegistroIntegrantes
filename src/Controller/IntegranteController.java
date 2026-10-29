package Controller;

import Model.Integrante;
import Model.Integrante.Ingenieria;
import View.FormularioIntegrante;

import java.util.ArrayList;

    public class IntegranteController {

        private final FormularioIntegrante vista;
        private final ArrayList<Integrante> listaIntegrantes;

        public IntegranteController(FormularioIntegrante vista) {
            this.vista = vista;
            this.listaIntegrantes = new ArrayList<>();

            // Registrar el evento del botón
            this.vista.getBtnRegistrar().addActionListener(e -> registrarIntegrante());
        }

        private void registrarIntegrante() {
            String nombres = vista.getNombres();
            String apellidos = vista.getApellidos();
            String repo = vista.getRepositorio();
            Boolean esNuevo = vista.isNuevoEstudiante();
            String proyectoStr = vista.getProyectoSeleccionado();

            // Validaciones de campos obligatorios
            if (nombres.isEmpty() || apellidos.isEmpty() || repo.isEmpty()) {
                vista.mostrarError("Todos los campos de texto son obligatorios.");
                return;
            }

            if (esNuevo == null) {
                vista.mostrarError("Debe seleccionar el tipo de estudiante (Nuevo o Antiguo).");
                return;
            }

            // Validación básica de URL del repositorio
            if (!repo.toLowerCase().contains("github.com") && !repo.startsWith("http")) {
                vista.mostrarError("Ingrese una URL de repositorio válida (ej: https://github.com/usuario/repo).");
                return;
            }

            // Mapeo del String seleccionado al Enum Ingenieria de modelo
            Ingenieria proyecto = parseIngenieria(proyectoStr);

            // Crear objeto Integrante y guardar en ArrayList
            Integrante nuevoIntegrante = new Integrante(nombres, apellidos, repo, esNuevo, proyecto);
            listaIntegrantes.add(nuevoIntegrante);

            // Actualizar la tabla de consulta
            vista.getModeloTabla().addRow(new Object[]{
                    nuevoIntegrante.getNombres(),
                    nuevoIntegrante.getApellidos(),
                    nuevoIntegrante.getRepositorioTrabajo(),
                    nuevoIntegrante.getTipoEstudiante() ? "Nuevo" : "Antiguo",
                    nuevoIntegrante.getProyectoEducativo()
            });

            //Confirmar y limpiar
            vista.mostrarMensaje("¡Integrante registrado exitosamente!");
            vista.limpiarCampos();
        }

        private Ingenieria parseIngenieria(String nombre) {
            switch (nombre) {
                case "Ingeniería Electrónica":
                    return Ingenieria.Electronica;
                case "Ingeniería Industrial":
                    return Ingenieria.Indusplay;
                case "Ingeniería Civil":
                    return Ingenieria.Civil;
                default:
                    return Ingenieria.Sistemas;
            }
        }
}
