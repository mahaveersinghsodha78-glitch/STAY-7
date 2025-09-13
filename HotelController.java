package com.example.hotelapp.controller;

import com.example.hotelapp.model.Hotel;
import com.example.hotelapp.repository.HotelRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/hotels")
@CrossOrigin(origins = "*")
public class HotelController {
    private final HotelRepository hotelRepo;
    public HotelController(HotelRepository hotelRepo){ this.hotelRepo = hotelRepo; }

    @GetMapping
    public List<Hotel> list(@RequestParam(value="city", required=false) String city){
        if(city==null || city.isBlank()) return hotelRepo.findAll();
        return hotelRepo.findByCityIgnoreCase(city.trim());
    }

    @GetMapping("/{id}")
    public Hotel get(@PathVariable Long id){
        return hotelRepo.findById(id).orElse(null);
    }

    @PostMapping
    public Hotel create(@RequestBody Hotel h){
        return hotelRepo.save(h);
    }
}
