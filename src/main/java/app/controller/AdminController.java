package app.controller;

import app.model.Role;
import app.model.User;
import app.service.RoleBootService;
import app.service.UserBootService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Set;

@Controller
public class AdminController {

    private final UserBootService userService;
    private final RoleBootService roleService;

    @Autowired
    public AdminController(UserBootService userService, RoleBootService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = "/admin")
    public String getAllUsers(ModelMap model) {
        List<User> listUsers = userService.getAll();
        model.addAttribute("users", listUsers);
        return "admin";
    }

    @GetMapping(value = "/create_user")
    public String createNewUser(ModelMap model) {
        Set<Role> roles = roleService.getAll();
        model.addAttribute("allRoles", roles);
        model.addAttribute("newUser", new User());
        return "create_user";
    }

    @PostMapping(value = "/createUser")
    public String addUser(@ModelAttribute User user) {
        userService.save(user);
        return "redirect:/admin";
    }

    @PostMapping("/deleteUser/{id}")
    public String deleteUserById(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/admin";
    }

    @GetMapping(value = "/editUser/{id}")
    public String editUser(ModelMap model, @PathVariable("id") Long id) {
        User user = userService.getById(id);
        model.addAttribute("user", user);
        Set<Role> all = roleService.getAll();
        model.addAttribute("allRoles", all);
        //
        //
        return "update_user";
    }

    @PostMapping(value = "/edit")
    public String edit(@ModelAttribute User user) {
        userService.update(user);
        return "redirect:/admin";
    }
}
