package com.example.demo.RestController;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;

import com.example.demo.entity.User;
import com.example.demo.request.LoginRequest;
import com.example.demo.respone.LoginRespone;
import com.example.demo.security.jwt.JwtUtils;
import com.example.demo.service.UserService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class LoginRestController {
	@Autowired
	HttpServletRequest req;
	@Autowired
	UserService userService;
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	JwtUtils jwtUtils;
	
	@PostMapping("/login")
	public ResponseEntity<LoginRespone> login(@RequestBody LoginRequest loginRequest){
	
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
	
		String token = jwtUtils.generateJwtToken(authentication);

		return ResponseEntity.ok(new LoginRespone(token)); 
	}
	
	@GetMapping("/user")
	@PreAuthorize("hasRole('USER') or hasRole('PM') or hasRole('ADMIN')")
	public ResponseEntity<User>getUserLogin(){
		User u = userService.findByUsername(req.getRemoteUser()).get();
		return ResponseEntity.ok(u); 
	}
	
}
