package by.emall.api.profile;

public class ProfileBody {
    private String name;
    private String surname;
    private String patronymic;
    private String email;
    private String phone;

    public ProfileBody(String name, String surname, String patronymic, String email, String phone) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.email = email;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }
}
