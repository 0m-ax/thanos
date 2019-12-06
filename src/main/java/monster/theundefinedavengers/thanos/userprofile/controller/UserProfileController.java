package monster.theundefinedavengers.thanos.userprofile.controller;

import monster.theundefinedavengers.thanos.auth.model.User;
import monster.theundefinedavengers.thanos.auth.repository.UserRepository;
import monster.theundefinedavengers.thanos.userprofile.model.UserProfileDto;
import monster.theundefinedavengers.thanos.userprofile.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;

@Controller
@RequestMapping(value="/user/profile")
public class UserProfileController {
    @GetMapping(value = "/")
    public String showProfile(Model model,Principal principal) {
        User u = userRepository.findByEmail(principal.getName());
        model.addAttribute("user",u);
        return "profile";
    }
    @PostMapping(value = "/")
    public String updateProfile(UserProfileDto userProfileDto,Model model,Principal principal) {
        User u = userRepository.findByEmail(principal.getName());
        u.setName(userProfileDto.getName());
        userRepository.save(u);
        model.addAttribute("user",u);
        return "profile";
    }
    @Value("${app.upload.dir:${user.home}}")
    public String uploadDir;
    @Autowired
    FileUploadService fileService;
    @Autowired
    UserRepository userRepository;

    @PostMapping("/uploadcv")
    public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes, Principal principal) {
        User u = userRepository.findByEmail(principal.getName());
        String id = fileService.uploadFile(file);
        u.setCvID(id);
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");
        userRepository.save(u);
        return "redirect:/user/profile/";
    }

    @RequestMapping(value = "/showcv", method = RequestMethod.GET)
    public void getFile(
            HttpServletResponse response,Principal principal) {
        try {
            User u = userRepository.findByEmail(principal.getName());
            response.setContentType("application/pdf");
            File initialFile = new File(uploadDir+ File.separator+u.getCvID());
            InputStream is = new FileInputStream(initialFile);
            org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
            response.flushBuffer();
        } catch (IOException ex) {
            throw new RuntimeException("IOError writing file to output stream");
        }

    }
}
