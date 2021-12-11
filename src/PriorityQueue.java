

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PriorityQueue<T> {
	private List<T> contents;
	private int currentSize;
	private Comparator<T> comparator;

	public PriorityQueue(Comparator<T> comparator) {
		currentSize = 0;
		contents = new ArrayList<T>();
		this.comparator = comparator;
	}

	public T peek() {
		if (currentSize == 0)
			throw new ArrayIndexOutOfBoundsException();
		return contents.get(0);
	}

	public int size() {
		return currentSize;
	}

	public T poll() {

		if (currentSize == 0)
			throw new ArrayIndexOutOfBoundsException();
		else if (currentSize == 1) {
			T item = contents.get(--currentSize);
			contents.remove(0);
			return item;
		} else {
			T item = contents.get(0);
			contents.set(0, contents.get(--currentSize));
			contents.remove(currentSize);
			siftDown(0);
			return item;
		}
	}

	public void add(T item) {

		int index = currentSize++;
		contents.add(item);
		siftUp(index);
	}

	private void siftDown(int index) {
		int leftIndex = index * 2 + 1, rightIndex = leftIndex + 1;
		while (leftIndex < currentSize) {
			T minValue = contents.get(leftIndex);
			int minIndex = leftIndex;
			if (rightIndex < currentSize) {
				T rightValue = contents.get(rightIndex);
				if (comparator.compare(minValue, rightValue) > 0) {
					minValue = rightValue;
					minIndex = rightIndex;
				}
			}
			if (comparator.compare(minValue, contents.get(index)) < 0) {
				swap(index, minIndex);
				index = minIndex;
			} else
				break;
			leftIndex = index * 2 + 1;
			rightIndex = leftIndex + 1;
		}
	}

	private void siftUp(int index) {
		while (index > 0 && comparator.compare(contents.get(parent(index)), contents.get(index)) > 0) {
			swap(index, parent(index));
			index = parent(index);
		}
	}

	private void swap(int index1, int index2) {
		T temp = contents.get(index1);
		contents.set(index1, contents.get(index2));
		contents.set(index2, temp);
	}

	private int parent(int index) {
		return (index - 1) / 2;
	}

	public String toString() {
		if (0 == currentSize)
			return "[]";
		String str = "[";
		for (int i = 0; i < currentSize - 1; i++) {
			str += contents.get(i) + "; ";
		}
		str += contents.get(currentSize - 1) + "]";
		return str;
	}
}
