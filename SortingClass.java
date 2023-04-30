package sortingComparision;

import java.util.Random;

public class SortingClass {

	/* this function takes array and partition number as parameters and calls the right function for merge sort */
	public static void mergeSort(int[] arrayToSort, int numberOfPartitions) {

		if(numberOfPartitions==2)
			mergeByTwo(arrayToSort,0,(arrayToSort.length-1));
		else if(numberOfPartitions==3)
			mergeByThree(arrayToSort);
	}
	/* this function takes array and pivot type as parameters and calls the right function for quick sort */
	public static void quickSort(int[] arrayToSort, String pivotType) {
		if(pivotType.equals("FirstElement"))
			quickPivotFirst(arrayToSort,0,(arrayToSort.length-1));
		else if(pivotType.equals("RandomElement"))
			quickPivotRandom(arrayToSort,0,(arrayToSort.length-1));
		else if(pivotType.equals("MidOfFirstMidLastElement"))
			quickPivotMid(arrayToSort,0,(arrayToSort.length-1));

	}

	static void mergeByTwo(int arr[], int l, int r) {
		if (l < r) {
			// Find the middle point  //RUN TIME ANALYSIS
			int m = (l + r) / 2; //O(1) 

			// Sort first and second halves
			mergeByTwo(arr, l, m);// T(N/2)
			mergeByTwo(arr, m + 1, r);//T(N/2)

			// Merge the sorted halves
			merge(arr, l, m, r); //O(N)
		}
	}

	static void merge(int arr[], int l, int m, int r) {
		int n1 = m - l + 1;
		int n2 = r - m;
        //setting new arrays
		int L[] = new int[n1];
		int R[] = new int[n2];

		for (int i = 0; i < n1; ++i)
			L[i] = arr[l + i];
		for (int j = 0; j < n2; ++j)
			R[j] = arr[m + 1 + j];

		int i = 0, j = 0;
        //find the lower element and set as the first element
		//as initial array and continue sorting next elements
		int k = l;
		//check both sub arrays
		while (i < n1 && j < n2) {
			if (L[i] <= R[j]) {
				arr[k] = L[i];
				i++;
			} else {
				arr[k] = R[j];
				j++;
			}
			k++;
		}
        //check left sub array
		while (i < n1) {
			arr[k] = L[i];
			i++;
			k++;
		}
       //check right sub array
		while (j < n2) {
			arr[k] = R[j];
			j++;
			k++;
		}
	}

	// Function for 3 part merge sort process
	public static void mergeByThree(int[] gArray) {
		// if array of size is zero returns null
		if (gArray == null)
			return;

		// creating duplicate of given array
		int[] fArray = new int[gArray.length];

		// copying elements of given array into
		// duplicate input array
		for (int i = 0; i < fArray.length; i++)
			fArray[i] = gArray[i];

		// sort function
		mergeSort3Way(fArray, 0, gArray.length, gArray);

		// copy back elements of duplicate array
		// to original array
		for (int i = 0; i < fArray.length; i++)
			gArray[i] = fArray[i];
	}

	/*
	 * Performing the merge sort algorithm on the given array of values in the
	 * rangeof indices [low, high). low is minimum index, high is maximum index
	 */
	public static void mergeSort3Way(int[] gArray, int low, int high, int[] destArray) {
		// If array size is less then 2 do nothing
		if (high - low < 2)
			return;

		// Dividing array into 3 parts
		int mid1 = low + ((high - low) / 3);
		int mid2 = low + 2 * ((high - low) / 3) + 1;

		// Sorting 3 arrays recursively
		mergeSort3Way(destArray, low, mid1, gArray);
		mergeSort3Way(destArray, mid1, mid2, gArray);
		mergeSort3Way(destArray, mid2, high, gArray);

		// Merging the sorted arrays
		merge(destArray, low, mid1, mid2, high, gArray);
	}
    //merge function for 3 part merge sort
	public static void merge(int[] gArray, int low, int mid1, int mid2, int high, int[] destArray)

	{
		int i = low, j = mid1, k = mid2, l = low;

		// choose lower of the lowest in three ranges

		while ((i < mid1) && (j < mid2) && (k < high))

		{
            
			if (gArray[i]<gArray[j] ) {

				if (gArray[i]<gArray[k])// if the element in first sub array is lower
					destArray[l++] = gArray[i++]; // copy it to destination array and continue comparing with next element in first sub array

				else // if the element in third sub array is lowest
					destArray[l++] = gArray[k++];// copy it to destination array and continue comparing with next element in third sub array
			}

			else { // if the element in second sub array is lower than or equal to element in first sub array
				if (gArray[j]<gArray[k]) // if the element in second sub array is smaller than the third sub array
					destArray[l++] = gArray[j++];// copy it to destination array and continue comparing with next element in second sub array
				else // if the element in third array is lower
					destArray[l++] = gArray[k++];// copy it to destination array and continue comparing with next element in third sub array
			}
		}

// occasion where first and second ranges have
// unsorted values
		while ((i < mid1) && (j < mid2)) { 
			if (gArray[i]<gArray[j]) //if first sub array has lower values
				destArray[l++] = gArray[i++];  // copy elements from first sub array
			else
				destArray[l++] = gArray[j++];
		}

// occasion where second and third sub arrays have
// unsorted values
		while ((j < mid2) && (k < high)) {
			if (gArray[j]<gArray[k]) //if second sub array has lower values
				destArray[l++] = gArray[j++]; // copy elements from second sub array

			else
				destArray[l++] = gArray[k++];
		}

// occasion where first and third sub arrays  have
// unsorted values
		while ((i < mid1) && (k < high)) {
			if (gArray[i]<gArray[k]) // if first sub array has lower values
				destArray[l++] = gArray[i++]; // copy elements from first sub array
			else
				destArray[l++] = gArray[k++]; // copy elements from third sub array
		}

// copy unsorted values from the first sub array
		while (i < mid1)
			destArray[l++] = gArray[i++];

// copy unsorted values from the second sub array
		while (j < mid2)
			destArray[l++] = gArray[j++];

// copy unsorted values from the third sub array
		while (k < high)
			destArray[l++] = gArray[k++];
	}

	 
	/*This function selects first element as pivot and sets it to its
	 * sorted position and sets other elements to its right if less then pivot
	 * and left if its higher than pivot*/
    static void quickPivotFirst(int arr[], int low, int high)
    {

        // If low is lesser than high
       if (low < high) {
            // idx is index of pivot element which is at its
            // sorted position
            int idx = partitionForFirst(arr, low, high);
 
            // Separately sort elements before
            // partition and after partition
            quickPivotFirst(arr, low, idx-1 );
            quickPivotFirst(arr, idx + 1, high);
        }
    }
    //swap function
    public static void swap(int[] arr, int i, int j)
    {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    static int partitionForFirst(int arr[], int low, int high)
    {
    	
    	
       int pivot = arr[low]; //set pivot as first element
        int k = high;
        for (int i = high; i > low; i--) { // start comparing elements from end of the array to pivot
            if (arr[i] > pivot)
                swap(arr, i, k--); // swap element with itself
        }
        swap(arr, low, k); // else swap elements
        return k;

    }

    static void quickPivotRandom(int arr[], int low, int high)
    {
        if (low < high)
        {
            int pi = partitionForRandom(arr, low, high);
 
            // Recursively sort elements before
            // partition and after partition
            quickPivotRandom(arr, low, pi-1);
            quickPivotRandom(arr, pi+1, high);
        }
    }

    static int partitionForRandom(int arr[], int low, int high)
    {
        // pivot is chosen randomly
        chooseRandomPivot(arr,low,high); //choose a random element to be pivot and change places with last element
        int pivot = arr[high]; // choose last element which was setted as random choosen pivot
     
 
        int i = (low-1); // set the index of the smaller element to the left of the array
        for (int j = low; j < high; j++)
        {
            // If current element is smaller than or
            // equal to pivot
            if (arr[j] < pivot)
            {
                i++; //increase the index of the smaller element by 1
 
                //  swap the elements at the index of the lower element and the current element
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
 
        // swap the element to the right of the lower elements with the pivot
        int temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;
        //return the index of the pivot element
        return i+1;
    }
    // this function selects a random element to be pivot and changes places with last element
    static void chooseRandomPivot(int arr[],int low,int high)
    {
     
        Random rand= new Random();
        int pivot = rand.nextInt(high-low)+low;
         
        int temp1=arr[pivot]; 
        arr[pivot]=arr[high];
        arr[high]=temp1;
    }
    /*This function sorts the given array using pivot as the element whose value is in the
middle among the {first, middle, and Last} elements in the array to be sorted */
	public static void quickPivotMid(int[] arr, int left, int right) {
	   // if subarray's size is 0 or 1 array is already sorted
		if (left >= right) return;
	    
	    // Choose middle element as pivot
	      int pivotIndex = getPivotIndex(arr, left, right);
	      int pivotValue = arr[pivotIndex];
	    
	    // Partition array
	    int index = partitionForMid(arr, left, right, pivotValue);
	    
	    // Sort sub-arrays recursively
	    quickPivotMid(arr, left, index - 1);
	    quickPivotMid(arr, index, right);
	}
	// this function chooses the pivot as the element whose value is in the
	//middle among the {first, middle, and Last} elements in the array to be sorted
    private static int getPivotIndex(int[] arr, int low, int high) {
        int mid = (low + high) / 2; //find middle element
        int first = arr[low];
        int middle = arr[mid];
        int last = arr[high];
        //compare middle, first and last element and choose the middle among them.
        if (first <= middle && middle <= last || last <= middle && middle <= first) {
            return mid;
        } else if (middle <= first && first <= last || last <= first && first <= middle) {
            return low;
        } else {
            return high;
        }
    }
	private static int partitionForMid(int[] arr, int left, int right, int pivot) {
	    while (left <= right) {
	    	// move the left pointer to right until it finds an element equal to or greater than pivot
	        while (arr[left] < pivot) left++;
	        //move the right pointer to left until it finds an element lower than or equal to pivot
	        while (arr[right] > pivot) right--;
	        // if left pointer and right pointer has not crossed yet
	        if (left <= right) {
	            // Swap elements
	            int temp = arr[left];
	            arr[left] = arr[right];
	            arr[right] = temp;
	            left++;
	            right--;
	        }
	    }
	    return left;
	}
}
