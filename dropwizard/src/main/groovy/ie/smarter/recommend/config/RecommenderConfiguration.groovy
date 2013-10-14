package ie.smarter.recommend.config

import com.fasterxml.jackson.annotation.JsonProperty
import com.yammer.dropwizard.config.Configuration

import javax.validation.constraints.NotNull

class RecommenderConfiguration extends Configuration {

    @NotNull
    @JsonProperty
    public String input_file

    @NotNull
    @JsonProperty
    String recommender_type

    @JsonProperty
    int num_recommendations = 0

    @JsonProperty
    @NotNull
    int n_user_neighbourhood = 0
}
