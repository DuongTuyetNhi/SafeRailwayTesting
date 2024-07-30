package enums;

public enum SeatType {
    HS("Hard seat"),
    SS("Soft seat"),
    SSC("Soft seat with air conditioner"),
    HB("Hard bed"),
    SB("Soft bed"),
    SBC("Soft bed with air conditioner");


    private final String seatType;

    SeatType(String seatType) {
        this.seatType = seatType;
    }
    public String getValueSeatType(){
        return seatType;
    }
}
