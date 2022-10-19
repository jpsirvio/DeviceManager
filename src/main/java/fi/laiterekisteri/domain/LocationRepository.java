package fi.laiterekisteri.domain;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface LocationRepository extends CrudRepository<Location, Long> {
	
	List<Location> findByOffice(String office);
	List<Location> findByDepartment(String department);
	List<Location> findByUnit(String unit);
	
	// Search locations table columns office, address, department, unit, room
	@Query(value = "select * from locations as l where l.deleted=false and (l.office like %:keyword% or l.address like %:keyword% or l.department like %:keyword% or l.unit like %:keyword% or l.room like %:keyword%)", nativeQuery = true)
	List<Location> findByKeyword(@Param("keyword") String keyword);
}
