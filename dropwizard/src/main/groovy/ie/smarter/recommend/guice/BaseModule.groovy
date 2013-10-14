package ie.smarter.recommend.guice

import ie.smarter.recommend.config.RecommenderConfiguration
import ie.smarter.recommend.resource.RecommenderResource
import ie.smarter.recommend.managed.RecommendationService
import com.google.inject.AbstractModule

class BaseModule extends AbstractModule {

    RecommenderConfiguration conf

    public BaseModule(RecommenderConfiguration conf) {
        this.conf = conf
    }

    @Override
    protected void configure() {
        bind(RecommenderConfiguration.class).toInstance(conf)
        bind(RecommenderResource.class)
        bind(RecommendationService.class).asEagerSingleton()
    }
}