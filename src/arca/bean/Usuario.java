package arca.bean;

import javax.swing.*;
import java.time.LocalDate;

public class Usuario {
    private String cpf;
    private String senha;
    private String email;
    private String nome;
    private String idade;
    private String telefone;

    //contrutores
    public Usuario(){}



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
        if (ValidaCpf.validarCpf(cpf)){
            this.cpf = cpf;
        } else {
            JOptionPane.showMessageDialog(null, "CPF inválido.");
        }
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (ValidaEmail.validarEmail(email)){
            this.email = email;
        }else{
            JOptionPane.showMessageDialog(null, "Email inválido.");

        }
    }

    public String getNome() {
        return nome;
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
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
