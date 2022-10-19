package fi.laiterekisteri;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fi.laiterekisteri.web.CategoryController;
import fi.laiterekisteri.web.DeviceController;
import fi.laiterekisteri.web.LocationController;
import fi.laiterekisteri.web.PersonController;

@SpringBootTest
class LaiterekisteriApplicationTests {
	
	@Autowired
	DeviceController dc;
	@Autowired 
	PersonController pc;
	@Autowired 
	LocationController lc;
	@Autowired 
	CategoryController cc;
	
	@Test
	public void contextLoads() throws Exception {
		assertThat(dc).isNotNull();
		assertThat(pc).isNotNull();
		assertThat(lc).isNotNull();
		assertThat(cc).isNotNull();
	}

}
