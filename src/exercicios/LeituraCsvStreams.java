package exercicios;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LeituraCsvStreams {
    public static void main(String[] args) {
        Path caminhoCsv = Path.of("gastos.csv");

        // Cria um CSV de exemplo automaticamente para o código rodar de primeira
        String conteudoExemplo =
                "categoria,descricao,valor\n" +
                        "Alimentacao,Almoco,35.50\n" +
                        "Transporte,Uber,22.00\n" +
                        "Alimentacao,Supermercado,180.40\n" +
                        "Educacao,Livro Java,89.90\n" +
                        "Transporte,Combustivel,150.00\n";

        try {
            Files.writeString(caminhoCsv, conteudoExemplo);

            // try-with-resources: fecha o arquivo automaticamente após a leitura
            try (Stream<String> linhas = Files.lines(caminhoCsv)) {
                Map<String, Double> totalPorCategoria = linhas
                        .skip(1) // Pula a linha 0 (cabeçalho: categoria,descricao,valor)
                        .filter(linha -> !linha.isBlank()) // Ignora eventuais linhas vazias
                        .map(linha -> linha.split(",")) // Quebra cada linha num vetor de textos
                        .collect(Collectors.groupingBy(
                                colunas -> colunas[0], // Agrupa pela coluna 0 (categoria)
                                Collectors.summingDouble(colunas -> Double.parseDouble(colunas[2])) // Soma a coluna 2 (valor)
                        ));

                System.out.println("=== Total Gasto por Categoria ===");
                totalPorCategoria.forEach((categoria, total) ->
                        System.out.printf("%s: R$ %.2f%n", categoria, total)
                );
            }
        } catch (IOException e) {
            System.out.println("Erro ao manipular o arquivo CSV: " + e.getMessage());
        }
    }
}

/* ============================================================================
 * DICIONÁRIO DO CÓDIGO
 * ============================================================================
 *
 * - Path.of("gastos.csv"): Representa o caminho de um arquivo ou diretório no
 *   sistema operacional de forma independente (Windows, Linux ou Mac).
 *
 * - Files.writeString(...): Método utilitário da API moderna de arquivos (NIO.2)
 *   que cria o arquivo e grava uma String inteira nele de uma só vez.
 *
 * - try (Stream<String> linhas = ...): Bloco try-with-resources. Garante que o
 *   recurso aberto (o ponteiro do arquivo no sistema) será fechado no final,
 *   mesmo se ocorrer erro. (Equivalente no Delphi: try ... finally Free; end;)
 *
 * - Files.lines(caminhoCsv): Lê o arquivo linha por linha sob demanda (lazy),
 *   sem precisar carregar um arquivo gigante inteiro na memória RAM.
 *
 * - Stream<String>: Fluxo de dados funcional onde encadeamos operações de
 *   transformação, filtro e agregação em sequência (estilo pipeline).
 *
 * - .skip(1): Ignora os N primeiros elementos do fluxo (usado para pular o
 *   cabeçalho do CSV).
 *
 * - .filter(linha -> !linha.isBlank()): Mantém no fluxo apenas as linhas que
 *   retornam true na condição (aqui, linhas que não estão vazias).
 *
 * - .map(linha -> linha.split(",")): Transforma cada item do fluxo em outro.
 *   Aqui converte cada String de linha em um array String[] separado por vírgula.
 *
 * - .collect(Collectors.groupingBy(...)): Operação terminal que encerra o
 *   Stream e agrupa os resultados em um Map (dicionário chave -> valor).
 *
 * - Collectors.summingDouble(...): Coletor auxiliar usado dentro do groupingBy
 *   para somar os valores numéricos de cada grupo.
 *
 * - Double.parseDouble(...): Converte uma String numérica (ex: "35.50") para o
 *   tipo primitivo double. (Equivalente no Delphi: StrToFloat)
 *
 * - Map<String, Double>: Estrutura de pares Chave-Valor (ex: "Alimentacao" -> 215.90).
 *   (Equivalente no Delphi: TDictionary<string, Double>)
 * ============================================================================
 */