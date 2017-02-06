package tvtracker.controllers;

import java.nio.charset.Charset;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tvtracker.domain.User;
import tvtracker.services.UserService;

@CrossOrigin
@RestController
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
    private BCryptPasswordEncoder passwordEncoder;
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public Map<String, Object> login(
		@RequestParam(value="email", required=true) String email,
		@RequestParam(value="password", required=true) String password
	) {
		Map<String, Object> res = new HashMap<String, Object>();
		User user = userService.findByEmail(email);
		
		if (user != null) {
			if(passwordEncoder.matches(password, user.getPassword())) {
				res.put("message","Successfully logged in");
				res.put("status", HttpStatus.OK.value());
				res.put("token", this.createToken(email, password));
				return res;
			}
		}
		
		res.put("error", "Wrong username or email");
		res.put("status", HttpStatus.FORBIDDEN.value());
		
		return res;
	}
	
	private String createToken(String email, String password) {
		String token = "Basic ";
		String plainCreds = email + ":" + password;
		byte[] plainCredsBytes = plainCreds.getBytes(Charset.forName("UTF-8"));
		token += Base64.getUrlEncoder().encodeToString(plainCredsBytes);
		return token;
	}

}
