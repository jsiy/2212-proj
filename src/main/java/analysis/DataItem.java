package analysis;

import com.google.gson.JsonObject;

/**
 * The DataItem class represents a single DataPoint that has been drawn from the analysis.
 * @author Joseph Siy, Rosy Ren
 *
 */
public class DataItem {
		/**
		 * The year that the data point represents
		 */
	  private int year;
	  /**
	   * The value that the data point contains
	   */
	  private int value;
	  /**
	   * The indicator that the data point came from
	   */
	  private String indicator;
	  
	  /**
	   * Constructor; parses information from a JsonObject
	   * @param o the object that we will be taking information from
	   */
	  public DataItem(JsonObject o){
	    if(o.get("value").isJsonNull()){
	      this.value = 0;
	    }
	    else{
	      this.value = o.get("value").getAsInt();
	    }
	    this.year = o.get("date").getAsInt();
//	    this.indicator = o.get("id").getAsString();
	    
	  }
	  
	  /**
	   * Getter method for year
	   * @return This data point's year
	   */
	  	public int getYear() {
			return year;
		}
	  	
	  	/**
	  	 * Setter method for year
	  	 * @param year This data point's new year
	  	 */
		public void setYear(int year) {
			this.year = year;
		}
		
		/**
		 * Getter method for value
		 * @return This data point's value
		 */
		public int getValue() {
			return value;
		}
		
		/**
		 * Setter method for value
		 * @param value This data point's new value
		 */
		public void setValue(int value) {
			this.value = value;
		}
		
		/**
		 * Getter method for indicator
		 * @return This data point's indicator
		 */
		public String getIndicator() {
			return indicator;
		}
		
		/**
		 * Setter method for indicator
		 * @param indicator This data point's new indicator
		 */
		public void setIndicator(String indicator) {
			this.indicator = indicator;
		}

	}
