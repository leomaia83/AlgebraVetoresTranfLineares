# Álgebra Linear: Vetores e Transformações Lineares

Aplicação de console em **Java** que implementa do zero operações de álgebra linear e transformações geométricas em 2D e 3D, com visualização gráfica dos resultados em **Python** (Matplotlib).

Projeto desenvolvido no curso de Ciência da Computação da Universidade de Fortaleza (Unifor).

## Funcionalidades

**Transformações geométricas (2D e 3D)**

| Transformação | 2D | 3D |
|---|---|---|
| Translação | ✔ | ✔ |
| Rotação (ângulo em graus) | ✔ | ✔ em torno de X, Y ou Z |
| Reflexão | ✔ em X, Y ou origem | ✔ em torno de X, Y ou Z |
| Projeção ortogonal sobre um eixo | ✔ | ✔ |
| Cisalhamento | ✔ | — |

Cada transformação é aplicada multiplicando a matriz da transformação pelo vetor (forma de matriz-coluna).

**Biblioteca de álgebra linear** (sem bibliotecas externas)

- Classes próprias `Matrix` e `Vector`
- Soma, multiplicação por escalar, transposta e produto de matrizes
- Eliminação de Gauss e resolução de sistemas lineares

**Exportação e visualização**

- Exporta o vetor original e o resultante para `.csv`
- Script Python lê o CSV e plota os dois vetores em 2D ou 3D, detectando a dimensão automaticamente

## Tecnologias

- Java (orientação a objetos, sem dependências externas)
- Python 3 com Matplotlib, Pandas e NumPy
- IntelliJ IDEA

## Estrutura

```
Cenario1/src/
├── Principal.java        # Menu de console e entrada de dados
├── Matrix.java           # Representação de matrizes
├── Vector.java           # Representação de vetores
├── LinearAlgebra.java    # Operações de álgebra linear
├── Transformations.java  # Matrizes de transformação 2D/3D
└── Exportador.java       # Exportação dos resultados para CSV
plot_vetores.py           # Visualização gráfica dos vetores
```

## Como executar

**1. Programa Java**

Abra o projeto no IntelliJ e execute a classe `Principal`. Ou, pelo terminal:

```bash
cd Cenario1/src
javac *.java
java Principal
```

Escolha uma transformação no menu, informe o vetor e os parâmetros e, ao final, responda `S` para exportar o resultado para CSV.

**2. Visualização em Python**

```bash
pip install matplotlib pandas numpy
python plot_vetores.py
```

Coloque o `.csv` gerado na mesma pasta do script. O programa lista os arquivos disponíveis e abre o gráfico do cenário escolhido: vetor original em azul e vetor resultante em vermelho.

## Exemplo

Rotação de 90° do vetor (1, 0) em 2D:

```
--- VETOR RESULTANTE ---
Vetor (Coluna):
[       0.00 ]
[       1.00 ]
```

## Próximos passos

- Integrar a eliminação de Gauss e a resolução de sistemas ao menu "Álgebra Linear"
- Adicionar testes unitários (JUnit)
- Permitir encadear várias transformações sobre o mesmo vetor

## Autor

**Leonardo Maia** · [LinkedIn](https://www.linkedin.com/in/leonardo-maia-menezes/) · [GitHub](https://github.com/leomaia83)
