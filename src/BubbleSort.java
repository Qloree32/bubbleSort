import java.io.*;
import java.util.*;

public class BubbleSort {

    // Function to create an array for random integers
    public static int[] createRandomArray(int arrayLength) {
        Random rand = new Random();
        int[] array = new int[arrayLength];
        
        for (int i = 0; i < arrayLength; i++) {
            array[i] = rand.nextInt(101);
        }
        
        return array;
    }

    // Function to write the array to a file
    public static void writeArrayToFile(int[] array, String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            for (int num : array) {
                writer.write(num + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Function to read an array from a file
    public static int[] readFileToArray(String filename) {
        ArrayList<Integer> list = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                list.add(Integer.parseInt(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // Convert ArrayList to array
        int[] array = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        
        return array;
    }

    // Function that performs the bubble sort on the array
    public static void bubbleSort(int[] array) {
        int n = array.length;
        boolean swapped;
        
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    // Main
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter task: (1) Generate random array and store in file, (2) Sort an array from file and store result");
        int task = scanner.nextInt();

        if (task == 1) {
            System.out.println("Enter array length:");
            int length = scanner.nextInt();

            System.out.println("Enter output filename:");
            String outputFilename = scanner.next();

            int[] randomArray = createRandomArray(length);
            writeArrayToFile(randomArray, outputFilename);
            System.out.println("Random array written to " + outputFilename);

        } else if (task == 2) {
            System.out.println("Enter input filename:");
            String inputFilename = scanner.next();

            System.out.println("Enter output filename for sorted array:");
            String outputFilename = scanner.next();

            int[] array = readFileToArray(inputFilename);
            bubbleSort(array);
            writeArrayToFile(array, outputFilename);
            System.out.println("Sorted array written to " + outputFilename);
        }

        scanner.close();
    }
}
