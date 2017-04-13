package org.apilytic.currency.persistence;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

/**
 * Created by g on 4/13/17.
 */
@Configuration
@EnableRedisRepositories(repositoryImplementationPostfix = "Service")
public class ApplicationConfiguration {
}
