import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Scanner;

public class ClassAndBFS {

	static int n;
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Node root = loadGraph("graph1.dat");
		
		System.out.println("Connection: "+ isConnected(root));
		
		
	}

	private static boolean isConnected(Node root) {
		// TODO Auto-generated method stub
		ArrayList<Node[]> current = new ArrayList<>();
		Node[] start = new Node[2];
		start[0] = root;
		start[1] = null;
		current.add(start);
		ArrayList<Node[]> next = new ArrayList<>();
		
		int count=0;
		while (!current.isEmpty()) {
			for (Node[] node:current) {
				node[0].visited = true;
				count++;
				next.addAll(getNeighbours(node));
			}
			current = next;
			next = new ArrayList<>();
		}
		return count==n;
	}

	private static ArrayList<Node[]> getNeighbours(Node[] node) {
		// TODO Auto-generated method stub
		ArrayList<Node[]> ret = new ArrayList<>();
		for (Node n:node[0].connected) {
			if (n.equals(node[1])) continue;
			if (n.visited) continue;
			Node[] temp = {n,node[0]};
			ret.add(temp);
		}
		return ret;
	}

	private static Node loadGraph(String path) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(new File(path));
		n = sc.nextInt();
		
		Node[] nodes = new Node[n];
		for (int i=0;i<n;i++) {
			Node node = new Node(i);
			nodes[i]=node;
		}
		
		int paths = sc.nextInt();
		for (int i=0;i<paths;i++) {
			int n1 = sc.nextInt();
			int n2 = sc.nextInt();
			Node node1 = nodes[n1];
			Node node2 = nodes[n2];
			node1.connected.add(node2);
			node2.connected.add(node1);
		}
		
		return nodes[0];
	}

}

class Node {
	int id;
	ArrayList<Node> connected = new ArrayList<>();
	boolean visited = false;
	
	public Node(int id) {
		this.id = id;
	}
}

