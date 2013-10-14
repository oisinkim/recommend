package ie.smarter.recommend.mahout

import org.apache.mahout.cf.taste.model.DataModel
import org.apache.mahout.cf.taste.recommender.RecommendedItem

public interface UserRecommender {
    public List<RecommendedItem> getRecommendations(long userId, int numberOfRecommendation)
    public DataModel getDataModel()
}