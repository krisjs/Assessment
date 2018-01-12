package ufo;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;


import java.util.List;

import org.junit.Test;

import ufo.dto.UfoSighting;
import ufo.service.UfoSightingService;
import ufo.service.UfoSightingServiceImpl;

public class UfoSightingServiceTest {
	
/*
 * This method tests list of sightings and Should returns the total 61393 records.  
 */
	@Test
	public void testListOfAllSighting() {
		UfoSightingService ufoSightingService = new UfoSightingServiceImpl();
		List<UfoSighting> ufoListData = ufoSightingService.getAllSightings();
		assertNotNull(ufoListData);
		assertThat(ufoListData, hasSize(61392));
	}
	
	/*
	 * This method test the search functionality with year and month. It should returns the total 107 records.  
	 */
	@Test
	public void testSearchListOfAllSightingWithYearAndMonth() {
		UfoSightingService ufoSightingService = new UfoSightingServiceImpl();
		List<UfoSighting> ufoListData = ufoSightingService.search(1995,10);
		assertNotNull(ufoListData);
		assertThat(ufoListData, hasSize(106));
	}

}
