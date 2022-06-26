//package com.spring.Security.webSecurity.serves;
//
//
//import com.spring.Security.dao.UserRepository;
//import com.spring.Security.entity.User;
//import com.spring.Security.webSecurity.UserDetailsImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import javax.transaction.Transactional;
//import java.util.Collection;
//
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//        @Autowired
//        UserRepository userRepository;
//
//        @Override
//        @Transactional
//        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//            User user = userRepository.findByUsername(username)
//                    .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
//
//            return UserDetailsImpl.build(user);
//        }
//
//    }
//
