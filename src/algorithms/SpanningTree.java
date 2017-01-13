package algorithms;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class SpanningTree {
	Map<String, Map> adjacencyMap;
	
	public SpanningTree() {
		adjacencyMap = new HashMap<String, Map>();
	}
	public void addVertex(String u) {
		adjacencyMap.put(u, new HashMap());
	}

	public Map<String, Integer> getVertex(String u) {
		return (Map<String, Integer>)adjacencyMap.get(u);
	}

	public int length() {
		return adjacencyMap.size();
	}
	
	public Map<String, Map> map() {
		return adjacencyMap;
	}
	
	public boolean containsVertex(String u) {
		return adjacencyMap.containsKey(u);
	}
	
	public void addEdge( WeightedEdge e) {
		Map<String, Integer> edges = null;				

		if (adjacencyMap.containsKey(e.u)) {
			edges = adjacencyMap.get(e.u);				
		} else {
			edges = new HashMap();
		}

		edges.put(e.v, e.weight);
		adjacencyMap.put(e.u, edges);
		
		//add second vertex 'V'
		if (!adjacencyMap.containsKey(e.v)) {
			adjacencyMap.put(e.v, new HashMap());	
		}
		
	}
	
	public void print() {
		
		for (Entry<String, Map> entry : adjacencyMap.entrySet()) {
			String u = entry.getKey();
			
			//System.out.println("Vertex "+u);
			
			Map<String, Integer> vertices = entry.getValue();
			for (Entry<String, Integer> vw : vertices.entrySet()) {
				String v = vw.getKey();
				Integer w = vw.getValue();
			
				System.out.println("("+u+")------"+w+"-------("+v+")");
			}
		}
	}
	
	public Integer weight() {
		Integer weight = 0;
		
		for (Entry<String, Map> entry : adjacencyMap.entrySet()) {
			String u = entry.getKey();
							
			Map<String, Integer> vertices = entry.getValue();
			for (Entry<String, Integer> vw : vertices.entrySet()) {
				weight = weight + vw.getValue();				
			}
		}
		return weight;
	}
}
