package fi.laiterekisteri;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import fi.laiterekisteri.domain.Category;
import fi.laiterekisteri.domain.CategoryRepository;
import fi.laiterekisteri.domain.Device;
import fi.laiterekisteri.domain.DeviceRepository;
import fi.laiterekisteri.domain.PersonRepository;
import fi.laiterekisteri.domain.LocationRepository;

@DataJpaTest
class RepositoryTests {

	@Autowired
	DeviceRepository drepo;
	@Autowired
	PersonRepository prepo;
	@Autowired
	LocationRepository lrepo;
	@Autowired
	CategoryRepository crepo;
	
	@Test
	public void findDeviceByIdAndValidateCategory() {
		Category category = drepo.findById((long) 7).get().getCategory();
		System.out.println("Haetaan device id:llä 7 " + category);
		assertEquals(category.getCname(), "Smartphone");
	}
	
	@Test
	public void findDeviceByIdAndValidateModel() {
		Device device= drepo.findById((long) 7).get();
		System.out.println("Haetaan device id:llä 7 " + device);
		assertEquals(device.getModel(), "Samsung A52");
	}
	
	/*
	@Test
	public void findByCategoryNameShouldReturnNumberOfDevices() {
		List<Category> categories = crepo.findByCname("Smartphone");
		assertEquals(categories.size(), 1);
	}
	*/
	
	@Test
	public void insertNewDevice() {
		Device device = new Device(prepo.findByUsername("99999"),lrepo.findByOffice("Test Office").get(0),crepo.findByCname("Smartphone").get(0),"smartphone","Samsung A52","ABC1234","This is a laptop",false);		
		drepo.save(device);
		assertNotNull(device.getDeviceId());
	}
	
	/*
	@Test
	public void deleteDevice() {
		Device device= new Device(prepo.findByUsername("99999"),lrepo.findByOffice("Test Office").get(0),crepo.findByCname("Smartphone").get(0),"smartphone","Samsung A52","ABC1234","This is a laptop",false);
		drepo.deleteById(device.getDeviceId());
		assertNotNull(device.getDeviceId());		
	}
	*/
}
