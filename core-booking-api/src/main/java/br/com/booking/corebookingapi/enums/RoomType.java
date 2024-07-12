package br.com.booking.corebookingapi.enums;

public enum RoomType {
    INDIVIDUAL("Individual"),
    DUPLO("Duplo"),
    TRIPLO("Triplo"),
    COMPARTILHADO("Compartilhado");

    private final String text;

    RoomType(String text) {
        this.text = text;
    }
}
