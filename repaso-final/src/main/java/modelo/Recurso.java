package modelo;

import excepciones.RecursoAntiguoException;
import excepciones.RecursoNoEncontradoException;
import excepciones.RecursoPrestadoException;

import java.util.Objects;

public abstract class Recurso {

    private final String codigo;
    private final String titulo;
    private final int anioPublicacion;
    private boolean disponible;
    private String usuarioPrestamo;

    public String getCodigo() {
        return codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getAnioPublicacion() {
        return anioPublicacion;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public String getUsuarioPrestamo() {
        return usuarioPrestamo;
    }

    public Recurso(String codigo, String titulo, int anioPublicacion){
        this.codigo = codigo;
        this.titulo = titulo;
        this.anioPublicacion = anioPublicacion;
        this.disponible = true;
        this.usuarioPrestamo = null;
    }

    //Solamente los recursos fisicos puedem implementar un prestamo

    public void prestar(String usuario) throws RecursoPrestadoException, RecursoAntiguoException {

        if (anioPublicacion < 1923) {
            throw new RecursoAntiguoException("El recurso es anterior a 1923 y no  puede ser prestado");
        }

        if (!disponible) {
            throw new RecursoPrestadoException("El recurso ya se encuentra prestado");
        }

        if (usuario == null || usuario.isBlank()) {
            throw new IllegalArgumentException("Usuaririo inválido");
        }

        disponible = false;
        usuarioPrestamo = usuario;
    }

    public void devolver() {

        disponible = true;
        usuarioPrestamo = null;
    }

    //Cada subclase determina si puedde prestarse

    public abstract boolean esPrestable();

    //Se compara por el código, es el identificador unico


    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Recurso recurso)) return false;
        return Objects.equals(codigo, recurso.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(codigo);
    }

    //Facilitamos el mostrar la informacion del rescurso en consola

    @Override
    public String toString() {
        return "Recurso:" +
                "\nCodigo: " + codigo +
                "\nTitulo: " + titulo +
                "\nAño de publicacion: " + anioPublicacion +
                "\nDisponible: " + disponible +
                "\nUsuarioPrestamo: " + usuarioPrestamo;
    }
}
