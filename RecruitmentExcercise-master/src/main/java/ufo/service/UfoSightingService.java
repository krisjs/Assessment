package ufo.service;

import ufo.dto.UfoSighting;

import java.io.IOException;
import java.util.List;

public interface UfoSightingService {


    /**
     * Returns all the sightings in the tsv file.
     * @throws IOException 
     */
    List<UfoSighting> getAllSightings() throws IOException;

    /**
     * Search for sightings happened in the specified year and month;
     *
     * @param yearSeen  The year when the sighting happened
     * @param monthSeen The month when the sightings happened
     * @throws IOException 
     */
    List<UfoSighting> search(int yearSeen, int monthSeen) throws IOException;


}
