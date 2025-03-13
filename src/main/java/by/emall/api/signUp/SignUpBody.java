package by.emall.api.signUp;

public class SignUpBody {
    private String name;
    private String surname;
    private String patronymic;
    private String phone;
    private String email;
    private String password;
    private int[] target_ids;

    public SignUpBody(String name, String surname, String patronymic, String phone, String email, String password, int[] target_ids) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.target_ids = target_ids;
    }

    public SignUpBody() {
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

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int[] getTarget_ids() {
        return target_ids;
    }
}
