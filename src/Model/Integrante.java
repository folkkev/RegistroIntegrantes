package Model;

public class Integrante {

    String Nombres;
    String Apellidos;
    String RepositorioTrabajo;
    Boolean TipoEstudiante;
    Ingenieria ProyectoEducativo;


    public enum Ingenieria {
        Sistemas,
        Electronica,
        Indusplay,
        Civil,
    }

    public Ingenieria getProyectoEducativo() {
        return ProyectoEducativo;
    }

    public void setProyectoEducativo(Ingenieria proyectoEducativo) {
        ProyectoEducativo = proyectoEducativo;
    }

    public Boolean getTipoEstudiante() {
        return TipoEstudiante;
    }

    public void setTipoEstudiante(Boolean tipoEstudiante) {
        TipoEstudiante = tipoEstudiante;
    }

    public String getRepositorioTrabajo() {
        return RepositorioTrabajo;
    }

    public void setRepositorioTrabajo(String repositorioTrabajo) {
        RepositorioTrabajo = repositorioTrabajo;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String apellidos) {
        Apellidos = apellidos;
    }

    public String getNombres() {
        return Nombres;
    }

    public void setNombres(String nombres) {
        Nombres = nombres;


    }

    public Integrante(String nombres, String apellidos, String repositorioTrabajo, Boolean tipoEstudiante, Ingenieria proyectoEducativo) {
        Nombres = nombres;
        Apellidos = apellidos;
        RepositorioTrabajo = repositorioTrabajo;
        TipoEstudiante = tipoEstudiante;
        ProyectoEducativo = proyectoEducativo;
    }
}
