import java.time.LocalDate;
import java.time.Month;

public class DiscountApp {
    public static double calculateDiscount(double price, int purchaseCount, int stockLevel) {
        double discount = 0.0;
        LocalDate now = LocalDate.now();

        // 1. User Behavior: Loyalty discount (5%)
        if (purchaseCount > 10) {
            discount += 0.05;
        }

        // 2. Season: Holiday discount (20% in December)
        if (now.getMonth() == Month.DECEMBER) {
            discount += 0.20;
        }

        // 3. Inventory: High stock discount (10% if stock > 100)
        if (stockLevel > 100) {
            discount += 0.10;
        }

        return price * (1 - discount);
    }

    public static void main(String[] args) {
        System.out.println("Dynamic Discount System Started");
    }
}