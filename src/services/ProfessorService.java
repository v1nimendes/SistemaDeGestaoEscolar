package services;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import entities.Professor;
import entities.Turma;

public class ProfessorService {
	
	public static void adicionarProfessor(Scanner sc, List<Turma> turmas) {
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

				Turma turma = TurmaService.encontrarOuCriarTurma(nomeTurma, turmas);
				turma.adicionarProfessor(professor);
				professor.adicionarTurma(turma);
			}

			System.out.println("Professor adicionado com sucesso!");

			System.out.println("\nDeseja adicionar outro Professor? (Sim/Nao)");
			resp = sc.next();
		} while (resp.equalsIgnoreCase("sim"));
	}
	
	public static void listarProfessores(Scanner sc, List<Turma> turmas) {
		String resp = "nao";
		do {
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
			System.out.print("\nDeseja realizar uma nova consulta? (Sim/Nao)");
			resp = sc.next();
		}while(resp.equalsIgnoreCase("sim"));
		
	}

}
