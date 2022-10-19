package fi.laiterekisteri.domain;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends CrudRepository<Device, Long> {
	
	// Search devices table columns product, model, serialnumber
	@Query(value = "select * from devices as d where d.deleted=false and (d.device_id like %:keyword% or d.product like %:keyword% or d.model like %:keyword% or d.serialnumber like %:keyword%)", nativeQuery = true)
	List<Device> findByKeyword(@Param("keyword") String keyword);
}
