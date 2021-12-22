import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class ArrayAndBFS {

	
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		int[][] graph = loadGraph("graph1.dat");
		
		System.out.println("Connection: "+ isConnected(graph));
	}
	
	
	private static boolean isConnected(int[][] g) {
		// TODO Auto-generated method stub
		ArrayList<Integer[]> current = new ArrayList<>();
		Integer[] start= {0,-1};
		current.add(start);
		ArrayList<Integer[]> next = new ArrayList<>();
		HashSet<Integer> visited = new HashSet<>();
		
		
		while (!current.isEmpty()) {
			for (Integer[] node:current) {
				visited.add(node[0]);
				ArrayList<Integer[]> neighbours = getNextOf(g, node[0], node[1], visited);
				next.addAll(neighbours);
			}
			current = next;
			next = new ArrayList<>();
		}
		
		return g.length == visited.size();
	}


	private static ArrayList<Integer[]> getNextOf(int[][] g, Integer node, int incoming, HashSet<Integer> visited) {
		// TODO Auto-generated method stub
		ArrayList<Integer[]> ret = new ArrayList<>();
		for (int i=0;i<g.length;i++) {
			if (i==node) continue;
			if (i==incoming) continue;
			if (visited.contains(i)) continue;
			if (g[i][node] == 1) {
				Integer[] temp = {i,node};
				ret.add(temp);
			}
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
