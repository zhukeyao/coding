import java.util.*;

class Node {
	public int value;
	public int x;
	public int y;
	public Node(int index_x, int index_y, int v) {
		value = v;
		x = index_x;
		y = index_y;
	}
}

class NodeComparator implements Comparator<Node> {
	public int compare(Node n1, Node n2) {
		if (n1.value > n2.value) return 1;
		if (n1.value == n2.value) return 0;
		return -1;
	}
}

class findN {
	private static final int N = 10;
	public static void main(String[] argv) {
		findN sol = new findN();
		int[] x = new int[N];
		int[] y = new int[N];
		
		for (int i=0; i<N; i++) {
			x[i] = 10 - i;
			y[i] = 2 * 10 - i;
		}
		
		int[] result = new int[N];
		result = sol.find_n_max(x, y, N);
		
		for (int i=0; i<N; i++) {
			System.out.println(result[i]);
		}
	}

	int[] find_n_max(int[] x, int[] y, int N) {
		NodeComparator comp = new NodeComparator();
		PriorityQueue<Node> heap = new PriorityQueue<Node>(N, comp);
		int x_index = 0;
		int y_index = 0;
		Node node = new Node(x_index, y_index, x[x_index] + y[y_index]);
		int[] result = new int[N];
		heap.add(node);
		int pos = 0;
		while (pos < N) {
			node = heap.poll();	
			int x_temp = node.x;
			int y_temp = node.y;
			result[pos] = node.value;
			pos++;
			int x_next = x_temp == N-1 ? N-1 : x_temp+1;
			int y_next = y_temp == N-1 ? N-1 : y_temp+1;
			node = new Node(x_temp, y_next, x[x_temp]+y[y_next]);
			heap.add(node);
			node = new Node(x_next,y_next, x[x_next]+y[y_next]);
			heap.add(node);
		}
		return result;
	}
}
