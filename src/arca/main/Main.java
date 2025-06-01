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


                        if (escolhaLogin== 0){
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
                                    if (infoUser.length == 5 && infoUser[2].equals(entraCpf.getText()) && infoUser[4].equals(entraSenha.getText())) {
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
                        }
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
                        painelCadas.add(new JLabel("Digite Email(opcional):"), gbc);
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

                        int escolhaCadas = JOptionPane.showConfirmDialog(null, painelCadas, "Cadastro", JOptionPane.OK_CANCEL_OPTION);

                        if (escolhaCadas == 0) {
                            uso.setNome(eNome.getText());
                            uso.calcularIda(eDtNascimento.getText());
                            uso.setCpf(eCpf.getText());
                            uso.setEmail(eEmail.getText());

                            if (eSenhaConfirma.getText().equals(eSenha.getText())) {
                                uso.setSenha(eSenha.getText());
                                
                                try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO_USUARIOS, true))) {
                                    bw.write(uso.getNome() + ":" + uso.getIdade() + ":" + uso.getCpf() + ":" + uso.getEmail() + ":" + uso.getSenha()+"\n");
                                    bw.newLine();
                                    JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
                                } catch (IOException e) {
                                    JOptionPane.showMessageDialog(null, "Erro ao salvar usuário.");
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Senha inválida. Certifique-se de que ela contenha no mínimo uma letra maiúscula, uma letra minúscula, um número e um caractere especial (como !, @, #, etc.)");
                            }

                        }
                    default:
                        break;
                }
                repetir = "não";
            } catch (Exception e) {
                throw new RuntimeException(e);
            }


    }
}
