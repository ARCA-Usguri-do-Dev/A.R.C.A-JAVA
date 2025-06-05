package arca.bean;

public class Admin extends Usuario{
    @Override
    public String tipoUsuario() {
        return "Admin";
    }
}
