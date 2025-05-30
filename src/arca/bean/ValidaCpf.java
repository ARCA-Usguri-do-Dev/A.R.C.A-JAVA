package arca.bean;

public class ValidaCpf{
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

}
