package monster.theundefinedavengers.thanos.auth.controller;

import monster.theundefinedavengers.thanos.auth.model.User;
import monster.theundefinedavengers.thanos.auth.model.UserDto;
import monster.theundefinedavengers.thanos.auth.model.UserRegistrationDto;
import monster.theundefinedavengers.thanos.auth.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(path = "/auth")
public class AuthController {

    @Autowired
    UserServiceImpl userService;

    @ExceptionHandler(BindException.class)
    public String validationError(Model model) {
        UserRegistrationDto userRegistrationDto = new UserRegistrationDto();
        model.addAttribute("user", userRegistrationDto);
        model.addAttribute("error",true);
        return "registration";
    }

    @PreAuthorize("hasAuthority('USER_ROLE')")
    @GetMapping(value="/test")
    public String test() throws ServletException {
        return "test";
    }

    @GetMapping(value="/login")
    public String login() throws ServletException {
        return "login";
    }

    @PostMapping(value="/login")
    public String login(UserDto userDto, HttpServletRequest request,Model model) {
        try {
            request.login(userDto.getEmail(),userDto.getPassword());
            model.addAttribute("e","no");
        }catch (ServletException e){
            model.addAttribute("e","yes");
        }
        return "login";
    }

    @GetMapping(value = "/registration")
    public String showRegistrationForm(WebRequest request, Model model) {
        UserRegistrationDto userRegistrationDto = new UserRegistrationDto();
        model.addAttribute("user", userRegistrationDto);
        return "registration";
    }

    @PostMapping(value = "/registration")
    public String registerUserAccount(@Valid UserRegistrationDto userRegistrationDto, Model model) {

            User user = userService.registerNewUserAccount(userRegistrationDto);
            return "redirect:login";
    }

    @RequestMapping(value="/profile")
    public String showProfile() {
        return "profile";
    }
}
