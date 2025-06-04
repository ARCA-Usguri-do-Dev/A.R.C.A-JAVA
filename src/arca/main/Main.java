package arca.main;
import arca.bean.*;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Main {
    private static final String ARQUIVO_USUARIOS = "usuarios.txt";
    private static final String ARQUIVO_PONTO = "pontoDeAjuda.txt";

    public static void main(String[] args) {
        Usuario usu = new Usuario();
        PontoApoio pontoApoio = new PontoApoio();
        PontoApoio re = new StatusRecusado();
        PontoApoio ap = new StatusAprovado();

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

                Object[] options = {"Login", "Criar Conta"};
                int escolhaPainel = JOptionPane.showOptionDialog(null, painel, " ", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

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
                        painelLogin.add(new JLabel("Senha:"), gbc);
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

                                JPanel painelPergunta = new JPanel(new GridBagLayout());
                                gbc.anchor = GridBagConstraints.CENTER;
                                gbc.gridx=0;gbc.gridy=0;
                                painelPergunta.add( new JLabel("Gostaria de cadastrar um local de apoio em nossa plataforma"),gbc);
                                gbc.gridy=1;
                                painelPergunta.add( new JLabel("para que ele possa ser acessado por quem está "),gbc);
                                gbc.gridy=2;
                                painelPergunta.add( new JLabel("em busca de acolhimento e orientação?"),gbc);

                                Object[] btP = {"Sim", "Não"};
                                int optionsP = JOptionPane.showOptionDialog(null, painelPergunta," ", JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,null,btP,btP[0]);
                                if (optionsP == 0){
                                    String repetirPonto = "sim";

                                    while (repetirPonto.equals("sim"))
                                        try {
                                            //Painel Regsitro ponto
                                            gbc.anchor = GridBagConstraints.WEST;

                                            JPanel painelRePonto = new JPanel(new GridBagLayout());

                                            //Regsitro ponto - entrada Nome
                                            JTextField entradaNome = new JTextField(11);
                                            gbc.gridx=0;gbc.gridy=0;
                                            painelRePonto.add(new JLabel("Nome:"),gbc);
                                            gbc.gridx=1;
                                            painelRePonto.add(entradaNome,gbc);

                                            //Regsitro ponto - entrada Telefone
                                            JTextField entradaTele = new JTextField(11);
                                            gbc.gridx=0;gbc.gridy=1;
                                            painelRePonto.add(new JLabel("Telefone:"),gbc);
                                            gbc.gridx=1;
                                            painelRePonto.add(entradaTele,gbc);

                                            //Regsitro ponto - entrada Capacidade
                                            JTextField entradaCapa = new JTextField(11);
                                            gbc.gridx=0;gbc.gridy=2;
                                            painelRePonto.add(new JLabel("Capacidade:"),gbc);
                                            gbc.gridx=1;
                                            painelRePonto.add(entradaCapa,gbc);

                                            //Regsitro ponto - entrada Descrição
                                            JTextField entradaDes = new JTextField(16);
                                            gbc.gridx=0;gbc.gridy=3;
                                            painelRePonto.add(new JLabel("Descrição:"),gbc);
                                            gbc.gridx=1;
                                            painelRePonto.add(entradaDes,gbc);

                                            //Regsitro ponto - entrada cep
                                            JTextField entradaCEP = new JTextField(6);
                                            gbc.gridx = 0;gbc.gridy = 5;
                                            painelRePonto.add(new JLabel("CEP:"), gbc);
                                            gbc.gridx = 1;
                                            painelRePonto.add(entradaCEP, gbc);

                                            //Regsitro ponto - entrada numero
                                            JTextField entradaNu = new JTextField(6);
                                            gbc.gridx = 3;
                                            painelRePonto.add(new JLabel("Numero:"), gbc);
                                            gbc.gridx = 4;
                                            painelRePonto.add(entradaNu, gbc);


                                            int optiosPontoAju = JOptionPane.showConfirmDialog(null,painelRePonto,"Cadastro Ponto de Apoio",JOptionPane.OK_CANCEL_OPTION);

                                            if (optiosPontoAju == JOptionPane.OK_OPTION){
                                                pontoApoio.setPontoNome(entradaNome.getText());
                                                pontoApoio.setTelefonePont(entradaTele.getText());
                                                pontoApoio.setCapacidade(entradaCapa.getText());
                                                pontoApoio.setDescricaoPonto(entradaDes.getText());

                                                EnderecoPonto endereco = EnderecoPonto.buscarEnderecoPorCEP(entradaCEP.getText());
                                                EnderecoPonto coordenadas = EnderecoPonto.buscarCoordenadas( endereco.getCep()+ ", " + endereco.getLogradouro() + ", " + endereco.getLocalidade());

                                                endereco.setNumero(entradaNu.getText());

                                                JPanel ajudaConfirma = new JPanel(new GridBagLayout());

                                                gbc.gridx=0;gbc.gridy=0;
                                                ajudaConfirma.add(new JLabel("Status:  " + pontoApoio.status()),gbc);

                                                gbc.gridy=1;
                                                ajudaConfirma.add(new JLabel("Nome:  "+pontoApoio.getPontoNome()),gbc);

                                                gbc.gridy=2;
                                                ajudaConfirma.add(new JLabel("Telefone:  "+pontoApoio.getTelefonePont()),gbc);

                                                gbc.gridy=3;
                                                ajudaConfirma.add(new JLabel("Capacidade:  "+pontoApoio.getCapacidade()),gbc);

                                                gbc.gridy=4;
                                                ajudaConfirma.add(new JLabel("Descrição:  "),gbc);

                                                gbc.gridy=5;
                                                ajudaConfirma.add(new JLabel(pontoApoio.getDescricaoPonto()),gbc);

                                                gbc.gridy=6;
                                                ajudaConfirma.add(new JLabel(" "),gbc);

                                                gbc.gridy = 7;
                                                gbc.gridwidth = 4;
                                                gbc.fill = GridBagConstraints.HORIZONTAL;


                                                if (coordenadas != null){
                                                    String info = String.format("%s, %s, %s, %s, %s, %s",
                                                            endereco.getLogradouro(),
                                                            endereco.getLocalidade(),
                                                            endereco.getBairro(),
                                                            endereco.getCep(),
                                                            coordenadas.getLatitude(),
                                                            coordenadas.getLongitude()
                                                    );
                                                    ajudaConfirma.add(new JLabel(info), gbc);

                                                    Object[] btAjuda = {"Confirmar","Fazer Novamente"};
                                                    int painelAjudaConfirma = JOptionPane.showOptionDialog(null,ajudaConfirma,"Confirmar informações",JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, btAjuda,btAjuda[0]);

                                                    if (painelAjudaConfirma == 0){
                                                        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO_PONTO, true))) {
                                                            bw.write(ap.status()+ ":" +pontoApoio.getPontoNome() + ":" + pontoApoio.getTelefonePont() + ":" + pontoApoio.getCapacidade() + ":" + pontoApoio.getDescricaoPonto() + ":" + endereco.getLogradouro() + ":" + endereco.getLocalidade() + ":" + endereco.getBairro() + ":" + endereco.getCep() + ":" + coordenadas.getLatitude() + ":" + coordenadas.getLongitude());
                                                            bw.newLine();
                                                        } catch (IOException e) {
                                                            JOptionPane.showMessageDialog(null, "Erro ao registrar ponto de apoio.");
                                                        }
                                                    }
                                                    repetirPonto = "não";
                                                    repetir = "não";
                                                }
                                            }else {
                                                repetirPonto = "não";
                                                repetir = "não";
                                            }
                                        } catch (Exception e) {
                                            System.out.println("Erro ocorrido: " + e.getMessage());
                                            throw e;
                                        }
                                }else {
                                    repetir= "não";
                                }


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
                            usu.setNome(eNome.getText());
                            usu.calcularIda(eDtNascimento.getText());
                            usu.setCpf(eCpf.getText());
                            usu.setEmail(eEmail.getText());
                            usu.setTelefone(eTelefone.getText());
                            usu.setSenha(eSenha.getText());

                            // Verifica se o CPF já está cadastrado
                            boolean cpfExiste = false;
                            try (BufferedReader br = new BufferedReader(new FileReader("usuarios.txt"))) {
                                String linha;
                                while ((linha = br.readLine()) != null) {
                                    String[] infoUser = linha.split(":");
                                    if (infoUser.length >= 5 && infoUser[2].equals(usu.getCpf())) {
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
                            if (!usu.getSenha().equals(eSenhaConfirma.getText())) {
                                JOptionPane.showMessageDialog(null, "As senhas não conferem. Por favor, confirme corretamente.");
                                return;
                            }

                            //salva o usuário
                            try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO_USUARIOS, true))) {
                                bw.write(usu.getNome() + ":" + usu.getIdade() + ":" + usu.getCpf() + ":" + usu.getEmail() + ":" + usu.getTelefone() + ":" + usu.getSenha());
                                bw.newLine();
                                JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
                            } catch (IOException e) {
                                JOptionPane.showMessageDialog(null, "Erro ao cadastrar usuário.");
                            }

                        }else{
                            repetir = "não";
                        }
                        break;
                    default:
                        repetir = "não";
                        break;
                }
            } catch (Exception e) {
                Object[] bt = {"Sim","Não"};
                int repeticao = JOptionPane.showOptionDialog(null, "Ocorreu um erro ao tentar realizar o cadastro. Gostaria de tente novamente?", "Cadastro", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, bt,bt[0]);
                if (repeticao==1 ||repeticao == -1){
                    repetir = "não";
                }
            }
    }
}
