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

	@GetMapping(value = "user")
	public String getUserPage(ModelMap model, @AuthenticationPrincipal User authUser) {
		model.addAttribute("user", userService.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
		model.addAttribute("authUser", authUser);
		return "main-view";
	}

//	@GetMapping(value = "user/{id}")
//	public String getUserPageById(ModelMap model, @PathVariable("id") Long id) {
//		model.addAttribute("user", userService.getUserById(id));
////		System.out.println(userService.getUserById(2L));
//		return "user-view";
//	}
//
//	@GetMapping(value = "hello")
//	public String printWelcome(ModelMap model) {
//		List<String> messages = new ArrayList<>();
//		messages.add("Hello!");
//		messages.add("I'm Spring MVC-SECURITY application");
//		messages.add("5.2.0 version by sep'19 ");
//		model.addAttribute("messages", messages);
//		return "hello";
//	}

    @GetMapping(value = "login")
    public String loginPage() {
        return "login";
    }
}