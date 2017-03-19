package andkantor.f1betting.controller;

import andkantor.f1betting.entity.User;
import andkantor.f1betting.entity.UserRole;
import andkantor.f1betting.form.RegistrationForm;
import andkantor.f1betting.form.UserDetailsProvider;
import andkantor.f1betting.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class SecurityController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserDetailsProvider userDetailsProvider;

    @Autowired
    PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(@ModelAttribute(name = "form") RegistrationForm form) {
        return "security/register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String postRegister(@ModelAttribute(name = "form") @Valid RegistrationForm form, BindingResult result) {
        if (!result.hasErrors()) {
            User user = new User(form.getUsername(), passwordEncoder.encode(form.getPassword()), true);
            user.getUserRoles().add(new UserRole(user, "USER"));
            userRepository.save(user);

            loginRegisteredUser(user);
        } else {
            return "security/register";
        }

        return "redirect:/home";
    }

    @RequestMapping("/login")
    public String login(@RequestParam(required = false) String error, Model model) {
        model.addAttribute("loginError", error);
        return "security/login";
    }

    private void loginRegisteredUser(User user) {
        UserDetails userDetails = userDetailsProvider.loadUserByUsername(user.getUsername());
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

}
