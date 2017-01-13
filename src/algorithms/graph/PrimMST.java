package algorithms.graph;

import algorithms.IndexMinPQ;
import algorithms.EdgeWeighted;

public class PrimMST {
		
	public static int M = Integer.MAX_VALUE;

	public PrimMST() {}
	
	/**
	 * Perform Prim's Minimum Spanning Algorithm
	 * @param G the given spanning graph
	 * @param r the start vertex
	 * @return a array containing weighted edges- resulted Minimum Spanning Tree
	 */
	public EdgeWeighted[] doPrimMST(int G[][], int r) {
		EdgeWeighted MST[] = new EdgeWeighted[G.length];
		
		//Initialize a priority queue
		IndexMinPQ<EdgeWeighted> Q = new IndexMinPQ<EdgeWeighted>(G.length);
		for(int i =0; i<G.length; i++) {
			Q.insert( i, new EdgeWeighted(M,M));
		};

		// Set root or the start vertex
		Q.decreaseKey(r, new EdgeWeighted(0,0));
		
		int i=0;
		int outerCntr = 0;
		int innerCntr = 0;
		while (!Q.isEmpty()) {
			// extract minimum
			EdgeWeighted edge = Q.minKey();
			int u = Q.delMin();
			
			//System.out.println("Q poped, location="+u+" "+edge);
			
			// For saving the resulted Minimum Spanning Tree
			edge.u = u;
			MST[i++] = edge;

			for (int v=0;  v < G[u].length; v++) {
				int w = G[u][v];
				
				//System.out.println("Processing graph ("+u+","+v+") key="+w);

				if (Q.contains(v)) {
					edge = Q.keyOf(v);
					//System.out.println("Q location ("+v+") contains="+edge);
					if (w < edge.weight) {
						//System.out.println("Updating Q location ("+v+") key="+w+" pi="+u);
						Q.decreaseKey(v, new EdgeWeighted(w,u));		
						innerCntr++;
					}
				}
			}
			outerCntr++;
		}
		
		System.out.println("Total loop counts:: outer="+outerCntr+" inner="+innerCntr);
		return MST;
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
		PrimMST pmst = new PrimMST();		
		EdgeWeighted mst[] = pmst.doPrimMST(graph, r);
		
		//print the resulted MST
		System.out.println("Graph V="+graph.length);
		int totalWeight = 0;
		for (int i=0; i<mst.length; i++) {
			System.out.println("MST - "+mst[i]);
			totalWeight += mst[i].weight;
		}
		
		//print the total weight
		System.out.println("MST - Total Weight="+totalWeight);

	}

}
