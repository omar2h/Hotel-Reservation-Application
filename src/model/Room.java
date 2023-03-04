package model;

public class Room implements IRoom {
    private String roomNumber;
    private double price;
    private RoomType enumeration;

    public Room(String roomNumber, double price, RoomType enumeration){
        this.roomNumber = roomNumber;
        this.price = price;
        this.enumeration = enumeration;
    }

    @Override
    public String getRoomNumber(){
        return roomNumber;
    }

    @Override
    public Double getRoomPrice(){
        return price;
    }

    @Override
    public RoomType getRoomType(){
        return enumeration;
    }

    @Override
    public boolean isFree(){
        return true;
    }

    @Override
    public String toString() {
        return "Room Number: " + getRoomNumber() + " RoomType: " + getRoomType() + " Price: $" + getRoomPrice();
    }

}
