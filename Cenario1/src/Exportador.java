import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Exportador {

    public static void salvarParaCSV(Vector original, Vector transformado, String nomeArquivo) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(nomeArquivo + ".csv"))) {
            int dim = original.getDim();

            // 1. Cabeçalho Dinâmico (Detecta se é 2D ou 3D)
            if (dim == 3) {
                writer.println("Legenda;Eixo X;Eixo Y;Eixo Z");
            } else {
                writer.println("Legenda;Eixo X;Eixo Y");
            }

            // 2. Exportar Vetor Original
            escreverLinha(writer, "Origem", new double[dim]);
            escreverLinha(writer, "Vetor Original", original.getData());

            // 3. Exportar Vetor Resultante
            escreverLinha(writer, "Origem", new double[dim]);
            escreverLinha(writer, "Vetor Resultante", transformado.getData());

            System.out.println("\n[OK] Arquivo '" + nomeArquivo + ".csv' gerado para " + dim + "D!");

        } catch (IOException e) {
            System.out.println("Erro ao exportar arquivo: " + e.getMessage());
        }
    }

    private static void escreverLinha(PrintWriter writer, String legenda, double[] dados) {
        StringBuilder sb = new StringBuilder(legenda);
        for (double valor : dados) {
            sb.append(";").append(String.valueOf(valor).replace('.', ','));
        }
        writer.println(sb.toString());
    }
}