package fideteca.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data
@Entity
@Table(name="categoria")
public class Categoria implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    
    @Column(unique=true, nullable=false, length=100)
    @NotNull
    @Size(max=100)
    private String nombre;
    
    @Column(length=255)
    @Size(max=255)
    private String descripcion;
    
    // Relación de uno a muchos con la clase Libro
    // Sin "cascade" ni "orphanRemoval" para evitar la propagación de operaciones.
    @OneToMany(mappedBy = "categoria")
    private List<Libro> libros;

}