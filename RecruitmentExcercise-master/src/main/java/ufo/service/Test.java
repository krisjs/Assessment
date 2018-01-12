/**
 * 
 */
package ufo.service;

/**
 * @author krishanrao
 *
 */
public class Test {

	/**
	 * @param args
	 */
	 static UfoSightingServiceImpl ufoSightingService = new UfoSightingServiceImpl();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//ufoSightingService.getAllSightings();
		ufoSightingService.search(1995, 10);
		
	}

}
