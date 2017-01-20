package tvtracker.controllers;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubscriptionController {

	@RequestMapping(value="/subscribe", method = RequestMethod.POST)
	public Map<String, Object> subscribe(Principal user) {
		Map<String, Object> res = new HashMap<String, Object>();
		
		System.out.println(user);
		res.put("user", user);
		
		return res;
	}
}
