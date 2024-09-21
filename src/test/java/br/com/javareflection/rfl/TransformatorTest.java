package br.com.javareflection.rfl;

import java.lang.reflect.InvocationTargetException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import br.com.javareflection.Endereco;
import br.com.javareflection.Pessoa;
import br.com.javareflection.PessoaDTO;

public class TransformatorTest {

    Pessoa pessoa = new Pessoa(1, "João", "1234");
    Endereco endereco = new Endereco("Simon de Colonia ", 41);

    @Test
    public void shouldTransform() throws ClassNotFoundException, NoSuchMethodException, InstantiationException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Transformator transformator = new Transformator();
        PessoaDTO pessoaDTO = transformator.transform(pessoa);

        Assertions.assertInstanceOf(PessoaDTO.class, pessoaDTO);
        // Assertions.assertEquals(pessoa.getNome(), pessoaDTO.getNome());
        // Assertions.assertEquals(pessoa.getCpf(), pessoaDTO.getCpf());

        Assertions.assertAll(
                "Deve validar todos",
                () -> Assertions.assertEquals(pessoa.getNome(), pessoaDTO.getNome()),
                () -> Assertions.assertEquals(pessoa.getCpf(), pessoaDTO.getCpf()));
    }

    @Test
    public void shouldNotTransform() {
        Assertions.assertThrows(ClassNotFoundException.class, () -> {
            Transformator transformator = new Transformator();
            transformator.transform(endereco);
        });
    }

    @Test
    public void shouldTransformWhenSomeFieldIsNull() throws ClassNotFoundException, NoSuchMethodException,
            InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Pessoa pessoaSemCPF = new Pessoa("João");
        Transformator transformator = new Transformator();
        PessoaDTO pessoaDTOSemCPF = transformator.transform(pessoaSemCPF); 

        Assertions.assertEquals(pessoa.getNome(), pessoaDTOSemCPF.getNome());
        Assertions.assertNull(pessoaDTOSemCPF.getCpf());

    }
}
