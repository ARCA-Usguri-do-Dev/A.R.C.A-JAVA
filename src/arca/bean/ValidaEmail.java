package arca.bean;

public class ValidaEmail {
    //valida e-mail
    public static boolean validarEmail(String email){
        String emailFerificado = "^[\\w.-]+@[\\w-]+\\.[a-zA-Z]{2,}$";

        return email.matches(emailFerificado);
    }
}
