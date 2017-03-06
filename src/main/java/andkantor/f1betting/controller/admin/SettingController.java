package andkantor.f1betting.controller.admin;

import andkantor.f1betting.model.setting.Configuration;
import andkantor.f1betting.model.setting.ConfigurationManager;
import andkantor.f1betting.repository.SeasonRepository;
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

    @Autowired
    ConfigurationManager configurationManager;

    @Autowired
    SeasonRepository seasonRepository;

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String settings(Model model) {
        model.addAttribute("configuration", configurationManager.getConfiguration());
        model.addAttribute("seasons", seasonRepository.findAll());
        return "admin/setting/form";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveSettings(@ModelAttribute Configuration configuration) {
        configurationManager.save(configuration);
        return "redirect:/admin/settings/edit";
    }

}
