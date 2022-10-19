package fi.laiterekisteri.domain;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PersonRepository extends CrudRepository<Person, Long> {
	
	//List<Person> findByUsername(String username);
	Person findByUsername(String username);
	
	// Search persons table columns user_name, first_name, last_name, email
	@Query(value = "select * from persons as p where p.deleted=false and (p.username like %:keyword% or p.first_name like %:keyword% or p.last_name like %:keyword% or p.email like %:keyword%)", nativeQuery = true)
	List<Person> findByKeyword(@Param("keyword") String keyword);
}
