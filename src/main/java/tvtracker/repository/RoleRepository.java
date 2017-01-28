package tvtracker.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tvtracker.domain.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
}