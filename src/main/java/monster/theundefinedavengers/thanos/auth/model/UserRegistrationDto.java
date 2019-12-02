package monster.theundefinedavengers.thanos.auth.model;

import monster.theundefinedavengers.thanos.auth.Validators.PasswordMatches;

@PasswordMatches
public class UserRegistrationDto {

    private String name;
    private String email;
    private String password;
    private String password2;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

}
