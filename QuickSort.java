import java.util.*;

//https://www.geeksforgeeks.org/hoares-vs-lomuto-partition-scheme-quicksort/
//https://www.geeksforgeeks.org/quicksort-using-random-pivoting/

public class QuickSort<T extends Comparable <? super T>> implements SortInterface<T> {

	enum PivotType {FirstElement, RandomElement, MidOfFirstMidLastElement}
	private QuickSort.PivotType type = QuickSort.PivotType.FirstElement;
	private T pivot;
	@Override
	public void sort(T[] arrayToSort) {
		// TODO Auto-generated method stub
		
		
		this.quickSort(arrayToSort, 0, arrayToSort.length - 1);
		
	}

	public QuickSort.PivotType getPivotType(){
		return this.type;
	}
	
	public void setPivotType(QuickSort.PivotType pivotType) {
		this.type = pivotType;
	}
	
	private int partition(T[] array, int first, int last) {
		this.choosePivot(array, first, last);
		boolean done = false;
		while (!done) {
				      // Increment lowIndex while numbers[lowIndex] < pivot
			while (array[first].compareTo(pivot) < 0) {
				         first += 1;
				      }
				      
				      // Decrement highIndex while pivot < numbers[highIndex]
				      while (pivot.compareTo(array[last]) < 0) {
				         last -= 1;
				      }
				      
				      // If zero or one elements remain, then all numbers are 
				      // partitioned. Return highIndex.
				      if (first >= last) {
				         done = true;
				      }
				      else {
				         // Swap numbers[lowIndex] and numbers[highIndex]
				         T temp = array[first];
				         array[first] = array[last];
				         array[last] = temp;
				         
				         // Update lowIndex and highIndex
				         first += 1;
				         last -= 1;
				      }
		}
		
		return last;
	}
	
	private void quickSort(T[] array, int first, int last) {
		if (first >= last) {
		      return;
		   }
		   
		   int lowEndIndex = partition(array, first, last);
		   
		   quickSort(array, first, lowEndIndex);
		   quickSort(array, lowEndIndex + 1, last);

	}
	
	public void choosePivot(T[] array, int first, int last) {
		int mid = (first + (last - first)) / 2;
		if(this.getPivotType().name() == "FirstElement") {
			this.pivot = array[first];
		}else if(type.name() == "RandomElement") {
			T max = array[first];
			for(int j = 0; j < last; j++) {
				if(array[j].compareTo(max) > 0) {
					max = array[j];
				}
			}
			this.pivot = max;
		}else {
			this.pivot = array[mid];
		}
	}
}
