package viewers;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Year;

import analysis.DataItem;

/**
 * Viewer class for Scatter Charts
 * @author Chris Ng
 */
public class scatterView implements viewInterface {
	
	/**
	 * the chart that will contain the viewer analysis
	 */
	JFreeChart chart;
	
	/**
	 * Empty constructor
	 */
	public scatterView() {
	}
	
	/**
	 * Getter method for the scatterview chart
	 * @return the chart containing the scatterview
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
	public void setData(viewsServer server, Object analysis){
		//PLS implement getAnalysisName in each Analysis to be the TITLES of the analysis
		//ex: # of cars is one title, pollution rate is another title, overall title
		// is # of cars vs pollution rate
		
		ArrayList<ArrayList<DataItem>> info = (ArrayList<ArrayList<DataItem>>)analysis; 
		if(info.size()==2) {
			String analysisName1 = server.getAnalysisObj().getAnalysisNameX();
			String analysisName2 = server.getAnalysisObj().getAnalysisNameY();
			
			//Create first series and append data
			TimeSeries series1 = new TimeSeries(analysisName1);
			
			for (int i = 0; i < info.get(0).size(); i++) {
				DataItem dataItem = info.get(0).get(i);
				int year = dataItem.getYear();
				int value = dataItem.getValue();
				series1.add(new Year(year), value);
			}
		
			//Create 2nd series and append data
			TimeSeries series2 = new TimeSeries(analysisName2);
			for (int i = 0; i < info.get(1).size(); i++) {
				DataItem dataItem = info.get(1).get(i);
				int year = dataItem.getYear();
				int value = dataItem.getValue();
				series2.add(new Year(year), value);
			}
			
			//Create a collection and append the series
			TimeSeriesCollection dataset = new TimeSeriesCollection();
			dataset.addSeries(series1);
			dataset.addSeries(series2);
			
			//Create a new plot and renderer
			//Make each series a different color representation
			XYPlot plot = new XYPlot();
			XYItemRenderer itemrenderer1 = new XYLineAndShapeRenderer(false, true);
			XYItemRenderer itemrenderer2 = new XYLineAndShapeRenderer(false, true);
			itemrenderer1.setSeriesPaint(0, Color.RED);
			itemrenderer1.setSeriesPaint(1, Color.GREEN);
			
			plot.setDataset(dataset);
			plot.setRenderer(itemrenderer1);
			plot.setDomainAxis(new DateAxis("Year"));
			plot.setRangeAxis(new NumberAxis(""));
			
			//Finally create the chart object
			chart = new JFreeChart(analysisName1 + " vs. " + analysisName2, new Font("Serif", java.awt.Font.BOLD, 18), plot, true);
		}
		else if(info.size()==1) {
			String analysisName1 = server.getAnalysisObj().getAnalysisNameX();
			String YAxis = "Year";
			
			//Create first series and append data
			TimeSeries series1 = new TimeSeries(analysisName1);
			
			for (int i = 0; i < info.get(0).size(); i++) {
				DataItem dataItem = info.get(0).get(i);
				int year = dataItem.getYear();
				int value = dataItem.getValue();
				series1.add(new Year(year), value);
			}
			
			//Create a collection and append the series
			TimeSeriesCollection dataset = new TimeSeriesCollection();
			dataset.addSeries(series1);
			
			//Create a new plot and renderer
			//Make each series a different color representation
			XYPlot plot = new XYPlot();
			XYItemRenderer itemrenderer1 = new XYLineAndShapeRenderer(false, true);
			itemrenderer1.setSeriesPaint(0, Color.RED);
			
			plot.setDataset(dataset);
			plot.setRenderer(itemrenderer1);
			plot.setDomainAxis(new NumberAxis(analysisName1));

			//Finally create the chart object
			chart = new JFreeChart(analysisName1, new Font("Serif", java.awt.Font.BOLD, 18), plot, true);
		}
	}

	
	
}