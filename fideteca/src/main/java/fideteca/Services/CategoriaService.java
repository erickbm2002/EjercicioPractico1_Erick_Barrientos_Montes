package fideteca.Services;

import fideteca.domain.Libro;
import fideteca.service.LibroService;
import fideteca.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/libro")
public class CategoriaService {

    @Autowired
    private LibroService libroService;

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/listado")
    public String listado(Model model) {
        var libros = libroService.getLibros();
        model.addAttribute("libros", libros);
        return "/libro/listado";
    }

    @GetMapping("/nuevo")
    public String libroNuevo(Libro libro, Model model) {
        model.addAttribute("categorias", categoriaService.getCategorias());
        return "/libro/modifica";
    }

    @PostMapping("/guardar")
    public String guardarLibro(@Valid Libro libro,
            BindingResult result,
            Model model,
            RedirectAttributes redirect) {
        if (result.hasErrors()) {
            model.addAttribute("categorias", categoriaService.getCategorias());
            return "/libro/modifica";
        }
        libroService.save(libro);
        redirect.addFlashAttribute("msg", "Libro guardado correctamente");
        return "redirect:/libro/listado";
    }

    @GetMapping("/editar/{id}")
    public String editarLibro(@PathVariable("id") Long id, Model model) {
        model.addAttribute("categorias", categoriaService.getCategorias());
        model.addAttribute("libro", libroService.getLibro(id));
        return "/libro/modifica";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarLibro(@PathVariable("id") Long id, RedirectAttributes redirect) {
        libroService.delete(id);
        redirect.addFlashAttribute("msg", "Libro eliminado correctamente");
        return "redirect:/libro/listado";
    }
}