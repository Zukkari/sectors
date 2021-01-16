package io.github.zukkari.sectors.repository;

import io.github.zukkari.sectors.model.SectorForm;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectorFormRepository extends MongoRepository<SectorForm, String> {}
