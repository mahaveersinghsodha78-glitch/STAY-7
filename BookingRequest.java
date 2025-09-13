package com.example.hotelapp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

public class BookingRequest {
    public Long userId;
    public Long hotelId;
    public Long roomId;
    public String guestName;
    @JsonFormat(pattern="yyyy-MM-dd")
    public LocalDate checkIn;
    @JsonFormat(pattern="yyyy-MM-dd")
    public LocalDate checkOut;
    public int roomsCount = 1;
}
