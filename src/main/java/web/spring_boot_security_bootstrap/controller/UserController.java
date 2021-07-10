package web.spring_boot_security_bootstrap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import web.spring_boot_security_bootstrap.entity.User;
import web.spring_boot_security_bootstrap.service.UserService;

@Controller
@RequestMapping("/")
public class UserController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String start() {
        return "main-view";
    }

    @GetMapping(value = "user")
    public String getUserPage(ModelMap model, @AuthenticationPrincipal User authUser) {
        model.addAttribute("user", userService.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
        model.addAttribute("authUser", authUser);
        return "main-view";
    }

    @GetMapping(value = "login")
    public String loginPage() {
        return "login";
    }
}