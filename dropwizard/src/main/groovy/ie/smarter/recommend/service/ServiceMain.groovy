package ie.smarter.recommend.service

import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider
import com.google.inject.Guice
import com.google.inject.Injector
import com.yammer.dropwizard.Service
import com.yammer.dropwizard.assets.AssetsBundle
import com.yammer.dropwizard.config.Bootstrap
import com.yammer.dropwizard.config.Environment
import com.yammer.dropwizard.json.ObjectMapperFactory
import ie.smarter.recommend.config.RecommenderConfiguration
import ie.smarter.recommend.healthchecks.MahoutHealthCheck
import ie.smarter.recommend.managed.RecommendationService
import ie.smarter.recommend.guice.BaseModule
import ie.smarter.recommend.guice.SwaggerBundle
import ie.smarter.recommend.resource.RecommenderResource

public class ServiceMain extends Service<RecommenderConfiguration> {

    public static void main(String[] args) throws Exception {
        new ServiceMain().run(args)
    }

    private Injector injector

    @Override
    void initialize(Bootstrap<RecommenderConfiguration> bootstrap) {
        bootstrap.setName('recommend')
        ObjectMapperFactory factory = bootstrap.objectMapperFactory
        factory.setSerializerProvider(new DefaultSerializerProvider.Impl())
        factory.enable(SerializationFeature.INDENT_OUTPUT)

        bootstrap.addBundle(new SwaggerBundle())
        bootstrap.addBundle(new AssetsBundle('/swagger-ui', '/swagger-ui', 'index.html'))
    }

    @Override
    void run(RecommenderConfiguration configuration, Environment environment) throws Exception {
        injector = Guice.createInjector(
            new BaseModule(configuration)
        )

        //Jersey Resources
        environment.addResource(injector.getInstance(RecommenderResource.class))

        //The Managed Lifecycle Recommendation Service
        environment.manage(injector.getInstance(RecommendationService.class))

        //Healthchecks
        environment.addHealthCheck(injector.getInstance(MahoutHealthCheck.class))
    }

}