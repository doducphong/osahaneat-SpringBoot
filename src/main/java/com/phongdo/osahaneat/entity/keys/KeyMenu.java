package com.phongdo.osahaneat.entity.keys;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class KeyMenu implements Serializable {

    @Column(name = "cate_id")
    private int cateId;

    @Column(name = "restaurant_id")
    private int resId;

    public KeyMenu() {}

    public KeyMenu(int cateId, int resId) {
        this.cateId = cateId;
        this.resId = resId;
    }

    public int getCateId() {
        return cateId;
    }

    public void setCateId(int cateId) {
        this.cateId = cateId;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }
}
