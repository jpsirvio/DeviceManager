package fi.laiterekisteri.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fi.laiterekisteri.domain.Device;
import fi.laiterekisteri.domain.DeviceRepository;

@Service
public class DeviceService {
	 @Autowired
	 private DeviceRepository drepo;
	 
	 // Get the List of Devices
	 public List<Device> getAllDevices(){
		 List<Device> devicelist = (List<Device>)drepo.findAll();
		 return devicelist;
	 }
	 
	 //Get Devices By keyword
	 public List<Device> getByKeyword(String keyword){
		 return drepo.findByKeyword(keyword);
	 }
}
