package view;

import model.Consulta;
import service.ConsultaService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TelaInicial extends JFrame {

    private ConsultaService consultaService;

    public TelaInicial() {
        consultaService = new ConsultaService();

        setTitle("Sistema de Consultas Médicas");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel panelNavegacao = new JPanel();
        panelNavegacao.setLayout(new GridLayout(1, 4));

        JButton btnConsultasPendentes = new JButton("Consultas Pendentes");
        JButton btnConsultasRealizadas = new JButton("Consultas Realizadas");
        JButton btnGerenciarPacientes = new JButton("Gerenciar Pacientes");
        JButton btnSair = new JButton("Sair");

        panelNavegacao.add(btnConsultasPendentes);
        panelNavegacao.add(btnConsultasRealizadas);
        panelNavegacao.add(btnGerenciarPacientes);
        panelNavegacao.add(btnSair);

        panel.add(panelNavegacao, BorderLayout.NORTH);

        btnConsultasPendentes.addActionListener(e -> {
            List<Consulta> consultasPendentes = consultaService.listarConsultasPendentes();
            TelaConsultasPendentes telaConsultasPendentes = new TelaConsultasPendentes(consultasPendentes);
            telaConsultasPendentes.setVisible(true);
        });

        btnConsultasRealizadas.addActionListener(e -> {
            List<Consulta> consultasRealizadas = consultaService.listarConsultasRealizadas();
            TelaConsultasRealizadas telaConsultasRealizadas = new TelaConsultasRealizadas(consultasRealizadas);
            telaConsultasRealizadas.setVisible(true);
        });

        btnGerenciarPacientes.addActionListener(e -> {
            SwingUtilities.invokeLater(() -> {
                TelaGerenciarPacientes telaGerenciarPacientes = new TelaGerenciarPacientes(this);
                telaGerenciarPacientes.setVisible(true);
            });
        });

        btnSair.addActionListener(e -> System.exit(0));

        getContentPane().add(panel, BorderLayout.CENTER);

        setVisible(true);
    }


    public void atualizarTabelasConsultas() {
        List<Consulta> consultasPendentes = consultaService.listarConsultasPendentes();
        List<Consulta> consultasRealizadas = consultaService.listarConsultasRealizadas();

        // Atualizar as tabelas existentes, caso necessário
        for (Frame frame : Frame.getFrames()) {
            if (frame instanceof TelaConsultasPendentes) {
                ((TelaConsultasPendentes) frame).atualizarTabela(consultasPendentes);
            } else if (frame instanceof TelaConsultasRealizadas) {
                ((TelaConsultasRealizadas) frame).atualizarTabela(consultasRealizadas);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TelaInicial::new);
    }
}
