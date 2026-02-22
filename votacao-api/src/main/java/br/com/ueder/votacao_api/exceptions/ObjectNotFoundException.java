package br.com.ueder.votacao_api.exceptions;

public class ObjectNotFoundException extends RuntimeException {

    public ObjectNotFoundException(String id) {
        super("Registro não encontrado: " + id);
    }

}
