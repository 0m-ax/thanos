package monster.theundefinedavengers.thanos.auth.model;


import monster.theundefinedavengers.thanos.auth.Validators.PasswordMatches;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@PasswordMatches(message = "Passwords don't match")
public class UserRegistrationDto {

    @NotEmpty
    @Size(min=2, max=30)
    private String name;

    @NotEmpty
    @Email(message = "Email should be valid")
    private String email;

    @NotEmpty
    private String password;

    @NotEmpty
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
