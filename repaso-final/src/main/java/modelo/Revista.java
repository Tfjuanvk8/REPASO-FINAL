package modelo;

public class Revista extends Recurso {

    private final int numeroEdicion;
    private final String editorial;

    public Revista(String codigo, String titulo, int anioPublicacion, int numeroEdicion, String editorial) {
        super(codigo, titulo, anioPublicacion);
        this.numeroEdicion = numeroEdicion;
        this.editorial = editorial;
    }

    public int getNumeroEdicion() {
        return numeroEdicion;
    }

    public String getEditorial() {
        return editorial;
    }

    @Override
    public boolean esPrestable() {
        return getAnioPublicacion() >= 1923; //Determina si el libro es anterior a 1923 para prestrarlo
    }

    @Override
    public String toString() {
        return "Revista{" + super.toString() +
                "numeroEdicion=" + numeroEdicion +
                ", editorial='" + editorial + '\'' +
                '}';
    }
}
