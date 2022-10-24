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
	
	/*
	@Bean
	public CommandLineRunner demo(PersonRepository prepo, DeviceRepository drepo, LocationRepository lrepo, CategoryRepository crepo) {
		return (args) -> {
			
			// create demodata for H2-server
			
			
			Log.info("create persons");
			// user-user, admin-admin- muut-sala1234
			// Person(String userName, String email, String firstName, String lastName, String notes, String passwordHash, String role, boolean admin, boolean deleted)
			prepo.save(new Person("-----"," ","unspecified","-","This is a unspecified user for devices without a named user","$2a$10$WsCC6wZO08qVeOpZ1VkfCe5bvuSYqyryNsSOx2OVfrYb9CWslN1/W","USER",false,false));
			prepo.save(new Person("11111","first.user@test.com","First","User","This is the first user. Admin, not deleted","$2a$10$WsCC6wZO08qVeOpZ1VkfCe5bvuSYqyryNsSOx2OVfrYb9CWslN1/W","ADMIN",true,false));
			prepo.save(new Person("22222","second.user@test.com","Second","User","Second user, not an admin, not deleted","$2a$10$WsCC6wZO08qVeOpZ1VkfCe5bvuSYqyryNsSOx2OVfrYb9CWslN1/W","USER",false,false));
			prepo.save(new Person("33333","third.user@test.com","Third","User","Third user, not an admin, is not deleted","$2a$10$WsCC6wZO08qVeOpZ1VkfCe5bvuSYqyryNsSOx2OVfrYb9CWslN1/W","USER",false,false));
			prepo.save(new Person("44444","fourth.user@test.com","Fourth","User","Fourth user, admin and is not deleted","$2a$10$WsCC6wZO08qVeOpZ1VkfCe5bvuSYqyryNsSOx2OVfrYb9CWslN1/W","ADMIN",true,false));
			prepo.save(new Person("user","user@test.com","User","User","Test User","$2a$12$YORc6xN.gFQtFYPexg5QueyOK4W6igrOPCHDKEC7/I7abXR61x6QO","USER",false,false));
			prepo.save(new Person("admin","admin@test.com","Admin","Admin","Test Admin","$2a$12$hIDisOJbsGRa8xRhLM7lNOM30tvyHGs08W26M69R2Lw/0XD20a/TK","ADMIN",true,false));
			prepo.save(new Person("superadmin","superadmin@test.com","Super","Admin","Test Super Admin","$2a$10$WsCC6wZO08qVeOpZ1VkfCe5bvuSYqyryNsSOx2OVfrYb9CWslN1/W","ADMIN",true,true));
			prepo.save(new Person("dummyuser1","dummu1@test.com","Dummy","One","Dummy User 1","$2a$10$WsCC6wZO08qVeOpZ1VkfCe5bvuSYqyryNsSOx2OVfrYb9CWslN1/W","USER",false,false));
			prepo.save(new Person("dummyuser2","dummu2@test.com","Dummy","Two","Dummy User 2","$2a$10$WsCC6wZO08qVeOpZ1VkfCe5bvuSYqyryNsSOx2OVfrYb9CWslN1/W","USER",false,false));
			prepo.save(new Person("dummyuser3","dummu3@test.com","Dummy","Three","Dummy User 3","$2a$10$WsCC6wZO08qVeOpZ1VkfCe5bvuSYqyryNsSOx2OVfrYb9CWslN1/W","USER",false,false));
			prepo.save(new Person("dummyuser4","dummu4@test.com","Dummy","Four","Dummy User 4","$2a$10$WsCC6wZO08qVeOpZ1VkfCe5bvuSYqyryNsSOx2OVfrYb9CWslN1/W","USER",false,false));
			
			Log.info("create locations");
			// String office, String address, String room, String notes, int deleted
			lrepo.save(new Location("Test Office","1 Office Street, Testville","Department A","Unit 1","2nd Floor, Room 3","This is a note",false));
			lrepo.save(new Location("Old Office","4 Office Street, Testville","Department B","Unit 3","5th Floor, Room 6","This is a note",false));
			lrepo.save(new Location("w/ user","","","","","This is for devices carried by the user and with no specific location",false));
			lrepo.save(new Location("Office 1","1 Office Street, Testville","Department 1","Unit 1","1st Floor, Room 1","Dummy Office",false));
			lrepo.save(new Location("Office 2","4 Office Street, Testville","Department 2","Unit 2","2nd Floor, Room 2","Dummy Office",false));
			lrepo.save(new Location("Office 3","3 Office Street, Testville","Department 3","Unit 3","3rd Floor, Room 3","Dummy Office",false));
			lrepo.save(new Location("Office 4","2 Office Street, Testville","Department 4","Unit 4","4th Floor, Room 4","Dummy Office",false));
			
			Log.info("create device categories");
			// String category, String notes, int deleted
			crepo.save(new Category("Laptop Computer","This is category for laptops",false));
			crepo.save(new Category("Laptop","This is old category for laptops",true));
			crepo.save(new Category("Smartphone","This is category for smartphones",false));
			crepo.save(new Category("Video Conference System","This category is for Video Conference Systems",false));
			crepo.save(new Category("Dummy category 1","This category is a dummy category",false));
			crepo.save(new Category("Dummy category 2","This category is a dummy category",false));
			crepo.save(new Category("Dummy category 3","This category is a dummy category",false));
			crepo.save(new Category("Dummy category 4","This category is a dummy category",false));
			crepo.save(new Category("Dummy category 5","This category is a dummy category",false));

			Log.info("create devices");
			// Device(Person person, Location location, Category category, String product, String model, String serialnumber, String notes, int deleted)
			drepo.save(new Device(prepo.findByUsername("11111"),lrepo.findByOffice("w/ user").get(0),crepo.findByCname("Laptop Computer").get(0),"laptop","Dell Latitude 5410","ABC1234","This is a laptop",false));
			drepo.save(new Device(prepo.findByUsername("22222"),lrepo.findByOffice("w/ user").get(0),crepo.findByCname("Laptop Computer").get(0),"laptop","Dell Latitude 5410","BCD2345","This is a laptop",false));
			drepo.save(new Device(prepo.findByUsername("33333"),lrepo.findByOffice("Test Office").get(0),crepo.findByCname("Laptop Computer").get(0),"laptop","Dell Latitude 5410","CDE3456","This is a laptop",false));
			drepo.save(new Device(prepo.findByUsername("44444"),lrepo.findByOffice("Old Office").get(0),crepo.findByCname("Laptop Computer").get(0),"laptop","Lenovo X450","DEF4567","This is a laptop",true));
			drepo.save(new Device(prepo.findByUsername("-----"),lrepo.findByOffice("Test Office").get(0),crepo.findByCname("Video Conference System").get(0),"video conferencing device","Polycom","EFG6789","Userless video conferencing device",false));
			drepo.save(new Device(prepo.findByUsername("11111"),lrepo.findByOffice("w/ user").get(0),crepo.findByCname("Smartphone").get(0),"smartphone","Samsung A52","ABC1234","This is a laptop",false));
			drepo.save(new Device(prepo.findByUsername("22222"),lrepo.findByOffice("w/ user").get(0),crepo.findByCname("Smartphone").get(0),"smartphone","Samsung A52","ABC1234","This is a laptop",false));
			drepo.save(new Device(prepo.findByUsername("33333"),lrepo.findByOffice("w/ user").get(0),crepo.findByCname("Smartphone").get(0),"smartphone","Samsung A52","ABC1234","This is a laptop",false));
			drepo.save(new Device(prepo.findByUsername("44444"),lrepo.findByOffice("w/ user").get(0),crepo.findByCname("Smartphone").get(0),"smartphone","Samsung A52","ABC1234","This is a laptop",false));
			drepo.save(new Device(prepo.findByUsername("22222"),lrepo.findByOffice("Test Office").get(0),crepo.findByCname("Smartphone").get(0),"smartphone","Samsung A52","ABC1234","This is a laptop",false));
			drepo.save(new Device(prepo.findByUsername("33333"),lrepo.findByOffice("Test Office").get(0),crepo.findByCname("Smartphone").get(0),"smartphone","Samsung A52","ABC1234","This is a laptop",false));
			
			
			
			// Check data			
			Log.info("fetch all devices");
			for (Device device : drepo.findAll()) {
				Log.info("Fetch device: " + device.toString());
			}
			Log.info("fetch all persons");
			for (Person person : prepo.findAll()) {
				Log.info("Fetch person: " + person.toString());
			}
			Log.info("fetch all locations");
			for (Location location : lrepo.findAll()) {
				Log.info("Fetch location: " + location.toString());
			}
			Log.info("fetch all categories");
			for (Category category : crepo.findAll()) {
				Log.info("Fetch category: " + category.toString());
			}
			
		};
		
	}
	*/

}
