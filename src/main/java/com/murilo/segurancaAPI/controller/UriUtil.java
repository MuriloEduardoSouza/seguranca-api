package com.murilo.segurancaAPI.controller;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public class UriUtil {
    public static URI fromCurrentRequestWithId(Object id){
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(id)
                .toUri();
    }
}
