package ru.supplyphotos.supplyrxtest.data.order_id;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author libgo (19.03.2018)
 */

public class Data {

    @SerializedName("order_id")
    @Expose
    private Integer orderId;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
}
