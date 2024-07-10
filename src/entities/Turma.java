package entities;

import java.util.ArrayList;
import java.util.List;

public class Turma {
	private String nome;
	private List<Aluno> alunos;
	private List<Professor> professores;
	
	public Turma(String nome) {
		this.nome = nome;
		this.alunos = new ArrayList<>();
		this.professores = new ArrayList<>();
	}
	
	public void adicionarAluno(Aluno aluno) {
		alunos.add(aluno);
	}
	
	public void adicionarProfessor(Professor professor) {
		professores.add(professor);
	}
	public String getNome() {
		return nome;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public List<Professor> getProfessores() {
		return professores;
	}
}
