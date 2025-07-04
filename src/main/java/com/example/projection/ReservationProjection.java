package com.example.projection;

import java.time.LocalDate;

public interface ReservationProjection {
    Integer getIdReservation();
    String getNomAdherant();
    String getTitreLivre();
    String getStatut();
    LocalDate getDateReservation();
}
