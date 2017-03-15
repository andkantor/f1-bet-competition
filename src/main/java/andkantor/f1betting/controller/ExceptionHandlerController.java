package andkantor.f1betting.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandlerController {

    private static Logger logger = LoggerFactory.getLogger(ExceptionHandlerController.class);

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String exception(final Throwable throwable, final Model model) {
        logger.error("Exception during execution of SpringSecurity application", throwable);
        String errorMessage = (throwable != null ? throwable.getMessage() : "Unknown error");
        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("httpStatus", HttpStatus.INTERNAL_SERVER_ERROR);
        return "error";
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String forbidden(final Model model) {
        logger.error("Forbidden");
        String errorMessage = "Forbidden";
        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("httpStatus", HttpStatus.FORBIDDEN);
        return "error";
    }
}