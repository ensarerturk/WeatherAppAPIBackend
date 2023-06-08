package org.example.service;

import org.example.general.BaseEntityService;
import org.example.general.handler.APIKeyNotFoundException;
import org.example.model.ApiKeyEntity;
import org.example.repository.ApiKeyRepository;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class ApiKeyService extends BaseEntityService<ApiKeyEntity, ApiKeyRepository> {
    private final ApiKeyRepository apiKeyRepository;

    private final UserRepository userRepository;

    public ApiKeyService(ApiKeyRepository repository, ApiKeyRepository apiKeyRepository, UserRepository userRepository) {
        super(repository);
        this.apiKeyRepository = apiKeyRepository;
        this.userRepository = userRepository;
    }

    /**
     * Returns the API key.
     *
     * @return API Key
     * @throws APIKeyNotFoundException is thrown when API key is not found
     */
    public String getApiKey() {
        ApiKeyEntity apiKeyEntity = apiKeyRepository.findByUserId(1L);
        if (apiKeyEntity == null) {
            throw new APIKeyNotFoundException();
        }

        return apiKeyEntity.getApiKey();
    }
}
