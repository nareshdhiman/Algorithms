package algorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileInputReader {
	public static int M = Integer.MAX_VALUE-10000;
	public EdgeWeighted[] ew;
	public int vertices=0;
	public int edges=0;
	public int unweighted = 0;

	public FileInputReader(String filename) throws FileNotFoundException {
		Scanner lineReader = new Scanner(new File(filename));
		
		int e =0;
		while(lineReader.hasNext()) {
			String line = lineReader.nextLine();
			//System.out.println("read line ="+line);
			String[] what = line.split(" ");
			if (what.length == 1) {
				//first input is vertices
				if (vertices == 0) {
					vertices = Integer.parseInt(what[0]);
				} else if (edges == 0) {
					edges = Integer.parseInt(what[0]);
					ew = new EdgeWeighted[edges];
				} else {
					System.out.println("Either vertices or edges got reading issue!");
				}
			} else if (what.length == 3) {
				if (ew != null) {
					EdgeWeighted edge = new EdgeWeighted();
					edge.u = Integer.parseInt(what[0]);
					edge.v = Integer.parseInt(what[1]);
					edge.weight = Integer.parseInt(what[2]);
					ew[e++] = edge;
				} else {
					System.out.println("No edges provided!");
				}
			} else {
				System.out.println("Invalid input");
			}
		}

		if (edges != e) {
			System.out.println("Not all edges were entered");
		}
	}

	public int[][] getAdjacencyMatrix() {
		int[][] G = new int[vertices][vertices];
		//initialize all values to M
		for( int i=0; i<G.length; i++) {
			for( int j=0; j<G.length; j++) {
				G[i][j] = 0;
			}
		}
		
		for( EdgeWeighted e : ew) {
			G[e.u][e.v] = e.weight;
		}
		return G;
	}
	
	public void print() {
		System.out.println("Vertices = "+vertices);
		System.out.println("Edges    = "+edges);
		for(EdgeWeighted e : ew) {
			System.out.println("Edge ("+e.u+","+e.v+") = "+e.weight);
		}
	}

	public void printAdjMatrix(int[][] G) {
		System.out.println("Adjacency Matrix :: ");
		for( int i=0; i<G.length; i++) {
			System.out.println(" Vertex :: "+i);
			for( int j=0; j<G.length; j++) {
				if (G[i][j] != 0) {
					System.out.println("	Vertices :: "+j+" weight :: "+G[i][j]);
				}
			}
		}
	}

	public static void main(String[] args) throws FileNotFoundException {

		String filename = System.getProperty("user.dir")+"/src/resources/tinyGraph.txt";
		FileInputReader reader = new FileInputReader(filename);
		reader.print();
		
		//print 
		reader.printAdjMatrix(reader.getAdjacencyMatrix());
	}
}
