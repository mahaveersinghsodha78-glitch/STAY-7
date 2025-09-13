package com.example.hotelapp.model;

import jakarta.persistence.*;

@Entity
public class Room {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String type;
    private double price;
    private int totalRooms = 1;
    private int availableRooms = 1;

    @ManyToOne(fetch=FetchType.LAZY)
    private Hotel hotel;

    public Room() {}
    public Room(String type, double price, int totalRooms) {
        this.type = type; this.price = price; this.totalRooms = totalRooms; this.availableRooms = totalRooms;
    }
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public String getType(){return type;} public void setType(String type){this.type=type;}
    public double getPrice(){return price;} public void setPrice(double price){this.price=price;}
    public int getTotalRooms(){return totalRooms;} public void setTotalRooms(int totalRooms){this.totalRooms=totalRooms;}
    public int getAvailableRooms(){return availableRooms;} public void setAvailableRooms(int availableRooms){this.availableRooms=availableRooms;}
    public Hotel getHotel(){return hotel;} public void setHotel(Hotel hotel){this.hotel=hotel;}
}
