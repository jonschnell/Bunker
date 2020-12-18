package bunker.repository;

import bunker.models.friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public interface friendRepository extends JpaRepository<friend, Integer> {
    List<friend> findByUser_Id(UUID u_id);
    List<friend> findByUser_IdAndFriendName(UUID u_id, String f_name);
    friend findById(UUID id);
    int removeByUser_id(UUID id);
}