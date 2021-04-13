package analysis;

import selection.selection;

/**
 * A subclass of Analysis, this class does not process data and hence requires no overwriting.
 * @author Joseph Siy, Rosy Ren
 */
public class Analysis5 extends Analysis {

	public Analysis5(selection s) {
		super(s);
		super.setAnalysisNameX("Government expenditure on education");
		super.setAnalysisNameY("");
	}

}
