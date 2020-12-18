package bunker.repository;

import bunker.models.credential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public interface credentialRepository extends JpaRepository<credential, Integer> {
//    credential createCredentialByUser(UUID id, credential c);
    List<credential> findByUser_Id(UUID u_id);
    //List<HashMap> UUID_findByUser_Id(UUID u_id);
    List<credential> findByUser_IdAndCredentialName(UUID u_id, String c_name);
    credential findById(UUID id);
    int removeByUser_id(UUID id);
}