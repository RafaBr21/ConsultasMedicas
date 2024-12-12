package model; // Ajuste o nome do pacote conforme necessário

import model.Paciente;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class PacienteTableModel extends AbstractTableModel {
    private List<Paciente> pacientes;
    private String[] colunas = {"ID", "Nome", "Email", "Telefone", "Endereço"};

    public PacienteTableModel(List<Paciente> pacientes) {
        this.pacientes = pacientes;
    }

    @Override
    public int getRowCount() {
        return pacientes.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return colunas[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Paciente paciente = pacientes.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return paciente.getId();
            case 1:
                return paciente.getNome();
            case 2:
                return paciente.getEmail();
            case 3:
                return paciente.getTelefone();
            case 4:
                return paciente.getEndereco().toString();
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) { // Aqui vai tornar todas as células não editáveis

        return false;
    }

    public void addPaciente(Paciente paciente) {
        pacientes.add(paciente);
        fireTableRowsInserted(pacientes.size() - 1, pacientes.size() - 1);
    }

    public void removePaciente(int rowIndex) {
        pacientes.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public Paciente getPacienteAt(int rowIndex) {
        return pacientes.get(rowIndex);
    }
}
