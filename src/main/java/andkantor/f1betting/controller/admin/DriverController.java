package andkantor.f1betting.controller.admin;

import andkantor.f1betting.entity.Driver;
import andkantor.f1betting.repository.DriverRepository;
import andkantor.f1betting.repository.FinalPositionRepository;
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

    @Autowired
    FinalPositionRepository finalPositionRepository;

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

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Driver driver, Model model) {
        model.addAttribute("driver", driver);
        return "admin/driver/form";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveNew(@Valid Driver driver) {
        driverRepository.save(driver);
        return "redirect:/admin/driver/list";
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public String edit(@PathVariable Long id, Model model) {
        Driver driver = driverRepository.findOne(id);
        model.addAttribute("driver", driver);
        return "admin/driver/form";
    }

    @RequestMapping(value = "/{id}/save", method = RequestMethod.POST)
    public String saveExisting(@PathVariable Long id, @Valid Driver driver) {
        driver.setId(id);
        driverRepository.save(driver);
        return "redirect:/admin/driver/list";
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable Long id) {
        Driver driver = driverRepository.findOne(id);
        finalPositionRepository.deleteByDriver(driver);
        driverRepository.delete(driver);
        return "redirect:/admin/driver/list";
    }
}
