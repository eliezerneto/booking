package br.com.booking.corebookingapi.schedule;

import br.com.booking.corebookingapi.repository.BookingRepository;
import br.com.booking.corebookingapi.service.SendNotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class NotificationScheduler {
    private static final Logger logger = LoggerFactory.getLogger(NotificationScheduler.class);

    @Autowired
    private SendNotificationService sendNotificationService;

    @Scheduled(fixedRateString = "${schedule.notification.fixedrate}")
    public void sendNotification(){
        logger.info("Iniciado o processo de envio de notificações.");

        sendNotificationService.sendNewBookingNotification();
        sendNotificationService.sendPaymentBookingNotification();

        logger.info("Finalizado o processo de envio de notificações.");
    }

}
