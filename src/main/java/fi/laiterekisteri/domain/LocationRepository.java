package fi.laiterekisteri.domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface LocationRepository extends CrudRepository<Location, Long> {
	
	List<Location> findByOffice(String office);
	List<Location> findByDepartment(String department);
	List<Location> findByUnit(String unit);
}
