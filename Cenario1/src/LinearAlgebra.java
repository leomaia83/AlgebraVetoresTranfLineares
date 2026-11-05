public class LinearAlgebra {

    public Matrix transpose(Matrix a) {
        Matrix result = new Matrix(a.getCols(), a.getRows());
        for (int i = 0; i < a.getRows(); i++) {
            for (int j = 0; j < a.getCols(); j++) {
                result.set(j, i, a.get(i, j));
            }
        }
        return result;
    }

    public Matrix transpose(Vector a) {
        Matrix result = new Matrix(1, a.getDim());
        for (int i = 0; i < a.getDim(); i++) {
            result.set(0, i, a.get(i));
        }
        return result;
    }

    public Matrix sum(Matrix a, Matrix b) {
        if (a.getRows() != b.getRows() || a.getCols() != b.getCols()) {
            throw new IllegalArgumentException("Dimensões incompatíveis para soma.");
        }
        Matrix result = new Matrix(a.getRows(), a.getCols());
        for (int i = 0; i < a.getRows(); i++) {
            for (int j = 0; j < a.getCols(); j++) {
                result.set(i, j, a.get(i, j) + b.get(i, j));
            }
        }
        return result;
    }

    public Vector sum(Vector a, Vector b) {
        if (a.getDim() != b.getDim()) {
            throw new IllegalArgumentException("Vetores devem ter a mesma dimensão.");
        }
        double[] elements = new double[a.getDim()];
        for (int i = 0; i < a.getDim(); i++) {
            elements[i] = a.get(i) + b.get(i);
        }
        return new Vector(a.getDim(), elements);
    }

    public Matrix times(double escalar, Matrix b) {
        Matrix result = new Matrix(b.getRows(), b.getCols());
        for (int i = 0; i < b.getRows(); i++) {
            for (int j = 0; j < b.getCols(); j++) {
                result.set(i, j, escalar * b.get(i, j));
            }
        }
        return result;
    }

    public Vector times(double escalar, Vector b) {
        double[] elements = new double[b.getDim()];
        for (int i = 0; i < b.getDim(); i++) {
            elements[i] = escalar * b.get(i);
        }
        return new Vector(b.getDim(), elements);
    }

    public Matrix dot(Matrix a, Matrix b) {
        if (a.getCols() != b.getRows()) {
            throw new IllegalArgumentException("Dimensões incompatíveis para multiplicação.");
        }
        Matrix result = new Matrix(a.getRows(), b.getCols());
        for (int i = 0; i < a.getRows(); i++) {
            for (int j = 0; j < b.getCols(); j++) {
                double soma = 0;
                for (int k = 0; k < a.getCols(); k++) {
                    soma += a.get(i, k) * b.get(k, j);
                }
                result.set(i, j, soma);
            }
        }
        return result;
    }

    public Matrix gauss(Matrix a) {
        Matrix result = a.copy();
        int linhas = result.getRows();
        int colunas = result.getCols();
        int pivotRow = 0;

        for (int j = 0; j < colunas && pivotRow < linhas; j++) {
            int maxRow = pivotRow;
            for (int i = pivotRow + 1; i < linhas; i++) {
                if (Math.abs(result.get(i, j)) > Math.abs(result.get(maxRow, j))) maxRow = i;
            }
            if (Math.abs(result.get(maxRow, j)) < 1e-10) continue;
            trocarLinhas(result, pivotRow, maxRow);
            for (int i = pivotRow + 1; i < linhas; i++) {
                double fator = result.get(i, j) / result.get(pivotRow, j);
                for (int k = j; k < colunas; k++) {
                    result.set(i, k, result.get(i, k) - fator * result.get(pivotRow, k));
                }
            }
            pivotRow++;
        }
        return result;
    }

    public Matrix solve(Matrix a) {
        Matrix result = a.copy();
        int linhas = result.getRows();
        int colunas = result.getCols();
        int pivotRow = 0;

        for (int j = 0; j < colunas - 1 && pivotRow < linhas; j++) {
            int maxRow = pivotRow;
            for (int i = pivotRow + 1; i < linhas; i++) {
                if (Math.abs(result.get(i, j)) > Math.abs(result.get(maxRow, j))) maxRow = i;
            }
            if (Math.abs(result.get(maxRow, j)) < 1e-10) continue;
            trocarLinhas(result, pivotRow, maxRow);
            double pivot = result.get(pivotRow, j);
            for (int k = j; k < colunas; k++) {
                result.set(pivotRow, k, result.get(pivotRow, k) / pivot);
            }
            for (int i = 0; i < linhas; i++) {
                if (i != pivotRow) {
                    double fator = result.get(i, j);
                    for (int k = j; k < colunas; k++) {
                        result.set(i, k, result.get(i, k) - fator * result.get(pivotRow, k));
                    }
                }
            }
            pivotRow++;
        }
        return result;
    }

    private void trocarLinhas(Matrix a, int linha1, int linha2) {
        if (linha1 == linha2) return;
        for (int j = 0; j < a.getCols(); j++) {
            double temp = a.get(linha1, j);
            a.set(linha1, j, a.get(linha2, j));
            a.set(linha2, j, temp);
        }
    }
}