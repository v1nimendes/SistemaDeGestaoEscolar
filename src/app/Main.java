package app;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import entities.Aluno;
import entities.Materia;
import entities.Professor;
import entities.Turma;
import services.AlunoService;
import services.ProfessorService;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		List<Turma> turmas = new ArrayList<>();

		while (true) {
			System.out.println("-----------------------------------");
			System.out.println("        Escolha uma opção");
			System.out.println("-----------------------------------");
			System.out.println("1- Adicionar Professor");
			System.out.println("2- Adicionar Aluno");
			System.out.println("3- Listar Alunos por turma");
			System.out.println("4- Procurar Aluno por matricula");
			System.out.println("5- Listar Professores");
			System.out.println("-----------------------------------");
			System.out.println("6- Sair");
			System.out.println("-----------------------------------");
			int op = sc.nextInt();
			sc.nextLine();

			switch (op) {
			case 1:
				ProfessorService.adicionarProfessor(sc, turmas);
				break;
			case 2:
				AlunoService.adicionarAluno(sc, turmas);
				break;
			case 3:
				AlunoService.listarAlunosPorTurma(sc, turmas);
				break;
			case 4:
				AlunoService.procurarAlunoPorMatricula(sc, turmas);
				break;
			case 5:
				ProfessorService.listarProfessores(sc, turmas);
				break;
			case 6:
				System.out.println("Encerrando programa...");
				sc.close();
				return;
			default:
				System.out.println("Opção inválida. Tente novamente!");
			}
		}
	}
}