package fi.laiterekisteri.domain;

import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {
	
	//List<Person> findByUsername(String username);
	Person findByUsername(String username);
	
}
