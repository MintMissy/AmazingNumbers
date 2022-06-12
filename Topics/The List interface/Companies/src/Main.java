import java.util.Collection;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String companies = scanner.nextLine();

        String[] companiesList = companies.split(" ");
        System.out.print("[");
        for (int i = 0; i < companiesList.length - 1; i++) {
            System.out.print(companiesList[i] + ", ");
        }
        System.out.print(companiesList[companiesList.length - 1]);
        System.out.print("]");
    }
}