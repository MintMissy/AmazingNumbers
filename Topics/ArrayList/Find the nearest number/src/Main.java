import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> numbers = new ArrayList<Integer>();

        String setOfIntegers = scanner.nextLine();
        String[] integers = setOfIntegers.split(" ");
        for (String integer : integers) {
            numbers.add(parseInt(integer));
        }

        int n = scanner.nextInt();
        ArrayList<Integer> result = findNearestNumber(numbers, n);
        for (int number : result) {
            System.out.print(number + " ");
        }

    }

    private static ArrayList<Integer> findNearestNumber(ArrayList<Integer> numbers, int givenNumber) {
        ArrayList<Integer> result = new ArrayList<>();
        int minDistance = Math.abs(numbers.get(0) - givenNumber);

        for (Integer number : numbers) {
            int distance = Math.abs(givenNumber - number);
            if (distance < minDistance) {
                result.clear();
                result.add(number);
                minDistance = distance;
            } else if (distance == minDistance) {
                result.add(number);
            }
        }
        Collections.sort(result);
        return result;
    }
}