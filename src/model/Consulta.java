package model;

import java.util.Date;

public class Consulta {

    private Paciente paciente;
    private Date dataConsulta;
    private Date dataSolicitacao;
    private Boolean realizada;

    public Consulta(Paciente paciente, Date dataConsulta, Date dataSolicitacao, Boolean realizada) {
        this.paciente = paciente;
        this.dataConsulta = dataConsulta;
        this.dataSolicitacao = dataSolicitacao;
        this.realizada = realizada;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Date getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(Date dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public Date getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(Date dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public Boolean getRealizada() {
        return realizada;
    }

    public void setRealizada(Boolean realizada) {
        this.realizada = realizada;
    }
}
