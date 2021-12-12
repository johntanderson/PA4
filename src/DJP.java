

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

public class DJP extends Graph {

	public DJP(String filePath, GraphType type) throws FileNotFoundException {
		super(filePath, type);
	}

	public ArrayList<Edge> runDJP(int source) throws IndexOutOfBoundsException { // complete this function
		int[] label = new int[numVertices];
		Edge[] parent = new Edge[numVertices];
		PriorityQueue<PriorityQueueElement> open = new PriorityQueue<>(new PriorityQueueElementComparator());
		boolean[] closed = new boolean[numVertices];
		ArrayList<Edge> result = new ArrayList<>();
		for (int i = 0; i < numVertices; i++) {
			label[i] = Integer.MAX_VALUE;
			closed[i] = false;
		}
		label[source] = 0;
		open.add(new PriorityQueueElement(source, 0));
		while (open.size() > 0) {
			int U = open.poll().item;
			for (Edge e: adjList.get(U)) {
				int V = e.dest;
				if (closed[V]) continue;
				if (e.weight < label[V]){
					label[V] = e.weight;
					parent[V] = e;
					open.add(new PriorityQueueElement(V, label[V]));
				}
			}
			closed[U] = true;
		}
		for (Edge e:parent) {
			if (e!=null) result.add(e);
		}
		return result;
	}
}
