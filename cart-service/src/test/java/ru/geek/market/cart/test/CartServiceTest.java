package ru.geek.market.cart.test;

import ru.geek.market.cart.model.Cart;

//@SpringBootTest(classes = CartService.class)
class CartServiceTest extends Cart {
//    @Autowired
//    CartService cartService;
//
//    @MockBean
//    ProductServiceIntegration productServiceIntegration;
//
//
//    @Test
//    void getCurrentCartTest() {
//        Assertions.assertNotNull(cartService.getCurrentCart());
//    }
//
//    @Test
//    void addProductToCartTest() {
//        ProductDto potatoDto = new ProductDto(1L, "Potato", BigDecimal.valueOf(1.2), "Other vegetables");
//        Mockito.when(productServiceIntegration.findById(1L)).thenReturn(potatoDto);
//        CartItem potatoItem = new CartItem(1L, "Potato", BigDecimal.valueOf(1.2), 1, BigDecimal.valueOf(1.2));
//
//        cartService.addProductToCart(1L);
//        Assertions.assertEquals(potatoItem, cartService.getCurrentCart().getItems().get(0));
//
//        ProductDto onionDto = new ProductDto(3L, "Onion", BigDecimal.valueOf(1.1), "Other vegetables");
//        CartItem onionItem = new CartItem(3L, "Onion", BigDecimal.valueOf(1.1), 1, BigDecimal.valueOf(1.1));
//        Mockito.when(productServiceIntegration.findById(3L)).thenReturn(onionDto);
//
//        cartService.addProductToCart(3L);
//        Assertions.assertEquals(onionItem, cartService.getCurrentCart().getItems().get(1));
//        Assertions.assertEquals(2, cartService.getCurrentCart().getItems().size());
//    }
}