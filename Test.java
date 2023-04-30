package sortingComparision;

import java.util.*;

public class Test {
	
	//the java.lang.stackoverflow error occured in array size as 100.000 and solved by using -Xmx320m -Xss208m parameters in run configuration arguments
	// and increasing the java stack size.
	
	/* this function creates an array according to its parameters as type and size 
	 * it creates array with equal, random, increasing or decreasing integers as type
	 * and 1000,10000 or 100000 number long arrays as size*/
	public static int[] createArray(char arrayType, int arraySize)
	{		
		int[] arr= new int[arraySize];
		Random rand = new Random();
		if(arrayType=='e'|| arrayType=='E') // create an array as all elements are equal and two
		{
			for(int i=0;i<arraySize;i++)
			{					
				arr[i]=2;
			}					
		}
		if(arrayType=='r'|| arrayType=='R') //create an array as all elements are random
		{
			for(int i=0;i<arraySize;i++)
			{				
				int num= rand.nextInt(10);
				arr[i]=num;
			}	
		}
		if(arrayType=='i'|| arrayType=='I') //create an array as all elements are in increasing order
		{
			for(int i=0;i<arraySize;i++)
			{					
				arr[i]=i;
			}	
		}
		if(arrayType=='d'|| arrayType=='D') //create an array as all elements are in decreasing order
		{
			for(int i=0;i<arraySize;i++)
			{					
				arr[i]=arraySize-i;
			}	
		}
		return arr;
		
	}
	// display function
	public static void displayArray(int[] arr)
	{
		for(int i=0;i<arr.length;i++)
		{
			System.out.print(arr[i]+" ");
		}
		System.out.println(" ");
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//deciding array type and size
		System.out.println("Please enter your array type as its capital ");
		System.out.println("press 'e' for equal integers");
		System.out.println("press 'r' for random integers");
		System.out.println("press 'i' for increasing integers ( I for uppercase)");
		System.out.println("press 'd' for decreasing integers");
		Scanner input = new Scanner(System.in);
		char type = input.next().charAt(0);
		System.out.println("Please enter your array size, it can be 1000,10000 or 100000 ");
	    int size = input.nextInt();
		int[] array=createArray(type,size);
	    //menu
		System.out.println("----MENU----");
		System.out.println("1: Merge sort by 2");
		System.out.println("2: Merge sort by 3");
		System.out.println("3: Quick sort by pivot as first element");
		System.out.println("4: Quick sort by pivot as random element");
		System.out.println("5: Quick sort by pivot as mid element");
		Scanner choice_input = new Scanner(System.in);
		int choice = choice_input.nextInt();
		switch(choice) {
		  case 1:
			    long startTime_mrg2 = System. currentTimeMillis();
			    SortingClass.mergeSort(array, 2);
			    long estimatedTime_mrg2 = System. currentTimeMillis() - startTime_mrg2;
			    System.out.println("Running time for Merge Sort by 2: "+estimatedTime_mrg2+" ms");
				break;
		  case 2:
			    long startTime_mrg3 = System. currentTimeMillis();
			    SortingClass.mergeSort(array, 3);
			    long estimatedTime_mrg3 = System. currentTimeMillis() - startTime_mrg3;
			    System.out.println("Running time for Merge Sort by 3: "+estimatedTime_mrg3+" ms");
		         break;
		  case 3:
			    long startTime_qckF = System. currentTimeMillis();
			    SortingClass.quickSort(array, "FirstElement");
			    long estimatedTime_qckF = System. currentTimeMillis() - startTime_qckF;
			    System.out.println("Running time for Quick Sort with pivot as First Element: "+estimatedTime_qckF+" ms");
		         break;
		  case 4:
			    long startTime_qckR = System. currentTimeMillis();
			    SortingClass.quickSort(array, "RandomElement");
			    long estimatedTime_qckR = System. currentTimeMillis() - startTime_qckR;
			    System.out.println("Running time for Quick Sort with pivot as Random Element: "+estimatedTime_qckR+" ms");
		         break;
		  case 5:
			    long startTime_qckM = System. currentTimeMillis();
			    SortingClass.quickSort(array, "MidOfFirstMidLastElement");
			    long estimatedTime_qckM = System. currentTimeMillis() - startTime_qckM;
			    System.out.println("Running time for Quick Sort with pivot as Middle Element: "+estimatedTime_qckM+" ms");
		         break;
		
		}
		System.out.println("Do you want to see sorted array? Y/N");
		System.out.println("Console might not show if your array size is greater than 1000");
		// if array size is 10.000 or 100.000 the console might not be enough to show that large data set.
		// you can test with smaller sized array
		Scanner display_ = new Scanner(System.in);
		char display=display_.next().charAt(0);
		if(display=='y'||display=='Y')
		{
		     displayArray(array);
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		/*int[] arr= new int[10];
		 Random rand = new Random();

		for(int i=0;i<10;i++)
		{
			   
	        // Generate random integers in range 0 to 999
	        int ran= rand.nextInt(10);
			arr[i]=ran;
		}
		for(int i=0;i<10;i++)
		{
			System.out.print(arr[i]+" ");
		}
		System.out.println("two ");
		SortingClass.mergeSort(arr, 2);
		for(int i=0;i<10;i++)
		{
			System.out.print(+arr[i]+" ");
		}
		System.out.println(" three");
		SortingClass.mergeSort(arr, 3);
		for(int i=0;i<10;i++)
		{
			System.out.print(+arr[i]+" ");
		}*/
	}

}
