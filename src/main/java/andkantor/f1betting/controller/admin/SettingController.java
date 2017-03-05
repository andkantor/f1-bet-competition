package andkantor.f1betting.controller.admin;

import andkantor.f1betting.model.Configuration;
import andkantor.f1betting.repository.SettingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/admin/settings")
public class SettingController {

    @Autowired
    SettingRepository settingRepository;

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String settings(Model model) {
        Configuration configuration = new Configuration(settingRepository.findAll());
        model.addAttribute("configuration", configuration);
        return "admin/setting/form";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveSettings(@ModelAttribute Configuration configuration) {
        configuration.toSettingList()
                .forEach(settingRepository::save);
        return "redirect:/admin/settings/edit";
    }

}
