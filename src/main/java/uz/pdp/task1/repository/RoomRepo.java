package uz.pdp.task1.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.task1.entity.Room;

public interface RoomRepo extends JpaRepository<Room, Integer> {

    Page<Room> getAllByHotel_Id(Integer hotel_id, Pageable pageable);
}
