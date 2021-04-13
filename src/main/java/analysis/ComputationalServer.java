package analysis;

import java.util.ArrayList;

import selection.selection;

/**
 * The computational server does the bulk of the work for this system; it is responsible for invoking the calculation of analyses, as well as obtaining the selection for the analysis, and creating the analyses via a factory method.
 * @author Joseph Siy, Rosy Ren
 */
public class ComputationalServer{
	/**
	 * The selection that we will be calculating the analysis for
	 */
	  private selection selection;
	  /**
	   * The factory class that houses the factory method for analyses.
	   */
	  private Factory factory;
	  
	  /**
	   * Constructor; takes selection as a parameter and creates a computational server.
	   * @param s the selection that the computational server will leverage
	   */
	  public ComputationalServer(selection s){
		  this.selection = s;
		  this.factory = new Factory(s);
	  }
	  
	  /**
	   * Calculates an analysis based on specifications from the enclosed Selection object.
	   * @return an ArrayList<ArrayList<DataItem>> that can be plotted on viewers
	   */
	  public ArrayList<ArrayList<DataItem>> calculate(){
	    Analysis analysis = factory.FactoryMethod(selection.getAType().getType());
	    return analysis.doAnalysis();
	  }

	  /**
	   * This method is called by the MainUI; upon recalculation, the selection object is updated to reflect the MainUI's selections
	   * @param s the new selection object for the computational server.
	   */
	  public void readSelection(selection s){
	    this.selection = s;
	    this.factory = new Factory(selection);
	  }

	  
	}
