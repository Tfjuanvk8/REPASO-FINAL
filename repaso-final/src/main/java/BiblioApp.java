import modelo.*;
import catalogo.Catalogo;
import excepciones.*;

public class BiblioApp {

    public static void main(String[] args) {

        Catalogo<Recurso> catalogo = new Catalogo<>();

        // 1. Crear dos libros.
        Libro libro1 = new Libro("L001", "Java desde cero", 2024, "Juan Perez", "9781234567890");

        Libro libro2 = new Libro("L002", "Historia de la programacion", 1920, "Carlos Gomez", "9780987654321");

        // 2. Crear dos revistas.
        Revista revista1 = new Revista("R001", "Ciencia y tecnologia", 2023, 15, "Editorial Alfa");

        Revista revista2 = new Revista("R002", "Investigacion actual", 2025, 20, "Editorial Beta");

        // 3. Crear dos recursos digitales.
        RecursoDigital digital1 = new RecursoDigital("D001", "Curso de Java", 2024, "https://www.ejemplo.com/java");

        RecursoDigital digital2 = new RecursoDigital("D002", "Bases de datos", 2022, "https://www.ejemplo.com/bases");

        // 4. Agregar los seis recursos.
        catalogo.agregar(libro1);
        catalogo.agregar(libro2);
        catalogo.agregar(revista1);
        catalogo.agregar(revista2);
        catalogo.agregar(digital1);
        catalogo.agregar(digital2);

        System.out.println("RECURSOS REGISTRADOS: " + catalogo.cantidadRecursos());

        // 5. Realizar un préstamo.
        System.out.println("\n===== PRESTAMOS =====");

        try {
            libro1.prestar("Juan");

            System.out.println("Prestamo realizado correctamente.");
            System.out.println("Usuario: " + libro1.getUsuarioPrestamo());

            libro1.prestar("Andres");

        } catch (RecursoPrestadoException | RecursoAntiguoException e) {
            System.out.println("Error: " + e.getMessage());
        }

// 6. Contar préstamos antes de devolver.
        System.out.println("\n===== RECURSOS PRESTADOS =====");

        System.out.println("Libros prestados: " +
                catalogo.contarPrestados(Libro.class));

        System.out.println("Revistas prestadas: " +
                catalogo.contarPrestados(Revista.class));

        System.out.println("Digitales prestados: " +
                catalogo.contarPrestados(RecursoDigital.class));

        // 7. Devolver el libro.
        libro1.devolver();

        System.out.println("Libro devuelto correctamente.");
        System.out.println("Disponible: " + libro1.isDisponible());

        // 8. Intentar prestar un recurso anterior a 1923.
        System.out.println("\n===== RECURSO ANTIGUO =====");

        try {

            libro2.prestar("Carlos");

        } catch (RecursoAntiguoException | RecursoPrestadoException e) {

            System.out.println("Error: " + e.getMessage());

        }

        // 9. Buscar por código.
        System.out.println("\n===== BUSQUEDA POR CODIGO =====");

        try {

            Recurso encontrado = catalogo.buscarCodigo("R001");

            System.out.println(encontrado);

        } catch (RecursoNoEncontradoException e) {

            System.out.println(e.getMessage());

        }

        // 10. Buscar por fragmento de título.
        System.out.println("\n===== BUSQUEDA POR TITULO =====");

        catalogo.buscarTitulo("java")
                .forEach(System.out::println);

        // 11. Mostrar libros disponibles en mayúsculas.
        System.out.println("\n===== LIBROS DISPONIBLES =====");

        catalogo.titulosLibrosDisponibles()
                .forEach(System.out::println);

        // 12. Mostrar recursos disponibles para préstamo.
        System.out.println("\n===== DISPONIBLES PARA PRESTAMO =====");

        catalogo.listarDisponiblesPrestamo()
                .forEach(System.out::println);

        // 13. Ordenar por año y título.
        System.out.println("\n===== RECURSOS ORDENADOS =====");

        catalogo.ordenarPorAnioYTitulo()
                .forEach(System.out::println);

        // 14. Contar préstamos por tipo.
        System.out.println("\n===== RECURSOS PRESTADOS =====");

        System.out.println("Libros prestados: "
                + catalogo.contarPrestados(Libro.class));

        System.out.println("Revistas prestadas: "
                + catalogo.contarPrestados(Revista.class));

        System.out.println("Digitales prestados: "
                + catalogo.contarPrestados(RecursoDigital.class)); //Cuenta los receursos pertenecientes a la clase solicitada

        // 15. Reporte general.
        catalogo.reportarUso();
    }
}