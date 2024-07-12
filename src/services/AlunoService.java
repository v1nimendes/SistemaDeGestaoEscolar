package services;

import java.util.List;
import java.util.Scanner;

import entities.Aluno;
import entities.Materia;
import entities.Professor;
import entities.Turma;

public class AlunoService {
	
	public static void adicionarAluno(Scanner sc, List<Turma> turmas) {
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

			Turma turma = TurmaService.encontrarOuCriarTurma(nomeTurma, turmas);

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
	
	public static void listarAlunosPorTurma(Scanner sc, List<Turma> turmas) {
		String resp = "nao";
		do {
			System.out.println("Digite o nome da turma:");
			String nomeTurma = sc.nextLine();

			Turma turma = TurmaService.encontrarTurma(nomeTurma, turmas);
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
	
	public static void procurarAlunoPorMatricula(Scanner sc, List<Turma> turmas) {
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
}
