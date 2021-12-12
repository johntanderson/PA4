

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Kruskal extends Graph {

	public Kruskal(String filePath, GraphType type) throws FileNotFoundException {
		super(filePath, type);
	}

	public ArrayList<Edge> runKruskal() { // complete this function
		ArrayList<Edge> edgeList = new ArrayList<>();
		ArrayList<Edge> da = new ArrayList<>();
		int numEdgesAdded = 0;

		for (ArrayList<Edge> row:adjList) edgeList.addAll(row);
		edgeList.sort(new ComparatorEdge());
		UnionFind objUF = new UnionFind(numVertices);
		for (Edge e:edgeList) {
			int src = e.src, dest = e.dest;
			if (objUF.find(src) != objUF.find(dest)) {
				objUF.doUnion(src, dest);
				da.add(e);
				numEdgesAdded++;
				if (numEdgesAdded == numVertices-1) break;
			}
		}
		return da;
	}


}

class ComparatorEdge implements Comparator<Edge> {

	@Override
	public int compare(Edge o1, Edge o2) {
		return Integer.compare(o1.weight, o2.weight);
	}
}
