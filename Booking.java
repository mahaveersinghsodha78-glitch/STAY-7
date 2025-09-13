package com.example.hotelapp.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Booking {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String guestName;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private int roomsCount;
    private String status = "CONFIRMED";

    @ManyToOne
    private User user;

    @ManyToOne
    private Hotel hotel;

    @ManyToOne
    private Room room;

    public Booking() {}
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public String getGuestName(){return guestName;} public void setGuestName(String guestName){this.guestName=guestName;}
    public LocalDate getCheckIn(){return checkIn;} public void setCheckIn(LocalDate checkIn){this.checkIn=checkIn;}
    public LocalDate getCheckOut(){return checkOut;} public void setCheckOut(LocalDate checkOut){this.checkOut=checkOut;}
    public int getRoomsCount(){return roomsCount;} public void setRoomsCount(int roomsCount){this.roomsCount=roomsCount;}
    public String getStatus(){return status;} public void setStatus(String status){this.status=status;}
    public User getUser(){return user;} public void setUser(User user){this.user=user;}
    public Hotel getHotel(){return hotel;} public void setHotel(Hotel hotel){this.hotel=hotel;}
    public Room getRoom(){return room;} public void setRoom(Room room){this.room=room;}
}
