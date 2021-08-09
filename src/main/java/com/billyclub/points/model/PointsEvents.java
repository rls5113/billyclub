package com.billyclub.points.model;

import java.util.ArrayList;
import java.util.List;

public class PointsEvents {

    private List<PointsEvent> pointsEventList;

    public List<PointsEvent> getPointsEventList() {
        if(this.pointsEventList == null) {
            this.pointsEventList = new ArrayList<>();
        }
        return this.pointsEventList;
    }

    public void setPointsEventList(List<PointsEvent> pointsEventList) {
        this.pointsEventList = pointsEventList;
    }
}
