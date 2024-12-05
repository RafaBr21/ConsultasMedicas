package view;

import model.Consulta;
import model.Paciente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class TelaExibicao extends JFrame {
    private ArrayList<Consulta> consultas;
    private JTabbedPane tabbedPane;

    public TelaExibicao(ArrayList<Consulta> consultas) {
        this.consultas = consultas;
        setTitle("Consultas Médicas");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Criar um painel de abas (JTabbedPane)
        tabbedPane = new JTabbedPane();

        // Adicionar a aba de consultas a serem realizadas
        JPanel panelConsultasPendentes = new JPanel(new BorderLayout());
        String[] colunas = {"Paciente", "Data da Consulta", "Realizada"};
        Object[][] dadosPendentes = new Object[consultas.size()][3];

        int i = 0;
        for (Consulta consulta : consultas) {
            if (!consulta.getRealizada()) {
                dadosPendentes[i][0] = consulta.getPaciente().getNome();
                dadosPendentes[i][1] = consulta.getDataConsulta();
                dadosPendentes[i][2] = consulta.getRealizada() ? "Sim" : "Não";
                i++;
            }
        }
        JTable tabelaPendentes = new JTable(dadosPendentes, colunas);
        JScrollPane scrollPanePendentes = new JScrollPane(tabelaPendentes);
        panelConsultasPendentes.add(scrollPanePendentes, BorderLayout.CENTER);
        tabbedPane.addTab("Consultas Pendentes", panelConsultasPendentes);

        // Adicionando a aba de consultas realizadas
        JPanel panelConsultasRealizadas = new JPanel(new BorderLayout());
        Object[][] dadosRealizadas = new Object[consultas.size()][3];

        i = 0;
        for (Consulta consulta : consultas) {
            if (consulta.getRealizada()) {
                dadosRealizadas[i][0] = consulta.getPaciente().getNome();
                dadosRealizadas[i][1] = consulta.getDataConsulta();
                dadosRealizadas[i][2] = consulta.getRealizada() ? "Sim" : "Não";
                i++;
            }
        }
        JTable tabelaRealizadas = new JTable(dadosRealizadas, colunas);
        JScrollPane scrollPaneRealizadas = new JScrollPane(tabelaRealizadas);
        panelConsultasRealizadas.add(scrollPaneRealizadas, BorderLayout.CENTER);
        tabbedPane.addTab("Consultas Realizadas", panelConsultasRealizadas);

        // Adicionando a aba de pacientes
        JPanel panelPacientes = new JPanel(new BorderLayout());
        String[] colunasPacientes = {"Paciente", "Email", "Telefone"};
        Object[][] dadosPacientes = new Object[consultas.size()][3];

        i = 0;
        for (Consulta consulta : consultas) {
            dadosPacientes[i][0] = consulta.getPaciente().getNome();
            dadosPacientes[i][1] = consulta.getPaciente().getEmail();
            dadosPacientes[i][2] = consulta.getPaciente().getTelefone();
            i++;
        }
        JTable tabelaPacientes = new JTable(dadosPacientes, colunasPacientes);
        JScrollPane scrollPanePacientes = new JScrollPane(tabelaPacientes);
        panelPacientes.add(scrollPanePacientes, BorderLayout.CENTER);
        tabbedPane.addTab("Pacientes", panelPacientes);

        // Adicionando evento de clique na tabela de pacientes
        tabelaPacientes.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                int linha = tabelaPacientes.getSelectedRow();
                if (linha != -1) {
                    Paciente pacienteSelecionado = consultas.get(linha).getPaciente();
                    String mensagem = "Paciente: " + pacienteSelecionado.getNome() + "\n" +
                            "Email: " + pacienteSelecionado.getEmail() + "\n" +
                            "Telefone: " + pacienteSelecionado.getTelefone() + "\n" +
                            "Endereço: " + pacienteSelecionado.getEndereco().getRua() + ", " +
                            pacienteSelecionado.getEndereco().getNumero() + " - " +
                            pacienteSelecionado.getEndereco().getCidade() + " - " +
                            pacienteSelecionado.getEndereco().getCep();
                    JOptionPane.showMessageDialog(null, mensagem);
                }
            }
        });

        // Adicionando o JTabbedPane ao JFrame
        add(tabbedPane);

        setVisible(true);
    }
}
