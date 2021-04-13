package analysis;

/**
 * An object that holds information about its associated analysis type
 * @author Joey Siy
 *
 */
public class AnalysisType{
	
/**
 * the associated number of the analysis
 */
  private int num;
  
  /**
   * The amount of series in the analysis
   */
  private int series;
  
  /**
   * The indicators of the analysis
   */
  private String[] indicators;

  /**
   * Constructor; takes the analysis type in as a number and creates an AnalysisType object with the relevant characteristics
   * @param n the analysis type as a number from one to eight
   */
  public AnalysisType(int n){
    this.num = n;
    switch(n) {
    case 1:
    	this.series = 2;
    	this.indicators = new String[]{"SE.PRM.ENRR", "SE.ADT.LITR.ZS"};
    	break;
    case 2:
    	this.series = 2;
    	this.indicators = new String[]{"CC.EST", "SG.GEN.PARL.ZS"};
    	break;
    case 3:
    	this.series = 2;
    	this.indicators = new String[]{"SE.ADT.LITR.ZS", "SE.XPD.TOTL.GB.ZS"};
    	break;
    case 4:
    	this.series = 2;
    	this.indicators = new String[]{"CC.EST","NY.GDP.MKTP.KD.ZG"};
    	break;
    case 5:
    	this.series = 1;
    	this.indicators = new String[]{"SE.XPD.TOTL.GB.ZS"};
    	break;
    case 6:
    	this.series = 2;
    	this.indicators = new String[]{"AG.LND.AGRI.ZS","AG.LND.FRST.ZS"};
    	break;
    case 7:
    	this.series = 2;
    	this.indicators = new String[]{"CC.EST","EG.ELC.ACCS.ZS"};
    	break;
    case 8:
    	this.series = 2;
    	this.indicators = new String[]{"EN.CLC.SPEI.XD","NY.GDP.MKTP.KD.ZG"};
    	break;
    }
  }

  /**
   * Getter method for the analysis type as its associated number
   * @return the analysis type's number
   */
  public int getType(){
    return num;
  }
  
  /**
   * Getter method for the series of the analysis type
   * @return the analysis type's series
   */
  public int getSeries(){
    return series;
  }

  /**
   * Getter method for the indicators
   * @param n the series for which we want to get the indicator (i.e. n=0 for the first indicator in the list)
   * @return the indicator as a String
   */
  public String getIndicator(int n){
    try{return indicators[n];
    }catch (IndexOutOfBoundsException e){
      return null;
    }
  }
}