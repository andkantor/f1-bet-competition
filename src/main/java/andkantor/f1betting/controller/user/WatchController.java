package andkantor.f1betting.controller.user;

import andkantor.f1betting.entity.User;
import andkantor.f1betting.entity.Watch;
import andkantor.f1betting.form.WatchForm;
import andkantor.f1betting.repository.WatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/")
public class WatchController extends BaseController {

    @Autowired
    WatchRepository watchRepository;

    @RequestMapping(value = "/watch", method = RequestMethod.POST)
    public @ResponseBody String watch(@Valid WatchForm form) {
        User watched = userRepository.findByUsername(form.getUsername());
        if (watched == null) {
            return "failed";
        }
        Watch watch = new Watch(getUser(), watched);

        if (form.isWatch()) {
            watchRepository.save(watch);
        } else {
            watchRepository.delete(watch);
        }

        return "success";
    }

}
