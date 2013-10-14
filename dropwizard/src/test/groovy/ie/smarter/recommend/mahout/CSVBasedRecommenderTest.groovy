package ie.smarter.recommend.mahout

import org.apache.mahout.cf.taste.impl.recommender.GenericRecommendedItem
import org.apache.mahout.cf.taste.recommender.RecommendedItem
import org.junit.Test

class CSVBasedRecommenderTest {

    UserRecommender csvBasedRecommender = new CSVBasedRecommender('src/main/resources/input.csv', 'org.apache.mahout.cf.taste.impl.similarity.SpearmanCorrelationSimilarity', 5)

    @Test
    void dataModelTest() {
        assert csvBasedRecommender.dataModel.getNumItems() == 7
        assert csvBasedRecommender.dataModel.getNumUsers() == 5
    }

    @Test
    void recommendationTest() {
        List<RecommendedItem> actualItems = csvBasedRecommender.getRecommendations(1, 1)
        assert actualItems.size() == 1

        List<RecommendedItem> expectedItems = [new GenericRecommendedItem(104, 5.0)]
        assert expectedItems == actualItems
    }
}
