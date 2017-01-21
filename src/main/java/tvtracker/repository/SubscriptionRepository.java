package tvtracker.repository;

import org.springframework.data.repository.CrudRepository;

import tvtracker.domain.Subscription;
import tvtracker.domain.User;

public interface SubscriptionRepository extends CrudRepository<Subscription, Integer> {
	
	Subscription[] findByUser(User user);
}
