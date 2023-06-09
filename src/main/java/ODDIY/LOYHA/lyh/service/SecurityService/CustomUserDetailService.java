package ODDIY.LOYHA.lyh.service.SecurityService;

import ODDIY.LOYHA.lyh.entity.Security.User;
import ODDIY.LOYHA.lyh.repository.SecurityRepository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class CustomUserDetailService implements UserDetailsService {

    private  final UserRepository userRepository;

    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(userName);
        System.out.println("foydalanuvchu nomi -<- "+userName);
        System.out.println("Foydalanuvchi nomi ->- "+user.getUserName());
        if (user == null){
            System.out.println("foydalanuvchi topilmadi" );
            return (UserDetails) new UsernameNotFoundException("Not found UserName");
        }
        return new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName().toString())).collect(Collectors.toList());
            }

            @Override
            public String getPassword() {
                System.out.println(user.getPassword());
                return user.getPassword();
            }

            @Override
            public String getUsername() {
                System.out.println(user.getUserName());
                return user.getUserName();
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
        };
    }
}
