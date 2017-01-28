package tvtracker.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import tvtracker.domain.Show;
import tvtracker.domain.Subscription;
import tvtracker.domain.User;
import tvtracker.repository.ShowRepository;

@Service
@Transactional
public class SubscriptionService {
	
	@Autowired
    private ShowRepository showRepository;
	
	public Subscription create(int tvmazeId, User user){		
		Show show = showRepository.findByTvmazeId(tvmazeId);
		
		//Check if subscription already exists
		Set<Subscription> subscriptions = user.getSubscriptions();
		for(Subscription s: subscriptions) {
			if(s.getShow().getTvmazeId() == tvmazeId) {
				return s;
			}
		}
		
		Subscription subscription = new Subscription();
		
		// Get show from TVMaze if not in Database
		if(show == null) {
			show = new Show();
			int id = show.getId();
			
			RestTemplate restTemplate = new RestTemplate();
			show = restTemplate.getForObject(
				"http://api.tvmaze.com/shows/" + Integer.toString(tvmazeId),
				Show.class
			);
			
			show.setTvmazeId(show.getId());
			show.setId(id);
			
			showRepository.save(show);
		}
		
		subscriptions.add(new Subscription(user, show));
		user.setSubscriptions(subscriptions);
		
		return subscription;
	}
	
	public List<Show> read(User user) {
		List<Show> shows = new ArrayList<>();
		Set<Subscription> subscriptions = user.getSubscriptions();
		for(Subscription s : subscriptions) {
			shows.add(s.getShow());
		}
		
		return shows;
	}
	
	public void delete(int id, User user) {
		Set<Subscription> subscriptions = user.getSubscriptions();
		
		subscriptions.removeIf(s -> s.getShow().getId() == id);
		
		
		user.setSubscriptions(subscriptions);
	}
}
