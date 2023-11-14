package Esercizio14112023Progetto.Esercizio14112023Progetto.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ErrorPayload {
    private String message;
    private Date date;
}
