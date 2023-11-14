package Esercizio14112023Progetto.Esercizio14112023Progetto.entities;

import jakarta.validation.constraints.NotNull;

public record SetUserDispositivoPayload(

        String stato,
        @NotNull(message = "devi attribuire un utente")
        int user_id
) {
}
