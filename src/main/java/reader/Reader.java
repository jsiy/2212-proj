package reader;

import java.util.ArrayList;

import com.google.gson.JsonArray;

import analysis.DataItem;
import selection.selection;
import worldbankapi.WorldBankAPI;

/**
 * The Reader class takes data from the WorldBankAPI, and then parses data from the provided JsonArray into an ArrayList<ArrayList<Data>>, for use by other classes
 * 
 * @author Joseph Siy, Rosy Ren
 */

public class Reader {

	  public Reader(){}
	  
	  /**
	   * The getData function is called by the data object in the analysis subsystem, and retrieves data from the WorldBankAPI, reads it, transforms it into a more usable format, then returns it.
	   * @param selection the selection for which we wish to obtain data
	   * @return the data in an ArrayList<ArrayList<DataItem>> format
	   */
	  public ArrayList<ArrayList<DataItem>> getData(selection selection){
	    WorldBankAPI api = new WorldBankAPI();
	    ArrayList<JsonArray> rawdata = new ArrayList<JsonArray>();
	    rawdata = api.getData(selection);
	    ArrayList<ArrayList<DataItem>> returndata = new ArrayList<ArrayList<DataItem>>();


	    for(int i = 0; i < rawdata.size(); i++){
	      returndata.add(new ArrayList<DataItem>());
	      for(int j = 0; j < rawdata.get(i).getAsJsonArray().size(); j++){
	          returndata.get(i).add(new DataItem(rawdata.get(i).getAsJsonArray().get(j).getAsJsonObject()));
	      }
	    }
	    return returndata; 
	  }
}
