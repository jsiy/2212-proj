package viewers;

import java.util.ArrayList;

import org.jfree.chart.JFreeChart;

/**
 * The interface that all the view types extend
 * @author Chris Ng
 */
public interface viewInterface {

	public Object getChartObj();
	
	public void setData(viewsServer server, Object analysis);
	
}
