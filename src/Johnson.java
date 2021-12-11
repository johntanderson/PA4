

import java.util.ArrayList;

public class Johnson extends Graph {

	public Johnson(final Graph graph) {
		super(graph);
	}

	public int[][] execute() throws Exception { // complete this method
		adjList.add(new ArrayList<Edge>());
		for (int i = 0; i < numVertices; i++) {
			Edge e = new Edge(numVertices, i, 0);
			adjList.get(numVertices).add(e);
		}
		numEdges += numVertices;
		numVertices++;
		BellmanFord bellmanFord = new BellmanFord(this);
		int[] arr = bellmanFord.execute(numVertices-1);
		adjList.remove(adjList.size()-1);
		numVertices--;
		numEdges-=numVertices;
		if (arr == null) return null;
		for (int i = 0; i < numVertices; i++) {
			for (int j = 0; j < adjList.get(i).size(); j++) {
				Edge e = adjList.get(i).get(j);
				e.weight = e.weight+arr[e.src]-arr[e.dest];
			}
		}
		int[][] allPairMatrix = new int[numVertices][];
		Dijkstra dijkstra = new Dijkstra(this);
		for (int i = 0; i < numVertices; i++) {
			allPairMatrix[i] = dijkstra.execute(i);
		}
		for (int i = 0; i < numVertices; i++) {
			for (int j = 0; j < numVertices; j++) {
				if (i!=j && allPairMatrix[i][j]!=Integer.MAX_VALUE)
					allPairMatrix[i][j] = allPairMatrix[i][j]-arr[i]+arr[j];
			}
		}
		for (int i = 0; i < numVertices; i++) {
			for (int j = 0; j < adjList.get(i).size(); j++) {
				Edge e = adjList.get(i).get(j);
				e.weight = e.weight-arr[e.src]+arr[e.dest];
			}
		}
		return allPairMatrix;
	}
}
