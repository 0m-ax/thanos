package monster.theundefinedavengers.thanos.auth.model;

public class UserDto {
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setUsername(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
