import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class ArrayAndDFS {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		int[][] graph = loadGraph("graph1.dat");
		
		System.out.println("Connection: "+ isConnected(graph));
	}

	private static boolean isConnected(int[][] graph) {
		// TODO Auto-generated method stub
		HashSet<Integer> visited = new HashSet<>(); 
		
		visited.add(0);
		goVisit(graph, -1,0, visited);
		
		
		return visited.size() == graph.length;
	}
	
	
	
	private static void goVisit(int[][] graph, int incoming, int current, HashSet<Integer> visited) {
		// TODO Auto-generated method stub
		ArrayList<Integer> neighbours = getNeighbours(graph, incoming, current, visited);
		for (Integer node:neighbours) {
			visited.add(node);
			goVisit(graph, current, node, visited);
		}
	}
	
	

	private static ArrayList<Integer> getNeighbours(int[][] graph, int incoming, int current, HashSet<Integer> visited) {
		// TODO Auto-generated method stub
		ArrayList<Integer> ret = new ArrayList<>();
		for (int i=0;i<graph.length;i++) {
			if (i==current) continue;
			if (i==incoming) continue;
			if (visited.contains(i)) continue;
			ret.add(i);
		}
		return ret;
	}

	private static int[][] loadGraph(String path) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(new File(path));
		
		int n = sc.nextInt();
		int[][] g = new int[n][n];
		for (int i=0;i<g.length;i++) {
			for (int j=0;j<g[0].length;j++) {
				g[i][j]=0;
			}
		}
		
		int paths = sc.nextInt();
		for (int i=0;i<paths;i++) {
			int node1 = sc.nextInt();
			int node2 = sc.nextInt();
			
			g[node1][node2] = 1;
			g[node2][node1] = 1;
		}
		
		return g;
	}

}
