import java.util.*;
import java.util.stream.Collectors;

class Age implements Comparable<Age> {
    private final int value;

    public Age(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public int compareTo(Age age) {
        if (age.getValue() > this.getValue()){
            return -1;
        }else if (age.getValue() < this.getValue()){
            return 1;
        }
        return 0;
    }
}

// do not change the code below
class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Age> list = Arrays.stream(sc.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .mapToObj(Age::new)
                .sorted()
                .collect(Collectors.toList());

        Checker.check(list);
    }
}