package ru.supplyphotos.supplyrxtest.data.order_item_id;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author libgo (19.03.2018)
 */

public class OrderItemId {

    @SerializedName("data")
    @Expose
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

}
