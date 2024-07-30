package enums;

public enum Locations {
    SAI_GON("Sài Gòn"),
    PHAN_THIET("Phan Thiết"),
    NHA_TRANG("Nha Trang"),
    DA_NANG("Đà Nẵng"),
    HUE("Huế"),
    QUANG_NGAI("Quảng Ngãi");

    private final String location;

    Locations(String location) {
        this.location = location;
    }
    public String getValueLocation(){
        return location;
    }
}
