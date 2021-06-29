package web.spring_boot_security_bootstrap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import web.spring_boot_security_bootstrap.entity.Role;
import web.spring_boot_security_bootstrap.entity.User;
import web.spring_boot_security_bootstrap.service.RoleService;
import web.spring_boot_security_bootstrap.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
	private UserService userService;
	private RoleService roleService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Autowired
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}


    @GetMapping(value = "")
	public String getUsersPage(ModelMap model, @AuthenticationPrincipal User authUser) {
		List<User> users = userService.getAllUsers();
		List<Role> roles = roleService.getAllRoles();
		model.addAttribute("usersList", users);
		model.addAttribute("rolesList", roles);
		model.addAttribute("authUser", authUser);
		return "main-view";
	}

	@GetMapping(value = "/add")
	public String addUserPage(ModelMap model) {
		List<Role> rolesList = roleService.getAllRoles();
		model.addAttribute("rolesList", rolesList);
		model.addAttribute("user", new User());
		return "main-view#new-user";
	}

//	@PostMapping(value = "/add")
//	public String addUser(@ModelAttribute("user") User user) {
//		userService.create(user);
//		return "redirect:/admin";
//	}
//
//	@GetMapping(value = "/edit/{id}")
//	public String editUserPage(ModelMap model, @PathVariable("id") long id) {
//		List<Role> rolesList = roleService.getAllRoles();
//		model.addAttribute("rolesList", rolesList);
//		model.addAttribute("user", userService.getUserById(id));
//		return "useredit-view";
//	}
//
//	@PostMapping(value = "/edit")
//	public String updateUser(@ModelAttribute("user") User user) {
//		userService.update(user);
//		return "redirect:/admin";
//	}
//
//	@GetMapping(value = "/delete/{id}")
//	public String deleteUser(@PathVariable("id") long id) {
//		userService.delete(userService.getUserById(id));
//		return "redirect:/admin";
//	}

}