package mylab.order.di.xml;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:mylab-order-di.xml")
public class OrderSpringTest {

    @Autowired
    private ShoppingCart cart;

    @Autowired
    private OrderService service;

    @Test
    public void testShoppingCart() {
        // 1. shoppingCart 객체 null 여부 검증
        assertNotNull(cart);
        
        // 2. products 리스트 개수 검증
        assertEquals(2, cart.getProducts().size());
        
        // 3. 상품 이름 검증
        assertEquals("노트북", cart.getProducts().get(0).getName());
        assertEquals("스마트폰", cart.getProducts().get(1).getName());
    }

    @Test
    public void testOrderService() {
        // 1. orderService 객체 null 여부 검증
        assertNotNull(service);
        
        // 2. orderService 내의 shoppingCart 객체 null 여부 검증
        assertNotNull(service.getShoppingCart());
        
        // 3. 총 주문 금액 계산 결과 검증 (150,000 + 800,000 = 950,000)
        assertEquals(950000.0, service.calculateOrderTotal());
    }
}