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
				adicionarProfessor(sc, turmas);
				break;
			case 2:
				adicionarAluno(sc, turmas);
				break;
			case 3:
				listarAlunosPorTurma(sc, turmas);
				break;
			case 4:
				procurarAlunoPorMatricula(sc, turmas);
				break;
			case 5:
				listarProfessores(turmas);
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

	private static void adicionarProfessor(Scanner sc, List<Turma> turmas) {
		String resp = "nao";
		do {
			System.out.print("\nDigite o nome do professor: ");
			String nome = sc.next();
			sc.nextLine();

			System.out.print("Digite a matéria que o professor leciona: ");
			String materia = sc.nextLine();

			Professor professor = new Professor(nome, materia);

			System.out.print("Informe em quantas turmas o professor leciona: ");
			int x = sc.nextInt();
			sc.nextLine();

			for (int i = 0; i < x; i++) {
				System.out.print("Digite o nome da turma: ");
				String nomeTurma = sc.nextLine();

				Turma turma = encontrarOuCriarTurma(nomeTurma, turmas);
				turma.adicionarProfessor(professor);
				professor.adicionarTurma(turma);
			}

			System.out.println("Professor adicionado com sucesso!");

			System.out.println("\nDeseja adicionar outro Professor? (Sim/Nao)");
			resp = sc.next();
		} while (resp.equalsIgnoreCase("sim"));
	}

	private static void adicionarAluno(Scanner sc, List<Turma> turmas) {
		String resp = "nao";
		do {
			System.out.print("\nDigite o nome do aluno: ");
			String nome = sc.next();
			sc.nextLine();

			System.out.print("Digite a matricula do aluno: ");
			int matricula = sc.nextInt();

			sc.nextLine();
			System.out.print("Digite o nome da turma: ");
			String nomeTurma = sc.nextLine();

			Turma turma = encontrarOuCriarTurma(nomeTurma, turmas);

			Aluno aluno = new Aluno(nome, matricula, turma);

			for (Professor professor : turma.getProfessores()) {
				System.out.println("Digite as três notas para a matéria " + professor.getMateria() + ":");
				for (int i = 0; i < 3; i++) {
					double nota = sc.nextDouble();
					aluno.adicionarNota(professor.getMateria(), nota);
				}
			}

			System.out.println("Digite o número de faltas do aluno:");
			int faltas = sc.nextInt();
			aluno.adicionarFalta(faltas);

			turma.adicionarAluno(aluno);

			System.out.println("Aluno adicionado com sucesso!");

			System.out.println("\nDeseja adicionar outro Aluno? (Sim/Nao)");
			resp = sc.next();
		} while (resp.equalsIgnoreCase("sim"));
	}

	private static void listarAlunosPorTurma(Scanner sc, List<Turma> turmas) {
		String resp = "nao";
		do {
			System.out.println("Digite o nome da turma:");
			String nomeTurma = sc.nextLine();

			Turma turma = encontrarTurma(nomeTurma, turmas);
			if (turma == null) {
				System.out.println("Turma não encontrada.");
				return;
			}

			List<Aluno> alunos = turma.getAlunos();
			if (alunos.isEmpty()) {
				System.out.println("Não há alunos nesta turma.");
				return;
			}

			for (Aluno aluno : alunos) {
				System.out.println("Nome do aluno: " + aluno.getNome());
				for (Materia materia : aluno.getMaterias()) {
					System.out.println("Média em " + materia.getNome() + ": " + materia.calcularMedia());
				}
				System.out.println("Status: " + aluno.getStatus());
			}

			System.out.print("\nDeseja realizar uma nova consulta? (Sim/Nao)");
			resp = sc.next();
		} while (resp.equalsIgnoreCase("sim"));

	}

	private static void procurarAlunoPorMatricula(Scanner sc, List<Turma> turmas) {
		String resp = "nao";
		do {
			System.out.print("Digite o número de matricula: ");
			int matricula = sc.nextInt();

			for (Turma turma : turmas) {
				for (Aluno aluno : turma.getAlunos()) {
					if (aluno.getMatricula() == matricula) {
						System.out.println("Aluno encontrado:");
						System.out.println("Nome: " + aluno.getNome() + " | Matrícula: " + aluno.getMatricula());
						System.out.println("Turma: " + aluno.getTurma().getNome());
					}
				}
			}
			System.out.println("Aluno com matrícula " + matricula + " não encontrado.");

			System.out.print("\nDeseja realizar uma nova consulta? (Sim/Nao)");
			resp = sc.next();
		} while (resp.equalsIgnoreCase("sim"));
	}

	private static void listarProfessores(List<Turma> turmas) {
		Set<Professor> professoresSet = new HashSet<>();

		for (Turma turma : turmas) {
			professoresSet.addAll(turma.getProfessores());
		}

		for (Professor professor : professoresSet) {
			System.out.print("Nome: " + professor.getNome() + " | Matéria: " + professor.getMateria() + " | Turmas: ");
			for (Turma t : professor.getTurmas()) {
				System.out.print(t.getNome() + " ");
			}
			System.out.println();
		}
	}

	private static Turma encontrarOuCriarTurma(String nomeTurma, List<Turma> turmas) {
		for (Turma turma : turmas) {
			if (turma.getNome().equals(nomeTurma)) {
				return turma;
			}
		}
		Turma novaTurma = new Turma(nomeTurma);
		turmas.add(novaTurma);
		return novaTurma;
	}

	private static Turma encontrarTurma(String nomeTurma, List<Turma> turmas) {
		for (Turma turma : turmas) {
			if (turma.getNome().equals(nomeTurma)) {
				return turma;
			}
		}
		return null;
	}
}