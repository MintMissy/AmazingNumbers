// You can experiment here, it won’t be checked

public class Task {
  public static void main(String[] args) {
    System.out.println(Direction.NORTH.toString());
    //returns "N" as a String

    System.out.println(Direction.NORTH.getShortCode());
    // returns "N" as a String

//    System.out.println(Direction.valueOf("N"));
    //returns Direction.NORTH as a Direction object

    System.out.println(Direction.NORTH.name());
    // returns "NORTH" as a String

    System.out.println(Direction.valueOf("NORTH"));
    // returns Direction.NORTH as a Direction object
  }

  enum Direction {
    EAST("E"),
    WEST("W"),
    NORTH("N"),
    SOUTH("S");

    private final String shortCode;

    Direction(String code) {
      this.shortCode = code;
    }

    public String getShortCode() {
      return this.shortCode;
    }
  }
}
