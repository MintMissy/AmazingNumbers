class User {
    private String firstName;
    private String lastName;

    public User() {
        this.firstName = "";
        this.lastName = "";
    }

    public void setFirstName(String firstName) {
        if (!"".equals(firstName) && firstName != null) {
            this.firstName = firstName;
        }
    }

    public void setLastName(String lastName) {
        if (!"".equals(lastName) && lastName != null) {
            this.lastName = lastName;
        }
    }

    public String getFullName() {
        if ((lastName == null && firstName == null)
                || ("".equals(firstName) && "".equals(lastName))
        ) {
            return "Unknown";
        } else if (firstName == null || "".equals(firstName)) {
            return lastName;
        } else if (lastName == null || "".equals(lastName)) {
            return firstName;
        }

        return this.firstName + " " + this.lastName; // write your code here
    }
}