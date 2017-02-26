package andkantor.f1betting.controller.admin;

import andkantor.f1betting.model.race.Racer;
import andkantor.f1betting.repository.RacerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/admin/racer")
public class RacerController {

    @Autowired
    RacerRepository racerRepository;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String getCreate(Racer racer, Model model) {
        model.addAttribute("racer", racer);
        return "admin/racer/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String postCreate(@Valid Racer racer) {
        racerRepository.save(racer);
        return "redirect:/admin/racer/list";
    }

    @RequestMapping("/list")
    public String list(Model model) {
        Iterable<Racer> racers = racerRepository.findAll();
        model.addAttribute("racers", racers);
        return "admin/racer/list";
    }

    @RequestMapping("/{id}/view")
    public String view(@PathVariable Long id, Model model) {
        Racer racer = racerRepository.findOne(id);
        model.addAttribute("racer", racer);
        return "admin/racer/view";
    }
}
