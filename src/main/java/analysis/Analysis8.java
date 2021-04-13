package analysis;

import selection.selection;

/**
 * A subclass of Analysis, this class does not process data and hence requires no overwriting.
 * @author Joseph Siy, Rosy Ren
 */
public class Analysis8 extends Analysis {

	public Analysis8(selection s) {
		super(s);
		
		super.setAnalysisNameX("Mean drought index");
		super.setAnalysisNameY("GDP growth");
	}

}
