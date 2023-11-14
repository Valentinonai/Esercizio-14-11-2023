package Esercizio14112023Progetto.Esercizio14112023Progetto.exceptions;

import lombok.Getter;

@Getter
public class NotFound extends  RuntimeException{
    public NotFound(String message) {
        super(message);
    }
}
