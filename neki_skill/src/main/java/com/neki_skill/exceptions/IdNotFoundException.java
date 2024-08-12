package com.neki_skill.exceptions;

public class IdNotFoundException extends RuntimeException {

    public IdNotFoundException() {
        super("Id não encontrado");
    }

    public String m1() {
        return "ERROR";
    }

    public String m2() {
        return "Id não encontrado";
    }
}
