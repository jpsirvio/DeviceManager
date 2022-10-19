package fi.laiterekisteri.domain;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends CrudRepository<Category, Long> {

	List<Category> findByCname(String cname);
	
	// Search categories table column cname
	@Query(value = "select * from categories as c where c.deleted=false and c.cname like %:keyword%", nativeQuery = true)
	List<Category> findByKeyword(@Param("keyword") String keyword);
}