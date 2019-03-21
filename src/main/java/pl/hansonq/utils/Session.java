package pl.hansonq.utils;

public class Session {
    private static Session ourInstance = new Session();

    public static Session getInstance() {
        return ourInstance;
    }

    private Session() {
    }

    private int id;
    private String username;
    private boolean isLogedIn;
    private String accessCode;

    public String getAccessCode() {
        return accessCode;
    }

    public void setAccessCode(String accessCode) {
        this.accessCode = accessCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isLogedIn() {
        return isLogedIn;
    }

    public void setLogedIn(boolean logedIn) {
        isLogedIn = logedIn;
    }
}
