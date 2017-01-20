package tvtracker.services;

import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tvtracker.domain.Show;
import tvtracker.domain.Subscription;
import tvtracker.domain.User;
import tvtracker.repository.ShowRepository;

@Service
@Transactional
public class SubscriptionService {
	
	@Autowired
    private ShowRepository showRepository;
	
	public Subscription create(int tvmazeId, String name, User user){
		Subscription subscription = new Subscription();
		
		Show show = showRepository.findByTvmazeId(tvmazeId);
		
		if(show == null) {
			show = new Show();
			show.setTvmazeId(tvmazeId);
			show.setName(name);
			showRepository.save(show);
		}
		
		
		Set<Subscription> subscriptions = new HashSet<>();
		subscriptions.add(new Subscription(user, show));
		user.getSubscriptions().addAll(subscriptions);
		
		return subscription;
	}
}
