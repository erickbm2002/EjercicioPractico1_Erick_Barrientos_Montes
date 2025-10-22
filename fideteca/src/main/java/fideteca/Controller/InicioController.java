package fideteca.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InicioController {

    @GetMapping("/")
    public String inicio(Model model) {
        return "index";
    }
    
    @GetMapping("/contacto")
    public String contacto(Model model) {
        return "contacto";
    }

}