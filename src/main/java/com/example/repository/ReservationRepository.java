package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.model.Reservation;
import com.example.projection.ReservationProjection;

public interface ReservationRepository extends JpaRepository<Reservation, Integer>{

@Query(value = "SELECT idReservation AS idReservation, nomAdherant AS nomAdherant, titreLivre AS titreLivre, dateReservation AS dateReservation, statut AS statut FROM Vue_Reservations_Detaillees", nativeQuery = true)
List<ReservationProjection> getAllReservations();

}
