package exercicios;

import java.util.HashMap;
import java.util.Map;

public class FrequenciaPalavra {
    public static void main(String[] args) {
        String texto = "Java é poderoso. Estudar Java exige prática, e prática leva à fluência em Java!";


        String textoLimpo = texto.toLowerCase().replaceAll("[^\\p{L}\\s]", "");
        String[] palavras = textoLimpo.split("\\s+");


        Map<String, Integer> contagem = new HashMap<>();
        for (String palavra : palavras) {
            if (!palavra.isBlank()) {
                contagem.put(palavra, contagem.getOrDefault(palavra, 0) + 1);
            }
        }

        System.out.println("Top 3 palavras mais comuns:");
        contagem.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(3)
                .forEach(entry -> System.out.println(entry.getKey() + " -> " + entry.getValue()));
    }
}

/**
 * DESCRIÇÃO:
 * Programa que analisa um texto, limpa pontuações, conta quantas vezes cada
 * palavra aparece usando HashMap e exibe as 3 palavras mais frequentes.
 *
 * DICIONÁRIO DE TERMOS E MÉTODOS UTILIZADOS:
 * - String[] args: Vetor de textos que recebe parâmetros externos ao iniciar o programa.
 * - toLowerCase(): Converte todas as letras do texto para minúsculas.
 * - replaceAll(): Remove caracteres indesejados (como pontuações), trocando-os por vazio.
 * - split(): Fatiador de texto; quebra a frase nos espaços e gera um vetor de palavras.
 * - Map / HashMap: Estrutura de chave e valor (Palavra -> Quantidade). Não garante ordem.
 * - isBlank(): Verifica se a palavra está vazia ou contém apenas espaços em branco.
 * - getOrDefault(chave, 0): Busca a contagem da palavra; se não existir no mapa, retorna 0.
 * - entrySet(): Extrai os pares (chave + valor) do mapa para permitir ordenação.
 * - stream(): Abre um fluxo de processamento em sequência sobre os dados do mapa.
 * - sorted(comparingByValue().reversed()): Ordena os pares pela quantidade, do maior para o menor.
 * - limit(3): Corta o fluxo mantendo apenas os 3 primeiros resultados.
 * - forEach(entry -> ...): Percorre os itens finais e imprime cada um no console (Lambda).
 */