public class Vector {
    private int dim;
    private double[] elements;

    public Vector(int dim, double[] elements) {
        if (dim <= 0) {
            throw new IllegalArgumentException("A dimensão do vetor deve ser maior que zero.");
        }
        if (elements == null || elements.length != dim) {
            throw new IllegalArgumentException("O array de elementos deve ter exatamente a dimensão informada.");
        }

        this.dim = dim;
        this.elements = new double[dim];
        for (int i = 0; i < dim; i++) {
            this.elements[i] = elements[i];
        }
    }

    public double get(int i) {
        validarIndice(i);
        return elements[i];
    }

    public void set(int i, double value) {
        validarIndice(i);
        elements[i] = value;
    }

    public int getDim() {
        return dim;
    }

    public double[] getData() {
        // Retorna uma cópia dos elementos para manter o encapsulamento seguro
        double[] copia = new double[dim];
        System.arraycopy(this.elements, 0, copia, 0, dim);
        return copia;
    }

    public Vector copy() {
        double[] copia = new double[dim];
        System.arraycopy(elements, 0, copia, 0, dim);
        return new Vector(dim, copia);
    }

    public void mostrar() {
        System.out.println("Vetor (Coluna):");
        for (int i = 0; i < dim; i++) {
            System.out.printf("[ %10.2f ]%n", elements[i]);
        }
    }

    private void validarIndice(int i) {
        if (i < 0 || i >= dim) {
            throw new IndexOutOfBoundsException("Índice inválido para o vetor.");
        }
    }
}