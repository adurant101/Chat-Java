package javachatserver;

//user for list of users username and password
public class User {
    private String username;
    private String password;

    //set username and password
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    //setter and getting username
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    //setter and getter password
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    //check is username and password is valid with object
    public boolean isValid (String u, String p)
    {
        return u.equals(username) && p.equals(password);
    }

    //display username
    private static final String TO_STRING = "{\"username\": \"%s\"}";
    public String toString()
    {
        return String.format(TO_STRING, username);
    }
}
