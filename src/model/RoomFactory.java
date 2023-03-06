package model;

public class RoomFactory {
    public static IRoom getRoom(String roomNumber, double price, RoomType roomType){
        if(price == 0)
            return new FreeRoom(roomNumber, roomType);
        else
            return new Room(roomNumber, price, roomType);
    }
}
