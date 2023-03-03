package ru.geek.market.cart.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.geek.market.api.DTO.ProductDto;
import ru.geek.market.cart.model.Cart;
import ru.geek.market.cart.model.CartItem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

//@SpringBootTest(classes = Cart.class)
public class CartTest {

//    private Cart cartToTest;
//
//    private ProductDto productDto1 = new ProductDto(1L, "Potato", BigDecimal.valueOf(1.2), "Other vegetables");
//    private ProductDto productDto2 = new ProductDto(3L, "Onion", BigDecimal.valueOf(1.1), "Other vegetables");
//    private CartItem item1 = new CartItem(1L, "Potato", BigDecimal.valueOf(1.2), 1, BigDecimal.valueOf(1.2));
//    private CartItem item2 = new CartItem(2L, "Cabbage", BigDecimal.valueOf(1.5), 1, BigDecimal.valueOf(1.5));
//
//    private List<CartItem> cartItems = new ArrayList<>(List.of(
//            item1, item2
//    ));
//
//    private void fillTestCartWithItems() {
//        cartToTest = new Cart(cartItems, BigDecimal.valueOf(2.7));
//    }
//
//    @Test
//    void addProductToCartTest() {
//        fillTestCartWithItems();
//
//        cartToTest.addProductToCart(productDto1);
//
//        CartItem updatedItem = cartToTest.getItems().stream().filter(item -> item.getProductId().equals(1L)).findAny().get();
//
//        Assertions.assertEquals(2, updatedItem.getQuantity());
//        Assertions.assertEquals(BigDecimal.valueOf(1.2), updatedItem.getProductPrice());
//        Assertions.assertEquals(BigDecimal.valueOf(2.4), updatedItem.getPriceCalculated());
//    }
//
//    @Test
//    void calculateTotalPriceTest() {
//        cartToTest = new Cart();
//
//        cartToTest.addProductToCart(productDto1); // price 1.2
//        cartToTest.addProductToCart(productDto1);
//        Assertions.assertEquals(BigDecimal.valueOf(2.4), cartToTest.getTotalPrice());
//
//        cartToTest.addProductToCart(productDto2); // price +1.1
//        Assertions.assertEquals(BigDecimal.valueOf(3.5), cartToTest.getTotalPrice());
//    }
//
//    @Test
//    void increaseItemQuantityTest(){
//        fillTestCartWithItems();
//        cartToTest.increaseItemQuantity(1L); //add one more potato
//
//        Assertions.assertEquals(2, cartToTest.getItems().size()); //size of the list not changed
//        CartItem potato =  cartToTest.getItems().stream().filter(cartItem -> cartItem.getProductId().equals(1L)).findAny().get();
//        Assertions.assertEquals(2, potato.getQuantity());
//    }
//
//    @Test
//    void decreaseItemQuantityTest(){
//        fillTestCartWithItems();
//
//        cartToTest.decreaseItemQuantity(1L); //potato quantity 1 - 1 = 0
//        Assertions.assertEquals(1, cartToTest.getItems().size());
//
//        cartToTest.addProductToCart(productDto1);
//        cartToTest.addProductToCart(productDto1);
//        cartToTest.addProductToCart(productDto1);
//        cartToTest.decreaseItemQuantity(1L);
//        CartItem potato =  cartToTest.getItems().stream().filter(cartItem -> cartItem.getProductId().equals(1L)).findFirst().get();
//        Assertions.assertEquals(2, potato.getQuantity());
//    }
//
//    @Test
//    void removeProductTest(){
//        fillTestCartWithItems();
//        cartToTest.removeProduct(1L);
//        Assertions.assertEquals(1, cartToTest.getItems().size());
//        Assertions.assertEquals(BigDecimal.valueOf(1.5), cartToTest.getTotalPrice());
//        CartItem onion =  cartToTest.getItems().stream().filter(cartItem -> cartItem.getProductId().equals(2L)).findFirst().get();
//        Assertions.assertEquals(onion, cartToTest.getItems().get(0));
//    }
}
