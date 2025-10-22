package fideteca.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;

@Data
@Entity
@Table(name="libro")
public class Libro implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    
    @Column(nullable=false, length=255)
    @NotNull
    @Size(max=255)
    private String titulo;
    
    @Column(nullable=false, length=200)
    @NotNull
    @Size(max=200)
    private String autor;
    
    @Column(length=20)
    @Size(max=20)
    private String isbn;
    
    @Column(columnDefinition = "TEXT")
    private String descripcion;
    
    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;
    
    @Column(name = "fecha_publicacion")
    private LocalDate fechaPublicacion;
    
    @Column(nullable = false)
    private Boolean disponible = true;
    
    @Column(precision = 10, scale = 2)
    @DecimalMin(value = "0.0", inclusive = true)
    private BigDecimal precio;

}