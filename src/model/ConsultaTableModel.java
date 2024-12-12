package model;

import model.Consulta;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ConsultaTableModel extends AbstractTableModel {

    private List<Consulta> consultas;
    private String[] colunas = {"ID", "Data", "Paciente", "Status"};

    public ConsultaTableModel(List<Consulta> consultas) {
        this.consultas = consultas;
    }

    @Override
    public int getRowCount() {
        return consultas.size();
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
        Consulta consulta = consultas.get(rowIndex);
        switch (columnIndex) {
            case 0:

                return rowIndex + 1;
            case 1:
                return consulta.getDataConsulta();
            case 2:
                return consulta.getPaciente().getNome();
            case 3:

                return consulta.isRealizada() ? "Realizada" : "Pendente";
            default:
                return null;
        }
    }
}
