public class Matrix {
    private double[][] matrix;

    public Matrix(int m, int n) {
        if (m <= 0 || n <= 0) {
            throw new IllegalArgumentException("As dimensões da matriz devem ser maiores que zero.");
        }
        matrix = new double[m][n];
    }

    public void set(int i, int j, double valor) {
        validarIndices(i, j);
        matrix[i][j] = valor;
    }

    public double get(int i, int j) {
        validarIndices(i, j);
        return matrix[i][j];
    }

    public int getRows() {
        return matrix.length;
    }

    public int getCols() {
        return matrix[0].length;
    }

    public Matrix copy() {
        Matrix copia = new Matrix(getRows(), getCols());
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getCols(); j++) {
                copia.set(i, j, this.get(i, j));
            }
        }
        return copia;
    }

    public void mostrar() {
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getCols(); j++) {
                System.out.printf("%10.2f", matrix[i][j]);
            }
            System.out.println();
        }
    }

    private void validarIndices(int i, int j) {
        if (i < 0 || i >= getRows() || j < 0 || j >= getCols()) {
            throw new IndexOutOfBoundsException("Índices inválidos para a matriz.");
        }
    }
}