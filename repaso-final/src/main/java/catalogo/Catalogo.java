package catalogo;

import excepciones.RecursoAntiguoException;
import modelo.*;
import excepciones.RecursoNoEncontradoException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Catalogo <T extends Recurso> {

    private final ArrayList <T> recursos;

    public Catalogo() {
        recursos = new ArrayList<>();
    }

    //Agrega un recurso si no existe otro con el mismo código

    public void agregar (T recurso) {

        if (recurso == null)  {
            throw new IllegalArgumentException("Recurso inválido");
        }

        if (recursos.contains(recurso)) {
            throw new IllegalArgumentException("Ya existe un recurso con ese codigo");
        }
        //El contains verifica por medio del metodo equals personalizado que yo haya definido para compararlos y ver
        // si ya el recurso a agregar lo contiene la lista

        recursos.add(recurso);
    }

    //Elimina un recurso buscando su codigo

    public boolean eliminar (String codigo) {
        return recursos.removeIf (recurso -> recurso.getCodigo().equalsIgnoreCase(codigo)); //Ignore case para comparar sin importar los casos especificos, como
        // por ejemplo, mayusculas o minusculas
    }

    //Busca por código y lanza una excepcion si no existe
    public T buscarCodigo(String codigo) throws RecursoNoEncontradoException {
        return recursos.stream()
                .filter(r -> r.getCodigo().equalsIgnoreCase(codigo))
                .findFirst()
                .orElseThrow(() -> new RecursoNoEncontradoException("No eciste un recurso con codigo: " + codigo));
    }

    public List<T> buscarTitulo (String fragmento) {

        return recursos.stream()
                .filter(r -> r.getTitulo().toLowerCase().contains(fragmento.toLowerCase()))
                .toList();
    }

    //Devuelve solamente recursos que pueden prestarse y estan disponibles
    public List<T> listarDisponiblesPrestamo() {

        return recursos.stream()
                .filter(r -> r.esPrestable() && r.isDisponible())
                .toList();
    }

    // Obtiene los títulos de los libros disponibles en mayúsculas.
    public List<String> titulosLibrosDisponibles() {

        return recursos.stream()
                .filter(r -> r instanceof Libro)
                .filter(r -> r.isDisponible() && r.esPrestable())
                .map(r -> r.getTitulo().toUpperCase())
                .toList();
    }

    //Ordena por año descendente y, en caso de empate, por titulo
    public List <T> ordenarPorAnioYTitulo() {

        return recursos.stream()
                .sorted(Comparator.comparingInt((T r) -> r.getAnioPublicacion()
                                ).reversed()
                                .thenComparing(Recurso::getTitulo)
                )
                .toList();

    }

    //Cuenta los recursos prestados de una clase especifica
    public long contarPrestados (Class <? extends T> tipo) {

        return recursos.stream()
                .filter(tipo::isInstance)
                .filter(r -> !r.isDisponible())  //filtramos por recursos que no esten disponibles, es decir que esten filtrados
                .count();
    }



    //Devuelve la cantidad etotal de recursos

    public int cantidadRecursos() {
        return recursos.size();
    }

    public void reportarUso() {

        long disponibles = recursos.stream()
                .filter(r -> r.esPrestable() && r.isDisponible())
                .count();

        long prestados = recursos.stream()
                .filter(r -> !r.isDisponible())
                .count();

        long libros = recursos.stream()
                .filter(r -> r instanceof Libro)
                .count();

        long revistas = recursos.stream()
                .filter(r -> r instanceof Revista)
                .count();

        long digitales = recursos.stream()
                .filter(r-> r instanceof RecursoDigital)
                .count();

        System.out.println("\n===== REPORTE DE BIBLIOTECA =====");
        System.out.println("Total de recursos: " + recursos.size());
        System.out.println("Libros: " + libros);
        System.out.println("Revistas: " + revistas);
        System.out.println("Recursos digitales: " + digitales);
        System.out.println("Disponibles para préstamo: " + disponibles);
        System.out.println("Actualmente prestados: " + prestados);
        System.out.println("================================\n");
    }
}
