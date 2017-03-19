package andkantor.f1betting.controller.admin;

import andkantor.f1betting.entity.User;
import andkantor.f1betting.form.UserForm;
import andkantor.f1betting.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/admin/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/{username}/edit", method = RequestMethod.GET)
    public String edit(@PathVariable String username, Model model) {
        User user = userRepository.findByUsername(username);
        model.addAttribute("userForm", new UserForm(user));
        return "admin/user/form";
    }

    @RequestMapping(value = "/{username}/save", method = RequestMethod.POST)
    public String save(@PathVariable String username, @Valid UserForm userForm) {
        User user = userRepository.findByUsername(username);

        user.setUsername(userForm.getUsername());
        user.setEmail(userForm.getEmail());
        user.setEnabled(userForm.isEnabled());
        if (userForm.getPassword().length() > 0) {
            user.setPassword(passwordEncoder.encode(userForm.getPassword()));
        }

        userRepository.save(user);

        return "redirect:/admin/user/list";
    }

    @RequestMapping("/list")
    public String list(Model model) {
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "admin/user/list";
    }
}
