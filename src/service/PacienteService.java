package service;

import model.Paciente;

import java.util.ArrayList;
import java.util.List;

public class PacienteService {
    private static List<Paciente> pacientes = new ArrayList<>();


    public static void adicionarPaciente(Paciente paciente) {
        pacientes.add(paciente);
    }


    public static void editarPaciente(int index, Paciente paciente) {
        if (index >= 0 && index < pacientes.size()) {
            pacientes.set(index, paciente);
        }
    }


    public static void removerPaciente(int index) {
        if (index >= 0 && index < pacientes.size()) {
            pacientes.remove(index);
        }
    }


    public static List<Paciente> listarPacientes() {
        return pacientes;
    }


    public static Paciente buscarPacientePorNome(String nome) {
        for (Paciente paciente : pacientes) {
            if (paciente.getNome().equalsIgnoreCase(nome)) {
                return paciente;
            }
        }
        return null;
    }
}
