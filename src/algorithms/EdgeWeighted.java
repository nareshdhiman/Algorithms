package algorithms;

public class EdgeWeighted implements Comparable<EdgeWeighted> {

	public int weight;
	public int u;
	public int v;
	
	public EdgeWeighted(int weight, int v) {
		super();
		this.weight = weight;
		this.v = v;
	}

	public EdgeWeighted(int weight, int v, int u) {
		super();
		this.weight = weight;
		this.v = v;
		this.u = u;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Edge [weight=" + weight + ", u=" + u + ", v=" + v
				+ "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + weight;
		result = prime * result + v;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EdgeWeighted other = (EdgeWeighted) obj;
		if (weight != other.weight)
			return false;
		return true;
	}

	public EdgeWeighted() {}

	@Override
	public int compareTo(EdgeWeighted o) {
		if (weight-o.weight>0) return 1;
		else if (weight-o.weight<0) return -1;
		else return 0;
	}

}
