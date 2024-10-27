package ru.stas.NauJava.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.stas.NauJava.Dao.UserRepository;
import ru.stas.NauJava.Entity.User;

@Controller
public class RegistrationController
{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/registration")
    public String registration()
    {
        return "registration";
    }
    @PostMapping("/registration")
    public String adduser(User user, Model model)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (userRepository.findByUsername(user.getUsername()).isEmpty()){
            userRepository.save(user);
            return "redirect:/login";
        }else{
            model.addAttribute("message", "User exists");
            return "registration";
        }
    }
}
