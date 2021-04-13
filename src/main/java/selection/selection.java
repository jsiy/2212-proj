package selection;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import analysis.AnalysisType;
/**
 * @author Chris Ng
 */
public class selection {
	
	/**
	 * The starting year for the analysis
	 */
	private int yearStart;
	
	/**
	 * The ending year for the analysis
	 */
	private int yearEnd;
	
	/**
	 * The analysis type as a number from one to eight
	 */
	private int analysisType;
	
	/**
	 * The analysis stype as an object that we can get information from; initialized with the int analysisType
	 */
	private AnalysisType aType;
	
	/**
	 * A list of all the viewTypes we wish to render
	 */
	private ArrayList<String> viewType;
	
	/**
	 * A temporary holding place for whatever is in the "viewsList" ComboBox
	 */
	private String toSelect;
	
	/**
	 * The country for which we wish to select an analysis, stored as a String in plain english
	 */
	private String country;
	
	/**
	 * A 2d matrix built from the country to analysis DB text file
	 */
	private String[][] CountryToAnalysisDB = new String[8][];
	
	/**
	 * A 2d matrix built from the country to year DB text file
	 */
	private String[][] CountryToYearDB = new String[294][];
	
	/**
	 * A 2d matrix built from the viewers to analysis DB text file
	 */
	private String[][] ViewersToAnalysisDB = new String[8][];

	/**
	 * Constructor; intializes values to default (what they are upon startup in the MainUI), as well as reads and builds the databases for selection validation
	 */
	public selection() {
		this.aType = new AnalysisType(1);
		yearStart = 2020;
		yearEnd = 2021;
		analysisType = 1;
		country = "Afghanistan";
		viewType = new ArrayList<String>();
		toSelect = null;
		
		
		try {
			BufferedReader file = new BufferedReader(new FileReader("CountryToAnalysisDB.txt"));
			String line = "";
			int counter = 0;
			while ((line = file.readLine()) != null) { 
		    	String[] arr = line.split(",");
		    	this.CountryToAnalysisDB[counter] = arr;
		    	counter++;
		    }
		}
		catch(Exception e){
			System.out.println("Country file not found. Cannot access database");
		}
		
		try {
			BufferedReader file = new BufferedReader(new FileReader("CountryToYearDB.txt"));
			String line = "";
			int counter = 0;
			file.readLine();
			while ((line = file.readLine()) != null) { 
				String[] arr = line.replaceAll("\"", "").split(",");
				String[] subarr = new String[4];
				for(int i = 1; i < 5; i++) {
					subarr[i-1] = arr[i];
				}
				this.CountryToYearDB[counter] = subarr;
				counter++;
		    }
		}
		catch(Exception e){
			System.out.println(e.getStackTrace());
			System.out.println("Years file not found. Cannot access database");
		}
		try {
			BufferedReader file = new BufferedReader(new FileReader("ViewersToAnalysisDB.txt"));
			String line = "";
			int counter = 0;
			while ((line = file.readLine()) != null) { 
		    	String[] arr = line.split(",");
		    	this.ViewersToAnalysisDB[counter] = arr;
		    	counter++;
		    }
		}
		catch(Exception e){
			System.out.println("Viewers file not found. Cannot access database");
		}
	}
	
	/**
	 * This method is used by the API to convert the country to a country code we can query the API for
	 * @return the selected country as a 3 character code
	 */
	public String getCountryCode() {
		for(int i = 0; i < CountryToYearDB.length; i++) {
			if(CountryToYearDB[i][0].equals(this.country)) {
				return CountryToYearDB[i][3];
			}
		}
		return null;
	}
	
	/**
	 * Getter method for country
	 * @return the selected country
	 */
	public String getCountry(){
		return this.country;
	}
	
	/**
	 * Setter method for country
	 * @param country the new selected country
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	
	/**
	 * Getter method for viewtypes
	 * @return all the viewtypes currently selected
	 */
	public ArrayList<String> getViewType() {
		return this.viewType;
	}
	
	/**
	 * Sets the temporary holding place for viewsList ComboBox selections
	 * @param view the currently selected viewsList ComboBox selection
	 */
	public void setToSelect(String view) {
		this.toSelect = view;
	}
	
	/**
	 * Getter method for the current viewsList ComboBox selection
	 * @return the current viewsList ComboBox selection
	 */
	public String getToSelect() {
		return this.toSelect;
	}
	
	/**
	 * Setter method for the year start
	 * @param year starting year for the analysis
	 */
	public void setSelectionYearStart(int year) {
		yearStart = year;
	}
	
	/**
	 * Setter method for the year end
	 * @param year ending year for analysis
	 */
	public void setSelectionYearEnd(int year) {
		yearEnd = year;
	}
	
	/**
	 * Setter method for the year end
	 * @param type the type of analysis as an integer
	 */
	public void setAnalysisType(int type) {
		analysisType = type;
	}
	
	/**
	 * Adds a specified viewtype
	 * @param type the specified viewtype
	 */
	public void addViewType(String type) {
		viewType.add(type);
	}
	
	/**
	 * Removes a specified viewtype
	 * @param type the specified viewtype
	 */
	public void removeViewType(String type) {
		viewType.remove(type);
	}
	
	/**
	 * Clears all viewtypes
	 */
	public void clearViewType() {
		this.viewType = new ArrayList<String>();
	}
	
	/**
	 * Getter method for the start year
	 * @return start year for the selection
	 */
	public int getYearStart() {
		return yearStart;
	}
	
	/**
	 * Getter method for the end year
	 * @return end year for the selection
	 */
	public int getYearEnd() {
		return yearEnd;
	}
	
	/**
	 * Getter method for the analysis type (number)
	 * @return the analysis type (as a number)
	 */
	public int getAnalysisType() {
		return analysisType;
	}
	
	/**
	 * Getter method for the list of viewtypes
	 * @return the list of selected views
	 */
	public ArrayList<String> getSelectedViews() {
		return viewType;
	}
	
	/**
	 * Getter method for the AnalysisType object
	 * @return the analysis type (as an AnalysisType)
	 */
	public AnalysisType getAType() {
		return this.aType;
	}
	
	/**
	 * Setter method for the AnalysisType object
	 * @param newType the new AnalysisType
	 */
	public void setAType(AnalysisType newType) {
		this.aType = newType;
	}
	
	/**
	 * Validates a selected country against the selected analysis
	 * @param country the country to be validated
	 * @return true if valid, false otherwise
	 */
	public boolean validateCountry(String country) {
		for(int i = 0; i < CountryToAnalysisDB[this.analysisType - 1].length; i++) {
			if(CountryToAnalysisDB[this.analysisType - 1][i].equals(country)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Validates a selected year range against the selected country
	 * @param start the start of the year range
	 * @param end the end of the year range
	 * @return true if valid, false otherwise
	 */	
	public boolean validateYears(int start, int end) {
		for(int i = 0; i < CountryToYearDB.length; i++) {
			if(CountryToYearDB[i][2].equals("Now")) {
				CountryToYearDB[i][2] = "2021";
			}
			if(CountryToYearDB[i][0].equals(this.country)
					&& Integer.parseInt(CountryToYearDB[i][1]) < start
					&& Integer.parseInt(CountryToYearDB[i][2]) > end
					&& !(start>end) ) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Validates a selected viewer against the selected analysis
	 * @param viewer the name of the viewer to be validated
	 * @return true if valid, false otherwise
	 */	
	public boolean validateViewer(String viewer) {
		if(viewType.contains(viewer)) {
			return false;
		}
		for(int i = 0; i < ViewersToAnalysisDB[this.analysisType - 1].length; i++) {
			if(ViewersToAnalysisDB[this.analysisType - 1][i].equals(viewer)) {
				return true;
			}
		}
		return false;
	}
	
}
