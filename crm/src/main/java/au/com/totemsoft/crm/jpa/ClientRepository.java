package au.com.totemsoft.crm.jpa;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import au.com.totemsoft.crm.model.ClientEntity;

@Repository
@Transactional//(readOnly = false)
public interface ClientRepository extends CrudRepository<ClientEntity, Integer> {

    Optional<ClientEntity> findById(Integer id);

    void deleteById(Integer id);

    @SuppressWarnings("unchecked")
    ClientEntity save(ClientEntity client);

}
