package com.example.demo.RestController;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Role;
import com.example.demo.entity.RoleName;
import com.example.demo.entity.Role_User;
import com.example.demo.entity.User;
import com.example.demo.service.RoleService;
import com.example.demo.service.Role_UserService;
import com.example.demo.service.UserService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class UserRestController {
	@Autowired
	HttpServletRequest req;
	@Autowired
	HttpServletResponse resp;
	@Autowired
	UserService userService;
	@Autowired
	RoleService roleService;
	@Autowired
	Role_UserService roleUserService;

	@PostMapping("/create/user")
	@PreAuthorize("hasRole('PM') or hasRole('ADMIN')")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		if (userService.exitByUsername(user.getUsername())) {
			return ResponseEntity.badRequest().build();
		}
		userService.save(user);
		roleService.reateRole(user, RoleName.ROLE_USER);
		return ResponseEntity.ok(user);
	}

	@PostMapping("/create/admin")
	@PreAuthorize("hasRole('PM') or hasRole('ADMIN')")
	public ResponseEntity<User> createAdmin(@RequestBody User user) {
		if (userService.exitByUsername(user.getUsername())) {
			return ResponseEntity.badRequest().build();
		}
		userService.save(user);
		roleService.reateRole(user, RoleName.ROLE_ADMIN);
		return ResponseEntity.ok(user);
	}

	@PostMapping("/create/pm")
	@PreAuthorize("hasRole('PM')")
	public ResponseEntity<User> createPm(@RequestBody User user) {
		if (userService.exitByUsername(user.getUsername())) {
			return ResponseEntity.badRequest().build();
		}
		userService.save(user);
		roleService.reateRole(user, RoleName.ROLE_PM);
		return ResponseEntity.ok(user);
	}
	@PreAuthorize("hasRole('PM') or hasRole('ADMIN')")
	@GetMapping("/list/user")
	public ResponseEntity<?> getListUser() {
		return ResponseEntity.ok(userService.findAll());
	}

	@GetMapping("/user/{username}")
	@PreAuthorize("hasRole('PM') or hasRole('ADMIN')")
	public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username) {
		if (userService.exitByUsername(username)) {
			return ResponseEntity.ok(userService.findByUsername(username).get());
		}
		return ResponseEntity.badRequest().build();
	}

	@DeleteMapping("/delete/{username}")
	@PreAuthorize("hasRole('PM') or hasRole('ADMIN')")
	public ResponseEntity<?> deleteUserByUsername(@PathVariable("username") String username) throws Exception {

		if (userService.exitByUsername(username)) {
			User user = userService.findByUsername(username).get();
			if (!user.getUsername().equals(req.getRemoteUser())) {
				List<Role_User> roles = userService.findByUsername(username).get().getRoles();
				for (Role_User role_User : roles) {
					if (role_User.getRole().getName().equals(RoleName.ROLE_PM)) {
						resp.sendError(403, "Không thể xóa tài khoản giám đốc!");
						return ResponseEntity.badRequest().build();
					}
				}
				for (Role_User role_User : roles) {
					roleUserService.delete(role_User);
				}
				userService.delete(userService.findByUsername(username).get());
				return ResponseEntity.ok(null);
			} else {
				resp.sendError(403, "Không thể xóa bản thân!");
				return ResponseEntity.badRequest().build();
			}
		}
		return ResponseEntity.badRequest().build();
	}

	@PutMapping("/update/user")
	@PreAuthorize("hasRole('PM') or hasRole('ADMIN')")
	public ResponseEntity<User> updateUser(@RequestBody User user, @Param("role") String role) throws IOException {
		if (userService.exitByUsername(user.getUsername())) {
			if (role.equals("pm") && userService.isPM(req.getRemoteUser())) {
				userService.save(user);
				try {
					roleUserService.deleteRole(user);
					roleService.reateRole(user, RoleName.ROLE_PM);
				} catch (Exception e) {
					System.err.println(e);
				}
			} else if (role.equals("pm") && !userService.isPM(req.getRemoteUser())) {
				resp.sendError(403, "Bạn không có quyền giám đốc");
				return ResponseEntity.badRequest().build();
			} else if (userService.isPM(user.getUsername()) && !userService.isPM(req.getRemoteUser())) {
				resp.sendError(403, "Bạn không có quyền giám đốc");
				return ResponseEntity.badRequest().build();
			}else {
				if (role.equals("admin")) {
					userService.save(user);
					roleUserService.deleteRole(user);
					roleService.reateRole(user, RoleName.ROLE_ADMIN);
				} else {
					userService.save(user);
					roleUserService.deleteRole(user);
					roleService.reateRole(user, RoleName.ROLE_USER);
				}
			}
			return ResponseEntity.ok(user);
		} else {
			return ResponseEntity.badRequest().build();
		}
	}
}
