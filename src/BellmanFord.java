import java.util.Arrays;

public class BellmanFord extends Graph {

	public BellmanFord(final Graph graph) {
		super(graph);
	}

	public int[] execute(int source) { // complete this method
		int[] dist = new int[numVertices];
		boolean didDistChange;
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[source] = 0;
		for (int i = 1; i < numVertices; i++) {
			didDistChange = false;
			for (int j = 0; j < numVertices; j++) {
				for (int k = 0; k < adjList.get(j).size(); k++) {
					Edge e = adjList.get(j).get(k);
					if (dist[e.src] == Integer.MAX_VALUE) continue;
					int newDist = dist[e.src]+e.weight;
					if(newDist < dist[e.dest]) {
						dist[e.dest] = newDist;
						didDistChange = true;
					}
				}
			}
			if (!didDistChange) return dist;
		}
		for (int i = 0; i < numVertices; i++) {
			for (int j = 0; j < adjList.get(j).size(); j++) {
				Edge e = adjList.get(i).get(j);
				if (dist[e.src] == Integer.MAX_VALUE) continue;
				if (dist[e.src]+e.weight < dist[e.dest]) return null;
			}
		}
		return dist;
	}
}
