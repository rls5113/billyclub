package com.billyclub.points.repo;

import com.billyclub.points.model.PointsEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.EnumTranslationConfiguration;
import org.springframework.data.rest.core.config.MetadataConfiguration;
import org.springframework.data.rest.core.config.ProjectionDefinitionConfiguration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@Configuration

public class RepositoryConfig {//extends RepositoryRestConfiguration {

//    public RepositoryConfig(ProjectionDefinitionConfiguration projectionConfiguration, MetadataConfiguration metadataConfiguration, EnumTranslationConfiguration enumTranslationConfiguration) {
//        super(projectionConfiguration, metadataConfiguration, enumTranslationConfiguration);
//    }
//
//    @Override
    protected void  configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(PointsEvent.class);
    }
}
