package com.billyclub.points.repo;

import com.billyclub.points.model.PointsEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import javax.annotation.PostConstruct;

@Configuration
public class RepositoryConfig {

    @Autowired
    RepositoryRestConfiguration repositoryRestConfiguration;

    @PostConstruct
    public void init() {
        repositoryRestConfiguration.exposeIdsFor(PointsEvent.class);
        repositoryRestConfiguration.setReturnBodyForPutAndPost(true);
        repositoryRestConfiguration.setReturnBodyOnCreate(true);
        repositoryRestConfiguration.setReturnBodyOnUpdate(true);
    }
}
