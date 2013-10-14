package ie.smarter.recommend.managed

import com.yammer.dropwizard.lifecycle.Managed
import ie.smarter.recommend.config.RecommenderConfiguration
import ie.smarter.recommend.domain.RecommendationResponse
import ie.smarter.recommend.mahout.CSVBasedRecommender
import ie.smarter.recommend.mahout.UserRecommender
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import javax.inject.Inject

class RecommendationService implements Managed {

    private static final Logger log = LoggerFactory.getLogger(RecommendationService.class)

    boolean started = false

    RecommenderConfiguration conf

    UserRecommender recommender

    @Inject
    RecommendationService(RecommenderConfiguration conf) {
        this.conf = conf
    }

    public List<RecommendationResponse> getRecommendations(long userId){
        log.debug('Getting recommendations for user {}',userId)
        return getRecommendations(userId, conf.num_recommendations)
    }

    public List<RecommendationResponse> getRecommendations(long userId, int numberOfRecommendations){
        if(started){
            recommender.getRecommendations(userId,numberOfRecommendations).collect {
                new RecommendationResponse(itemId: it.itemID, value: it.value)
            }
        }
        else {
            throw new Exception('Recommendation engine not yet started.')
        }
    }

    boolean isStarted(){
        return started
    }

    @Override
    void start() throws Exception {
        //load the CSV file into the Mahout Recommendation Engine
        log.info ('Starting Recommendation Service using input_file {}',conf.input_file)
        recommender = new CSVBasedRecommender(conf.input_file, conf.recommender_type, conf.n_user_neighbourhood)
        started = true
    }

    @Override
    void stop() throws Exception {
        //stop it.
        log.info ('Stopping Recommendation Service using input_file {}',conf.input_file)
        recommender = null
        started = false
    }
}
