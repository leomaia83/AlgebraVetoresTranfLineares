import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        LinearAlgebra la = new LinearAlgebra();
        Transformations tf = new Transformations();
        int opcao;

        do {
            System.out.println("\n==============================");
            System.out.println("MENU PRINCIPAL");
            System.out.println("1 - Trabalhar com Matrizes");
            System.out.println("2 - Trabalhar com Vetores");
            System.out.println("3 - Trabalhar com Álgebra Linear");
            System.out.println("4 - Transformações Geométricas");
            System.out.println("0 - Sair");
            System.out.println("==============================");
            System.out.print("Escolha uma opção: ");
            opcao = s.nextInt();

            switch (opcao) {
                case 1: menuMatriz(s); break;
                case 2: menuVetor(s); break;
                case 3: menuAlgebraLinear(s, la); break;
                case 4: menuTransformacoes(s, tf); break;
                case 0: System.out.println("Programa encerrado."); break;
                default: System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
        s.close();
    }

    public static void menuTransformacoes(Scanner s, Transformations tf) {
        int operacao;
        do {
            System.out.println("\n==============================");
            System.out.println("TRANSFORMAÇÕES GEOMÉTRICAS (2D/3D)");
            System.out.println("1 - Translação");
            System.out.println("2 - Rotação");
            System.out.println("3 - Reflexão");
            System.out.println("4 - Projeção");
            System.out.println("5 - Cisalhamento (Apenas 2D)");
            System.out.println("0 - Voltar");
            System.out.println("==============================");
            System.out.print("Escolha uma operação: ");
            operacao = s.nextInt();

            if (operacao == 0) break;

            System.out.println("\nDigite o vetor original:");
            Vector v = lerVetor(s);
            int dim = v.getDim();
            Vector resultado = null;

            try {
                switch (operacao) {
                    case 1:
                        System.out.println("Digite o vetor de deslocamento:");
                        Vector offset = lerVetor(s);
                        resultado = tf.translate(v, offset);
                        break;
                    case 2:
                        System.out.print("Ângulo de rotação (graus): ");
                        double angulo = s.nextDouble();
                        String eixoRot = "";
                        if (dim == 3) {
                            System.out.print("Eixo de rotação (X, Y ou Z): ");
                            eixoRot = s.next().toUpperCase();
                        }
                        resultado = tf.rotate(v, angulo, eixoRot);
                        break;
                    case 3:
                        String eixoRef;
                        if (dim == 2) {
                            System.out.println("Eixo de reflexão: X | Y | O (Origem)");
                        } else {
                            System.out.println("Eixo de reflexão 3D: X | Y | Z");
                        }
                        System.out.print("Opção: ");
                        eixoRef = s.next().toUpperCase();
                        resultado = tf.reflect(v, eixoRef);
                        break;
                    case 4:
                        String eixoProj;
                        System.out.print(dim == 2 ? "Eixo (X ou Y): " : "Eixo (X, Y ou Z): ");
                        eixoProj = s.next().toUpperCase();
                        resultado = tf.project(v, eixoProj);
                        break;
                    case 5:
                        System.out.print("Fator kx (inclinação em X): ");
                        double kx = s.nextDouble();
                        System.out.print("Fator ky (inclinação em Y): ");
                        double ky = s.nextDouble();
                        resultado = tf.shear(v, kx, ky);
                        break;
                }

                if (resultado != null) {
                    System.out.println("\n--- VETOR RESULTANTE ---");
                    resultado.mostrar();

                    System.out.print("\nDeseja exportar para CSV? (S/N): ");
                    String resposta = s.next(); // Captura o S ou N

                    if (resposta.equalsIgnoreCase("S")) {
                        // CRÍTICO: Esta parte deve estar aqui dentro
                        System.out.print("Digite um nome para este cenário (sem espaços, ex: rotacao90): ");
                        String nomeCenario = s.next();
                        Exportador.salvarParaCSV(v, resultado, nomeCenario);
                        System.out.println("Arquivo " + nomeCenario + ".csv gerado!");
                    }
                }
            } catch (IllegalArgumentException e) {
                System.out.println("\n[!] ERRO: " + e.getMessage());
            }
        } while (operacao != 0);
    }


    public static Vector lerVetor(Scanner s) {
        System.out.print("Nº de linhas: ");
        int dim = s.nextInt();
        double[] dados = new double[dim];
        for (int i = 0; i < dim; i++) {
            System.out.print("Linha ["+(i+1)+"]: ");
            dados[i] = s.nextDouble();
        }
        return new Vector(dim, dados);
    }

    public static Matrix lerMatriz(Scanner s) {
        System.out.print("Linhas: "); int m = s.nextInt();
        System.out.print("Colunas: "); int n = s.nextInt();
        Matrix mat = new Matrix(m, n);
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++) {
                System.out.print("["+(i+1)+"]["+(j+1)+"]: ");
                mat.set(i, j, s.nextDouble());
            }
        return mat;
    }

    public static boolean posicaoValidaMatriz(Matrix m, int l, int c) {
        return l >= 1 && l <= m.getRows() && c >= 1 && c <= m.getCols();
    }

    public static boolean posicaoValidaVetor(Vector v, int l) {
        return l >= 1 && l <= v.getDim();
    }

    public static void menuMatriz(Scanner s) {
        Matrix matriz = lerMatriz(s);
        matriz.mostrar();
    }

    public static void menuVetor(Scanner s) {
        Vector vetor = lerVetor(s);
        vetor.mostrar();
    }

    public static void menuAlgebraLinear(Scanner s, LinearAlgebra la) {
        System.out.println("Menu Álgebra Linear acedido.");
    }

    public static void interpretarSolucao(Matrix rref) {
        int colunas = rref.getCols();
        for (int i = 0; i < rref.getRows(); i++) {
            System.out.printf("x%d = %.4f%n", i + 1, rref.get(i, colunas - 1));
        }
    }
}