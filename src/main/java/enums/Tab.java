package enums;

public enum Tab {
    HOME("Home"),
    FAQ("FAQ"),
    CONTACT("Contact"),
    TIMETABLE("Timetable"),
    TICKET_PRICE("Ticket price"),
    BOOK_TICKET("Book ticket"),
    MY_TICKET("My ticket"),
    REGISTER("Register"),
    LOGIN("Login"),
    CHANGE_PASSWORD("Change password"),
    LOGOUT("Log out");


    private final String tab;

    Tab(String tab) {
        this.tab = tab;
    }
    public String getValueTab(){
        return tab;
    }
}
