package entities;

import java.util.ArrayList;
import java.util.List;

public class Materia {
    private String nome;
    private List<Double> notas;

    public Materia(String nome) {
        this.nome = nome;
        this.notas = new ArrayList<>();
    }

    public void adicionarNota(double nota) {
        notas.add(nota);
    }

    public double calcularMedia() {
        double soma = 0;
        for (double nota : notas) {
            soma += nota;
        }
        return soma / notas.size();
    }

    public String getNome() {
        return nome;
    }

    public List<Double> getNotas() {
        return notas;
    }
}
