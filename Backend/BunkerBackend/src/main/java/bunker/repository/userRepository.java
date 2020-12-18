package bunker.repository;


import bunker.models.user;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.UUID;


@Repository
@Transactional
@Configurable
public interface userRepository extends JpaRepository<user, Integer> {

    user findByUser(String u);
    public user findById(UUID id);
    //void deleteById(UUID id);
    int removeById(UUID id);
    //user createUser(user u);
    //HashMap logIn(HashMap u);

    }