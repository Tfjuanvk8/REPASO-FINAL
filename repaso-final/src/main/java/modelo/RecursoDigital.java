package modelo;

import excepciones.RecursoAntiguoException;
import excepciones.RecursoPrestadoException;

public class RecursoDigital extends Recurso {

    private final String url;

    public RecursoDigital(String codigo, String titulo, int anioPublicacion, String url) {
        super(codigo, titulo, anioPublicacion);
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public boolean esPrestable() {
        return false;
    }

    @Override
    public void prestar(String usuario) throws RecursoPrestadoException, RecursoAntiguoException {
        throw new UnsupportedOperationException("Los recursos digitales solamente peueden consultarse en sala");
    }

    @Override
    public String toString() {
        return "RecursoDigital{" + super.toString() +
                "url='" + url + '\'' +
                '}';
    }
}
