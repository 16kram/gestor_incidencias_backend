/**
 * Clase MyUserDetails
 * permite cargar datos específicos del usuario
 */
package com.example.PruebaAuthSpring.Usuario;

import com.example.PruebaAuthSpring.Persistencia.Entidades.Usuario;
import java.util.Collection;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author char_
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyUserDetails implements UserDetails {

    private Usuario user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        System.out.println(user.getRole());
        return List.of(new SimpleGrantedAuthority(user.getRole().toString()));
        //return List.of(new SimpleGrantedAuthority("ADMIN"));
    }

    @Override
    public String getPassword() {
        System.out.println("clave=" + user.getPassword());
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        System.out.println("usuario=" + user.getUsername());
        return user.getUsername();
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

}
