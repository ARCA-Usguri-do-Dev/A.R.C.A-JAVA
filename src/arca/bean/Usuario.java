package arca.bean;

import javax.swing.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Usuario {
    private String cpf;
    private String senha;
    private String email;
    private String nome;
    private String idade;
    private String telefone;

    //contrutores
    public Usuario(){}

    public Usuario(String cpf, String senha, String email, String nome, String idade, String telefone) {
        this.cpf = cpf;
        this.senha = senha;
        this.email = email;
        this.nome = nome;
        this.idade = idade;
        this.telefone = telefone;
    }

    //setter/getters
    public String getCpf() {
        if (cpf.length() == 11){
            return String.format("%s.%s.%s-%s",
                    cpf.substring(0, 3),
                    cpf.substring(3, 6),
                    cpf.substring(6, 9),
                    cpf.substring(9, 11));

        }else {
            return cpf;
        }

    }

    public void setCpf(String cpf) {
        if (validarCpf(cpf)){
            this.cpf = cpf;
        } else {
            JOptionPane.showMessageDialog(null, "CPF inválido.");
        }
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        if(validaenha(senha)){
            this.senha = senha;

        }else{
            JOptionPane.showMessageDialog(null,"senha fora dos requisitos ");
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (validarEmail(email)){
            this.email = email;
        }else{
            JOptionPane.showMessageDialog(null, "Email inválido.");

        }
    }

    public String getNome() {
        return nome.substring(0,1).toUpperCase()+nome.substring(1).toLowerCase();
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;

    }

    public String getTelefone() {
        if(telefone.length() ==11){
            return String.format("(%s)%s",telefone.substring(0,2), telefone.substring(2,11));
        }else {
//            return String.format("(11)%s",telefone);
            return telefone;
        }
    }

    public void setTelefone(String telefone) {
        if (telefone.length() == 11 ||telefone.length() == 9){
            this.telefone = telefone;
        }else{
            JOptionPane.showMessageDialog(null,"erro no numero");
        }
    }

    //valida CPF
    public static boolean validarCpf(String cpf){
        int soma = 0,resto, numero;

        String dec1, dec2;
        try {
            if (cpf == null || (cpf.length() != 11 && cpf.length() != 14)) return false;

            if(cpf.length() == 11){
                for (int i =0 ; i<9; i++){
                    numero = Integer.parseInt(cpf.substring(i,i+1));
                    soma += numero * (10-i);
                }
                resto = soma % 11;
                if (resto < 2){
                    dec1 ="0";
                }else {
                    dec1 = String.valueOf(11- resto);
                }

                soma = 0;
                for (int i =0 ; i<9; i++){
                    numero = Integer.parseInt(cpf.substring(i,i+1));
                    soma += numero * (11-i);
                }
                soma += Integer.parseInt(dec1) * 2;
                resto = soma % 11;
                if (resto < 2){
                    dec2="0";
                }else {
                    dec2= String.valueOf(11-resto);
                }

                //formato cpf
                String cpfVericado = cpf.substring(0,9)+dec1+dec2;
                return cpf.equals(cpfVericado);

            }else{
                String cpfLimpo = cpf.replace(".", "").replace("-", "");

                for (int i =0 ; i<9; i++){
                    numero = Integer.parseInt(cpfLimpo.substring(i,i+1));
                    soma += numero * (10-i);
                }
                resto = soma % 11;
                if (resto < 2){
                    dec1 ="0";
                }else {
                    dec1 = String.valueOf(11- resto);
                }

                soma = 0;
                for (int i =0 ; i<9; i++){
                    numero = Integer.parseInt(cpfLimpo.substring(i,i+1));
                    soma += numero * (11-i);
                }
                soma += Integer.parseInt(dec1) * 2;
                resto = soma % 11;
                if (resto < 2){
                    dec2="0";
                }else {
                    dec2= String.valueOf(11-resto);
                }

                //formato cpf
                String cpfVericado = cpfLimpo.substring(0,9)+dec1+dec2;
                return cpfLimpo.equals(cpfVericado);
            }
        } catch (NumberFormatException e) {
            return false;
        }
    }

    //Valida emial
    public static boolean validarEmail(String email){
        String emailFerificado = "^[\\w.-]+@[\\w-]+\\.[a-zA-Z]{2,}$";

        return email.matches(emailFerificado);
    }

    //calcular idade
    public void calcularIda(String dataNasciimento){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataAtual = LocalDate.now();
        LocalDate dataFormatada = LocalDate.parse(dataNasciimento,dtf);
        Period idade = Period.between(dataFormatada, dataAtual);
        setIdade(String.valueOf(idade.getYears()));
    }

    //requesitos para criar semha
    public static boolean validaenha(String senha) {
        if (senha == null || senha.length() < 8) {
            return false;
        }

        boolean maiuscula = false;
        boolean minuscula = false;
        boolean numero = false;
        boolean especial = false;

        for (char c : senha.toCharArray()) {
            if (Character.isUpperCase(c)) maiuscula = true;
            else if (Character.isLowerCase(c)) minuscula = true;
            else if (Character.isDigit(c)) numero = true;
            else especial = true;
        }

        return maiuscula && minuscula && numero && especial;
    }



    //exibir informações
    @Override
    public String toString() {
        return String.format("%s %s %s %s %s %s",cpf,senha,email,nome,idade,telefone);
    }
}
