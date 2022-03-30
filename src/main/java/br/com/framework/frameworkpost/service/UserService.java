package br.com.framework.frameworkpost.service;

import br.com.framework.frameworkpost.domain.User;
import br.com.framework.frameworkpost.domain.excpeiton.NotFoundException;
import br.com.framework.frameworkpost.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> listAll(){
        return userRepository.findAll();
    }

    public User create(User user) throws Exception {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);

    }

    public User update(Long id, User user) {
        User userBd = findById(id);
        userBd.setUserName(user.getUserName());
        return userRepository.save(userBd);

    }

    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException(String.format("Usuário %d não encontrado", userId)));
    }
}
