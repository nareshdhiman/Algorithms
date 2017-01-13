package algorithms.graph;

import java.io.FileNotFoundException;

import algorithms.DisjointSet;
import algorithms.EdgeWeighted;
import algorithms.FileInputReader;
import algorithms.IndexMinPQ;

public class KrushalMST {

	public KrushalMST() {}

	/**
	 * Perform Kruskal's Minimum Spanning Algorithm
	 * @param G the array containing edges
	 * @param r the number of vertices
	 * @return a array containing weighted edges- resulted Minimum Spanning Tree
	 */
	public EdgeWeighted[] doKrushalMST(EdgeWeighted[] G, int V) {
		EdgeWeighted[] MST = new EdgeWeighted[V-1];
		
		//Initialize a priority queue
		IndexMinPQ<EdgeWeighted> Q = new IndexMinPQ<EdgeWeighted>(G.length);
		for(int i =0; i<G.length; i++) {
			Q.insert( i, new EdgeWeighted(G[i].weight,G[i].v, G[i].u));
		};

		//DisjointSet will also initialize the sets
		DisjointSet ds = new DisjointSet(V);
		
		int i=0;
		while (!Q.isEmpty()) {
			// extract minimum and delete from PQ
			EdgeWeighted edge = Q.minKey();
			Q.delMin();
						
			if (ds.find(edge.u) != ds.find(edge.v)) {
				MST[i++] = edge;
				ds.union(edge.u, edge.v);
			}
		}
		return MST;
	}

	public static void main(String[] args) throws FileNotFoundException {
	
		String filename = System.getProperty("user.dir")+"/src/resources/tinyGraph.txt";
		FileInputReader reader = new FileInputReader(filename);
		reader.print();
		
		KrushalMST k = new KrushalMST();
		EdgeWeighted[] mst = k.doKrushalMST(reader.ew, reader.vertices);
		
		System.out.println("Krushal MST tree :: ");
		for (EdgeWeighted e : mst) {
			System.out.println("MST Edge ("+e.u+","+e.v+") = "+e.weight);
		}
		
	}	

}
