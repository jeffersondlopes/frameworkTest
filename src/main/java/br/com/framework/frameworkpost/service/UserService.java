package br.com.framework.frameworkpost.service;

import br.com.framework.frameworkpost.domain.User;
import br.com.framework.frameworkpost.domain.excpeiton.BusinessException;
import br.com.framework.frameworkpost.domain.excpeiton.NotFoundException;
import br.com.framework.frameworkpost.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private static String USER_BY_EMAIL_ALREADY_EXISTS = "Usuário/E-mail %s/%s já cadastrado.";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> listAll(){
        return userRepository.findAll();
    }

    public User create(User user)  {
        if (!userRepository.existsByEmail(user.getEmail())){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        } else {
            throw new BusinessException(String.format(USER_BY_EMAIL_ALREADY_EXISTS,user.getUserName(), user.getEmail()));
        }
    }

    public User update(Long id, User user) {
        User userBd = findById(id);
        userBd.setUserName(user.getUserName());
        return userRepository.save(userBd);
    }

    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(String.format("Usuário %d não encontrado", userId)));
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException(String.format("Usuário %s não encontrado", email)));
    }


}
