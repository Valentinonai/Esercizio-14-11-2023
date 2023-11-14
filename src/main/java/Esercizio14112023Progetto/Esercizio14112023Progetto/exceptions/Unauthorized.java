package Esercizio14112023Progetto.Esercizio14112023Progetto.exceptions;

import lombok.Getter;

@Getter
public class Unauthorized extends RuntimeException{
    public Unauthorized(String message) {
        super(message);
    }
}
