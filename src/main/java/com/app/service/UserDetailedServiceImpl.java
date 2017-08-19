package com.app.service;

import com.app.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

/**
 * This is implementation for user {@link com.app.entities.User} detailed service which will use
 * {@link com.app.dao.UserDao} as users repository.
 *
 */
@Service
public class UserDetailedServiceImpl implements UserDetailsService {
    private final UserService userService;

    @Autowired
    public UserDetailedServiceImpl(UserService usersService) {
        this.userService = usersService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findOne(username);
        if (user == null) {
            throw new UsernameNotFoundException("User is not found!");
        }
        return new UserDetailsExt(user);

    }


    //TODO 5.Создаем класс, чтобы не потерять информацию о user (в дан. случае только email).
    // В UserDetails есть getPassword, getUsername, а вся остальная информация потеряется, если не написать класс
    // UserDetailsExt и добавить в нем метод getEmail (и другие геттеры, если в User появятся еще поля)
    private class UserDetailsExt implements UserDetails {

        private User user;
        private Collection<SimpleGrantedAuthority> grantedAuthorities;//список ролей у конкретного user

        public UserDetailsExt(User user) {
            this.user = user;
            this.grantedAuthorities = new ArrayList<>();//ролей у одного человека может быть много
            //TODO переделать роли на enum и поставить здесь switch
            //Роли должны совпадать с теми, что используем в методе configure в SpringSecurityConfiguration
            if (user.isAdministrator()) {
                //authority = ROLE_ + role //в методе hasRole в ExpressionUrlAuthorizationConfigurer роль описывается как "hasRole('ROLE_" + role + "')"
                grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            } else {
                grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            }
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return grantedAuthorities;
        }

        @Override
        public String getPassword() {
            return user.getPassword();
        }

        @Override
        public String getUsername() {
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

        //TODO 6. дописывать геттеры для всех новых полей в {@link com.app.entities.User}
        public String getEmail() {
            return user.getEmail();
        }
    }
}
