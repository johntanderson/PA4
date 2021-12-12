
import java.util.ArrayList;

public class UnionFind {

	ArrayList<ArrayList<Integer>> representatives;

	public UnionFind(int initialNumSets) { // complete this constructor
		representatives = new ArrayList<>(initialNumSets);
		for (int i = 0; i < initialNumSets; i++) {
			representatives.add(i, new ArrayList<>());
			makeSet(i);
		}
	}

	public void makeSet(int x) { // complete this function
		ArrayList<Integer> da = new ArrayList<>();
		da.add(x);
		representatives.set(x, da);
	}

	public ArrayList<Integer> find(int x) { // complete this function
		return representatives.get(x);
	}

	private void append(ArrayList<Integer> arg1, ArrayList<Integer> arg2) { // complete this function
		if (arg2.size()!=0){
			arg1.addAll(arg2);
			for (Integer n:arg2) representatives.set(n, arg1);
		}
	}

	public void doUnion(int x, int y) { // complete this function
		ArrayList<Integer> dax = find(x);
		ArrayList<Integer> day = find(y);
		if (dax != day){
			if (dax.size() >= day.size()) append(dax, day);
			else append(day, dax);
		}
	}
}
