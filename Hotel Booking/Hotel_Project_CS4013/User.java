/**
 * A User with a name, username and password.
 */
public class User
{
    private String username;
    private String password;
    private String name;
    /**
     * Construcs a User with a name, username and password.
     */
    public User(String name, String username, String password)
    {
        this.username =username;
        this.password =password;
        this.name= name;
    }

    /**
     * Returns this Users username.
     */
    public String getUsername(){
        return username;
    }

    /**
     * Returns this Users name.
     */
    public String getName(){
        return name;
    }
    
    /**
     * Returns this Users password.
     */
    public String getPassword(){
        return password;
    }
}
