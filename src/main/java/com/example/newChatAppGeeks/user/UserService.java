package com.example.newChatAppGeeks.user;


import com.example.newChatAppGeeks.user.Status;
import com.example.newChatAppGeeks.user.User;
import com.example.newChatAppGeeks.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
     UserRepository repository;

    public void saveUser(User user) {
        user.setStatus(Status.ONLINE);
        repository.save(user);
    }

    public void disconnect(User user) {
        var storedUser = repository.findById(user.getNickName()).orElse(null);
        if (storedUser != null) {
            storedUser.setStatus(Status.OFFLINE);
            repository.save(storedUser);
        }
    }

    public List<User> findConnectedUsers() {
        return repository.findAllByStatus(Status.ONLINE);
    }


//    public User addUsers(User user) {
//        repository.save(user);
//        return user;
//    }
}
