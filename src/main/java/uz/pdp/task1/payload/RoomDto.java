package uz.pdp.task1.payload;

import lombok.Data;

@Data
public class RoomDto {

    private String roomNumber;
    private Integer floor;
    private Double size;
    private Integer hotelId;

}
