package analysis;

import selection.selection;

/**
 * A subclass of Analysis, this class does not process data and hence requires no overwriting.
 * @author Joseph Siy, Rosy Ren
 */
public class Analysis7 extends Analysis {

	public Analysis7(selection s) {
		super(s);
		super.setAnalysisNameX("Control of corruption");
		super.setAnalysisNameY("Access to electricity");
		
	}

}
