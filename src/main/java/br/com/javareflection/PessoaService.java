package br.com.javareflection;

import java.lang.reflect.InvocationTargetException;

import br.com.javareflection.rfl.Transformator;

public class PessoaService {

    public PessoaDTO list() throws ClassNotFoundException, NoSuchMethodException, InstantiationException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Pessoa pessoa = new PessoaRepository().list();
        PessoaDTO pessoaDTO = new Transformator().transform(pessoa);
        return pessoaDTO;
    }
}
