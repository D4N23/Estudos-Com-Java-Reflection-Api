package br.com.javareflection.rfl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.javareflection.Pessoa;

public class ObjectToJsonTest{

  Pessoa pessoa = new Pessoa(1,"Danilo", "123434");

  @Test
  public void shouldTransformToJson() throws JsonMappingException, JsonProcessingException{
    ObjectToJson objectToJson = new ObjectToJson();

    String jsonResult = objectToJson.transform(pessoa);
     // JSON esperado como objeto de Map, para evitar string hardcoded
        ObjectMapper objectMapper = new ObjectMapper();

        String expectedJson = objectMapper.writeValueAsString(pessoa);           /** "{\"id\":1,\"nome\":\"Danilo\",\"cpf\":\"123434\"}";*/

        Map<String, Object> expectedMap = objectMapper.readValue(expectedJson, Map.class);
        
        // Transforma o JSON gerado também em um Map
        Map<String, Object> resultMap = objectMapper.readValue(jsonResult, Map.class);

        // Compara os dois mapas
        assertEquals(expectedMap, resultMap);

  }
}