package view;

import model.Consulta;
import model.Paciente;
import service.ConsultaService;
import service.EnderecoService;
import service.PacienteService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TelaGerenciarPacientes extends JFrame {
    private DefaultTableModel modeloTabela;
    private JTable tabela;
    private List<Paciente> pacientes;
    private TelaInicial telaInicial;

    public TelaGerenciarPacientes(TelaInicial telaInicial) {
        this.telaInicial = telaInicial;
        setTitle("Gerenciar Pacientes");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        // Carregar a lista de pacientes do serviço
        pacientes = PacienteService.listarPacientes();

        JLabel titulo = new JLabel("Pacientes", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        add(titulo, BorderLayout.NORTH);

        // Colunas da tabela
        String[] colunas = {"Nome", "Email", "Telefone", "Endereço"};

        // Dados da tabela
        modeloTabela = new DefaultTableModel(colunas, 0);
        preencherTabela();

        tabela = new JTable(modeloTabela);
        JScrollPane scrollPane = new JScrollPane(tabela);
        add(scrollPane, BorderLayout.CENTER);

        JPanel botoes = new JPanel();
        botoes.setLayout(new GridLayout(1, 3));

        JButton btnAdicionar = new JButton("Adicionar");
        btnAdicionar.addActionListener(e -> adicionarPaciente());
        botoes.add(btnAdicionar);

        JButton btnEditar = new JButton("Editar");
        btnEditar.addActionListener(e -> editarPaciente(tabela.getSelectedRow()));
        botoes.add(btnEditar);

        JButton btnRemover = new JButton("Remover");
        btnRemover.addActionListener(e -> removerPaciente(tabela.getSelectedRow()));
        botoes.add(btnRemover);

        add(botoes, BorderLayout.SOUTH);
    }

    private void preencherTabela() {
        modeloTabela.setRowCount(0); // Limpa a tabela antes de preencher
        for (Paciente paciente : pacientes) {
            modeloTabela.addRow(new Object[]{
                    paciente.getNome(),
                    paciente.getEmail(),
                    paciente.getTelefone(),
                    paciente.getEndereco().toString()
            });
        }
    }

    private void adicionarPaciente() {
        String nome = JOptionPane.showInputDialog("Nome:");
        String email = JOptionPane.showInputDialog("Email:");
        String telefone = JOptionPane.showInputDialog("Telefone:");
        String cep = JOptionPane.showInputDialog("CEP:");

        if (nome != null && email != null && telefone != null && cep != null) {
            Paciente paciente = new Paciente(pacientes.size() + 1, nome, email, telefone, EnderecoService.buscarEnderecoPorCep(cep));
            PacienteService.adicionarPaciente(paciente);
            preencherTabela();
            JOptionPane.showMessageDialog(this, "Paciente adicionado com sucesso!");

            // Perguntar se deseja adicionar uma consulta para o paciente
            int resposta = JOptionPane.showConfirmDialog(this, "Deseja adicionar uma consulta para este paciente?", "Adicionar Consulta", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.YES_OPTION) {
                adicionarConsulta(paciente);
            }
        }
    }

    private void adicionarConsulta(Paciente paciente) {
        String dataConsultaStr = JOptionPane.showInputDialog("Data da Consulta (dd/MM/yyyy):");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataConsulta = LocalDate.parse(dataConsultaStr, formatter);
        boolean realizada = JOptionPane.showConfirmDialog(this, "Consulta realizada?", "Status da Consulta", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
        Consulta consulta = new Consulta(ConsultaService.listarConsultasPendentes().size() + ConsultaService.listarConsultasRealizadas().size() + 1, paciente, dataConsulta);
        if (realizada) {
            consulta.setRealizada(true);
            ConsultaService.marcarConsultaComoRealizada(consulta);
        } else {
            ConsultaService.adicionarConsulta(consulta);
        }
        telaInicial.atualizarTabelasConsultas(); // Atualizar as tabelas de consultas na TelaInicial
        JOptionPane.showMessageDialog(this, "Consulta adicionada com sucesso!");
    }

    private void editarPaciente(int index) {
        if (index >= 0 && index < pacientes.size()) {
            Paciente paciente = pacientes.get(index);
            String nome = JOptionPane.showInputDialog("Nome:", paciente.getNome());
            String email = JOptionPane.showInputDialog("Email:", paciente.getEmail());
            String telefone = JOptionPane.showInputDialog("Telefone:", paciente.getTelefone());
            String cep = JOptionPane.showInputDialog("CEP:", paciente.getEndereco().getCep());
            if (nome != null && email != null && telefone != null && cep != null) {
                paciente.setNome(nome);
                paciente.setEmail(email);
                paciente.setTelefone(telefone);
                paciente.setEndereco(EnderecoService.buscarEnderecoPorCep(cep));
                PacienteService.editarPaciente(index, paciente);
                preencherTabela();
                JOptionPane.showMessageDialog(this, "Paciente atualizado com sucesso!");
            }
        }
    }

    private void removerPaciente(int index) {
        if (index >= 0 && index < pacientes.size()) {
            PacienteService.removerPaciente(index);
            pacientes.remove(index);
            preencherTabela();
            JOptionPane.showMessageDialog(this, "Paciente removido com sucesso!");
        }
    }
}
