package tvtracker.controllers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tvtracker.domain.Role;
import tvtracker.domain.User;
import tvtracker.domain.UserRole;
import tvtracker.enums.RolesEnum;
import tvtracker.services.UserService;

@CrossOrigin
@RestController
public class SignupController {
	
	
	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value="/signup", method = RequestMethod.POST)
	public Map<String, Object> signup(
		@RequestParam(value="email", required=true) String email,
		@RequestParam(value="password", required=true) String password
	) {
		Map<String, Object> res = new HashMap<String, Object>();
		
		if (userService.findByEmail(email) != null) {
			res.put("error", "Email already exists");
			res.put("status", 403);
        } else {
        	User user = new User();
        	Set<UserRole> roles = new HashSet<>();
        	
        	user.setEmail(email);
        	user.setPassword(password);
        	
        	roles.add(new UserRole(user, new Role(RolesEnum.BASIC)));
        	
        	userService.create(user, roles);
        	res.put("message", "User Created");
        	res.put("status", 201);
        }
		
		
		return res;
	}

}
