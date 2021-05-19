package web.spring_boot_security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import web.spring_boot_security.entity.Role;
import web.spring_boot_security.entity.User;
import web.spring_boot_security.service.RoleService;
import web.spring_boot_security.service.UserService;

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
	public String getUsersPage(ModelMap model) {
		List<User> users = userService.getAllUsers();
		model.addAttribute("usersList", users);
		return "userlist-view";
	}

	@GetMapping(value = "/add")
	public String addUserPage(ModelMap model) {
		List<Role> rolesList = roleService.getAllRoles();
		model.addAttribute("rolesList", rolesList);
		model.addAttribute("user", new User());
		return "useredit-view";
	}

	@PostMapping(value = "/add")
	public String addUser(@ModelAttribute("user") User user) {
		userService.create(user);
		return "redirect:/admin";
	}

	@GetMapping(value = "/edit/{id}")
	public String editUserPage(ModelMap model, @PathVariable("id") long id) {
		List<Role> rolesList = roleService.getAllRoles();
		model.addAttribute("rolesList", rolesList);
		model.addAttribute("user", userService.getUserById(id));
		return "useredit-view";
	}

	@PostMapping(value = "/edit")
	public String updateUser(@ModelAttribute("user") User user) {
		userService.update(user);
		return "redirect:/admin";
	}

	@GetMapping(value = "/delete/{id}")
	public String deleteUser(@PathVariable("id") long id) {
		userService.delete(userService.getUserById(id));
		return "redirect:/admin";
	}

}