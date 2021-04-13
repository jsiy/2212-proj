package viewers;
/*
 * chiss
 */
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import analysis.Analysis;
import analysis.Analysis1;
import analysis.Analysis2;
import analysis.Analysis4;
import analysis.Analysis5;
import analysis.Analysis6;
import analysis.Analysis7;
import analysis.Analysis8;
import selection.selection;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * The views server exists to handle the rendering of and changes in the specific viewers.
 * @author Chris Ng
 *
 */
public class viewsServer{
	/**
	 * For validation and to obtain ray/empty view objects
	 */
	viewsDB viewDB;
	
	/**
	 * The panel that contains all the views
	 */
	JPanel viewPanel;
	
	/**
	 * The user's selection
	 */
	selection selection; 
	HashMap<String, Object> activePanels; //Panel containing the chartpanel objects of each view
	
	/*
	 * Create ONE instance of a viewsServer in MainUI, the selection object must be a param
	
		Adding/removing views will be triggered by the respective buttons on the mainUI.
		Within mainUI, update the selection object (using its methods) first, THEN trigger the
		addView()/removeView() method.
		
		Alternatively if the other update button is pressed,
		Make sure to update the selection object (using its methods) in the MainUI 
		when the analysis button is pressed before the next step.
		
		Pressing the analysis button will instead trigger the update() method. The
		UPDATED selection obj (which will not be updated in viewServer) must be passed as a param.
		
		This will reset the view panel by recreating the updated selection from scratch.
		
		NO selection obj updating will take place in viewsServer
	 */
	
	/**
	 * Constructor; creates a new views server with the selection of the MainUI
	 * @param s the selection of the MainUI
	 */
	public viewsServer(selection s) {
		
		viewDB = new viewsDB();
		createPanel();
		this.selection = s;
		activePanels = new HashMap<String, Object>();
		
	}
	
	/**
	 * Adds a view to the display
	 * @param newView Name of view
	 */
	public void addView(String newView) {
				
		//Pls implement the getAnalysisName as a graph title!!!!!!
		//Creates a new chart obj
		viewInterface view = viewDB.getGraph(newView);
		view.setData(this, getAnalysisObj().doAnalysis());
		
		if(!(newView.equals("Report"))) {
			//Set design of the chartPanel within the JPanel
			JFreeChart chart = (JFreeChart) viewDB.getGraph(newView).getChartObj();
			ChartPanel chartPanel = new ChartPanel(chart);
			chartPanel.setPreferredSize(new Dimension(400, 300));
			chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
			chartPanel.setBackground(Color.white);
			
			//Adds the chartPanel to the view Panel
			viewPanel.add(chartPanel);
			activePanels.put(newView, chartPanel);			
		}
		else {
			JScrollPane report = (JScrollPane) viewDB.getGraph(newView).getChartObj();
			
			viewPanel.add(report);
			activePanels.put(newView, report);
		}

	}
	
	/**
	 * Removes a view from the display
	 * @param viewToRemove Name of view
	 */
	public void removeView(String viewToRemove) {
		
		viewPanel.remove((Component) activePanels.get(viewToRemove)); //Remove the specified panel from the view panel
		viewDB.resetView(viewToRemove); //Reset the view in the viewDB
		activePanels.remove(viewToRemove); //Remove the data
		
	}
	
	/**
	 * Updates the viewers according to a new selection; this method is called by the "recalculate" action listener
	 * @param s the selection that has been updated or changed
	 */
	public void update(selection s) {
		this.selection = s; // Updates the selection to the new one
		//Since there can only exist one of each view per analysis, the view OBJs in viewDB are the same OBJs being displayed
		//so it is necessary to reset it with blank views when updating (since information will be changed)
		
		//For each view in the selection obj, add the new view to the display
		for (int i = 0; i < selection.getSelectedViews().size(); i++) {
			removeView(selection.getSelectedViews().get(i));
			viewPanel.repaint();
			addView(selection.getSelectedViews().get(i));
		}
		
	}
	
	/**
	 * Getter method for the panel containing all the views
	 * @return the panel containing all the views
	 */
	public JPanel getPanel() {
		return this.viewPanel;
	}
	
	/**
	 * Getter method for the current selection that will be reflected in the charts
	 * @return the current selection that will be reflected in the charts
	 */
	public selection getCurrentSelection() {
		return selection;
	}
	
	/**
	 * Getter method for an analysis object according to the selection type
	 * @return analysis object according to the selection type
	 */
	public Analysis getAnalysisObj() {
		Analysis analysis = null;
		
		switch(selection.getAnalysisType()) {
		case 1:
			analysis = new Analysis1(selection);
			break;
		case 2:
			analysis = new Analysis2(selection);
			break;
		case 3:
			analysis = new Analysis4(selection);
			break;
		case 4:
			analysis = new Analysis4(selection);
			break;
		case 5:
			analysis = new Analysis5(selection);
			break;
		case 6:
			analysis = new Analysis6(selection);
			break;
		case 7:
			analysis = new Analysis7(selection);
			break;
		case 8:
			analysis = new Analysis8(selection);
			break;
		}
		
		return analysis;
	}
	
	/**
	 * Creates a new blank panel
	 */
	private void createPanel() {
		viewPanel = new JPanel();
		//Add whatever front-end characteristics the panel should have
	}
	
}
