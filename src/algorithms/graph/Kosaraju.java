/**
 * 
 */
package algorithms.graph;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import algorithms.FileInputReader;

/**
 * @author naresh
 *
 */
public class Kosaraju {

	public Kosaraju() {}
	
	public void visitOrder(int v, List<Integer> visited, int[][] G, Stack finished) {
		if (!visited.contains(v)) {
			// vertex not discovered yet, make it VISITED
			visited.add(v);
			for (int u=0; u<G.length; u++) {
				if( G[v][u] != 0) {
					//visit all children
					visitOrder(u, visited, G, finished);
				}
			}
			
			//All children visited, make it DONE
			finished.push(v);
		}
	}

	public int[][] transpose(int[][] G) {
		int[][] GT = new int[G.length][G.length];
		
		for( int i=0; i<G.length; i++) {
			for(int j=0; j<G.length; j++) {
				GT[j][i] = G[i][j];
			}
		}
		return GT;
	}
	
	public void visitComp(int v, List<Integer> visited, int[][] G, List<Integer> comp ) {
		
		if (!visited.contains(v)) {
			// vertex not discovered, make it VISITED
			visited.add(v);
			for (int u=0; u<G.length; u++) {
				if( G[v][u] != 0) {
					// visit all children
					visitComp(u, visited, G, comp);
				}
			}
			// all children visited, make it COMPONENT
			comp.add(v);
		}
	}
	
	public List doKosaraju(int[][] G) {
		
		Stack finished = new Stack();
		List visited = new ArrayList();
		List stronglyCC = new ArrayList();
		
		//run DFS on original graph
		for (int v=0; v<G.length; v++) {
			this.visitOrder(v, visited, G, finished);
		}
		
		//transpose the original graph
		int[][] GT = this.transpose(G);
		
		//run DFS on transposed graph using the topological order
		visited.clear();
		while(!finished.isEmpty()) {
			//get the top vertex
			int v = (Integer)finished.pop();
			// list for storing components
			List comp = new ArrayList();
			//visit all children
			this.visitComp(v, visited, GT, comp);
			//component found, make it Strongly Connected Component
			if(!comp.isEmpty()) {
				stronglyCC.add(comp);
			}
		}
		
		return stronglyCC;
	}
	
	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {

		String filename = System.getProperty("user.dir")+"/src/resources/sccGraph.txt";
		FileInputReader reader = new FileInputReader(filename);
		reader.print();
		
		int[][] G = reader.getAdjacencyMatrix();
		
		Kosaraju raju = new Kosaraju();
		List stronglyCC = raju.doKosaraju(G);
		
		//Print Strongly Connected Components
		for (Object obj : stronglyCC) {
			List comp = (List)obj;
			System.out.println("Strongly Connected Component :: "+ comp.toString());
		}
	}
}
