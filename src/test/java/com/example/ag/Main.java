package com.example.ag;

import java.io.*;
import java.util.concurrent.TimeUnit;

public class Main {

    private static AlgoritmoGenetico algoritmoGenetico;
    
    /*
    *   PARÂMETROS DO ALGORITMO GENÉTICO
    */
    
    // TAMANHO FIXO DA POPULAÇÃO
    private static final int TAM_POPULACAO = 1000;
    
    // TAXA MÍNIMA DE MUTAÇÃO
    private static final double TAXA_MIN_MUTACAO = 0.1;
    
    // CONDIÇÃO DE PARADA: NÚMERO MÁXIMO DE GERAÇÕES SEM ATUALIZAR
    private static final int NUM_GERACOES = 1000;
    
    //private static final String NOME_ARQUIVO = "Teste_01.dat";
    
    public static void main(String[] args) throws IOException {

        lerArquivo("instancias/Teste_01.dat");
        //lerArquivos("instancias/");

    }

    public static void lerArquivos(String nomeDiretorio) throws FileNotFoundException, IOException{

        File file= new File(nomeDiretorio);
        File[] listaArquivos = file.listFiles();
        if(listaArquivos!=null){
            for(File arquivo:listaArquivos) {
                if (!arquivo.isDirectory() && arquivo.getAbsolutePath().endsWith(".dat")) {
                    long start = System.nanoTime(); //tempo inicial
                    String ww = arquivo.getAbsolutePath() + ": " + arquivo.getName();
                    System.out.println(ww);

                    FileInputStream stream = new FileInputStream(arquivo.getName());
                    InputStreamReader reader = new InputStreamReader(stream);
                    BufferedReader br = new BufferedReader(reader);
                    String linha = br.readLine();

                    //num linhas
                    String strNumLinha = linha.substring(linha.indexOf(" ")).trim();
                    int numLinha = Integer.parseInt(strNumLinha);

                    linha = br.readLine();

                    //num coluna
                    String strNumColuna = linha.substring(linha.indexOf(" ")).trim();
                    int numColuna = Integer.parseInt(strNumColuna);

                    algoritmoGenetico = new AlgoritmoGenetico(numLinha, numColuna, TAM_POPULACAO, TAXA_MIN_MUTACAO, NUM_GERACOES);

                    br.readLine();

                    for (int i = 0; i < numColuna; i++) {
                        linha = br.readLine();
                        String dados[] = linha.split("\\s+");

                        double custo = Double.parseDouble(dados[2]);
                        algoritmoGenetico.addCusto(i, custo);

                        for (int j = 3; j < dados.length; j++) {
                            algoritmoGenetico.addDados(i, Integer.parseInt(dados[j]));
                        }
                    }

                    algoritmoGenetico.executar();

                    long finish = System.nanoTime(); //tempo final
                    long time = (finish - start);
                    long time_ms = TimeUnit.NANOSECONDS.toMillis(time);

                    System.out.println("\nTempo de execução (ms): "+ time_ms);

                }
            }
        }

    }
    
    public static void lerArquivo(String nomeArquivo) throws FileNotFoundException, IOException{
        long start = System.nanoTime(); //tempo inicial
        FileInputStream stream = new FileInputStream(nomeArquivo);
        InputStreamReader reader = new InputStreamReader(stream);
        BufferedReader br = new BufferedReader(reader);
        String linha = br.readLine();
        
        //num linhas
        String strNumLinha = linha.substring(linha.indexOf(" ")).trim();
        int numLinha = Integer.parseInt(strNumLinha);
        
        linha = br.readLine();
        
        //num coluna
        String strNumColuna = linha.substring(linha.indexOf(" ")).trim();
        int numColuna = Integer.parseInt(strNumColuna);
        
        algoritmoGenetico = new AlgoritmoGenetico(numLinha, numColuna, TAM_POPULACAO, TAXA_MIN_MUTACAO, NUM_GERACOES);
        
        br.readLine();
        
        for (int i = 0; i < numColuna; i++) {
            linha = br.readLine();
            String dados[] = linha.split("\\s+");
            
            double custo = Double.parseDouble(dados[2]);
            algoritmoGenetico.addCusto(i, custo);
           
            for (int j = 3; j < dados.length; j++) {
                algoritmoGenetico.addDados(i, Integer.parseInt(dados[j]));
            }
        }

        algoritmoGenetico.executar();

        long finish = System.nanoTime(); //tempo final
        long time = (finish - start);
        long time_ms = TimeUnit.NANOSECONDS.toMillis(time);

        System.out.println("\nTempo de execução (ms): "+ time_ms);
    }
}
