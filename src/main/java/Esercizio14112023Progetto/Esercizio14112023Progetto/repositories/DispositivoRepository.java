package Esercizio14112023Progetto.Esercizio14112023Progetto.repositories;

import Esercizio14112023Progetto.Esercizio14112023Progetto.entities.Dispositivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DispositivoRepository extends JpaRepository<Dispositivo,Integer> {
}
