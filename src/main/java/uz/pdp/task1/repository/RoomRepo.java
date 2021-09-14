package uz.pdp.task1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.task1.entity.Room;

public interface RoomRepo extends JpaRepository<Room, Integer> {
}
