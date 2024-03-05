import java.util.*;
public class MergeSort<T extends Comparable <? super T>> implements SortInterface<T>{

	private ArrayList<T> arr;
	
	@Override
	public void sort(T[] arrayToSort) {
		// TODO Auto-generated method stub
		mergesort(arrayToSort, 0, arrayToSort.length - 1);
	}

	private void merge(T[] array, int first, int mid, int last) {
		int size = last - first + 1;
		int currPos = 0;
		int leftPar = 0;
		int rightPar = 0;
		this.arr = new ArrayList<T>();
		
		leftPar = first;
		rightPar = mid + 1;
		while(leftPar <= mid && rightPar <= last) {
			if(array[leftPar].compareTo(array[rightPar]) <= 0) {
				this.arr.add(array[leftPar]);
				++leftPar;
			}
			else {
				this.arr.add(array[rightPar]);
				++rightPar;
			}
			++currPos;
		}
		while(leftPar <= mid) {
			this.arr.add(array[leftPar]);
			++leftPar;
			++currPos;
		}
		while(rightPar <= last) {
			this.arr.add(array[rightPar]);
			++rightPar;
			++currPos;
		}
		for(currPos = 0; currPos < size; ++currPos) {
			array[first + currPos] = this.arr.get(currPos);
		}
	}
	
	private void mergesort(T[] array, int first, int last) {
		int mid = 0;
		
		if(first < last) {
			mid = (first + last) / 2;
			
			mergesort(array, first, mid);
			mergesort(array, mid + 1, last);
			
			merge(array, first, mid, last);
		}
		
	}
	
}
