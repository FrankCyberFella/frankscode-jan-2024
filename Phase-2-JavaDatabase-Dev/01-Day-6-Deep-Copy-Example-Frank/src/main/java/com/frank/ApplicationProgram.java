package com.frank;

public class ApplicationProgram {

    // This member data is static because the method that uses it is static
    private  static int nums[] = {1, 2, 3, 4};

    public static void main(String[] args) {
    	
        System.out.println("Hello from main()");

        showArray();

        SampleClass anObject = new SampleClass(nums);  // Instantiate aClass Object using our array
        anObject.showClass();

        System.out.println("\nafter changing an element in the array defined in main class");
        nums[0] = 999;  // Change the first element in nums

        showArray();         // Display the array in main() class
        anObject.showClass();// Display the array in the aClass object

        System.out.println("\nafter creating a new array main using the array in SampleClass Object");
        int[] Charles = anObject.getAnArray();

        Charles[0] = -48;    // Change value of 1st element in the array in main()
        showArray();         // Display the array in main() class
        anObject.showClass();// Display the array in the aClass object


    }

    public static void showArray() {
            System.out.println(("\nContents of array in main(): "));
            for (int i = 0; i < nums.length; i++) {
                    System.out.println("Element " + i + ": " + nums[i]);
            }
    }

}
