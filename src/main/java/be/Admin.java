package be;

public class Admin {

    private int id;
    private String username;
    private String password;

    private String type;
    public Admin(int id, String username, String password, String type ){
        this.id = id;
        this.username = username;
        this.password = password;
        this.type = type;
    }
    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setType() {
        this.type = "Admin";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
