

import java.util.Comparator;

public class PriorityQueueElementComparator implements Comparator<PriorityQueueElement> {

	@Override
	public int compare(PriorityQueueElement o1, PriorityQueueElement o2) {
		return o1.priority - o2.priority;
	}
}