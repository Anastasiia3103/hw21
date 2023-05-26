package hw21;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main (String[] args) {
        List<Product> products = List.of (
                new Product ("Book", 200, true, LocalDate.of (2023, 1, 1)),
                new Product ("Book", 300, true, LocalDate.of (2023, 2, 1)),
                new Product ("Book", 400, false, LocalDate.of (2023, 3, 1)),
                new Product ("Toy", 100, true, LocalDate.of (2023, 1, 1)),
                new Product ("Toy", 200, false, LocalDate.of (2023, 2,1))
        );

        List<Product> expensiveBooks = getExpensiveBooks(products);
        System.out.println ("Expensive books: ");
        expensiveBooks.forEach (System.out::println);
        System.out.println ();

        List<Product> discountedBooks = getDiscountedBooks(products);
        System.out.println ("Discounted books: ");
        discountedBooks.forEach (System.out::println);
        System.out.println ();

        Product cheapestBook = getCheapestBook(products);
        System.out.println ("The cheapest book: ");
        System.out.println (cheapestBook);
        System.out.println ();

        List<Product> lastThreeAddedProducts = getLastThreeAddedProducts(products);
        System.out.println ("Last Three Added Products:");
        lastThreeAddedProducts.forEach(System.out::println);
        System.out.println ();

        double totalCostOfBooks = getTotalCostOfBooks(products);
        System.out.println ("Total cost of books: $" + totalCostOfBooks);
        System.out.println ();

        Map<String, List<Product>> productsByType = groupProductsByType(products);
        System.out.println ("Products Grouped by Type: ");
        productsByType.forEach ((type, productList) -> {
            System.out.println (type + ":");
            productList.forEach (System.out::println);
            System.out.println ();
        });
    }
    public static List<Product> getExpensiveBooks(List<Product> products) {
        return products.stream ()
                .filter (product -> product.getType().equals("Book"))
                .filter (product -> product.getPrice() > 250)
                .collect(Collectors.toList());
    }
    public static List<Product> getDiscountedBooks(List<Product> products) {
        return products.stream ()
                .filter (product -> product.getType ().equals ("Book"))
                .filter (Product::isDiscountAvailable)
                .peek (product -> product.setPrice(product.getPrice () * 0.9))
                .collect(Collectors.toList());
    }
    public static Product getCheapestBook(List<Product> products) {
        return products.stream ()
                .filter (product -> product.getType ().equals ("Book"))
                .min (Comparator.comparingDouble (Product::getPrice))
                .orElseThrow(() -> new RuntimeException("Product [category: Book] not found"));
    }
    public static List<Product> getLastThreeAddedProducts(List<Product> products) {
        return products.stream ()
                .sorted (Comparator.comparing (Product::getAddedDate).reversed ())
                .limit (3)
                .collect(Collectors.toList());
    }
    public static double getTotalCostOfBooks(List<Product> products) {
        LocalDate currentYear = LocalDate.now ().withDayOfYear (1);
        return products.stream ()
                .filter (product -> product.getType ().equals ("Book"))
                .filter (product -> product.getPrice ()<= 75)
                .filter (product -> product.getAddedDate().getYear() == currentYear.getYear ())
                .mapToDouble (Product::getPrice)
                .sum ();
    }
    public static Map<String, List<Product>> groupProductsByType(List<Product> products) {
        return products.stream ()
                .collect(Collectors.groupingBy (Product::getType));
    }
}
