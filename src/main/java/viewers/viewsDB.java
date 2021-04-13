package viewers;

/*
 * chiss
 */
import java.util.HashMap;

/**
 * 
 * @author Chris Ng
 *
 */
public class viewsDB {

	/**
	 * The HashMap containing all the view "names" as strings, and their
	 * corresponding view object
	 */
	HashMap<String, viewInterface> viewDB;

	/**
	 * Constructor, initializing the DB
	 */
	public viewsDB() {
		viewDB = new HashMap<String, viewInterface>();
		viewDB.put("Scatter Chart", new scatterView());
		viewDB.put("Bar Chart", new barChart());
		viewDB.put("Line Chart", new lineChart());
		viewDB.put("Report", new Report());

	}

	// Retrieves the view object from the hashmap
	/**
	 * Retrieves the view object from the hashmap
	 * 
	 * @param graphType the type of graph of the view that we're trying to retrieve
	 * @return the view with the specified graph type
	 */
	public viewInterface getGraph(String graphType) {

		return viewDB.get(graphType);

	}

	// Resets the viewDB with fresh views

	/**
	 * Resets the viewDB with fresh views
	 */
	public void reset() {
		viewDB.clear();
		viewDB.put("Scatter Chart", new scatterView());
		viewDB.put("Bar Chart", new barChart());
		viewDB.put("Line Chart", new lineChart());
		viewDB.put("Report", new Report());
		
	}

	/**
	 * Reset a view by removing it from viewsDB and inserting a blank one
	 * 
	 * @param viewName name of view to be reset
	 */
	public void resetView(String viewName) {
		viewDB.remove(viewName);

		if (viewName.equals("Scatter Chart")) {
			viewDB.put(viewName, new scatterView());
		} else if (viewName.contentEquals("Bar Chart")) {
			viewDB.put(viewName, new barChart());
		} else if (viewName.contentEquals("Line Chart")) {
			viewDB.put(viewName, new lineChart());
		} else if (viewName.contentEquals("Report")) {
			viewDB.put(viewName, new Report());
		}
	}

}
