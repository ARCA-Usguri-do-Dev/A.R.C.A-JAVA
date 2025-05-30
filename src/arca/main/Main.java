package arca.main;

import arca.bean.Idade;
import arca.bean.Usuario;

public class Main {
    public static void main(String[] args) {
        Usuario uso = new Usuario();
        Idade ida = new Idade();

        String dataNascimento ="23/08/2006";

        ida.calcularIda(dataNascimento);

        String cpf = "58221073890";
        String cpf2 = "582.210.738-90";
        uso.setCpf(cpf2);

        String email = "Alexaf@gmail.com";
        String email2 = "alexrwefewfewf.fwfw";

        uso.setEmail(email2);
        System.out.println("data:  "+ ida.getIdade()+ "\nCPF: "+uso.getCpf());
        System.out.println("email:  "+ uso.getEmail());

    }
}
