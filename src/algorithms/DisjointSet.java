package algorithms;

public class DisjointSet {
	int[] parent, rank;

	public DisjointSet() {}

	public DisjointSet(int n) {
		parent = new int[n];
		rank = new int[n];
		//create set
		createSet(n);
	}
	
	public void createSet(int n) {
		for(int i=0; i<n; i++) {
			//initialize parent to itself
			parent[i] = i;
			//set rank to 0
			rank[i] = 0;
		}
	}

	//find the representative of the set
	public int find(int x) {
		if (parent[x] != x)
			//root node is not same means representative is parent of root node
			return find(parent[x]);
		else
			return parent[x];
	}
	
	public void mergeSet(int x, int y) {
		int px = find(x), py = find(y);
		
		if (px == py) return;
		
		if (rank[px] > rank[py])
			//px has more rank, make px parent of py
			parent[py] = px;
		else
			// py has more rank, make py parent of px
			parent[px] = py;
		
		if (rank[px] == rank[py]) rank[px] += 1;
	}
	
	public void union(int x, int y) {
		mergeSet(x,y);
	}
	
	public static void print(int[] parent) {
		System.out.println("Merged graph in array: length="+ parent.length);
		for(int i=0; i<parent.length; i++) {
			System.out.println("node: "+i+ " parent: "+parent[i]);
		}
		
	}
	
	public boolean isfriend(int x, int y) {
		if (find(x) == find(y))
			return true;
		else
			return false;
	}
	public static void main(String[] args) {
		// graph has 14 edges, 9 nodes( 0...8)
		//graph is edge(x, Y), w
		DisjointSet ds = new DisjointSet(9);		
		System.out.println("Graph nodes="+ds.parent.length);
		ds.union(7, 6);
		ds.union(2, 8);
		ds.union(6, 5);
		ds.union(0, 1);
		ds.union(2, 5);
		ds.union(8, 6);
		ds.union(2, 3);
		ds.union(7, 8);
		ds.union(0, 7);
		ds.union(1, 2);
		ds.union(3, 4);
		ds.union(5, 4);
		ds.union(1, 7);
		ds.union(3, 5);

		print(ds.parent);
		
		// check relationships
		System.out.println("8 firend with 2 : "+ds.isfriend(8, 2));
		System.out.println("8 firend with 7 : "+ds.isfriend(8, 7));
		System.out.println("8 firend with 6 : "+ds.isfriend(8, 6));
		System.out.println("4 firend with 3 : "+ds.isfriend(4, 3));
		System.out.println("4 firend with 5 : "+ds.isfriend(4, 5));
		System.out.println("0 firend with 1 : "+ds.isfriend(0, 1));
		System.out.println("0 firend with 7 : "+ds.isfriend(0, 7));
		System.out.println("5 firend with 3 : "+ds.isfriend(5, 3));
		System.out.println("0 firend with 4 : "+ds.isfriend(0, 4));
		
	

		ds = new DisjointSet(5);
		System.out.println("Graph nodes="+ds.parent.length);
		
		ds.union(0, 2);
		ds.union(4, 2);
		ds.union(3, 1);

		print(ds.parent);
		
		// check relationships
		System.out.println("0 firend with 1 : "+ds.isfriend(0, 1));
		System.out.println("0 firend with 4 : "+ds.isfriend(0, 4));
		System.out.println("3 firend with 4 : "+ds.isfriend(3, 4));
		System.out.println("3 firend with 1 : "+ds.isfriend(3, 1));
	}

}
