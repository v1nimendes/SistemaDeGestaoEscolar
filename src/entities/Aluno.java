package entities;

import java.util.ArrayList;
import java.util.List;

public class Aluno {
    private String nome;
    private int matricula;
    private List<Materia> materias;
    private int faltas;
    private Turma turma;

    public Aluno(String nome, int matricula, Turma turma) {
        this.nome = nome;
        this.matricula = matricula;
        this.materias = new ArrayList<>();
        this.faltas = 0;
        this.turma = turma;
    }

    public void adicionarNota(String nomeMateria, double nota) {
        Materia materia = encontrarOuCriarMateria(nomeMateria);
        materia.adicionarNota(nota);
    }

    public void adicionarFalta(int faltas) {
        this.faltas += faltas;
    }

    public double calcularMedia(String nomeMateria) {
        Materia materia = encontrarMateria(nomeMateria);
        if (materia == null) return 0.0;
        return materia.calcularMedia();
    }

    public String getStatus() {
        int materiasAbaixoMedia = 0;
        for (Materia materia : materias) {
            if (materia.calcularMedia() < 6) {
                materiasAbaixoMedia++;
            }
        }
        if (materiasAbaixoMedia >= 3 || faltas > 75) {
            return "Reprovado";
        } else if (materiasAbaixoMedia > 0) {
            return "Recuperação";
        } else {
            return "Aprovado";
        }
    }

    private Materia encontrarOuCriarMateria(String nomeMateria) {
        for (Materia materia : materias) {
            if (materia.getNome().equals(nomeMateria)) {
                return materia;
            }
        }
        Materia novaMateria = new Materia(nomeMateria);
        materias.add(novaMateria);
        return novaMateria;
    }

    private Materia encontrarMateria(String nomeMateria) {
        for (Materia materia : materias) {
            if (materia.getNome().equals(nomeMateria)) {
                return materia;
            }
        }
        return null;
    }

    public String getNome() {
        return nome;
    }

    public int getMatricula() {
        return matricula;
    }

    public List<Materia> getMaterias() {
        return materias;
    }

    public int getFaltas() {
        return faltas;
    }

    public Turma getTurma() {
        return turma;
    }
}