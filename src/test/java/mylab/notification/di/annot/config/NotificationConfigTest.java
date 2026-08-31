package mylab.notification.di.annot.config;

import mylab.notification.di.annot.EmailNotificationService;
import mylab.notification.di.annot.NotificationManager;
import mylab.notification.di.annot.SmsNotificationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = NotificationConfig.class)
public class NotificationConfigTest {

    @Autowired
    private NotificationManager notificationManager;

    @Test
    public void testNotificationManager() {
        // 1. NotificationManager 주입 및 Not Null 검증
        assertNotNull(notificationManager);

        // 2. 이메일 서비스 검증 (NotificationService 타입을 EmailNotificationService로 캐스팅)
        assertNotNull(notificationManager.getEmailService());
        assertTrue(notificationManager.getEmailService() instanceof EmailNotificationService);
        
        EmailNotificationService emailService = (EmailNotificationService) notificationManager.getEmailService();
        assertEquals("smtp.gmail.com", emailService.getSmtpServer());
        assertEquals(587, emailService.getPort());

        // 3. SMS 서비스 검증 (NotificationService 타입을 SmsNotificationService로 캐스팅)
        assertNotNull(notificationManager.getSmsService());
        assertTrue(notificationManager.getSmsService() instanceof SmsNotificationService);
        
        SmsNotificationService smsService = (SmsNotificationService) notificationManager.getSmsService();
        assertEquals("SKT", smsService.getProvider());

        // 4. NotificationManager 발송 메서드 실행 검증
        notificationManager.sendNotificationByEmail("테스트 이메일");
        notificationManager.sendNotificationBySms("테스트 SMS");
    }
}