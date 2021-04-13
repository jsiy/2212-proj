package analysis;

import selection.selection;

/**
 * A subclass of Analysis, this class does not process data and hence requires no overwriting.
 * @author Joseph Siy, Rosy Ren
 */
public class Analysis1 extends Analysis {
	
	public Analysis1(selection s) {
		super(s);
		super.setAnalysisNameX("School enrollment, primary and secondary");
		super.setAnalysisNameY("literacy rate, adult total");
	}
}
