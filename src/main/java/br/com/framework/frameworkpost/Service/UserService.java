package br.com.framework.frameworkpost.Service;

import br.com.framework.frameworkpost.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private void listAll(){

    }


}
