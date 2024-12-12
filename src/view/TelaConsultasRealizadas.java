package view;

import model.Consulta;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TelaConsultasRealizadas extends JFrame {
    private JTable tabela;

    public TelaConsultasRealizadas(List<Consulta> consultasRealizadas) {
        setTitle("Consultas Realizadas");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(800, 600);

        JLabel titulo = new JLabel("Consultas Realizadas", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        add(titulo, BorderLayout.NORTH);

        String[] colunas = {"Paciente", "Data"};
        Object[][] dados = new Object[consultasRealizadas.size()][2];

        for (int i = 0; i < consultasRealizadas.size(); i++) {
            Consulta consulta = consultasRealizadas.get(i);
            dados[i][0] = consulta.getPaciente().getNome();
            dados[i][1] = consulta.getDataConsulta();
        }

        tabela = new JTable(dados, colunas);
        JScrollPane scrollPane = new JScrollPane(tabela);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void atualizarTabela(List<Consulta> consultasRealizadas) {
        String[] colunas = {"Paciente", "Data"};
        Object[][] dados = new Object[consultasRealizadas.size()][2];

        for (int i = 0; i < consultasRealizadas.size(); i++) {
            Consulta consulta = consultasRealizadas.get(i);
            dados[i][0] = consulta.getPaciente().getNome();
            dados[i][1] = consulta.getDataConsulta();
        }

        tabela.setModel(new javax.swing.table.DefaultTableModel(dados, colunas));
    }
}
