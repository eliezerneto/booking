package br.com.booking.corebookingapi.enums;

public enum BookingStatus {

    CREATED("Criado"),
    WAITING_PAYMENT("Aguardando pagamento"),
    PAYED("Pago"),
    RESCHEDULED("Reagendado"),
    CANCELED("Cancelado"),
    FINISHED("Finalizado");

    private final String text;

    BookingStatus(String text) {
        this.text = text;
    }
}
