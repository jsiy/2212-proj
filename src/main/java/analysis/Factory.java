package analysis;

import selection.selection;

/**
 * The factory class is used to employ the Factory method design, so that we can dynamically and generically create analyses at runtime.
 * @author Joseph Siy, Rosy Ren
 *
 */
public class Factory{
	/**
	 * The selection that we want to push to the created analyses
	 */
	  private selection selection;
	  
	  /**
	   * The constructor; creates a factory class with the given selection
	   * @param s the selection of the analysis to be created
	   */
	  public Factory (selection s){
	    this.selection = s;
	  }
	  
	  /**
	   * Setter method for the factory's selection
	   * @param newSelection the new selection that will be used to create analyses
	   */
	  public void setSelection(selection newSelection) {
		  this.selection = newSelection;
	  }
	  
	  /**
	   * The factory method; takes an integer as input, creates the corresponding analysis, and returns it.
	   * @param num corresponds to the analysis' number
	   * @return returns the created analysis, or null if none were created.
	   */
	  public Analysis FactoryMethod(int num){
	    try{
	      if(num==1){
	        return new Analysis1(selection);
	      }
	      else if(num==2){
	        return new Analysis2(selection);
	      }

	      else if(num==3){
	        return new Analysis3(selection);
	      }

	      else if(num==4){
	        return new Analysis4(selection);
	      }

	      else if(num==5){
	        return new Analysis5(selection);
	      }

	      else if(num==6){
	        return new Analysis6(selection);
	      }

	      else if(num==7){
	        return new Analysis7(selection);
	      }

	      else if(num==8){
	        return new Analysis8(selection);
	      }
	      else{
	        throw new Exception();
	      }
	    }
	    catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	    
	  }

	}