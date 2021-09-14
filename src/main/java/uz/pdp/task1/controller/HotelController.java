package uz.pdp.task1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task1.entity.Hotel;
import uz.pdp.task1.repository.HotelRepo;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/hotel")
@RequiredArgsConstructor
public class HotelController {

    private final HotelRepo hotelRepo;

    @GetMapping
    public List<Hotel> getHotels() {
        return hotelRepo.findAll();
    }

    @GetMapping(value = "/{id}")
    public Hotel getHotel(@PathVariable Integer id) {
        Optional<Hotel> optionalHotel = hotelRepo.findById(id);
        return optionalHotel.orElseGet(Hotel::new);
    }

    @PostMapping
    public String addHotel(@RequestBody Hotel hotel) {
        hotelRepo.save(hotel);

        return "Hotel is saved!";
    }

    @DeleteMapping(value = "/{id}")
    public String deleteHotel(@PathVariable Integer id) {
        try {
            hotelRepo.deleteById(id);
            return "Hotel is deleted";
        } catch (Exception e) {
            return "Could not delete Hotel";
        }
    }

    @PutMapping(value = "/{id}")
    public String editHotel(@PathVariable Integer id, @RequestBody Hotel hotel) {
        Optional<Hotel> optionalHotel = hotelRepo.findById(id);
        if (optionalHotel.isPresent()) {
            Hotel realHotel = optionalHotel.get();
            realHotel.setName(hotel.getName());

            hotelRepo.save(realHotel);

            return "Hotel is edited!";
        }

        return "Hotel not found!";
    }

}
