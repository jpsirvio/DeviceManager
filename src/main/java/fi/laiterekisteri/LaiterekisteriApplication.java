package fi.laiterekisteri;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.laiterekisteri.domain.Category;
import fi.laiterekisteri.domain.CategoryRepository;
import fi.laiterekisteri.domain.Device;
import fi.laiterekisteri.domain.DeviceRepository;
import fi.laiterekisteri.domain.Location;
import fi.laiterekisteri.domain.LocationRepository;
import fi.laiterekisteri.domain.Person;
import fi.laiterekisteri.domain.PersonRepository;

@SpringBootApplication
public class LaiterekisteriApplication {
	
	// Main Application
	public static void main(String[] args) {
		SpringApplication.run(LaiterekisteriApplication.class, args);
	}

	// Logger for demodata
	private static final Logger Log = LoggerFactory.getLogger(LaiterekisteriApplication.class);

	// Create demodata
	@Bean
	public CommandLineRunner demo(PersonRepository prepo, DeviceRepository drepo, LocationRepository lrepo, CategoryRepository crepo) {
		return (args) -> {
			
			Log.info("create persons");
			// Person(String userName, String email, String firstName, String lastName, String notes, String passwordHash, String role, int admin, int deleted)
			prepo.save(new Person("-----"," ","unspecified","-","This is a unspecified user for devices without a named user","$2a$10$WsCC6wZO08qVeOpZ1VkfCe5bvuSYqyryNsSOx2OVfrYb9CWslN1/W","USER",0,0));
			prepo.save(new Person("11111","first.user@test.com","First","User","This is the first user. Admin, not deleted","$2a$10$WsCC6wZO08qVeOpZ1VkfCe5bvuSYqyryNsSOx2OVfrYb9CWslN1/W","ADMIN",1,0));
			prepo.save(new Person("22222","second.user@test.com","Second","User","Second user, not an admin, not deleted","$2a$10$WsCC6wZO08qVeOpZ1VkfCe5bvuSYqyryNsSOx2OVfrYb9CWslN1/W","USER",0,0));
			prepo.save(new Person("33333","third.user@test.com","Third","User","Third user, not an admin, is deleted","$2a$10$WsCC6wZO08qVeOpZ1VkfCe5bvuSYqyryNsSOx2OVfrYb9CWslN1/W","USER",0,1));
			prepo.save(new Person("44444","fourth.user@test.com","Fourth","User","Fourth user, admin and deleted","$2a$10$WsCC6wZO08qVeOpZ1VkfCe5bvuSYqyryNsSOx2OVfrYb9CWslN1/W","ADMIN",1,1));
			prepo.save(new Person("user","user@test.com","User","User","Test User","$2a$10$WsCC6wZO08qVeOpZ1VkfCe5bvuSYqyryNsSOx2OVfrYb9CWslN1/W","USER",0,0));
			prepo.save(new Person("admin","admin@test.com","Admin","Admin","Test Admin","$2a$10$WsCC6wZO08qVeOpZ1VkfCe5bvuSYqyryNsSOx2OVfrYb9CWslN1/W","ADMIN",1,0));
			prepo.save(new Person("superadmin","superadmin@test.com","Super","Admin","Test Super Admin","$2a$10$WsCC6wZO08qVeOpZ1VkfCe5bvuSYqyryNsSOx2OVfrYb9CWslN1/W","ADMIN",1,0));
			
			Log.info("create locations");
			// String office, String address, String room, String notes, int deleted
			lrepo.save(new Location("Test Office","1 Office Street, Testville","Department A","Unit 1","2nd Floor, Room 3","This is a note",0));
			lrepo.save(new Location("Old Office","4 Office Street, Testville","Department B","Unit 3","5th Floor, Room 6","This is a note",1));
			lrepo.save(new Location("w/ user","","","","","This is for devices carried by the user and with no static location",0));
			
			Log.info("create device categories");
			// String category, String notes, int deleted
			crepo.save(new Category("Laptop Computer","This is category for laptops",0));
			crepo.save(new Category("Laptop","This is an old category for laptops",1));
			crepo.save(new Category("Smartphone","This is category for smartphones",0));
			crepo.save(new Category("Video Conference System","",0));

			Log.info("create devices");
			// Device(Person person, Location location, Category category, String product, String model, String serialnumber, String notes, int deleted)
			drepo.save(new Device(prepo.findByUsername("11111"),lrepo.findByOffice("w/ user").get(0),crepo.findByCname("Laptop Computer").get(0),"laptop","Dell Latitude 5410","ABC1234","This is a laptop",0));
			drepo.save(new Device(prepo.findByUsername("22222"),lrepo.findByOffice("w/ user").get(0),crepo.findByCname("Laptop Computer").get(0),"laptop","Dell Latitude 5410","BCD2345","This is a laptop",0));
			drepo.save(new Device(prepo.findByUsername("33333"),lrepo.findByOffice("Test Office").get(0),crepo.findByCname("Laptop Computer").get(0),"laptop","Dell Latitude 5410","CDE3456","This is a laptop",0));
			drepo.save(new Device(prepo.findByUsername("44444"),lrepo.findByOffice("Old Office").get(0),crepo.findByCname("Laptop Computer").get(0),"laptop","Lenovo X450","DEF4567","This is a laptop",1));
			drepo.save(new Device(prepo.findByUsername("-----"),lrepo.findByOffice("Test Office").get(0),crepo.findByCname("Video Conference System").get(0),"video conferencing device","Polycom","EFG6789","Userless video conferencing device",0));

			Log.info("fetch all devices");
			for (Device device : drepo.findAll()) {
				Log.info(device.toString());
			}
			
		};
		
	}

}
