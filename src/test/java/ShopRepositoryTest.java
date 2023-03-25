import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.Product;
import ru.netology.ShopRepository;
import ru.netology.exceptions.AlreadyExistsException;
import ru.netology.exceptions.NotFoundException;

public class ShopRepositoryTest {
    @Test
    public void shouldRemoveByID() {
        Product milk = new Product(101, "Молоко", 150);
        Product bread = new Product(112, "Хлеб", 50);
        Product coffee = new Product(121, "Кофе", 250);

        ShopRepository rep = new ShopRepository();
        rep.add(milk);
        rep.add(bread);
        rep.add(coffee);

        Product[] expected = {milk, coffee};
        Product[] actual = rep.remove(112);

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldDeleteNonRealElement() {
        Product milk = new Product(101, "Молоко", 150);
        Product bread = new Product(110, "Хлеб", 50);
        Product coffee = new Product(111, "Кофе", 250);

        ShopRepository rep = new ShopRepository();
        rep.add(milk);
        rep.add(bread);
        rep.add(coffee);

        Assertions.assertThrows(NotFoundException.class, () -> {
            rep.remove(100);
        });
    }

    @Test
    public void shouldAddProduct() {
        Product milk = new Product(101, "Молоко", 150);

        ShopRepository rep = new ShopRepository();
        rep.add(milk);

        Product[] expected = {milk};
        Product[] actual = rep.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotAddProduct() {
        Product milk = new Product(101, "Молоко", 150);
        Product bread = new Product(101, "Хлеб", 50);

        ShopRepository rep = new ShopRepository();
        rep.add(milk);

        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            rep.add(bread);
        });
    }
}
