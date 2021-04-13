package analysis;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import selection.selection;

/**
 * The super class of all 8 analysis methods. Contains a generic doAnalysis() method that simply passes along data. This generic method can be overwritten in sub classes should we need to process the data.
 * This class also exists to employ the strategy (and indirectly facade) design patterns.
 * @author Joseph Siy, Rosy Ren
 *
 */
public class Analysis{
	/**
	 * Names for x and y axes
	 */
	private String nameX, nameY;
	/**
	 * The selection for which we perform analysis
	 */
	private selection selection;
	
	/**
	 * The constructor; simply creates an object with the specified selection
	 * @param s the specified selection
	 */
	public Analysis(selection s){
		this.selection = s;
	}
	
	/**
	 * The doAnalysis method will by default pass along data; subclasses may overwrite this.
	 * @return an ArrayList<ArrayList<DataItem>> with the processed data
	 */
	public ArrayList<ArrayList<DataItem>> doAnalysis(){
		Data data = new Data();
		System.out.println("fetching data for " + selection.getCountry() + ", year: " + selection.getYearStart() + "-" + selection.getYearEnd());
		boolean empty = true;
		ArrayList<ArrayList<DataItem>> list = Data.getData(selection);
		for(int i = 0; i < list.size(); i++) {
			for(int j = 0; j < list.get(i).size(); j++) {
				if(list.get(i).get(j).getValue()!=0) {
					empty = false;
				}
			}
		}
		if(empty) {
			JOptionPane.showMessageDialog(null, "No data available, please choose a different selection.", "Error", JOptionPane.INFORMATION_MESSAGE);
		}
		return list;
	}
	
	/**
	 * Getter method for analysis name to be plotted on x axis
	 * @return analysis name to be plotted on x axis
	 */
	public String getAnalysisNameX() {
		return nameX;
	}

	/**
	 * Setter method for analysis name to be plotted on x axis
	 * @param nameX analysis name to be plotted on x axis
	 */
	public void setAnalysisNameX(String nameX) {
		this.nameX = nameX;
	}
	
	/**
	 * Getter method for analysis name to be plotted on y axis
	 * @return analysis name to be plotted on y axis
	 */
	public String getAnalysisNameY() {
		return nameY;
	}
	
	/**
	 * Setter method for analysis name to be plotted on y axis
	 * @param nameY analysis name to be plotted on y axis
	 */
	public void setAnalysisNameY(String nameY) {
		this.nameY = nameY;
	}
}
