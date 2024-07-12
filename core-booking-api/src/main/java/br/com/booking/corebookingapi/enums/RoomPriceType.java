package br.com.booking.corebookingapi.enums;

public enum RoomPriceType {

    ALTA_ESTACAO("Alta estação"),
    BAIXA_ESTACAO("Baixa estação"),
    PROMOCAO("Promoção");

    private final String text;

    RoomPriceType(String text) {
        this.text = text;
    }
}
