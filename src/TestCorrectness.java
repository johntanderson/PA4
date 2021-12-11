

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

public class TestCorrectness {

	static final String BELLMANFORD1_GRAPH_PATH = "bellmanford1.txt";
	static final String BELLMANFORD2_GRAPH_PATH = "bellmanford2.txt";
	static final String BELLMANFORD3_GRAPH_PATH = "bellmanford3.txt";

	static final String DIJKSTRA1_GRAPH_PATH = "dijkstra1.txt";
	static final String DIJKSTRA2_GRAPH_PATH = "dijkstra2.txt";

	static final String APSP1_GRAPH_PATH = "apsp1.txt";
	static final String APSP2_GRAPH_PATH = "apsp2.txt";
	static final String APSP3_GRAPH_PATH = "apsp3.txt";
	
	static final String MST_GRAPH_PATH = "mst_graph.txt";

	private static void testBellmanFord() throws FileNotFoundException {
		System.out.println("*** Graph 1 ***\n");
		Graph graph = new Graph(BELLMANFORD1_GRAPH_PATH, GraphType.WEIGHTED);
		BellmanFord bf = new BellmanFord(graph);
		for (int i = 0; i < graph.numVertices; i++) {
			String distArray = Arrays.toString(bf.execute(i)).replaceAll("" + Integer.MAX_VALUE, "inf");
			System.out.printf("Distances from v%d: %s%n", i, distArray);
		}

		System.out.println("\n*** Graph 2 ***");
		graph = new Graph(BELLMANFORD2_GRAPH_PATH, GraphType.WEIGHTED);
		bf = new BellmanFord(graph);
		if (bf.execute(0) == null)
			System.out.println("\nHas a negative cycle.");
		else
			System.out.println("\nSomething is wrong.");

		System.out.println("\n*** Graph 3 ***\n");
		graph = new Graph(BELLMANFORD3_GRAPH_PATH, GraphType.WEIGHTED);
		bf = new BellmanFord(graph);
		for (int i = 0; i < graph.numVertices; i++) {
			String distArray = Arrays.toString(bf.execute(i)).replaceAll("" + Integer.MAX_VALUE, "inf");
			System.out.printf("Distances from v%d: %s%n", i, distArray);
		}
	}

	private static void testDijkstra() throws Exception {
		String filePaths[] = { DIJKSTRA1_GRAPH_PATH, DIJKSTRA2_GRAPH_PATH };
		for (int j = 0; j < filePaths.length; j++) {
			System.out.println("*** Graph " + (j + 1) + " ***\n");
			Graph graph = new Graph(filePaths[j], GraphType.WEIGHTED);
			Dijkstra dijk = new Dijkstra(graph);
			for (int i = 0; i < dijk.numVertices; i++) {
				int[] distance = dijk.execute(i);
				System.out.println("Distance array (from v" + i + "): "
						+ Arrays.toString(distance).replaceAll("" + Integer.MAX_VALUE, "inf"));
			}
			System.out.println();
		}
	}

	private static void testAPSP() throws Exception {
		Graph graph = new Graph(APSP1_GRAPH_PATH, GraphType.WEIGHTED);
		Johnson johnson = new Johnson(graph);
		FloydWarshall fw = new FloydWarshall(graph);

		int distArrayJohnson[][] = johnson.execute();
		System.out.println("*** Graph 1 Distance Matrix (using Johnson) ***\n");
		for (int i = 0; i < graph.numVertices; i++)
			System.out.printf("%s%n", Arrays.toString(distArrayJohnson[i]).replaceAll("" + Integer.MAX_VALUE, "infty"));

		System.out.println("\n*** Graph 1 Distance Matrix (using Floyd-Warshall) ***\n");

		int distArrayFW[][] = fw.execute();
		for (int i = 0; i < graph.numVertices; i++)
			System.out.printf("%s%n", Arrays.toString(distArrayFW[i]).replaceAll("" + Integer.MAX_VALUE, "infty"));

		System.out.println();
		graph = new Graph(APSP2_GRAPH_PATH, GraphType.WEIGHTED);
		johnson = new Johnson(graph);
		fw = new FloydWarshall(graph);
		distArrayJohnson = johnson.execute();
		System.out.println("*** Graph 2 Distance Matrix (using Johnson) ***\n");
		for (int i = 0; i < graph.numVertices; i++)
			System.out.printf("%s%n", Arrays.toString(distArrayJohnson[i]).replaceAll("" + Integer.MAX_VALUE, "infty"));

		System.out.println("\n*** Graph 2 Distance Matrix (using Floyd-Warshall) ***\n");

		distArrayFW = fw.execute();
		for (int i = 0; i < graph.numVertices; i++)
			System.out.printf("%s%n", Arrays.toString(distArrayFW[i]).replaceAll("" + Integer.MAX_VALUE, "infty"));

		System.out.println();
		graph = new Graph(APSP3_GRAPH_PATH, GraphType.WEIGHTED);
		johnson = new Johnson(graph);
		fw = new FloydWarshall(graph);
		distArrayJohnson = johnson.execute();
		distArrayFW = fw.execute();
		System.out.println("*** Graph 3 ***\n");
		if (distArrayJohnson != null)
			System.out.println("Something wrong with Johnson's method.");
		else if (distArrayFW != null)
			System.out.println("Something wrong with Floyd-Warshall's method.");
		else
			System.out.println("Has a negative cycle.");
	}

	private static void testDJP() throws FileNotFoundException {
		DJP djp = new DJP(MST_GRAPH_PATH, GraphType.WEIGHTED);
		ArrayList<Edge> mst = djp.runDJP(0);
		int mstWeight = 0;
		for (Edge e : mst)
			mstWeight += e.weight;
		System.out.printf("MST has weight %d%nThe edges are: %s%n", mstWeight, mst);
	}

	private static void testUnionFind() {
		UnionFind uf = new UnionFind(16);
		System.out.println("Initial sets are 0-15\n");
		for (int i = 0; i < 15; i += 4) {
			System.out.printf("UNION(%d,%d)%n", i, i + 1);
			uf.doUnion(i, i + 1);
		}
		System.out.println();
		for (int i = 0; i < 16; i++) {
			System.out.printf("List containing %2d: %s%n", i, uf.find(i));
		}
		System.out.println("\nUNION(0,5)");
		System.out.println("UNION(10,12)");
		System.out.println("UNION(0,10)\n");
		uf.doUnion(0, 5);
		uf.doUnion(10, 12);
		uf.doUnion(0, 10);
		for (int i = 0; i < 16; i++) {
			System.out.printf("List containing %2d: %s%n", i, uf.find(i));
		}
		System.out.println("\nUNION(6,8)");
		System.out.println("UNION(8,5)\n");
		uf.doUnion(6, 8);
		uf.doUnion(8, 5);
		for (int i = 0; i < 16; i++) {
			System.out.printf("List containing %2d: %s%n", i, uf.find(i));
		}
	}

	private static void testKruskal() throws FileNotFoundException {
		Kruskal kruskal = new Kruskal(MST_GRAPH_PATH, GraphType.WEIGHTED);
		ArrayList<Edge> mst = kruskal.runKruskal();
		int mstWeight = 0;
		for (Edge e : mst)
			mstWeight += e.weight;
		System.out.printf("MST has weight %d%nThe edges are: %s%n", mstWeight, mst);
	}

	private static void testKMPHelper(String text, String patterns[]) {
		System.out.println("*** Text is " + text + " *** \n");
		int numPatterns = patterns.length;
		for (int i = 0; i < numPatterns; i++) {
			ArrayList<Integer> occurrences = KMP.runKMP(text, patterns[i]);
			System.out.printf("Pattern %-7s has %d occurrence(s): ", patterns[i], occurrences.size());
			System.out.println(occurrences);
		}
		System.out.println();
	}

	private static void testKMP() {
		String text = "AABAACAADAABAABAACAADAAD";
		String patterns[] = { "AABA", "AABAA", "ABA", "CA", "EF", "AAC", "AAD" };
		testKMPHelper(text, patterns);

		text = "MISSISSIPPILY_OR_MISSISSIPPILESSLY";
		patterns = new String[] { "ISS", "ESS", "_", "IPP", "MISS", "LESSLY", "LY", "LESSLIE" };
		testKMPHelper(text, patterns);
	}

	public static void main(String args[]) throws Exception {
		System.out.println("****************** Bellman-Ford ******************\n");
		testBellmanFord();
		System.out.println("\n****************** Dijkstra ******************\n");
		testDijkstra();
		System.out.println("****************** APSP algorithms ******************\n");
		testAPSP();
		System.out.println("\n****************** DJP ******************\n");
		testDJP();
		System.out.println("\n****************** Union Find ******************\n");
		testUnionFind();
		System.out.println("\n****************** Kruskal ******************\n");
		testKruskal();
		System.out.println("\n****************** KMP ******************\n");
		testKMP();
	}
}
