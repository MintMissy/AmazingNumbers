import java.util.Scanner;

class ManufacturingController {
    static int producedProducts = 0;

    public static String requestProduct(String product) {
        producedProducts++;
        return producedProducts + ". Requested " + product;
    }

    public static int getNumberOfProducts() {
        return producedProducts;
    }
}

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String product = scanner.nextLine();
            System.out.println(ManufacturingController.requestProduct(product));
            System.out.println(ManufacturingController.getNumberOfProducts());
        }
    }
}