package com.example.hotelapp.controller;

import com.example.hotelapp.dto.BookingRequest;
import com.example.hotelapp.model.Booking;
import com.example.hotelapp.model.Hotel;
import com.example.hotelapp.model.Room;
import com.example.hotelapp.model.User;
import com.example.hotelapp.repository.BookingRepository;
import com.example.hotelapp.repository.HotelRepository;
import com.example.hotelapp.repository.RoomRepository;
import com.example.hotelapp.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "*")
public class BookingController {
    private final BookingRepository bookingRepo;
    private final RoomRepository roomRepo;
    private final UserRepository userRepo;
    private final HotelRepository hotelRepo;

    public BookingController(BookingRepository bookingRepo, RoomRepository roomRepo, UserRepository userRepo, HotelRepository hotelRepo){
        this.bookingRepo = bookingRepo;
        this.roomRepo = roomRepo;
        this.userRepo = userRepo;
        this.hotelRepo = hotelRepo;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> create(@RequestBody BookingRequest req){
        if(req.roomId==null || req.userId==null || req.hotelId==null) {
            return ResponseEntity.badRequest().body(Map.of("error","roomId,userId,hotelId required"));
        }
        Room room = roomRepo.findById(req.roomId).orElse(null);
        User user = userRepo.findById(req.userId).orElse(null);
        Hotel hotel = hotelRepo.findById(req.hotelId).orElse(null);
        if(room==null || user==null || hotel==null) return ResponseEntity.badRequest().body(Map.of("error","invalid ids"));

        if(room.getAvailableRooms() < req.roomsCount) {
            return ResponseEntity.status(409).body(Map.of("error","Not enough rooms available"));
        }
        room.setAvailableRooms(room.getAvailableRooms() - req.roomsCount);
        roomRepo.save(room);

        Booking b = new Booking();
        b.setGuestName(req.guestName == null ? user.getName() : req.guestName);
        b.setCheckIn(req.checkIn);
        b.setCheckOut(req.checkOut);
        b.setRoomsCount(req.roomsCount);
        b.setUser(user);
        b.setHotel(hotel);
        b.setRoom(room);
        bookingRepo.save(b);

        return ResponseEntity.ok(Map.of(
                "status","success",
                "bookingId", b.getId(),
                "hotel", hotel.getName(),
                "roomType", room.getType()
        ));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> byUser(@PathVariable Long userId){
        return ResponseEntity.ok(bookingRepo.findByUserId(userId));
    }
}
