package com.adrian.mymoviememoirbackend.statics;

public class EntryPerMonth {

    private int month;
    private long count;

    public EntryPerMonth(int month, long count) {
        this.month = month;
        this.count = count;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public long getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "EntryPerMonth{" +
                "month=" + month +
                ", count=" + count +
                '}';
    }
}
