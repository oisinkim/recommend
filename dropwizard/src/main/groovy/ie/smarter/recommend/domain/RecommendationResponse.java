package ie.smarter.recommend.domain;

import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * TODO : Using a groovy object here will result in the metaclass fields being exposed in the swagger docs, investigate?
 */
public class RecommendationResponse {
    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    @ApiModelProperty(value = "Item id", required=true, dataType = "Long")
    public Long itemId;

    @ApiModelProperty(value = "Recommendation preference value", required=true, dataType = "Float")
    public Float value;

    public String toString(){
        return new StringBuilder().
                append("itemId: [").
                append(itemId).
                append("], value: [").
                append(value).
                append("]").toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RecommendationResponse that = (RecommendationResponse) o;

        if (!itemId.equals(that.itemId)) return false;
        if (!value.equals(that.value)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = itemId.hashCode();
        result = 31 * result + value.hashCode();
        return result;
    }
}
