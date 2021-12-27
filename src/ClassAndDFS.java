import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ClassAndDFS {

	static int n;
	static Node[] nodes=null;
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Node root = loadGraph("graph1.dat");
		System.out.println("Has rings:" + hasRings(nodes));
	}

	
	private static boolean hasRings(Node[] nodes) {
		// TODO Auto-generated method stub
		Node node = findUnvisitedNode(nodes);

		while (node!=null) {
			if (hasRing(null, node)) {
				return true;
			}
			node = findUnvisitedNode(nodes);
		}
		
		return false;
	}


	private static boolean hasRing(Node incoming, Node node) {
		// TODO Auto-generated method stub
		node.visited = true;
		for (Node child:node.connected) {
			if (child!=incoming) {
				if (child.visited) {
					return true;
				}
				if (hasRing(node,child)) {
					return true;
				}
			}
		}
		return false;
	}


	private static Node findUnvisitedNode(Node[] nodes) {
		// TODO Auto-generated method stub
		for (Node node:nodes) {
			if (!node.visited) {
				return node;
			}
		}
		return null;
	}


	private static Node loadGraph(String path) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(new File(path));
		n = sc.nextInt();
		
		nodes = new Node[n];
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





