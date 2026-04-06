import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class DiscountAppTest {
    @Test
    void testLoyaltyAndStockDiscount() {
        // Price: 100, Purchases: 15 (5%), Stock: 150 (10%) = 15% total
        // Result should be 85.0
        double result = DiscountApp.calculateDiscount(100.0, 15, 150);
        assertEquals(85.0, result, 0.01);
    }

    @Test
    void testNoDiscount() {
        double result = DiscountApp.calculateDiscount(100.0, 2, 10);
        assertEquals(100.0, result, 0.01);
    }
}