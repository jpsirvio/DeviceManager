package fi.laiterekisteri.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fi.laiterekisteri.domain.Device;
import fi.laiterekisteri.domain.DeviceRepository;

@RestController
public class RestDeviceController {
	
	@Autowired
	private DeviceRepository drepo;
	
	// REST PERSON
	// REST List all persons
	@GetMapping("/devices/api")
	public Iterable<Device> getDevices() {
		return drepo.findAll();
	}
	
	// REST Find a person by id
	@GetMapping("/devices/api/{id}")
	public Optional<Device> findPersonRest(@PathVariable("deviceId") Long deviceId) {
		return drepo.findById(deviceId);
	}
	
	// REST Add a person
	@PostMapping("devices/api")
	Device newDevice(@RequestBody Device newDevice) {
		return drepo.save(newDevice);
	}
	
	// REST Update person
	@PutMapping("/devices/api/{id}")
	Device editDevice(@RequestBody Device editDevice, @PathVariable Long deviceId) {
		editDevice.setDeviceId(deviceId);
		return drepo.save(editDevice);
	}

}
