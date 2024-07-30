package models;

public class User {
    String username;
    String password;
    String pid;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, String pid) {
        this.username = username;
        this.password = password;
        this.pid = pid;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPid() {
        return pid;
    }
}
