package viewers;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import analysis.DataItem;

/**
 * Report viewer
 * @author Joey Siy, Rosy Ren
 */
public class Report implements viewInterface{
	
	/**
	 * The JScrollPane that contains the analysis
	 */
	JScrollPane chart;
	
	/**
	 * Empty constructor
	 */
	public Report() {}
	
	/**
	 * Returns the chart object
	 */
	public Object getChartObj() {
		return chart;
	}
	
	/**
	 * This function sets the data and creates the chart
	 * Chart is only actually instantiated with setData method which is called in the viewsServer
	 * This is because graph objs are stored in the viewsDB and couldn't contain any information if a selection wasnt made yet
	 * @param server the viewsserver that is handling the chart
	 * @param analysis the Analysis object that we are creating a viewer for
	 */
	public void setData(viewsServer server, Object analysis) {
		JTextArea report = new JTextArea();
		report.setEditable(false);
		report.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		report.setBackground(Color.white);
		String reportMessage = "";
		ArrayList<ArrayList<DataItem>> info = (ArrayList<ArrayList<DataItem>>) analysis;
		reportMessage = reportMessage + server.getAnalysisObj().getAnalysisNameX() + " vs " + server.getAnalysisObj().getAnalysisNameY() + "\n";
		reportMessage = reportMessage + "==============================\n";
		for(int i = 0; i < info.get(0).size(); i++) {
			reportMessage += "Year " + info.get(0).get(i).getYear() + ":\n";
			for(int j = 0; j < info.size(); j++) {
				if(j==0) {
					reportMessage += "\t" + server.getAnalysisObj().getAnalysisNameX() + 
							" => " + info.get(j).get(i).getValue() + "\n";
				}
				else if(j==1) {
					reportMessage += "\t" + server.getAnalysisObj().getAnalysisNameY() + 
							" => " + info.get(j).get(i).getValue() + "\n";
				}
			}
		}
		report.setText(reportMessage);
		
		JScrollPane outputScrollPane = new JScrollPane(report);
		outputScrollPane.getViewport().setPreferredSize(new Dimension(512, 448));
	      outputScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	      outputScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		chart = outputScrollPane;
	}

}

