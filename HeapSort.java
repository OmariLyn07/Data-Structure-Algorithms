
public class HeapSort<T extends Comparable <? super T>> implements SortInterface<T> {

	public int size = 0;
	
	@Override
	public void sort(T[] arrayToSort) {
		// TODO Auto-generated method stub
		/*
		System.out.print("Basic Array: ");
		for(int j = 0; j < arrayToSort.length; ++j) {
			System.out.print(arrayToSort[j] + " ");
		}
		System.out.println();
		*/
		heapify(arrayToSort);
		/*
		System.out.print("Heapified Array: ");
		for(int j = 0; j < arrayToSort.length; ++j) {
			System.out.print(arrayToSort[j] + " ");
		}
		System.out.println();
		*/
		heapSort(arrayToSort);
		/*
		System.out.print("Sorted Array: ");
		for(int j = 0; j < arrayToSort.length; ++j) {
			System.out.print(arrayToSort[j] + " ");
		}
		System.out.println();
		*/
	}

	private void heapify(T[] arr) {
		for(int i = arr.length / 2 -1; i >= 0; i--) {
			siftDown(i, arr, arr.length);
		}
	}
	
	private void heapSort(T[] arr) {
		for(int i = arr.length - 1; i > 0; i--) {
			T temp = arr[0];
			arr[0] = arr[i];
			arr[i] = temp;
			siftDown(0, arr, i);
		}
		
	}
	
	private void siftDown(int node, T[] arr, int size) {
		int child = 2 * node + 1;
		T val = arr[node];
		
		while(child < size) {
			T maxval = val;
			int maxIndex = -1;
			
			for(int i = 0; i < 2 && i + child < size; i++) {
				if(arr[i + child].compareTo(maxval) > 0) {
					maxval = arr[i + child];
					maxIndex = i + child;
				}
			}
			if(maxval == val) {
				return;
			}
			else {
				arr[node] = arr[maxIndex];
				arr[maxIndex] = val;
				node = maxIndex;
				child = 2 * node + 1;
			}
		}
	}
	
}
