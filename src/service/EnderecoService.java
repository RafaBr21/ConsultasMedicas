package service;

import model.Endereco;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class EnderecoService {
    public static Endereco buscarEnderecoPorCep(String cep) {
        try {
            String urlString = "https://viacep.com.br/ws/" + cep + "/json/";
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
            StringBuilder response = new StringBuilder();
            String linha;

            while ((linha = br.readLine()) != null) {
                response.append(linha);
            }

            JSONObject json = new JSONObject(response.toString());

            if (json.has("erro")) {
                System.err.println("Erro: CEP não encontrado - " + cep);
                return new Endereco("Desconhecida", "Desconhecida", "0", cep); // Retornará um endereço padrão
            }

            return new Endereco(
                    json.getString("localidade"),
                    json.getString("logradouro"),
                    "",  // Aqui o número será preenchido manualmente
                    cep
            );

        } catch (Exception e) {
            e.printStackTrace();
            return new Endereco("Erro", "Erro ao buscar endereço", "0", cep);
        }
    }
}
