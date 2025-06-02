package arca.main;
import arca.bean.Usuario;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Main {
    private static final String ARQUIVO_USUARIOS = "usuarios.txt";

    public static void main(String[] args) {
        Usuario uso = new Usuario();

        String repetir = "sim";
        while (repetir.equalsIgnoreCase("sim"))
            try {
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.insets = new Insets(2, 2, 2, 2);
                gbc.anchor = GridBagConstraints.WEST;

                JPanel painel = new JPanel(new GridBagLayout());

                painel.add(new JLabel("Bem-vindo! Como você prefere acessar? Selecione uma opção para prosseguir"), gbc);
                gbc.gridy=1;
                painel.add(new JLabel(" "), gbc);

                Object[] options = {"Login", "Criar Conta", "Anonimo"};
                int escolhaPainel = JOptionPane.showOptionDialog(null, painel, "Informe seus dados", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

                switch (escolhaPainel) {
                    //login
                    case 0:
                        JPanel painelLogin = new JPanel(new GridBagLayout());
                        //Login - entrada de CPF
                        JTextField entraCpf = new JTextField(11);
                        gbc.gridx = 0;gbc.gridy = 0;
                        painelLogin.add(new JLabel("CPF:"), gbc);
                        gbc.gridx = 1;
                        painelLogin.add(entraCpf, gbc);

                        //Login - entrada de CPF
                        JTextField entraSenha = new JTextField(11);
                        gbc.gridx = 0;gbc.gridy = 2;
                        painelLogin.add(new JLabel("senha:"), gbc);
                        gbc.gridx = 1;
                        painelLogin.add(entraSenha, gbc);


                        int escolhaLogin = JOptionPane.showConfirmDialog(null, painelLogin, "Login", JOptionPane.OK_CANCEL_OPTION);

                        if (escolhaLogin == 0){
                            boolean loginValido = false;

                            if (entraCpf.getText() == null || entraSenha.getText() == null || entraCpf.getText().isEmpty() || entraSenha.getText().isEmpty()) {
                                return;
                            }

                            if (entraCpf.getText().length() == 11) {
                                String cpfFormatado = String.format("%s.%s.%s-%s",
                                        entraCpf.getText().substring(0, 3),
                                        entraCpf.getText().substring(3, 6),
                                        entraCpf.getText().substring(6, 9),
                                        entraCpf.getText().substring(9, 11));
                                entraCpf.setText(cpfFormatado);
                            }

                            try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO_USUARIOS))) {
                                String linha;
                                while ((linha = br.readLine()) != null) {
                                    String[] infoUser = linha.split(":");
                                    if (infoUser.length == 6 && infoUser[2].equals(entraCpf.getText()) && infoUser[5].equals(entraSenha.getText())) {
                                        loginValido = true;
                                        break;
                                    }
                                }
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }

                            if (loginValido) {
                                JOptionPane.showMessageDialog(null, "Login bem-sucedido!");
                            } else {
                                JOptionPane.showMessageDialog(null, "Usuário ou senha inválidos.");
                            }
                        } else{
                            repetir = "não";
                        }
                        break;

                    //cadastro
                    case 1:
                        JPanel painelCadas = new JPanel(new GridBagLayout());

                        //cadastro - entrada Nome
                        JTextField eNome = new JTextField(11);
                        gbc.gridx = 0;gbc.gridy = 0;
                        painelCadas.add(new JLabel("Digite Nome Completo:"),gbc);
                        gbc.gridx = 1;
                        painelCadas.add(eNome, gbc);

                        //cadastro - entrada Data de Nascimento
                        JTextField eDtNascimento = new JTextField(11);
                        gbc.gridx = 0;gbc.gridy = 1;
                        painelCadas.add(new JLabel("Data de Nascimento:"), gbc);
                        gbc.gridx = 1;
                        painelCadas.add(eDtNascimento, gbc);

                        //cadastro - entrada CPF
                        JTextField eCpf = new JTextField(11);
                        gbc.gridx = 0;gbc.gridy = 2;
                        painelCadas.add(new JLabel("Digite CPF:"), gbc);
                        gbc.gridx = 1;
                        painelCadas.add(eCpf, gbc);

                        //cadastro - entrada Email
                        JTextField eEmail = new JTextField(11);
                        gbc.gridx = 0;gbc.gridy = 3;
                        painelCadas.add(new JLabel("Digite Email(opcional):"), gbc);
                        gbc.gridx = 1;
                        painelCadas.add(eEmail, gbc);

                        //cadastro - entrada telefone
                        JTextField eTelefone = new JTextField(11);
                        gbc.gridx = 0;gbc.gridy = 4;
                        painelCadas.add(new JLabel("Digite seu telefone:"), gbc);
                        gbc.gridx = 1;
                        painelCadas.add(eTelefone, gbc);

                        //cadastro - entrada Senha
                        JTextField eSenha = new JTextField(11);
                        gbc.gridx = 0;gbc.gridy = 5;
                        painelCadas.add(new JLabel("Digite sua senha:"), gbc);
                        gbc.gridx = 1;
                        painelCadas.add(eSenha, gbc);

                        //cadastro - entrada Confirmação senha
                        JTextField eSenhaConfirma = new JTextField(11);
                        gbc.gridx = 0;gbc.gridy = 6;
                        painelCadas.add(new JLabel("Confirme sua senha:"), gbc);
                        gbc.gridx = 1;
                        painelCadas.add(eSenhaConfirma, gbc);

                        int escolhaCadas = JOptionPane.showConfirmDialog(null, painelCadas, "Cadastro", JOptionPane.OK_CANCEL_OPTION);

                        if (escolhaCadas == 0) {
                            uso.setNome(eNome.getText());
                            uso.calcularIda(eDtNascimento.getText());
                            uso.setCpf(eCpf.getText());
                            uso.setEmail(eEmail.getText());
                            uso.setTelefone(eTelefone.getText());
                            uso.setSenha(eSenha.getText());

                            // Verifica se o CPF já está cadastrado
                            boolean cpfExiste = false;
                            try (BufferedReader br = new BufferedReader(new FileReader("usuarios.txt"))) {
                                String linha;
                                while ((linha = br.readLine()) != null) {
                                    String[] infoUser = linha.split(":");
                                    if (infoUser.length >= 5 && infoUser[2].equals(uso.getCpf())) {
                                        cpfExiste = true;
                                        break;
                                    }
                                }
                            } catch (IOException e) {
                                JOptionPane.showMessageDialog(null, "Erro ao verificar CPF.");
                                return;
                            }

                            if (cpfExiste) {
                                JOptionPane.showMessageDialog(null, "CPF já cadastrado! Tente outro.");
                                return;
                            }

                            // Verifica se a senha e a confirmação são iguais
                            if (!uso.getSenha().equals(eSenhaConfirma.getText())) {
                                JOptionPane.showMessageDialog(null, "As senhas não conferem. Por favor, confirme corretamente.");
                                return;
                            }

                            //salva o usuário
                            try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO_USUARIOS, true))) {
                                bw.write(uso.getNome() + ":" + uso.getIdade() + ":" + uso.getCpf() + ":" + uso.getEmail() + ":" + uso.getTelefone() + ":" + uso.getSenha());
                                bw.newLine();
                                JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
                            } catch (IOException e) {
                                JOptionPane.showMessageDialog(null, "Erro ao cadastrar usuário.");
                            }

                        }else{
                            repetir = "não";
                        }
                        break;
                    case -1:
                        repetir = "não";
                }


            } catch (Exception e) {
                throw new RuntimeException(e);
            }
    }
}
