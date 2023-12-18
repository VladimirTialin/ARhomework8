package ru.geekbrains.lesson8.presenters;

import java.util.Date;

public interface ViewObserver {

    void onReservationTable(Date orderDate, int tableNo, String name);
    void onChangeReservation(int oldReservation, Date reservationDate, int tableNo, String name);
}
