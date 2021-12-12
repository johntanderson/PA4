import java.util.ArrayList;
import java.util.Arrays;

public class FloydWarshall extends Graph {

	public FloydWarshall(Graph graph) {
		super(graph);
	}

	public int[][] execute() { // complete this method
		int[][] allPairMatrix = new int[numVertices][numVertices];
		for (int[] arr:allPairMatrix) Arrays.fill(arr, Integer.MAX_VALUE);
		for (int i = 0; i < numVertices; i++) allPairMatrix[i][i] = 0;
		for (ArrayList<Edge> row:adjList)
			for (Edge e:row)
				allPairMatrix[e.src][e.dest] = e.weight;
		for (int i = 0; i < numVertices; i++) {
			for (int j = 0; j < numVertices; j++) {
				for (int k = 0; k < numVertices; k++) {
					if (allPairMatrix[j][i]==Integer.MAX_VALUE || allPairMatrix[i][k]==Integer.MAX_VALUE) continue;
					int tempDist = allPairMatrix[j][i]+allPairMatrix[i][k];
					if (allPairMatrix[j][k] > tempDist) allPairMatrix[j][k] = tempDist;
				}
			}
		}
		for (int i = 0; i < numVertices; i++) if (allPairMatrix[i][i] < 0) return null;
		return allPairMatrix;
	}
}
