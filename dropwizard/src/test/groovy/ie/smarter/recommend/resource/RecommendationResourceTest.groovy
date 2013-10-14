package ie.smarter.recommend.resource

import com.yammer.dropwizard.testing.ResourceTest
import ie.smarter.recommend.config.RecommenderConfiguration
import ie.smarter.recommend.domain.RecommendationResponse
import ie.smarter.recommend.managed.RecommendationService
import org.junit.Test

import static org.fest.assertions.api.Assertions.assertThat

class RecommendationResourceTest extends ResourceTest {

    RecommenderConfiguration conf = new RecommenderConfiguration(input_file: 'src/main/resources/input.csv', num_recommendations: 1, n_user_neighbourhood: 5, recommender_type: 'org.apache.mahout.cf.taste.impl.similarity.SpearmanCorrelationSimilarity')
    RecommendationService recommendationService = new RecommendationService(conf)
    RecommenderResource recommenderResource = new RecommenderResource()
    RecommendationResponse recommenderResponse = new RecommendationResponse(itemId: new Integer(104), value: new Double(5.0))
    List<RecommendationResponse> expectedResponse = [recommenderResponse]

    @Override
    void setUpResources() throws Exception {
        recommendationService.start()
        recommenderResource.recommendationService = recommendationService
        addResource(recommenderResource)
    }

    @Test
    void simpleResourceTest() throws Exception {

        assertThat('GET Recommendations for userUiD',
                client().resource('/recommendation/1').get(expectedResponse.class),
                is(expectedResponse))
    }
}
