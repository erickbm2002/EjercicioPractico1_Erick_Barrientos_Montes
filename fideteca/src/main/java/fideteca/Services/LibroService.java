package fideteca.Services;

import fideteca.domain.Libro;
import fideteca.repository.LibroRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;
    
    @Transactional(readOnly=true)
    public List<Libro> getLibros() {
        return libroRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public Optional<Libro> getLibro(Integer id) {
        return libroRepository.findById(id);
    }

    @Transactional
    public void save(Libro libro) {
        libroRepository.save(libro);
    }

    @Transactional
    public void delete(Integer id) {
        // Verifica si el libro existe antes de intentar eliminarlo
        if (!libroRepository.existsById(id)) {
            // Lanza una excepción para indicar que el libro no fue encontrado
            throw new IllegalArgumentException("El libro con ID " + id + " no existe.");
        }
        libroRepository.deleteById(id);
    }
    
    // Métodos de búsqueda adicionales
    @Transactional(readOnly = true)
    public List<Libro> buscarPorTitulo(String titulo) {
        return libroRepository.findByTituloContainingIgnoreCase(titulo);
    }
    
    @Transactional(readOnly = true)
    public List<Libro> buscarPorAutor(String autor) {
        return libroRepository.findByAutorContainingIgnoreCase(autor);
    }
    
    @Transactional(readOnly = true)
    public List<Libro> getLibrosDisponibles() {
        return libroRepository.findByDisponibleTrue();
    }
    
    @Transactional(readOnly = true)
    public List<Libro> getLibrosPorCategoria(Integer categoriaId) {
        return libroRepository.findByCategoriaId(categoriaId);
    }
}