package tvtracker.controllers;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tvtracker.domain.User;
import tvtracker.services.SubscriptionService;
import tvtracker.services.UserService;

@CrossOrigin
@RestController
public class SubscriptionController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SubscriptionService subscriptionService;
	
	@RequestMapping(value="/subscribed", method = RequestMethod.GET)
	public Map<String, Object> subscribed(Principal principal) {
		Map<String, Object> res = new HashMap<String, Object>();
		
		User user = userService.findByEmail(principal.getName());
		res.put("shows", subscriptionService.read(user));
		
		return res;
	}

	@RequestMapping(value="/subscribe", method = RequestMethod.POST)
	public Map<String, Object> subscribe(
			Principal principal,
			@RequestParam(value="tvmazeId", required=true) int tvmazeId,
			@RequestParam(value="showName", required=true) String showName
	) {
		Map<String, Object> res = new HashMap<String, Object>();
		
		User user = userService.findByEmail(principal.getName());
		subscriptionService.create(tvmazeId, showName, user);
		
		
		System.out.println(user);
		res.put("user", user);
		
		return res;
	}
}
