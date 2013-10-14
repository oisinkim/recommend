package ie.smarter.recommend.managed

import ie.smarter.recommend.config.RecommenderConfiguration
import ie.smarter.recommend.domain.RecommendationResponse
import org.junit.Before
import org.junit.Test

class RecommendationServiceTest {
    RecommenderConfiguration conf = new RecommenderConfiguration(input_file: 'src/main/resources/input.csv', num_recommendations: 1, n_user_neighbourhood: 5, recommender_type: 'org.apache.mahout.cf.taste.impl.similarity.SpearmanCorrelationSimilarity')
    RecommendationService recommendationService = new RecommendationService(conf)
    RecommendationResponse recommenderResponse = new RecommendationResponse(itemId: new Integer(104), value: new Double(5.0))
    List<RecommendationResponse> expectedResponse = [recommenderResponse]

    @Before
    void setUp() throws Exception {
        recommendationService.start()
    }

    @Test
    void simpleServiceTest() throws Exception {
        assert recommendationService.getRecommendations(1).equals(expectedResponse)
    }
}
