package service;

import model.Endereco;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class EnderecoService {
    public static Endereco buscarEnderecoPorCep(String cep) {
        try {
            cep = cep.trim().replaceAll("[^0-9]", "");
            if (cep.isEmpty() || cep.length() != 8) {
                throw new IllegalArgumentException("CEP inválido. Deve conter 8 dígitos numéricos.");
            }

            String urlString = "https://viacep.com.br/ws/" + cep + "/json/";
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String linha;
            while ((linha = br.readLine()) != null) {
                response.append(linha);
            }

            JSONObject json = new JSONObject(response.toString());

            if (json.has("erro")) {
                throw new IllegalArgumentException("CEP não encontrado.");
            }

            return new Endereco(
                    json.getString("localidade"),
                    json.getString("logradouro"),
                    "",
                    cep
            );

        } catch (IllegalArgumentException e) {
            System.err.println("Erro: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro ao buscar o endereço: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
