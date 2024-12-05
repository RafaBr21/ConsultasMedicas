import model.*;
import service.EnderecoService;
import view.TelaExibicao;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        ArrayList<Consulta> consultas = new ArrayList<>();

        try {
            // Criando consultas com endereços buscados pelo ViaCEP
            Endereco endereco1 = EnderecoService.buscarEnderecoPorCep("01001000");
            if (endereco1 != null) endereco1.setNumero("180");
            consultas.add(new Consulta(new Paciente("Carlos Ferreira", "carlos@email.com", "9999-9999", endereco1), new Date(), new Date(), false));

            Endereco endereco2 = EnderecoService.buscarEnderecoPorCep("74890-729");
            if (endereco2 != null) endereco2.setNumero("25");
            consultas.add(new Consulta(new Paciente("Ana Souza", "ana@email.com", "8888-8888", endereco2), new Date(), new Date(), true));

            Endereco endereco3 = EnderecoService.buscarEnderecoPorCep("85866040");
            if (endereco3 != null) endereco3.setNumero("500");
            consultas.add(new Consulta(new Paciente("João Silva", "joao@email.com", "7777-7777", endereco3), new Date(), new Date(), false));

            Endereco endereco4 = EnderecoService.buscarEnderecoPorCep("78142-576");
            if (endereco4 != null) endereco4.setNumero("15");
            consultas.add(new Consulta(new Paciente("Tabata Moreira", "Tabata@gamil.com","4444-4444",endereco4), new Date(),new Date(),true));


        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar endereços: " + e.getMessage());
        }

        // Iniciando a interface gráfica
        SwingUtilities.invokeLater(() -> new TelaExibicao(consultas));
    }
}
