import java.util.*;

public class Main {

    static void changeList(List<String> list) {
        int longestStringLength = 0;
        String longestString = "";

        for (String str : list) {
            if (str.length() > longestStringLength) {
                longestStringLength = str.length();
                longestString = str;
            }
        }

        for (int i = 0; i < list.size(); i++) {
            list.set(i, longestString);
        }
    }

    /* Do not change code below */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        List<String> lst = Arrays.asList(s.split(" "));
        changeList(lst);
        lst.forEach(e -> System.out.print(e + " "));
    }
}