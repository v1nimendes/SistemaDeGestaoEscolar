package entities;

import java.util.ArrayList;
import java.util.List;

public class Professor {
	private String nome;
	private String materia;
	private List<Turma> turmas;
	
	public Professor(String nome, String materia) {
		this.nome = nome;
		this.materia = materia;
		this.turmas = new ArrayList<>();
	}

	public String getNome() {
		return nome;
	}

	public String getMateria() {
		return materia;
	}

	public List<Turma> getTurmas() {
		return turmas;
	}
	
	public void adicionarTurma(Turma turma) {
		turmas.add(turma);
	}

}
