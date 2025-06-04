package arca.main;

import javax.swing.*;
import java.awt.*;

public class Teste {
    public static void main(String[] args) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 2, 2, 2);
        gbc.anchor = GridBagConstraints.WEST;
        try {
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

            }else {

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
