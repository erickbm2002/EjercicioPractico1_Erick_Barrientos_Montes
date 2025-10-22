package fideteca.Controller;

import fideteca.domain.Libro;
import fideteca.Services.LibroService;
import fideteca.Services.CategoriaService;
import jakarta.validation.Valid;
import java.util.Locale;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/libro")
public class LibroController {

    @Autowired
    private LibroService libroService;
    
    @Autowired
    private CategoriaService categoriaService;
    
    @Autowired
    private MessageSource messageSource;
    
    @GetMapping("/listado")
    public String listado(Model model) {
        var libros = libroService.getLibros();
        model.addAttribute("libros", libros);
        model.addAttribute("totalLibros", libros.size());
        return "/libro/listado";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        var categorias = categoriaService.getCategorias();
        model.addAttribute("libro", new Libro());
        model.addAttribute("categorias", categorias);
        return "/libro/modifica";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid Libro libro, RedirectAttributes redirectAttributes) {
        libroService.save(libro);        
        redirectAttributes.addFlashAttribute("todoOk", messageSource.getMessage("mensaje.actualizado", null, Locale.getDefault()));
        return "redirect:/libro/listado";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam Integer id, RedirectAttributes redirectAttributes) {
        String titulo = "todoOk";
        String detalle = "mensaje.eliminado";
        try {
            libroService.delete(id);          
        } catch (IllegalArgumentException e) {            
            titulo = "error"; // Captura la excepción de argumento inválido para el mensaje de "no existe"
            detalle = "libro.error01";
        } catch (Exception e) {            
            titulo = "error";  // Captura cualquier otra excepción inesperada
            detalle = "libro.error02";
        }
        redirectAttributes.addFlashAttribute(titulo, messageSource.getMessage(detalle, null, Locale.getDefault()));
        return "redirect:/libro/listado";
    }

    @GetMapping("/modificar/{id}")    
    public String modificar(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Libro> libroOpt = libroService.getLibro(id);
        if (libroOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", messageSource.getMessage("libro.error01", null, Locale.getDefault()));
            return "redirect:/libro/listado";
        }
        var categorias = categoriaService.getCategorias();
        model.addAttribute("libro", libroOpt.get());
        model.addAttribute("categorias", categorias);
        return "/libro/modifica";
    }

}