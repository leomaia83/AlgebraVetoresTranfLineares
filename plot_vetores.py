import matplotlib.pyplot as plt
import pandas as pd
import numpy as np
import os

# 1. Configuração de caminhos
pasta_do_script = os.path.dirname(os.path.abspath(__file__))

# Loop infinito para o menu
while True:
    # 2. Busca dinâmica de arquivos CSV
    arquivos = [f for f in os.listdir(pasta_do_script) if f.endswith('.csv')]

    if not arquivos:
        print(f"\nErro: Nenhum arquivo .csv encontrado em: {pasta_do_script}")
        break

    print("\n" + "="*30)
    print("   MENU DE VETORES (3D/2D)")
    print("="*30)
    for i, nome in enumerate(arquivos):
        print(f"[{i}] - {nome}")
    print("[S] - Sair do programa")
    print("="*30)

    opcao = input("\nEscolha um arquivo ou 'S' para sair: ").strip().upper()

    if opcao == 'S':
        print("Encerrando visualizador...")
        break
    
    try:
        escolha = int(opcao)
        if escolha < 0 or escolha >= len(arquivos):
            print("Opção inválida!")
            continue
        
        nome_arquivo = arquivos[escolha]
        caminho_csv = os.path.join(pasta_do_script, nome_arquivo)
        df = pd.read_csv(caminho_csv, sep=';', decimal=',')
        
        # --- LÓGICA DO GRÁFICO (igual a anterior) ---
        fig = plt.figure(figsize=(10, 7))
        if 'Eixo Z' in df.columns:
            ax = fig.add_subplot(111, projection='3d')
            orig = df.iloc[1, 1:4].values.astype(float)
            res = df.iloc[3, 1:4].values.astype(float)
            ax.quiver(0, 0, 0, orig[0], orig[1], orig[2], color='blue', label='Original')
            ax.quiver(0, 0, 0, res[0], res[1], res[2], color='red', label='Resultante')
            lim = max(abs(np.concatenate([orig, res]))) + 1
            ax.set_xlim([-lim, lim]); ax.set_ylim([-lim, lim]); ax.set_zlim([-lim, lim])
            ax.set_xlabel('X'); ax.set_ylabel('Y'); ax.set_zlabel('Z')
        else:
            ax = fig.add_subplot(111)
            orig = df.iloc[1, 1:3].values.astype(float)
            res = df.iloc[3, 1:3].values.astype(float)
            plt.quiver([0, 0], [0, 0], [orig[0], res[0]], [orig[1], res[1]], 
                       color=['blue', 'red'], angles='xy', scale_units='xy', scale=1, label=['Original', 'Resultante'])
            lim = max(abs(np.concatenate([orig, res]))) + 1
            ax.set_xlim([-lim, lim]); ax.set_ylim([-lim, lim])
            plt.grid(True, linestyle='--')

        plt.title(f"Visualização: {nome_arquivo}")
        plt.legend()
        print(f"Exibindo '{nome_arquivo}'. FECHE A JANELA DO GRÁFICO para voltar ao menu.")
        plt.show() # O código para aqui até você fechar a janela

    except ValueError:
        print("Entrada inválida! Digite o número ou 'S'.")
    except Exception as e:
        print(f"Erro inesperado: {e}")