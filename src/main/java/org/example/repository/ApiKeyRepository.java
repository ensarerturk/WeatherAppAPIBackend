package org.example.repository;

import org.example.model.ApiKeyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiKeyRepository extends JpaRepository<ApiKeyEntity, Long> {

    /**
     * Retrieves an ApiKeyEntity object based on the specified user ID.
     *
     * @param user User ID
     * @return ApiKeyEntity object
     */
    ApiKeyEntity findByUserId(Long user);
}
