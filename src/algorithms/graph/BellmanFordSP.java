package algorithms.graph;

public class BellmanFordSP {

	public int[] D;	// Estimate tracker
	public int[] PI;	// Predecessor tracker

	public BellmanFordSP() {}

	public static int M = 10000;	//Infinity to show no connection or high weight
		
	/**
	 * Perform Dijkstra shortest algorithm
	 * @param G the given spanning graph
	 * @param s the start vertex
	 */
	public void doBellmanFord(int G[][], int s) {
		
		D = new int[G.length];	// Estimate tracker
		PI = new int[G.length];	// Predecessor tracker
		
		// Initialize Single Source
		for (int i=0; i<G.length; i++) {
			D[i] = M;
			PI[i] = M;
		}
		D[s] = 0;	// initial node distance from itself
		PI[s] = 0;	// Visited from itself
		
		for(int u=0; u<G.length-1; u++) {
			for (int v=0;  v < G[u].length; v++) {
				int w = G[u][v];
				if (w != M) { // skip self vertex and not connected
					//System.out.println("Relexing neighbor Vertex="+v+" edge w="+w+" est w="+D[v]);
					// Relaxing the weight
					if (D[v] > D[u]+w) {
						D[v] = D[u]+w;
						PI[v] = u;
					}
				}

			}
		}
		//run one more time to check if its still relaxing
		
	}
 
	public static void main(String[] args) {

		//Given graph in adjacency matrix representation
		int graph[][] = 
		{		// 	0,	1,	2,	3,	4,	5,	6,	7,	8
				{	M,	4,	M,	M,	M,	M,	M,	8,	M}, //0
				{	4,	M,	8,	M,	M,	M,	M,	11,	M}, //1
				{	M,	8,	M,	7,	M,	4,	M,	M,	2}, //2
				{	M,	M,	7,	M,	9,	14,	M,	M,	M}, //3
				{	M,	M,	M,	9,	M,	10,	M,	M,	M},	//4
				{	M,	M,	4,	14,	10,	M,	2,	M,	M},	//5
				{	M,	M,	M,	M,	M,	2,	M,	1,	6},	//6
				{	8,	11,	M,	M,	M,	M,	1,	M,	7},	//7
				{	M,	M,	2,	M,	M,	M,	6,	7,	M},	//8	
		};
		

		//Select the vertex
		int r = 0;
		BellmanFordSP dsp = new BellmanFordSP();		
		dsp.doBellmanFord(graph, r);
		
		System.out.println("Each vertex weight and predecessor ::"+graph.length);
		for (int i=0; i<graph.length; i++) {
			System.out.println("vertex="+i+" w="+dsp.D[i]+" predecessor="+dsp.PI[i]);
		}
	}

}
