package gov.nih.nlm.mor.Snomed;

public class SnomedActiveIngredient implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -360030014691136447L;
	private String code = null;
	private String name = null;
	
	public SnomedActiveIngredient(String c, String n) {
		this.code = c;
		this.name = n;
	}
	
	public String getCode() {
		return this.code;
	}
	
	public String getName() {
		return this.name;
	}	

}
