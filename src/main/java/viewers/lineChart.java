package viewers;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import analysis.DataItem;

/**
 * @author Chris Ng
 */
public class lineChart implements viewInterface {

	/**
	 * the chart object containing the data visualization
	 */
	JFreeChart chart;

	/**
	 * Empty constructor
	 */
	public lineChart() {
	}

	/**
	 * Returns the chart object containing the data visualization
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
		// PLS implement getAnalysisName in each Analysis to be the TITLES of the
		// analysis
		// ex: # of cars is one title, pollution rate is another title, overall title
		// is # of cars vs pollution rate

		ArrayList<ArrayList<DataItem>> info = (ArrayList<ArrayList<DataItem>>) analysis;
		if (info.size() == 2) {
			String analysisName1 = server.getAnalysisObj().getAnalysisNameX();
			String analysisName2 = server.getAnalysisObj().getAnalysisNameY();

			// Create first series and append data
			XYSeries series1 = new XYSeries(analysisName1);

			for (int i = 0; i < info.get(0).size(); i++) {
				DataItem dataItem = info.get(0).get(i);
				int year = dataItem.getYear();
				int value = dataItem.getValue();
				series1.add(year, value);
			}

			// Create 2nd series and append data
			XYSeries series2 = new XYSeries(analysisName2);
			for (int i = 0; i < info.get(1).size(); i++) {
				DataItem dataItem = info.get(1).get(i);
				int year = dataItem.getYear();
				int value = dataItem.getValue();
				series2.add(year, value);
			}

			// Create a collection and append the series
			XYSeriesCollection dataset = new XYSeriesCollection();
			dataset.addSeries(series1);
			dataset.addSeries(series2);

			// Finally create the chart object
			chart = ChartFactory.createXYLineChart(analysisName1 + " vs " + analysisName2, "Year", "", dataset,
					PlotOrientation.VERTICAL, true, true, false);

			XYPlot plot = chart.getXYPlot();

			XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
			renderer.setSeriesPaint(0, Color.RED);
			renderer.setSeriesStroke(0, new BasicStroke(2.0f));

			plot.setRenderer(renderer);
			plot.setBackgroundPaint(Color.white);

			plot.setRangeGridlinesVisible(true);
			plot.setRangeGridlinePaint(Color.BLACK);

			plot.setDomainGridlinesVisible(true);
			plot.setDomainGridlinePaint(Color.BLACK);

			chart.getLegend().setFrame(BlockBorder.NONE);

			chart.setTitle(
					new TextTitle(analysisName1 + " vs " + analysisName2, new Font("Serif", java.awt.Font.BOLD, 18)));

		} else if (info.size() == 1) {
			String analysisName1 = server.getAnalysisObj().getAnalysisNameX();
			String YAxis = "Year";

			// Create first series and append data
			XYSeries series1 = new XYSeries(analysisName1);

			for (int i = 0; i < info.get(0).size(); i++) {
				DataItem dataItem = info.get(0).get(i);
				int year = dataItem.getYear();
				int value = dataItem.getValue();
				series1.add(year, value);
			}

			// Create a collection and append the series
			XYSeriesCollection dataset = new XYSeriesCollection();
			dataset.addSeries(series1);

			chart = ChartFactory.createXYLineChart(analysisName1, "Year", "", dataset, PlotOrientation.VERTICAL, true,
					true, false);

			XYPlot plot = chart.getXYPlot();

			XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
			renderer.setSeriesPaint(0, Color.RED);
			renderer.setSeriesStroke(0, new BasicStroke(2.0f));

			plot.setRenderer(renderer);
			plot.setBackgroundPaint(Color.white);

			plot.setRangeGridlinesVisible(true);
			plot.setRangeGridlinePaint(Color.BLACK);

			plot.setDomainGridlinesVisible(true);
			plot.setDomainGridlinePaint(Color.BLACK);

			chart.getLegend().setFrame(BlockBorder.NONE);

			chart.setTitle(new TextTitle(analysisName1, new Font("Serif", java.awt.Font.BOLD, 18)));

		}
	}

}