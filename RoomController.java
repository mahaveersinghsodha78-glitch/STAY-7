package com.example.hotelapp.controller;

import com.example.hotelapp.model.Room;
import com.example.hotelapp.repository.RoomRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class RoomController {
    private final RoomRepository roomRepo;
    public RoomController(RoomRepository roomRepo){ this.roomRepo = roomRepo; }

    @GetMapping("/hotels/{hotelId}/rooms")
    public List<Room> roomsByHotel(@PathVariable Long hotelId){
        return roomRepo.findByHotelId(hotelId);
    }
}
