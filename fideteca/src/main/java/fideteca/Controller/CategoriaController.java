package fideteca.Controller;

import fideteca.domain.Categoria;
import fideteca.services.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/listado")
    public String listado(Model model) {
        var categorias = categoriaService.getCategorias();
        model.addAttribute("categorias", categorias);
        return "/categoria/listado";
    }

    @GetMapping("/nuevo")
    public String categoriaNueva(Categoria categoria) {
        return "/categoria/modifica";
    }

    @PostMapping("/guardar")
    public String guardarCategoria(@Valid Categoria categoria,
            BindingResult result,
            RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return "/categoria/modifica";
        }
        categoriaService.save(categoria);
        redirect.addFlashAttribute("msg", "Categoria guardada correctamente");
        return "redirect:/categoria/listado";
    }

    @GetMapping("/editar/{id}")
    public String editarCategoria(@PathVariable("id") Long id, Model model) {
        var categoria = categoriaService.getCategoria(id);
        model.addAttribute("categoria", categoria);
        return "/categoria/modifica";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarCategoria(@PathVariable("id") Long id, RedirectAttributes redirect) {
        categoriaService.delete(id);
        redirect.addFlashAttribute("msg", "Categoria eliminada correctamente");
        return "redirect:/categoria/listado";
    }
}