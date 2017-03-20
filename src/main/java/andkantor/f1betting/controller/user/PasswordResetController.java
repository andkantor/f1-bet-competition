package andkantor.f1betting.controller.user;

import andkantor.f1betting.entity.User;
import andkantor.f1betting.form.PasswordResetForm;
import andkantor.f1betting.form.error.InvalidOldPasswordError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class PasswordResetController extends BaseController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/password-reset", method = RequestMethod.GET)
    public String passwordReset(@ModelAttribute(name = "form") PasswordResetForm form) {
        return "security/password-form";
    }

    @RequestMapping(value = "/password-reset", method = RequestMethod.POST)
    public String passwordReset(@ModelAttribute(name = "form") @Valid PasswordResetForm form, BindingResult result) {
        User user = getUser();

        if (!passwordEncoder.matches(form.getOldPassword(), user.getPassword())) {
            result.addError(new InvalidOldPasswordError());
        }

        if (!result.hasErrors()) {
            String encodedNewPassword = passwordEncoder.encode(form.getPassword());
            user.setPassword(encodedNewPassword);
            userRepository.save(user);

            flash.setMessage("You have successfully reset you password");
        } else {
            return "security/password-form";
        }

        return "redirect:/home";
    }

}
