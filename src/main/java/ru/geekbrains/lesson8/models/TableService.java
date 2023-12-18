package ru.geekbrains.lesson8.models;

import ru.geekbrains.lesson8.presenters.Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class TableService implements Model {

    private Collection<Table> tables;
    private Reservation reservation;

    @Override
    public Collection<Table> loadTables() {
        if (tables == null) {
            tables = new ArrayList<>();
            for (int i = 0; i < 15; i++) {
                tables.add(new Table());
            }
        }

        return tables;
    }

    @Override
    public int reservationTable(Date reservationDate, int tableNo, String name) {

        for (Table table : tables) {
            if (table.getNo() == tableNo) {
                reservation = new Reservation(table, reservationDate, name);
                table.getReservations().add(reservation);
                return reservation.getId();
            }
        }
        throw new RuntimeException("Некорректный номер столика");
    }

    /**
     * Метод получения новой записи бронирования столика
     * @param oldReservation - номер предыдущего бронирования
     * @param reservationDate - новая дата бронирования
     * @param tableNo - новый номер столика
     * @param name - имя
     * @return Удаляет предыдущюю запись бронирования столика и создает новую запись бронирования столика
     */
    public int changeReservationTable(int oldReservation, Date reservationDate, int tableNo, String name) {
        for (Table table : tables) {
            if (table.getReservations().removeIf(reservation ->
                    reservation.getId() == oldReservation &&
                            reservation.getName().equals(name)))
                return reservationTable(reservationDate, tableNo, name);
        }
        return -1;
    }
}