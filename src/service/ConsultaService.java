package service;

import model.Consulta;

import java.util.ArrayList;
import java.util.List;

public class ConsultaService {
    private static List<Consulta> consultasPendentes = new ArrayList<>();
    private static List<Consulta> consultasRealizadas = new ArrayList<>();

    public static void adicionarConsulta(Consulta consulta) {
        if (!consulta.isRealizada()) {
            consultasPendentes.add(consulta);
        } else {
            consultasRealizadas.add(consulta);
        }
    }

    public static List<Consulta> listarConsultasPendentes() {
        return consultasPendentes;
    }

    public static List<Consulta> listarConsultasRealizadas() {
        return consultasRealizadas;
    }

    public static void marcarConsultaComoRealizada(Consulta consulta) {
        if (consultasPendentes.contains(consulta)) {
            consultasPendentes.remove(consulta);
            consulta.setRealizada(true);
            consultasRealizadas.add(consulta);
        } else if (!consultasRealizadas.contains(consulta)) {
            consulta.setRealizada(true);
            consultasRealizadas.add(consulta);
        }
    }
}
