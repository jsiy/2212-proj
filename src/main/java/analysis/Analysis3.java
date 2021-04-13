package analysis;

import selection.selection;

/**
 * A subclass of Analysis, this class does not process data and hence requires no overwriting.
 * @author Joseph Siy, Rosy Ren
 */
public class Analysis3 extends Analysis {

	public Analysis3(selection s) {
		super(s);
		super.setAnalysisNameX("Literacy rate, adult total");
		super.setAnalysisNameY("Government expenditure on education");
	}

}
