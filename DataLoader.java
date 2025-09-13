package com.example.hotelapp.config;

import com.example.hotelapp.model.Hotel;
import com.example.hotelapp.model.Room;
import com.example.hotelapp.model.User;
import com.example.hotelapp.repository.HotelRepository;
import com.example.hotelapp.repository.RoomRepository;
import com.example.hotelapp.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    private final HotelRepository hotelRepo;
    private final RoomRepository roomRepo;
    private final UserRepository userRepo;

    public DataLoader(HotelRepository hotelRepo, RoomRepository roomRepo, UserRepository userRepo){
        this.hotelRepo = hotelRepo; this.roomRepo = roomRepo; this.userRepo = userRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        if(hotelRepo.count() == 0) {
            Hotel h1 = new Hotel("Sahara Residency","Jaisalmer","Near Fort");
            h1 = hotelRepo.save(h1);
            Room r1 = new Room("Single",1200,5); r1.setHotel(h1); roomRepo.save(r1);
            Room r2 = new Room("Double",1800,3); r2.setHotel(h1); roomRepo.save(r2);

            Hotel h2 = new Hotel("Desert View Inn","Barmer","Main Road");
            h2 = hotelRepo.save(h2);
            Room r3 = new Room("Single",900,4); r3.setHotel(h2); roomRepo.save(r3);

            Hotel h3 = new Hotel("Royal Palace Hotel","Jaipur","City Center");
            h3 = hotelRepo.save(h3);
            Room r4 = new Room("Deluxe",2500,6); r4.setHotel(h3); roomRepo.save(r4);
        }

        if(userRepo.count() == 0){
            userRepo.save(new User("Mahaveer","mahaveer@example.com","9000000000","1234"));
        }
    }
}
