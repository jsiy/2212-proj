package analysis;

import selection.selection;

/**
 * A subclass of Analysis, this class does not process data and hence requires no overwriting.
 * @author Joseph Siy, Rosy Ren
 */
public class Analysis6 extends Analysis {

	public Analysis6(selection s) {
		super(s);

		super.setAnalysisNameX("Agricultural land");
		super.setAnalysisNameY("Forest area");
	}

}
