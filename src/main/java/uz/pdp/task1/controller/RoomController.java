package uz.pdp.task1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task1.entity.Hotel;
import uz.pdp.task1.entity.Room;
import uz.pdp.task1.payload.RoomDto;
import uz.pdp.task1.repository.HotelRepo;
import uz.pdp.task1.repository.RoomRepo;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/room")
@RequiredArgsConstructor
public class RoomController {

    private final HotelRepo hotelRepo;
    private final RoomRepo roomRepo;

    @GetMapping
    public List<Room> getRooms() {
        return roomRepo.findAll();
    }

    @GetMapping(value = "/{id}")
    public Room getRoom(@PathVariable Integer id) {
        Optional<Room> optionalRoom = roomRepo.findById(id);
        return optionalRoom.orElseGet(Room::new);
    }

    @PostMapping
    public String addRoom(@RequestBody RoomDto dto) {
        Optional<Hotel> optionalHotel = hotelRepo.findById(dto.getHotelId());
        if (!optionalHotel.isPresent()) {
            return "Hotel is not found";
        }
        Hotel hotel = optionalHotel.get();
        Room room = new Room();
        room.setHotel(hotel);
        room.setRoomNumber(dto.getRoomNumber());
        room.setFloor(dto.getFloor());
        room.setSize(dto.getSize());

        roomRepo.save(room);

        return "Room is saved!";
    }

    @DeleteMapping(value = "/{id}")
    public String deleteRoom(@PathVariable Integer id) {
        try {
            roomRepo.deleteById(id);
            return "Room is deleted";
        } catch (Exception e) {
            return "Room is not deleted";
        }
    }

    @PutMapping(value = "/{id}")
    public String editRoom(@PathVariable Integer id, @RequestBody RoomDto dto) {
        Optional<Hotel> optionalHotel = hotelRepo.findById(dto.getHotelId());
        if (!optionalHotel.isPresent()) {
            return "Hotel is not found";
        }

        Optional<Room> optionalRoom = roomRepo.findById(id);
        if (!optionalRoom.isPresent()) {
            return "Room is not found";
        }

        Room room = optionalRoom.get();

        room.setSize(dto.getSize());
        room.setRoomNumber(dto.getRoomNumber());
        room.setFloor(dto.getFloor());
        room.setHotel(optionalHotel.get());

        roomRepo.save(room);

        return "Room is edited";
    }
}
