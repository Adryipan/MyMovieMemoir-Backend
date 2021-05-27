package com.adrian.mymoviememoirbackend.statics;

public class MemoirPostcodeCount {

    private String postcode;
    private long total;

    public MemoirPostcodeCount(String postcode, long total) {
        this.postcode = postcode;
        this.total = total;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "MemoirPostcodeCount{" +
                "postcode='" + postcode + '\'' +
                ", total=" + total +
                '}';
    }
}
