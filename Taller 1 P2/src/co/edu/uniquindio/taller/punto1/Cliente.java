package co.edu.uniquindio.taller.punto1;

public class Cliente extends Persona {

    private String phone;

    public Cliente(String name, Integer age, String phone) {
        super(name, age);
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}