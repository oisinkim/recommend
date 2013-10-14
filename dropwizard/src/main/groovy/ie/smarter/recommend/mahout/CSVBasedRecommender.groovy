package ie.smarter.recommend.mahout

import org.apache.mahout.cf.taste.impl.model.file.FileDataModel
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender
import org.apache.mahout.cf.taste.model.DataModel
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood
import org.apache.mahout.cf.taste.recommender.RecommendedItem
import org.apache.mahout.cf.taste.recommender.Recommender
import org.apache.mahout.cf.taste.similarity.UserSimilarity
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class CSVBasedRecommender implements UserRecommender {

    private static final Logger log = LoggerFactory.getLogger(CSVBasedRecommender.class)

    private Recommender recommender

    DataModel dataModel

    UserSimilarity similarity

    public CSVBasedRecommender(String input_file, String recommender_type, int n_user_neighbourhood) {

        log.info("Current Heap is ${getMemoryUsedinMB()} MB")

        long inital_heap = getMemoryUsedinMB()

        dataModel = new FileDataModel(new File(input_file))

        String className = recommender_type

        similarity = Class.forName(className).getConstructor(DataModel).newInstance(dataModel)

        neighborhood = new NearestNUserNeighborhood(n_user_neighbourhood, similarity, dataModel)

        recommender = new GenericUserBasedRecommender(dataModel, neighborhood, similarity)

        log.info("Current Heap is ${getMemoryUsedinMB()} MB")
        log.info("Heap used by Mahout code is ${getMemoryUsedinMB() - inital_heap} MB")

        log.info("Cost per     1,000 users is ${((getMemoryUsedinMB() - inital_heap) / recommender.dataModel.numUsers) * 1000 } MB")
        log.info("Cost per    10,000 users is ${((getMemoryUsedinMB() - inital_heap) / recommender.dataModel.numUsers) * 10000 } MB")
        log.info("Cost per   100,000 users is ${((getMemoryUsedinMB() - inital_heap) / recommender.dataModel.numUsers) * 100000 } MB")
        log.info("Cost per   100,000 users is ${((getMemoryUsedinMB() - inital_heap) / recommender.dataModel.numUsers) * 100000 } MB")
        log.info("Cost per 1,000,000 users is ${((getMemoryUsedinMB() - inital_heap) / recommender.dataModel.numUsers) * 1000000 } MB")
    }


    UserNeighborhood neighborhood

    public List<RecommendedItem> getRecommendations(long userId, int numberOfRecommendation){
        if (log.isDebugEnabled())
            log.debug('Generating {} recommendations for user {}', numberOfRecommendation, userId)

        List<RecommendedItem> results = recommender.recommend(userId,numberOfRecommendation)

        if (log.isDebugEnabled())
            log.debug('Got {} results',results.size())

        results
    }

    private long getMemoryUsedinMB(){
        long totalMemory = Runtime.getRuntime().totalMemory()
        long freeMemory = Runtime.getRuntime().freeMemory()
        return ((totalMemory - freeMemory)/1024L/1024L)
    }

}
