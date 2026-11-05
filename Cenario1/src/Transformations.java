public class Transformations {
    private LinearAlgebra la = new LinearAlgebra();

    public Vector translate(Vector v, Vector offset) {
        return la.sum(v, offset);
    }

    public Vector rotate(Vector v, double angleDegrees, String axis) {
        double rad = Math.toRadians(angleDegrees);
        int dim = v.getDim();
        Matrix m;

        if (dim == 2) {
            m = new Matrix(2, 2);
            m.set(0, 0, Math.cos(rad));  m.set(0, 1, -Math.sin(rad));
            m.set(1, 0, Math.sin(rad));   m.set(1, 1, Math.cos(rad));
        } else if (dim == 3) {
            m = new Matrix(3, 3);
            switch (axis.toUpperCase()) {
                case "X":
                    m.set(0, 0, 1);
                    m.set(1, 1, Math.cos(rad)); m.set(1, 2, -Math.sin(rad));
                    m.set(2, 1, Math.sin(rad)); m.set(2, 2, Math.cos(rad));
                    break;
                case "Y":
                    m.set(0, 0, Math.cos(rad));  m.set(0, 2, Math.sin(rad));
                    m.set(1, 1, 1);
                    m.set(2, 0, -Math.sin(rad)); m.set(2, 2, Math.cos(rad));
                    break;
                case "Z":
                    m.set(0, 0, Math.cos(rad)); m.set(0, 1, -Math.sin(rad));
                    m.set(1, 0, Math.sin(rad)); m.set(1, 1, Math.cos(rad));
                    m.set(2, 2, 1);
                    break;
                default: throw new IllegalArgumentException("Eixo 3D inválido.");
            }
        } else {
            throw new IllegalArgumentException("Suporta apenas 2D ou 3D.");
        }
        return apply(m, v);
    }


    public Vector reflect(Vector v, String axis) {
        int dim = v.getDim();
        Matrix m = new Matrix(dim, dim);
        for(int i=0; i<dim; i++) m.set(i, i, 1);

        if (dim == 2) {
            if (axis.equalsIgnoreCase("X")) m.set(1, 1, -1);
            else if (axis.equalsIgnoreCase("Y")) m.set(0, 0, -1);
            else if (axis.equalsIgnoreCase("O")) { m.set(0, 0, -1); m.set(1, 1, -1); }
        } else if (dim == 3) {
            switch (axis.toUpperCase()) {
                case "X": // Refletir em torno do eixo X: inverte Y e Z
                    m.set(1, 1, -1); m.set(2, 2, -1); break;
                case "Y": // Refletir em torno do eixo Y: inverte X e Z
                    m.set(0, 0, -1); m.set(2, 2, -1); break;
                case "Z": // Refletir em torno do eixo Z: inverte X e Y
                    m.set(0, 0, -1); m.set(1, 1, -1); break;
                default: throw new IllegalArgumentException("Eixo 3D inválido (X, Y ou Z).");
            }
        }
        return apply(m, v);
    }

    public Vector project(Vector v, String axis) {
        int dim = v.getDim();
        Matrix m = new Matrix(dim, dim);
        if (axis.equalsIgnoreCase("X")) m.set(0, 0, 1);
        else if (axis.equalsIgnoreCase("Y")) m.set(1, 1, 1);
        else if (axis.equalsIgnoreCase("Z") && dim == 3) m.set(2, 2, 1);
        else throw new IllegalArgumentException("Eixo inválido.");
        return apply(m, v);
    }


    public Vector shear(Vector v, double kx, double ky) {
        if (v.getDim() != 2) throw new IllegalArgumentException("Cisalhamento apenas 2D.");
        Matrix m = new Matrix(2, 2);
        m.set(0, 0, 1);  m.set(0, 1, kx); // x' = x + kx*y
        m.set(1, 0, ky); m.set(1, 1, 1);  // y' = y + ky*x
        return apply(m, v);
    }

    private Vector apply(Matrix m, Vector v) {
        Matrix col = new Matrix(v.getDim(), 1);
        for (int i = 0; i < v.getDim(); i++) col.set(i, 0, v.get(i));
        Matrix resMat = la.dot(m, col);
        double[] resultElements = new double[v.getDim()];
        for (int i = 0; i < v.getDim(); i++) resultElements[i] = resMat.get(i, 0);
        return new Vector(v.getDim(), resultElements);
    }
}