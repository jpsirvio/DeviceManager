package fi.laiterekisteri;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import fi.laiterekisteri.web.CategoryController;
import fi.laiterekisteri.web.DeviceController;
import fi.laiterekisteri.web.LocationController;
import fi.laiterekisteri.web.PersonController;

@DataJpaTest
class RepositoryTests {

	@Autowired
	DeviceController dc;
	@Autowired 
	PersonController pc;
	@Autowired 
	LocationController lc;
	@Autowired 
	CategoryController cc;
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	

}
