package analysis;

import selection.selection;

/**
 * A subclass of Analysis, this class does not process data and hence requires no overwriting.
 * @author Joseph Siy, Rosy Ren
 */
public class Analysis2 extends Analysis {

	public Analysis2(selection s) {
		super(s);
		super.setAnalysisNameX("Control of Corruption");
		super.setAnalysisNameY("Proportion of seats held by women in national parliaments");
	}

}
