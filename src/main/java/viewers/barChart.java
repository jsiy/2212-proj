package viewers;

import java.awt.Font;
import java.util.ArrayList;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.Year;

import analysis.DataItem;

/**
 * Bar Chart for viewers
 * @author Group20
 *
 */
public class barChart implements viewInterface {
	
	/**
	 * Chart to be returned
	 */
	JFreeChart chart;

	/**
	 * Empty constructor
	 */
	public barChart() {
	}

	/**
	 * Returns chart object
	 * @return chart object
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
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();

			for (int i = 0; i < info.get(0).size(); i++) {
				DataItem dataItem = info.get(0).get(i);
				int year = dataItem.getYear();
				int value = dataItem.getValue();
				dataset.setValue(value, analysisName1, new Year(year));
			}

			// Create 2nd series and append data
			DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();
			for (int i = 0; i < info.get(1).size(); i++) {
				DataItem dataItem = info.get(1).get(i);
				int year = dataItem.getYear();
				int value = dataItem.getValue();
				dataset.setValue(value, analysisName2, new Year(year));
			}

			CategoryPlot plot = new CategoryPlot();
			BarRenderer barrenderer1 = new BarRenderer();
			BarRenderer barrenderer2 = new BarRenderer();

			plot.setDataset(0, dataset);
			plot.setRenderer(0, barrenderer1);
			CategoryAxis domainAxis = new CategoryAxis("Year");
			plot.setDomainAxis(domainAxis);
			plot.setRangeAxis(new NumberAxis(""));

			plot.setDataset(1, dataset2);
			plot.setRenderer(1, barrenderer2);
			plot.setRangeAxis(1, new NumberAxis(""));

			plot.mapDatasetToRangeAxis(0, 0);// 1st dataset to 1st y-axis
			plot.mapDatasetToRangeAxis(1, 1); // 2nd dataset to 2nd y-axis

			chart = new JFreeChart(analysisName1 + " vs " + analysisName2, new Font("Serif", java.awt.Font.BOLD, 18),
					plot, true);
		} else if (info.size() == 1) {
			String analysisName1 = server.getAnalysisObj().getAnalysisNameX();
			String YAxis = "Year";

			// Create first series and append data
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();

			for (int i = 0; i < info.get(0).size(); i++) {
				DataItem dataItem = info.get(0).get(i);
				int year = dataItem.getYear();
				int value = dataItem.getValue();
				dataset.setValue(value, analysisName1, new Year(year));
			}

			CategoryPlot plot = new CategoryPlot();
			BarRenderer barrenderer1 = new BarRenderer();
			BarRenderer barrenderer2 = new BarRenderer();

			plot.setDataset(0, dataset);
			plot.setRenderer(0, barrenderer1);
			CategoryAxis domainAxis = new CategoryAxis("Year");
			plot.setDomainAxis(domainAxis);
			plot.setRangeAxis(new NumberAxis(""));

			// plot.setDataset(1, dataset2);
			// plot.setRenderer(1, barrenderer2);
			// plot.setRangeAxis(1, new NumberAxis("IDK WHAT GOES HERE"));

			plot.mapDatasetToRangeAxis(0, 0);// 1st dataset to 1st y-axis

			chart = new JFreeChart(analysisName1, new Font("Serif", java.awt.Font.BOLD, 18), plot, true);
		}
	}

}