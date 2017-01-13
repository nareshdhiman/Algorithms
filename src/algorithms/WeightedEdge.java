package algorithms;

public class WeightedEdge {
	/**
	 * @return the u
	 */
	public String getU() {
		return u;
	}

	/**
	 * @param u the u to set
	 */
	public void setU(String u) {
		this.u = u;
	}
	
	

	/**
	 * @return the v
	 */
	public String getV() {
		return v;
	}

	/**
	 * @param v the v to set
	 */
	public void setV(String v) {
		this.v = v;
	}

	/**
	 * @return the weight
	 */
	public Integer getWeight() {
		return weight;
	}

	/**
	 * @param weight the weight to set
	 */
	public void setWeight(Integer weight) {
		this.weight = weight;
	}



	String v;
	Integer weight;
	String u;

	public WeightedEdge() {
		super();
		this.weight = 999999999;
	}
	
	public WeightedEdge(int weight, String u, String v) {
		super();
		this.weight = weight;
		this.u = u;
		this.v = v;
	}
	
}