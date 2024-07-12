package br.com.booking.corebookingapi.enums;

public enum AccommodationType {
    HOTEL("Hotel"),
    POUSADA("Pousada"),
    HOSTEL("Hostel"),
    FLAT("Flat"),
    RESORT("Resort");

    private final String text;

    AccommodationType(String text) {
        this.text = text;
    }
}
