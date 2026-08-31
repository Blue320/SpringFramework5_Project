package mylab.user.di.annot;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:mylab-user-di.xml")
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void testUserServiceDI() {
        assertNotNull(userService);
        assertNotNull(userService.getUserRepository());
        assertEquals("MySQL", userService.getUserRepository().getDbType());
        assertNotNull(userService.getSecurityService());
    }

    @Test
    public void testRegisterUser() {
        assertTrue(userService.registerUser("user1", "홍길동", "securePassword123"));
        assertFalse(userService.registerUser("user2", "이순신", null));
        assertFalse(userService.registerUser("user3", "강감찬", ""));
    }
}