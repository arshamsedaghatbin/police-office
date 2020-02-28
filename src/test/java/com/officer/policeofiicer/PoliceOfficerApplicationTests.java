 package com.officer.policeofiicer;

import com.officer.policeofiicer.domain.PoliceOfficer;
import com.officer.policeofiicer.domain.StolenBiker;
import com.officer.policeofiicer.domain.StolenStatus;
import com.officer.policeofiicer.domain.enumeration.PoliceOfficerStatus;
import com.officer.policeofiicer.service.PoliceOfficerCashService;
import com.officer.policeofiicer.service.PoliceOfficerService;
import com.officer.policeofiicer.service.StolenBikerCashService;
import com.officer.policeofiicer.service.StolenBikerService;
import com.officer.policeofiicer.service.dto.PoliceOfficerDTO;
import com.officer.policeofiicer.service.dto.StolenBikerDTO;
import com.officer.policeofiicer.service.impl.StolenBikerServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

 @SpringBootTest
class PoliceOfficerApplicationTests {

	@Autowired
	private PoliceOfficerCashService policeOfficerCashService;
	 @Autowired
	 private StolenBikerCashService stolenBikerCashService;
	@Autowired
	private StolenBikerService stolenBikerService;
	@Autowired
	private PoliceOfficerService policeOfficerService;

	@Test
	void test_add_free_police_to_redis() {
		PoliceOfficer policeOfficer=new PoliceOfficer();
		policeOfficer.setName("test");
		policeOfficerCashService.addFreePolice(policeOfficer);
		PoliceOfficer policeOfficer2=new PoliceOfficer();
		policeOfficer2.setName("test2");
		policeOfficerCashService.addFreePolice(policeOfficer2);
		PoliceOfficer freePolice = policeOfficerCashService.getFreePolice();
		assertEquals(freePolice.getName(),"test");
		PoliceOfficer freePolice2 = policeOfficerCashService.getFreePolice();
		assertEquals(freePolice2.getName(),"test2");
	}

	 @Test
	 void test_add_free_stolen_to_redis() {
		 StolenBiker stolenBiker=new StolenBiker();
		 stolenBiker.setFullName("test");
		 stolenBikerCashService.addStolenBiker(stolenBiker);
		 StolenBiker stolenBiker2=new StolenBiker();
		 stolenBiker2.setFullName("test2");
		 stolenBikerCashService.addStolenBiker(stolenBiker2);
		 StolenBiker freeStolenBiker = stolenBikerCashService.getFreeStolenBiker();
		 assertEquals(freeStolenBiker.getFullName(),"test");
		 StolenBiker freeStolenBiker1 = stolenBikerCashService.getFreeStolenBiker();
		 assertEquals(freeStolenBiker1.getFullName(),"test2");
	 }

	@Test
	@Transactional
	 void test_add_stolen_biker_without_police_officer(){
		StolenBikerDTO stolenBiker=new StolenBikerDTO();
		stolenBiker.setFullName("test");
		StolenBikerDTO stolenBikerDTO = stolenBikerService.save(stolenBiker);
		assertEquals(stolenBikerDTO.getStolenStatus(), StolenStatus.NOT_ASSIGN);
		stolenBikerCashService.getFreeStolenBiker();
	}

	 @Test
	 @Transactional
	 void test_add_stolen_biker_police_officer(){
		 PoliceOfficerDTO policeOfficerDTO=new PoliceOfficerDTO();
		 policeOfficerDTO.setName("arsham");
		 policeOfficerService.save(policeOfficerDTO);
		 StolenBikerDTO stolenBiker=new StolenBikerDTO();
		 stolenBiker.setFullName("test");
		 StolenBikerDTO stolenBikerDTO = stolenBikerService.save(stolenBiker);
		 assertEquals(stolenBikerDTO.getStolenStatus(), StolenStatus.IN_PROGRESS);
		 stolenBikerCashService.getFreeStolenBiker();
	 }

	 @Test
	 @Transactional
	 void test_add_police_officer_without_stolen_biker(){
		 PoliceOfficerDTO policeOfficerDTO=new PoliceOfficerDTO();
		 policeOfficerDTO.setName("arsham");
		 policeOfficerService.save(policeOfficerDTO);
		 assertEquals(policeOfficerDTO.getStatus(), PoliceOfficerStatus.RELEASE);
		 assertNotNull(policeOfficerCashService.getFreePolice());
	 }

	 @Test
	 @Transactional
	 void test_add_police_with_stolen_biker(){
		 StolenBikerDTO stolenBiker=new StolenBikerDTO();
		 stolenBiker.setFullName("test");
		 stolenBikerService.save(stolenBiker);
		 PoliceOfficerDTO policeOfficerDTO=new PoliceOfficerDTO();
		 policeOfficerDTO.setName("arsham");
		 policeOfficerService.save(policeOfficerDTO);
		 assertEquals(policeOfficerDTO.getStatus(), PoliceOfficerStatus.ENGAGE);
		 policeOfficerCashService.getFreePolice();
	 }

}
