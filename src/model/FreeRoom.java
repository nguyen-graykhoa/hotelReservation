package model;

public class FreeRoom extends Room{
    boolean isFree;
    public FreeRoom(String getRoomNumber, Double getRoomPrice, RoomType roomType) {
        super(getRoomNumber, 0.0, roomType);
        this.isFree = true;
    }

    public String toString() {
        return "Free room " + roomNumber + " price " + price + " room type " + enumeration
                + " is free: " + isFree;
    }

}
