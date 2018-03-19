package ru.supplyphotos.supplyrxtest.data.order_item_id;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author libgo (19.03.2018)
 */

public class Data {

    @SerializedName("order_item_id")
    @Expose
    private Integer orderItemId;

    public Integer getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Integer orderItemId) {
        this.orderItemId = orderItemId;
    }
}
