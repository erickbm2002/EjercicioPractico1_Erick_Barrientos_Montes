package fideteca.domain;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "libro")
public class Libro implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 255)
    private String titulo;

    @NotBlank
    @Size(max = 200)
    private String autor;

    @Size(max = 20)
    private String isbn;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    private LocalDate fechaPublicacion;
    private boolean disponible;

    @DecimalMin("0.0")
    @Digits(integer = 10, fraction = 2)
    private BigDecimal precio;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}