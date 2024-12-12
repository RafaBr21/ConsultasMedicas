package model;

import java.time.LocalDate;

public class Consulta {
    private int id;
    private Paciente paciente;
    private LocalDate dataConsulta;
    private boolean realizada;

    public Consulta(int id, Paciente paciente, LocalDate dataConsulta) {
        this.id = id;
        this.paciente = paciente;
        this.dataConsulta = dataConsulta;
        this.realizada = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public LocalDate getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(LocalDate dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public boolean isRealizada() {
        return realizada;
    }

    public void setRealizada(boolean realizada) {
        this.realizada = realizada;
    }

    @Override
    public String toString() {
        return "Consulta com " + paciente.getNome() + " em " + dataConsulta +
                " - " + (realizada ? "Realizada" : "Pendente");
    }
}
