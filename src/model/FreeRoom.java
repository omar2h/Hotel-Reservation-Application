package model;

public class FreeRoom extends Room {
    FreeRoom(String roomNumber, RoomType enumeration){
        super(roomNumber, 0, enumeration);
    }


    @Override
    public String toString() {
        return "Room Number: " + getRoomNumber() + " RoomType: " + getRoomType() + " Price: Free";
    }

}
