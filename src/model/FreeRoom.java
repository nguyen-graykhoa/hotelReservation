package model;

public class FreeRoom extends Room{
    boolean isFree;
    final double ROOM_PRICE = 0.0d;
    public FreeRoom(String getRoomNumber, Double getRoomPrice, RoomType roomType) {
        super(getRoomNumber, 0.0, roomType);
        this.price = ROOM_PRICE;
        this.isFree = true;
    }

    public String toString() {
        return "Free room " + roomNumber + " price " + price + " room type " + enumeration
                + " is free: " + isFree;
    }

}
