package br.com.ueder.votacao_api.exceptions;

public class RegistroDuplicadoException extends RuntimeException {

    public RegistroDuplicadoException(String msg) {
        super("Registro duplicado: " + msg);
    }

}
