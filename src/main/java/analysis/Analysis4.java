package analysis;

import selection.selection;

/**
 * A subclass of Analysis, this class does not process data and hence requires no overwriting.
 * @author Joseph Siy, Rosy Ren
 */
public class Analysis4 extends Analysis {

	public Analysis4(selection s) {
		super(s);
		super.setAnalysisNameX("Estimated control of corruption");
		super.setAnalysisNameY("GDP growth");
	}

}
