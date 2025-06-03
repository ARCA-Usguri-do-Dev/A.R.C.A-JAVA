package arca.bean;

import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class EnderecoPonto {
    //atributos
    private String cep ;
    private String logradouro;
    private String numero;
    private String localidade;
    //localidade
    private String latitude;
    private String longitude;

    //construtores
    public EnderecoPonto() {
    }

    //getter/setter
    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getLocalidade() {
        return localidade;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public static EnderecoPonto buscarEnderecoPorCEP(String cep) throws IOException {
        String urlString = "https://viacep.com.br/ws/" + cep + "/json/";
        URL url = new URL(urlString);

        HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
        conexao.setRequestMethod("GET");

        try (InputStreamReader reader = new InputStreamReader(conexao.getInputStream())) {
            Gson gson = new Gson();
            return gson.fromJson(reader, EnderecoPonto.class);
        }
    }

    public static EnderecoPonto buscarCoordenadas(String endereco) {
        String API_KEY = "c79f64910aa246a1926482c0db64a82f";
        String API_URL = "https://api.opencagedata.com/geocode/v1/json";

        try {
            String enderecoCodificado = URLEncoder.encode(endereco, StandardCharsets.UTF_8);
            String query = API_URL + "?q=" + enderecoCodificado + "&key=" + API_KEY;
            URI uri = new URI(query);
            URL url = uri.toURL();

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            int resposta = conn.getResponseCode();
            if (resposta == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder conteudo = new StringBuilder();
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    conteudo.append(inputLine);
                }

                in.close();
                conn.disconnect();

                JSONObject json = new JSONObject(conteudo.toString());
                JSONArray resultados = json.getJSONArray("results");

                if (!resultados.isEmpty()) {
                    JSONObject geometry = resultados.getJSONObject(0).getJSONObject("geometry");
                    double lat = geometry.getDouble("lat");
                    double lng = geometry.getDouble("lng");

                    // Criar objeto EnderecoPonto e setar valores
                    EnderecoPonto ponto = new EnderecoPonto();
                    ponto.setLatitude(String.valueOf(lat));
                    ponto.setLongitude(String.valueOf(lng));

                    return ponto;
                } else {
                    System.out.println("Nenhum resultado encontrado.");
                }

            } else {
                System.out.println("Erro na requisição: " + resposta);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao encontrar coordenadas.");
        }
        return null;
    }

}
