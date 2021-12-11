

public class PriorityQueueElement {

	int priority;
	int item;

	PriorityQueueElement(int item, int priority) {
		this.item = item;
		this.priority = priority;
	}

	public int getPriority() {
		return priority;
	}

	public int getItem() {
		return item;
	}

	public String toString() {
		return "<" + item + ", " + priority + ">";
	}
}