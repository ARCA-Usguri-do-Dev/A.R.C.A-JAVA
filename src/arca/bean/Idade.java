package arca.bean;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Idade extends Usuario{
    public void calcularIda(String dataNasciimento){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataAtual = LocalDate.now();
        LocalDate dataFormatada = LocalDate.parse(dataNasciimento,dtf);
        Period idade = Period.between(dataFormatada, dataAtual);
        setIdade(String.valueOf(idade.getYears()));
    }
}
