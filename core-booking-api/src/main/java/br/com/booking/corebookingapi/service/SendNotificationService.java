package br.com.booking.corebookingapi.service;

import br.com.booking.corebookingapi.entity.Booking;
import br.com.booking.corebookingapi.enums.BookingStatus;
import br.com.booking.corebookingapi.repository.BookingRepository;
import br.com.booking.corebookingapi.repository.RoomRepository;
import br.com.booking.corebookingapi.schedule.NotificationScheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SendNotificationService {

    private static final Logger logger = LoggerFactory.getLogger(SendNotificationService.class);

    @Autowired
    private BookingRepository bookingRepository;

    public void sendNewBookingNotification(){
        List<Booking> listBookingCreated = bookingRepository.findAllByStatus(BookingStatus.CREATED);

        for(Booking booking : listBookingCreated){
            logger.info("Simulando envio da notificação de Nova Reserva para: " + booking.getGuest().getEmail());
            booking.setStatus(BookingStatus.WAITING_PAYMENT);
            bookingRepository.save(booking);
        }
    }

    public void sendPaymentBookingNotification(){
        List<Booking> listBookingWaitingPayment = bookingRepository.findAllByStatus(BookingStatus.WAITING_PAYMENT);

        for(Booking booking : listBookingWaitingPayment){
            logger.info("Simulando envio da notificação de Pagamento para: " + booking.getGuest().getEmail());
            booking.setStatus(BookingStatus.PAYED);
            bookingRepository.save(booking);
        }
    }
}
