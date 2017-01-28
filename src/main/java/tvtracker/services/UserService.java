package tvtracker.services;

import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import tvtracker.domain.User;
import tvtracker.domain.UserRole;
import tvtracker.repository.RoleRepository;
import tvtracker.repository.UserRepository;

@Service
@Transactional
public class UserService {
	
	@Autowired
    private UserRepository userRepository;
	
	@Autowired
    private RoleRepository roleRepository;
	
	@Autowired
    private BCryptPasswordEncoder passwordEncoder;
	
	public User create(User user, Set<UserRole> userRoles) {
		User localUser = userRepository.findByEmail(user.getEmail());
		
		if (localUser == null) {
			String encryptedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encryptedPassword);
            
            for (UserRole ur : userRoles) {
                roleRepository.save(ur.getRole());
            }
            
            user.getUserRoles().addAll(userRoles);

            
            localUser = userRepository.save(user);
		}
		
		
		
		return localUser;
	}

	public User findByEmail(String username) {
        return userRepository.findByEmail(username);
    }
}
