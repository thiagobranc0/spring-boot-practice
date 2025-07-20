package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.model.DadosTemporada;
import br.com.alura.screenmatch.service.ConsumoApi;
import br.com.alura.screenmatch.service.ConverteDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private Scanner leitura = new Scanner(System.in);
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=7a8fd930";
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();

    public void exibeMenu() {
        System.out.println("Digite o nome da série para busca ");

        var nomeSerie = leitura.nextLine();
        nomeSerie = nomeSerie.replace(" ", "+");
        var json = consumoApi.obterDados(ENDERECO + nomeSerie + API_KEY);
        DadosSerie dados = conversor.converteDados(json, DadosSerie.class);
        System.out.println(dados);

        List<DadosTemporada> temporadas = new ArrayList();

		for(int i = 1; i <= dados.totalTemporadas(); i++) {
			json = consumoApi.obterDados(ENDERECO + nomeSerie + "&season=" + i + API_KEY);
			temporadas.add(conversor.converteDados(json, DadosTemporada.class));
		}

		temporadas.forEach(System.out::println);
    }
}
