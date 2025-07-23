package br.com.alura.screenmatch.service;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;

public class ConsultaGemini {
    private static final String CHAVE = System.getenv("GEMINI_KEY");
    public static String obterTraducao(String texto) {
        Client client = Client.builder().apiKey(CHAVE).build();;

        GenerateContentResponse response =
                client.models.generateContent(
                        "gemini-2.0-flash",
                        "Apenas traduza, sem me dizer nada alem disso, o seguinte trecho: " + texto,
                        null);

        return response.text();
    }
}
