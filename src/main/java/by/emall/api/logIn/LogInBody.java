package by.emall.api.logIn;

public class LogInBody {
    private String phone;
    private String password;

    public LogInBody(String phone, String password) {
        this.phone = phone;
        this.password = password;
    }

    public LogInBody(String phone) {
        this.phone = phone;
    }

    public LogInBody() {
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }
}
