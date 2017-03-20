package andkantor.f1betting.controller.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ExceptionHandlerController {

    private static Logger logger = LoggerFactory.getLogger(ExceptionHandlerController.class);

    @ExceptionHandler(AccessDeniedException.class)
    public String forbidden() {
        logger.error("Forbidden");

        return "error/403";
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public String notFound() {
        logger.error("Page not found");

        return "error/404";
    }

    @ExceptionHandler(Exception.class)
    public String exception(Exception exception) {
        logger.error("Internal server error", exception);

        return "error/500";
    }
}