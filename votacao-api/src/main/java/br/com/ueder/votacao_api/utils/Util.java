package br.com.ueder.votacao_api.utils;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public class Util {

    public static URI getURI(String lPath, Long lId) {
        return ServletUriComponentsBuilder.fromCurrentContextPath().path(lPath).buildAndExpand(lId).toUri();
    }
}
