import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Matricula {
    private String nome;
    private String tel;
    private String curso;

    public Matricula(String nome, String tel, String curso) {
        this.nome = nome;
        this.tel = tel;
        this.curso = curso;
    }

    public String getNome() {
        return nome;
    }

    public String getTel() {
        return tel;
    }

    public String getCurso() {
        return curso;
    }

    public static void matricula() throws IOException {
        Scanner input = new Scanner(System.in);
        Path arquivo = Paths.get("arquivo", "matricula.txt");

        String nomeRegex = "^[a-zA-ZÀ-ÿ\\s]{5,}$";
        String telRegex = "^\\(?\\d{2}\\)?\\s?9?\\d{4}-?\\d{4}$";
        String[] cursos = {
                "1 - Analise de Desenvolvimento de Sistemas",
                "2 - Ciências da Computação",
                "3 - Engenharia de Software",
                "4 - Engenharia da Computação",
                "5 - Gestão da Tecnologia da Informação"
        };

        if (Files.exists(arquivo)) {
            System.out.println("Arquivo existente!");
            return;
        }

        System.out.println("Deseja realizar uma matricula? S/N");
        String resposta = input.nextLine();

        if (resposta.equalsIgnoreCase("S")) {
            System.out.println("Insira seu nome: ");
            String nomeInput = input.nextLine();
            boolean isNome = nomeInput.matches(nomeRegex);

            System.out.println("Insira seu telefone: ");
            String telInput = input.nextLine();
            boolean isTel = telInput.matches(telRegex);

            if (!isNome || !isTel){
                throw new RuntimeException("Nome ou Telefone incorreto");
            }

            System.out.println("Cursos Disponível: ");
            for (String c : cursos){
                System.out.println(c);
            }

            System.out.println("Escolha um curso: ");
            int cursoInput = input.nextInt();

            if (cursoInput < 0 || cursoInput >= cursos.length){
                System.out.println("Curso invalido");
                return;
            }

            Matricula matricula = new Matricula(nomeInput, telInput, cursos[cursoInput]);

            try (FileWriter fw = new FileWriter(arquivo.toFile())) {
                fw.write("Nome: " + matricula.getNome() + "\n");
                fw.write("Telefone: " + matricula.getTel() + "\n");
                fw.write("Curso: " + matricula.getCurso() + "\n");

            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            System.out.println("Matricula realizada!");

        } else if (resposta.equalsIgnoreCase("N")) {
            System.out.println("Sistema encerrado");
        } else {
            System.out.println("Opção invalida!");

        }


    }

    public static void main(String[] args) throws IOException {
        matricula();
    }
}
