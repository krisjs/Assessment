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
     * @throws IOException 
     *
     */
public List<UfoSighting> getAllSightings() throws IOException {
        
        List<UfoSighting> uforows = new ArrayList<UfoSighting>();
        
        String line="";
        UfoSighting uforows1;
        String dateSeen="";
        String dateReported="";
        String placeSeen="";
        String shape="";
        String duration="";
        String description="";
        BufferedReader br = null;
        try{
            br = new BufferedReader(getReader("ufo_awesome.tsv"));
            while ((line = br.readLine()) != null ) {
                String[] lineVariables = line.split("\t");
                if(null != lineVariables[0]) {
                    dateSeen = lineVariables[0].toString();
                }
                if(null != lineVariables[1]) {
                    dateReported=lineVariables[1].toString();
                }
                if(null != lineVariables[2]) {
                    placeSeen=lineVariables[2].toString();
                }
                if(null != lineVariables[3]) {
                    shape=lineVariables[3].toString();
                }
                try {
                    if(null != lineVariables[4] ){
                        duration=lineVariables[4].toString();
                    }
                }catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(e.getMessage());
                }
                try {
                    if(null != lineVariables[5]) {
                        description=  lineVariables[5].toString();
                    }
                }catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(e.getMessage());
                }
                
                uforows1 = new UfoSighting(dateSeen,dateReported,placeSeen,shape,duration,description);
                
                uforows.add(uforows1);
            
            }
        } catch (IOException e) {
            System.err.println(e);
        }
        br.close();
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
     * @throws IOException 
     *
     */
    public List<UfoSighting> search(int yearSeen, int monthSeen) throws IOException {
        String years = Integer.toString(yearSeen);
        String month = Integer.toString(monthSeen);
        String  yearAndMonth= years.concat(month);
        List<UfoSighting> ufoListData = getAllSightings();
        List<UfoSighting> ufoSortedData =new ArrayList<UfoSighting>();
        for(UfoSighting u: ufoListData) {
            if(u.getDateSeen().startsWith(yearAndMonth)) {
                ufoSortedData.add(u);
            }
        }
        return ufoSortedData;
    }
    
    
}

