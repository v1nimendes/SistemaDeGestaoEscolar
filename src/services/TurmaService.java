package services;

import java.util.List;

import entities.Turma;

public class TurmaService {
	
	public static Turma encontrarOuCriarTurma(String nomeTurma, List<Turma> turmas) {
		for (Turma turma : turmas) {
			if (turma.getNome().equals(nomeTurma)) {
				return turma;
			}
		}
		Turma novaTurma = new Turma(nomeTurma);
		turmas.add(novaTurma);
		return novaTurma;
	}

	public static Turma encontrarTurma(String nomeTurma, List<Turma> turmas) {
		for (Turma turma : turmas) {
			if (turma.getNome().equals(nomeTurma)) {
				return turma;
			}
		}
		return null;
	}

}
