package ie.smarter.recommend.healthchecks

import ie.smarter.recommend.config.RecommenderConfiguration
import ie.smarter.recommend.managed.RecommendationService
import com.google.inject.Inject
import com.yammer.metrics.core.HealthCheck
import org.apache.mahout.cf.taste.model.DataModel

class MahoutHealthCheck extends HealthCheck{

    RecommenderConfiguration serviceConfiguration

    RecommendationService recommendationService

    @Inject
    protected MahoutHealthCheck(RecommenderConfiguration serviceConfiguration, RecommendationService recommendationService) {
        super('MahoutHealthCheck')
        this.serviceConfiguration = serviceConfiguration
        this.recommendationService = recommendationService
    }

    @Override
    protected HealthCheck.Result check() throws Exception {

        if (recommendationService && recommendationService.started) {
            DataModel model = recommendationService.recommender.dataModel
            HealthCheck.Result.healthy("RecommendationService loaded with ${model.numUsers} users and ${model.numItems} items")
        }
        else
            HealthCheck.Result.unhealthy('Recommendation Service not yet started')
    }
}
