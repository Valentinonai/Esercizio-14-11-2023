package Esercizio14112023Progetto.Esercizio14112023Progetto.entities;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name = "dispositivi")
public class Dispositivo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "tipo")
    private String tipo;
    @Column(name = "stato")
    @Enumerated(EnumType.STRING)
    private Stato stato;

    @ManyToOne
    @JoinColumn
    private User user;

}
