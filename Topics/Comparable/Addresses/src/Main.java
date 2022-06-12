import java.util.*;

class Address implements Comparable<Address> {
    private final String city;
    private final String street;
    private final String house;

    public Address(String city, String street, String house) {
        this.city = city;
        this.street = street;
        this.house = house;
    }

    @Override
    public String toString() {
        return "%s, %s, %s".formatted(house, street, city);
    }

    @Override
    public int compareTo(Address address) {
        String thisAddress = this.toString();
        String thatAddress = address.toString();

        int loopLength = Math.max(thisAddress.length(), thatAddress.length());

        for (int i = 0; i < loopLength; i++) {
            if (thisAddress.charAt(i) > thatAddress.charAt(i)) {
                return 1;
            } else if (thisAddress.charAt(i) < thatAddress.charAt(i)) {
                return -1;
            }
        }
        return 0;
    }
}

// do not change the code below
class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Address> list = new ArrayList<>();

        while (sc.hasNextLine()) {
            String[] arguments = sc.nextLine().split(",");
            list.add(new Address(arguments[0], arguments[1], arguments[2]));
        }
        Collections.sort(list);
        Checker.check(list);
    }
}