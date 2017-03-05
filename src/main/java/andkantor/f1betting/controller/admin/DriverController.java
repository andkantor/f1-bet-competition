package andkantor.f1betting.controller.admin;

import andkantor.f1betting.entity.Driver;
import andkantor.f1betting.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/admin/driver")
public class DriverController {

    @Autowired
    DriverRepository driverRepository;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String getCreate(Driver driver, Model model) {
        model.addAttribute("driver", driver);
        return "admin/driver/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String postCreate(@Valid Driver driver) {
        driverRepository.save(driver);
        return "redirect:/admin/driver/list";
    }

    @RequestMapping("/list")
    public String list(Model model) {
        Iterable<Driver> drivers = driverRepository.findAll();
        model.addAttribute("drivers", drivers);
        return "admin/driver/list";
    }

    @RequestMapping("/{id}/view")
    public String view(@PathVariable Long id, Model model) {
        Driver driver = driverRepository.findOne(id);
        model.addAttribute("driver", driver);
        return "admin/driver/view";
    }
}
