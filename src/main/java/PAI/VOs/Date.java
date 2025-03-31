package PAI.VOs;

import PAI.ddd.ValueObject;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;


public class Date implements ValueObject {

    private final LocalDate localDate;
    public Date(String date) {
        if (date == null || date.isBlank()) {
            throw new IllegalArgumentException("Date cannot be empty!");
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            this.localDate = LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Please use dd-MM-yyyy.");
        }
    }
    public Date(LocalDate localDate) {
        this.localDate = Objects.requireNonNull(localDate, "Date cannot be null!");
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public static Date now() {
        return new Date(LocalDate.now());
    }

    public boolean isBefore(Date other) {

        return this.localDate.isBefore(other.localDate);
    }

    public boolean isAfter(Date other) {

        return this.localDate.isAfter(other.localDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Date date = (Date) o;
        return Objects.equals(localDate, date.localDate);
    }

}