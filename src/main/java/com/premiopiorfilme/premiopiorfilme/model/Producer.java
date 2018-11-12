package com.premiopiorfilme.premiopiorfilme.model;

/**
 *
 * @author joao
 */
public class Producer {

    private Item min;
    private Item max;

    public Item getMin() {
        return min;
    }

    public void setMin(Item min) {
        this.min = min;
    }

    public Item getMax() {
        return max;
    }

    public void setMax(Item max) {
        this.max = max;
    }

    public static class Item {

        private String producer;
        private Integer interval;
        private Integer previousWin;
        private Integer followingWin;

        public Item(String producer, Integer interval, Integer previousWin, Integer followingWin) {
            this.producer = producer;
            this.interval = interval;
            this.previousWin = previousWin;
            this.followingWin = followingWin;
        }

        public String getProducer() {
            return producer;
        }

        public void setProducer(String producer) {
            this.producer = producer;
        }

        public Integer getInterval() {
            return interval;
        }

        public void setInterval(Integer interval) {
            this.interval = interval;
        }

        public Integer getPreviousWin() {
            return previousWin;
        }

        public void setPreviousWin(Integer previousWin) {
            this.previousWin = previousWin;
        }

        public Integer getFollowingWin() {
            return followingWin;
        }

        public void setFollowingWin(Integer followingWin) {
            this.followingWin = followingWin;
        }
    }

}
