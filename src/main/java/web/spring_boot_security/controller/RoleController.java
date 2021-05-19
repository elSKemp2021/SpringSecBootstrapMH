package web.spring_boot_security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.spring_boot_security.entity.Role;
import web.spring_boot_security.service.RoleService;

import java.util.List;

@Controller
@RequestMapping("/admin/roles")
public class RoleController {
	private RoleService roleService;

	@Autowired
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
	

    @GetMapping(value = "")
	public String getRolesPage(ModelMap model) {
		List<Role> roles = roleService.getAllRoles();
		model.addAttribute("roleslist", roles);
		return "roleslist-view";
	}

	@GetMapping(value = "/add")
	public String addRolePage(ModelMap model) {
		model.addAttribute("role", new Role());
		return "roleedit-view";
	}

	@PostMapping(value = "/add")
	public String addRole(@ModelAttribute("role") Role role) {
		roleService.create(role);
		return "redirect:/admin/roles";
	}

	@GetMapping(value = "/edit/{id}")
	public String editRolePage(ModelMap model, @PathVariable("id") long id) {
		model.addAttribute("role", roleService.getRoleById(id));
		return "roleedit-view";
	}

	@PostMapping(value = "/edit")
	public String updateRole(@ModelAttribute("role") Role role) {
		roleService.update(role);
		return "redirect:/admin/roles";
	}

	@GetMapping(value = "/{id}")
	public String getRolePage(ModelMap model, @PathVariable("id") long id) {
		model.addAttribute("role", roleService.getRoleById(id));
		return "role-view";
	}

	@GetMapping(value = "/delete/{id}")
	public String deleteRole(@PathVariable("id") long id) {
		roleService.delete(roleService.getRoleById(id));
		return "redirect:/admin/roles";
	}

}