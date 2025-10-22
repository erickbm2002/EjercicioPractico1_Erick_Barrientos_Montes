package fideteca.repository;

import fideteca.domain.Libro;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepository extends JpaRepository<Libro, Integer> {
    
    // Buscar libros por título (parcial, sin importar mayúsculas/minúsculas)
    public List<Libro> findByTituloContainingIgnoreCase(String titulo);
    
    // Buscar libros por autor (parcial, sin importar mayúsculas/minúsculas)
    public List<Libro> findByAutorContainingIgnoreCase(String autor);
    
    // Buscar libros disponibles
    public List<Libro> findByDisponibleTrue();
    
    // Buscar libros por categoría
    public List<Libro> findByCategoriaId(Integer categoriaId);
    
    // Buscar libros disponibles de una categoría específica
    public List<Libro> findByCategoriaIdAndDisponibleTrue(Integer categoriaId);

}