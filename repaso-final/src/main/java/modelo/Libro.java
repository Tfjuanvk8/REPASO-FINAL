package modelo;

public class Libro extends Recurso {

    private final String autor;
    private final String ISBN;

    public Libro(String codigo, String titulo, int anioPublicacion, String autor, String ISBN) {
        super(codigo, titulo, anioPublicacion);
        this.autor = autor;
        this.ISBN = ISBN;
    }

    public String getAutor() {
        return autor;
    }

    public String getISBN() {
        return ISBN;
    }

    @Override
    public boolean esPrestable() {
        return getAnioPublicacion() >= 1923; //Determina si el libro es anterior a 1923 para prestrarlo
    }

    @Override
    public String toString() {
        return "Libro{" + super.toString() +
                "autor='" + autor + '\'' +
                ", ISBN='" + ISBN + '\'' +
                '}';
    }
}
