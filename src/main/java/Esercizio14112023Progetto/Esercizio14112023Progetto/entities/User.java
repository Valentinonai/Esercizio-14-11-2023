package Esercizio14112023Progetto.Esercizio14112023Progetto.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name="users")
@JsonIgnoreProperties({"password", "enabled", "accountNonExpired", "accountNonLocked", "authorities", "credentialsNonExpired"})
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nome",nullable = false)
    private String nome;
    @Column(name = "cognome",nullable = false)
    private String cognome;
    @Column(name = "email",nullable = false)
    private String email;
    @Column(name="password",nullable = false)
    private String password;
    @Column(name = "username")
    private String username;
    @Column(name = "immagine")
    private String imageUrl;
    @Column(name = "ruolo")
    @Enumerated(EnumType.STRING)
    private Ruolo ruolo;
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    List<Dispositivo> listaDispositivi;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.ruolo.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    @Override
    public String getUsername() {
        return this.email;
    }
}
