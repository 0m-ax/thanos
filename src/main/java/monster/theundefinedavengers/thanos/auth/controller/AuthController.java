package monster.theundefinedavengers.thanos.auth.controller;

import monster.theundefinedavengers.thanos.auth.model.User;
import monster.theundefinedavengers.thanos.auth.model.UserDto;
import monster.theundefinedavengers.thanos.auth.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping(path = "/auth")
public class AuthController {
    @Autowired
    UserServiceImpl userService;
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
            request.login(userDto.getUsername(),userDto.getPassword());
            model.addAttribute("e","no");
        }catch (ServletException e){
            model.addAttribute("e","yes");
        }
        return "login";
    }
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String showRegistrationForm(WebRequest request, Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "registration";
    }
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registerUserAccount(
            UserDto userDto,
            Model model) {

            model.addAttribute("user", userDto);
            User user = userService.registerNewUserAccount(userDto);
            return "registration";

    }
}
