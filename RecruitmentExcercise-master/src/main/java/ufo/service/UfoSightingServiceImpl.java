/**
 *
 */
package ufo.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import ufo.dto.UfoSighting;

/**
 * @author krishanrao
 * I have used TSV parser from univocity to read the TSV file
 * This Class is implemented for reading and searching records from TSV file
 *
 */
public class UfoSightingServiceImpl implements UfoSightingService{
    
    
    /**
     * This method reads the TSV file
     *
     */
    
    public List<String[]> getAllSightingsFromTSVFile() {
        
        List<String[]> ufoRecords = new ArrayList<String[]>();
        
        String line="";
        try{
            BufferedReader br = new BufferedReader(getReader("ufo_awesome.tsv"));
            while ((line = br.readLine()) != null ) {
                String[] lineVariables = line.split("\t");
                ufoRecords.add(lineVariables);
            }
        } catch (IOException e) {
            System.err.println(e);
        }
        return ufoRecords;
    }
    
    /**
     * This method returns total 61393 records the TSV file
     *
     */
    public List<UfoSighting>    getAllSightings() {
        List<String[]> ufoRecords = getAllSightingsFromTSVFile();
        List<UfoSighting> uforows = new ArrayList<UfoSighting>();
        String dateSeen="";
        String dateReported="";
        String placeSeen="";
        String shape="";
        String duration="";
        String description="";
        for(String[] strlist : ufoRecords) {
            
            if(null != strlist[0]) {
                dateSeen = strlist[0].toString();
            }
            if(null != strlist[1]) {
                dateReported=strlist[1].toString();
            }
            if(null != strlist[2]) {
                placeSeen=strlist[2].toString();
            }
            if(null != strlist[3]) {
                shape=strlist[3].toString();
            }
            try {
                if(null != strlist[4] ){
                    duration=strlist[4].toString();
                }
            }catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
            }
            try {
                if(null != strlist[5]) {
                    description=  strlist[5].toString();
                }
            }catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
            }
            
            UfoSighting uforows1 = new UfoSighting(dateSeen,dateReported,placeSeen,shape,duration,description);
            
            uforows.add(uforows1);
        }
        return uforows;
    }
    
    /**
     *
     * This method is to read the TSV file path from resources.
     *
     */
    public Reader getReader(String relativePath) {
        
        try {
            return new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream(relativePath), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            
            e.printStackTrace();
        }
        return null;
    }
    /**
     *
     * This method is to search the Sightings based on year and month from the TSV file.
     *
     */
    public List<UfoSighting> search(int yearSeen, int monthSeen) {
        String years = Integer.toString(yearSeen);
        String month = Integer.toString(monthSeen);
        String  yearAndMonth= years.concat(month);
        List<UfoSighting> ufoListData = getAllSightings();
        List<UfoSighting> ufoSortedData =new ArrayList<UfoSighting>();
        for(UfoSighting u: ufoListData) {
            if(u.getDateSeen().contains(yearAndMonth)) {
                ufoSortedData.add(u);
            }
        }
        return ufoSortedData;
    }
    
    
}

