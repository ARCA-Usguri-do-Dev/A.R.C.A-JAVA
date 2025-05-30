package arca.main;

import arca.bean.Usuario;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        Usuario uso = new Usuario();

        //dados ficticios
        String dataNascimento = "23/08/2006";
        uso.calcularIda(dataNascimento);
        String cpf = "12345678909";
        uso.setCpf(cpf);
        String email = "teste@gmail.com";
        uso.setEmail(email);
        String tele = "123456789";
        uso.setTelefone(tele);
        String nome = "Leonardo Da SiLVA";
        uso.setNome(nome);
        String senha = "Leo123.to";
        uso.setSenha(senha);

        Usuario id = new Usuario(uso.getCpf(), uso.getSenha(), uso.getEmail(), uso.getNome(), uso.getIdade(), uso.getTelefone());
        System.out.println(id);

        String repetir = "sim";
        while (repetir.equalsIgnoreCase("sim"))
            try {
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.insets = new Insets(2, 2, 2, 2);
                gbc.anchor = GridBagConstraints.WEST;

                JPanel painel = new JPanel(new GridBagLayout());

                painel.add(new JLabel("Escolha uma das opções para contimuar!"), gbc);

                Object[] options = {"Login", "Cadastro", "Anonimo"};
                int escolhaPainel = JOptionPane.showOptionDialog(null, painel, "Informe seus dados", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

                switch (escolhaPainel) {
                    case 0:
                        JPanel painelLogin = new JPanel(new GridBagLayout());

                        JTextField entraCpf = new JTextField(11);
                        JTextField entraSenha = new JTextField(11);

                        gbc.gridx = 0;gbc.gridy = 0;
                        painelLogin.add(new JLabel("CPF:"), gbc);
                        gbc.gridx = 1;
                        painelLogin.add(entraCpf, gbc);

                        gbc.gridx = 0;gbc.gridy = 2;
                        painelLogin.add(new JLabel("senha:"), gbc);
                        gbc.gridx = 1;
                        painelLogin.add(entraSenha, gbc);

                        int escolhaLogin = JOptionPane.showConfirmDialog(null, painelLogin, "Login", JOptionPane.OK_CANCEL_OPTION);
                        break;
                    case 1:
                        JPanel painelCadas = new JPanel(new GridBagLayout());

                        JTextField eNome = new JTextField(11);
                        JTextField eDtNascimento = new JTextField(11);
                        JTextField eCpf = new JTextField(11);
                        JTextField eEmail = new JTextField(11);
                        JTextField eSenha = new JTextField(11);
                        JTextField eSenhaConfirma = new JTextField(11);


                        gbc.gridx = 0;gbc.gridy = 0;
                        painelCadas.add(new JLabel("Digite Nome:"),gbc);
                        gbc.gridx = 1;
                        painelCadas.add(eNome, gbc);

                        gbc.gridx = 0;gbc.gridy = 1;
                        painelCadas.add(new JLabel("Data de Nascimento:"), gbc);
                        gbc.gridx = 1;
                        painelCadas.add(eDtNascimento, gbc);

                        gbc.gridx = 0;gbc.gridy = 2;
                        painelCadas.add(new JLabel("Digite CPF:"), gbc);
                        gbc.gridx = 1;
                        painelCadas.add(eCpf, gbc);

                        gbc.gridx = 0;gbc.gridy = 3;
                        painelCadas.add(new JLabel("Digite Email:"), gbc);
                        gbc.gridx = 1;
                        painelCadas.add(eEmail, gbc);

                        gbc.gridx = 0;gbc.gridy = 4;
                        painelCadas.add(new JLabel("Digite sua Senha:"), gbc);
                        gbc.gridx = 1;
                        painelCadas.add(eSenha, gbc);

                        gbc.gridx = 0;gbc.gridy = 5;
                        painelCadas.add(new JLabel("Confirme senha:"), gbc);
                        gbc.gridx = 1;
                        painelCadas.add(eSenhaConfirma, gbc);

                        int escolhaCadas = JOptionPane.showConfirmDialog(null, painelCadas, "Login", JOptionPane.OK_CANCEL_OPTION);
                        break;
                    default:
                        break;
                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }


    }
}
