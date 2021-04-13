package analysis;

import java.util.ArrayList;

import reader.Reader;
import selection.selection;

/**
 * The data class simply retrieves the data from the reader, and then provides this data to analyses.
 * 
 * @author Joseph Siy, Rosy Ren
 */
public class Data{
	public Data(){

	}
	
	/**
	 * The getData function retrieves data from the reader, and returns it to the calling object
	 * @param selection the selection for which we wish to receive data
	 * @return the data that has been read and transformed from the WorldBankAPI
	 */
	public static ArrayList<ArrayList<DataItem>> getData(selection selection){
		Reader reader = new Reader();
	    return reader.getData(selection);
	}

}