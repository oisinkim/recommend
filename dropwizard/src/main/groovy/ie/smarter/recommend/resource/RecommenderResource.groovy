package ie.smarter.recommend.resource

import com.wordnik.swagger.annotations.Api
import com.wordnik.swagger.annotations.ApiOperation
import com.yammer.metrics.annotation.Timed
import ie.smarter.recommend.config.RecommenderConfiguration
import ie.smarter.recommend.domain.RecommendationResponse
import ie.smarter.recommend.managed.RecommendationService
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import javax.inject.Inject
import javax.ws.rs.*
import javax.ws.rs.core.MediaType

@Produces(MediaType.APPLICATION_JSON)
@Path('/recommendation')
@Api(value = '/recommendation', description = 'Get recommendations for a user')
public class RecommenderResource {

    private RecommenderConfiguration conf

    @Inject
    private RecommendationService recommendationService

    private static final Logger log = LoggerFactory.getLogger(RecommenderResource.class)

    @Inject
    RecommenderResource(RecommenderConfiguration conf) {
        this.conf = conf
    }

    @GET
    @Timed(name = 'recommendations')
    @ApiOperation(value = 'The userId', notes = 'Test data covers users 1 to 5', response = RecommendationResponse.class)
    @Path('/{userId}')
    public List<RecommendationResponse> getRecommendations(@PathParam('userId') String userId) {
        log.debug('Generating recommendations for user {}',userId)
        if (userId.isLong())
            return recommendationService.getRecommendations(userId.toLong().longValue())
        else
            throw new Exception("UserId is not a long ${userId}")
    }

}
