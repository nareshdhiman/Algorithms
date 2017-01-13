package algorithms.graph;

import java.util.Map;
import java.util.Map.Entry;
import algorithms.SpanningTree;
import algorithms.WeightedEdge;

public class GenericMinimumSpanningTree {

	/**
	 * Starts with an empty graph, add an edge one at a time
	 * @param st
	 * @param start
	 * @return
	 */
	public SpanningTree performMST(SpanningTree st, String start) {
		
		SpanningTree mst = new SpanningTree();
		mst.addVertex(start);
		
		//Visit thru all vertices resolved in MST
		int round=1;
		while( mst.length() != st.length()) {
			System.out.println("Processing round : "+round++);

			WeightedEdge edge = null;
			Integer minWeight = 9999;
			
			for (Entry<String, Map> entry : mst.map().entrySet()) {
				String u = entry.getKey();
				
				// Visit thru adjacent vertices of resolved vertex								
				for (Entry<String, Integer> vw : st.getVertex(u).entrySet()) {
					String v = vw.getKey();
					Integer w = vw.getValue();
					
					//System.out.println("processing ("+u+","+v+")");
					//Skip Vertex, if already resolved
					if (!mst.containsVertex(v)) {
						//System.out.println("Not already resolved ("+u+","+v+"), compare weights: w="+w+" minW="+minWeight);
						if (w <= minWeight) {
							//System.out.println("Newly resolved ("+u+","+v+")");
							edge = new WeightedEdge();
							edge.setU(u);
							edge.setV(v);
							edge.setWeight(w);
							
							minWeight = w;
						}
					}
				}
			}
			//add resolved edge to MST
			if (edge != null) {
				mst.addEdge(edge);
			}
		}
		
		return mst;
	}

	public SpanningTree prepareSpanningTree() {
		//prepare given spanning tree
		SpanningTree st = new SpanningTree();
		st.addVertex("a");
		st.addEdge(new WeightedEdge(4, "a", "b"));
		st.addEdge(new WeightedEdge(8, "a", "h"));

		// "b" : {"a" : 4, "h" : 11},
		st.addVertex("b");
		st.addEdge(new WeightedEdge(4, "b", "a"));
		st.addEdge(new WeightedEdge(11, "b", "h"));

		// "c" : {"b" : 8, "i" : 2, "f" : 4, "d" :7},
		st.addVertex("c");
		st.addEdge(new WeightedEdge(8, "c", "b"));
		st.addEdge(new WeightedEdge(2, "c", "i"));
		st.addEdge(new WeightedEdge(4, "c", "f"));
		st.addEdge(new WeightedEdge(7, "c", "d"));
		
		// "d" : {"c" : 7, "f" : 14, "e" : 9},
		st.addVertex("d");
		st.addEdge(new WeightedEdge(7, "d", "c"));
		st.addEdge(new WeightedEdge(14, "d", "f"));
		st.addEdge(new WeightedEdge(9, "d", "e"));

		// "e" : {"d" : 9, "f" : 10},
		st.addVertex("e");
		st.addEdge(new WeightedEdge(9, "e", "d"));
		st.addEdge(new WeightedEdge(10, "e", "f"));

		// "f" : {"e" : 10, "d" : 14, "c" : 4, "g" : 2},
		st.addVertex("f");
		st.addEdge(new WeightedEdge(10, "f", "e"));
		st.addEdge(new WeightedEdge(14, "f", "d"));
		st.addEdge(new WeightedEdge(4, "f", "c"));
		st.addEdge(new WeightedEdge(2, "f", "g"));

		// "g" : {"h" : 1, "i" :6, "f" : 2},
		st.addVertex("g");
		st.addEdge(new WeightedEdge(1, "g", "h"));
		st.addEdge(new WeightedEdge(6, "g", "i"));
		st.addEdge(new WeightedEdge(2, "g", "f"));

		// "h" : {"a" : 8, "b" : 11, "i" : 7, "g" : 1},
		st.addVertex("h");
		st.addEdge(new WeightedEdge(8, "h", "a"));
		st.addEdge(new WeightedEdge(11, "h", "b"));
		st.addEdge(new WeightedEdge(7, "h", "i"));
		st.addEdge(new WeightedEdge(1, "h", "g"));

		// "i" : {"h" : 7, "c" : 2, "g" :6}
		st.addVertex("i");
		st.addEdge(new WeightedEdge(7, "i", "h"));
		st.addEdge(new WeightedEdge(2, "i", "c"));
		st.addEdge(new WeightedEdge(6, "i", "g"));
		
		return st;
	}
	
	public static void main(String[] args) {
	
		System.out.println("Minimum Spanning Algorithm Example");
		GenericMinimumSpanningTree mst = new GenericMinimumSpanningTree();
		
		//prepare given spanning tree
		SpanningTree st = mst.prepareSpanningTree();
		
		//print initial tree
		System.out.println("Given Spanning Tree -->");
		st.print();
		
		SpanningTree solvedST = mst.performMST(st, "i");
		System.out.println("Minimum Spanning Tree :: minimum weight = "+solvedST.weight());
		solvedST.print();
			
		
	}
}
