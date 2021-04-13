package worldbankapi;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import selection.selection;

/**
* The WorldBankAPI class can be called as an object to retrieve data from the WorldBank Database and return it as a JsonArray
* @author  Joseph Siy, Rosy Ren
*/
public class WorldBankAPI {
	
	/**
	 * Empty constructor as this class has no instance variables
	 */
	public WorldBankAPI(){

	}
  
	  /**
	  * Retrieves data from the World Bank Database in accordance with the provided Selection
	  * @param selection the selection object for which we retrieve data.
	  */
	public ArrayList<JsonArray> getData(selection selection) {
    ArrayList<JsonArray> Data = new ArrayList<JsonArray>();
    for(int i = 0; i < selection.getAType().getSeries(); i++){
      String urlString = String.format(
				"http://api.worldbank.org/v2/country/%s/indicator/%s?date=%s:%s&format=json", 
				selection.getCountryCode(), 
				selection.getAType().getIndicator(i), 
				selection.getYearStart(), 
				selection.getYearEnd());
      try {
			URL url = new URL(urlString);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			int responsecode = conn.getResponseCode();
			if (responsecode == 200) {
				String inline = "";
				Scanner sc = new Scanner(url.openStream());
				while (sc.hasNext()) {
					inline += sc.nextLine();
				}
				sc.close();
        JsonArray jsonArray = new JsonParser().parse(inline).getAsJsonArray();
        Data.add((JsonArray) jsonArray.get(1));
			}
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return Data;
	}
}

