package com.example.edz.bean;

import java.util.List;

/**
 * authr : edz on 2018/1/26  下午5:10
 * describe ：
 */
public class CouponListBean {

    /**
     * currentPage : 1
     * itemsPerPage : 10
     * totalPages : 2
     * totalItems : 14
     * items : [{"id":177211,"userName":"18369221509","userRealName":"孙启盼","couponId":121,"couponName":"金矿存钱罐优惠券","couponAmount":201.8,"overAmount":10000,"overGold":0,"bigProductTypeIds":"5","couponDesc":"存钱罐90天及以上满10000元可用","useRangeDesc":"存钱罐","startTime":"2018-01-15 00:00:00","endTime":"2018-01-31 00:00:00","totalDays":16,"isUse":0,"useTime":"0001-01-01 00:00:00","createTime":"2018-01-26 16:15:55","productIds":"9,10,11","couponType":0,"increaseFormula":"","increaseDay":0,"increaseValue":0,"isExpire":0,"isApply":0,"h5URL":"https://admin.zhaojinmao.cn/Areas/Product/Html/ProductDetails_9.html"},{"id":177210,"userName":"18369221509","userRealName":"孙启盼","couponId":120,"couponName":"金矿存钱罐优惠券","couponAmount":188,"overAmount":10000,"overGold":0,"bigProductTypeIds":"5","couponDesc":"存钱罐30天满10000元可用","useRangeDesc":"存钱罐","startTime":"2018-01-15 00:00:00","endTime":"2018-01-31 00:00:00","totalDays":16,"isUse":0,"useTime":"0001-01-01 00:00:00","createTime":"2018-01-26 16:15:51","productIds":"8,9,10,11","couponType":0,"increaseFormula":"","increaseDay":0,"increaseValue":0,"isExpire":0,"isApply":0,"h5URL":"https://admin.zhaojinmao.cn/Areas/Product/Html/ProductDetails_8.html"},{"id":177209,"userName":"18369221509","userRealName":"孙启盼","couponId":119,"couponName":"金矿存钱罐优惠券","couponAmount":98,"overAmount":5000,"overGold":0,"bigProductTypeIds":"5","couponDesc":"存钱罐30天及以上满5000元可用","useRangeDesc":"存钱罐","startTime":"2018-01-15 00:00:00","endTime":"2018-01-31 00:00:00","totalDays":16,"isUse":0,"useTime":"0001-01-01 00:00:00","createTime":"2018-01-26 16:15:46","productIds":"8,9,10,11","couponType":0,"increaseFormula":"","increaseDay":0,"increaseValue":0,"isExpire":0,"isApply":0,"h5URL":"https://admin.zhaojinmao.cn/Areas/Product/Html/ProductDetails_8.html"},{"id":177208,"userName":"18369221509","userRealName":"孙启盼","couponId":113,"couponName":"存钱罐30天及以上产品","couponAmount":388,"overAmount":99999,"overGold":0,"bigProductTypeIds":"5","couponDesc":"投资满99999元可用","useRangeDesc":"存钱罐","startTime":"2018-01-01 00:00:00","endTime":"2018-01-31 00:00:00","totalDays":30,"isUse":0,"useTime":"0001-01-01 00:00:00","createTime":"2018-01-26 16:15:15","productIds":"8,9,10,11","couponType":0,"increaseFormula":"","increaseDay":0,"increaseValue":0,"isExpire":0,"isApply":0,"h5URL":"https://admin.zhaojinmao.cn/Areas/Product/Html/ProductDetails_8.html"},{"id":177207,"userName":"18369221509","userRealName":"孙启盼","couponId":112,"couponName":"存钱罐30天及以上产品","couponAmount":188,"overAmount":49999,"overGold":0,"bigProductTypeIds":"5","couponDesc":"投资满49999元可用","useRangeDesc":"存钱罐","startTime":"2018-01-01 00:00:00","endTime":"2018-01-31 00:00:00","totalDays":30,"isUse":0,"useTime":"0001-01-01 00:00:00","createTime":"2018-01-26 16:15:09","productIds":"8,9,10,11","couponType":0,"increaseFormula":"","increaseDay":0,"increaseValue":0,"isExpire":0,"isApply":0,"h5URL":"https://admin.zhaojinmao.cn/Areas/Product/Html/ProductDetails_8.html"},{"id":177206,"userName":"18369221509","userRealName":"孙启盼","couponId":111,"couponName":"存钱罐30天以及上产品","couponAmount":38,"overAmount":9999,"overGold":0,"bigProductTypeIds":"5","couponDesc":"投资满9999元可用","useRangeDesc":"存钱罐","startTime":"2018-01-01 00:00:00","endTime":"2018-01-31 00:00:00","totalDays":30,"isUse":0,"useTime":"0001-01-01 00:00:00","createTime":"2018-01-26 16:15:03","productIds":"8,9,10,11","couponType":0,"increaseFormula":"","increaseDay":0,"increaseValue":0,"isExpire":0,"isApply":0,"h5URL":"https://admin.zhaojinmao.cn/Areas/Product/Html/ProductDetails_8.html"},{"id":177205,"userName":"18369221509","userRealName":"孙启盼","couponId":110,"couponName":"存钱罐30天以及上产品","couponAmount":18,"overAmount":4999,"overGold":0,"bigProductTypeIds":"5","couponDesc":"投资满4999元可用","useRangeDesc":"存钱罐","startTime":"2018-01-01 00:00:00","endTime":"2018-01-31 00:00:00","totalDays":30,"isUse":0,"useTime":"0001-01-01 00:00:00","createTime":"2018-01-26 16:14:59","productIds":"8,9,10,11","couponType":0,"increaseFormula":"","increaseDay":0,"increaseValue":0,"isExpire":0,"isApply":0,"h5URL":"https://admin.zhaojinmao.cn/Areas/Product/Html/ProductDetails_8.html"},{"id":177204,"userName":"18369221509","userRealName":"孙启盼","couponId":109,"couponName":"存钱罐30天以及上产品","couponAmount":5,"overAmount":999,"overGold":0,"bigProductTypeIds":"5","couponDesc":"投资满999元可用","useRangeDesc":"存钱罐","startTime":"2018-01-01 00:00:00","endTime":"2018-01-31 00:00:00","totalDays":30,"isUse":0,"useTime":"0001-01-01 00:00:00","createTime":"2018-01-26 16:14:55","productIds":"8,9,10,11","couponType":0,"increaseFormula":"","increaseDay":0,"increaseValue":0,"isExpire":0,"isApply":0,"h5URL":"https://admin.zhaojinmao.cn/Areas/Product/Html/ProductDetails_8.html"},{"id":177201,"userName":"18369221509","userRealName":"孙启盼","couponId":106,"couponName":"反馈·满减券·","couponAmount":188,"overAmount":5000,"overGold":0,"bigProductTypeIds":"5","couponDesc":"投资存钱罐满5000可用","useRangeDesc":"存钱罐","startTime":"2018-01-02 00:00:00","endTime":"2018-01-31 00:00:00","totalDays":29,"isUse":0,"useTime":"0001-01-01 00:00:00","createTime":"2018-01-26 16:14:40","productIds":"8","couponType":0,"increaseFormula":"","increaseDay":0,"increaseValue":0,"isExpire":0,"isApply":0,"h5URL":"https://admin.zhaojinmao.cn/Areas/Product/Html/ProductDetails_8.html"},{"id":177200,"userName":"18369221509","userRealName":"孙启盼","couponId":105,"couponName":"反馈·满减券·","couponAmount":388,"overAmount":10000,"overGold":0,"bigProductTypeIds":"5","couponDesc":"投资存钱罐满10000可用","useRangeDesc":"存钱罐","startTime":"2018-01-02 00:00:00","endTime":"2018-01-31 00:00:00","totalDays":29,"isUse":0,"useTime":"0001-01-01 00:00:00","createTime":"2018-01-26 16:14:35","productIds":"8","couponType":0,"increaseFormula":"","increaseDay":0,"increaseValue":0,"isExpire":0,"isApply":0,"h5URL":"https://admin.zhaojinmao.cn/Areas/Product/Html/ProductDetails_8.html"}]
     */

    private int currentPage;
    private int itemsPerPage;
    private int totalPages;
    private int totalItems;
    private List<ItemsBean> items;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getItemsPerPage() {
        return itemsPerPage;
    }

    public void setItemsPerPage(int itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }

    public static class ItemsBean {
        /**
         * id : 177211
         * userName : 18369221509
         * userRealName : 孙启盼
         * couponId : 121
         * couponName : 金矿存钱罐优惠券
         * couponAmount : 201.8
         * overAmount : 10000
         * overGold : 0
         * bigProductTypeIds : 5
         * couponDesc : 存钱罐90天及以上满10000元可用
         * useRangeDesc : 存钱罐
         * startTime : 2018-01-15 00:00:00
         * endTime : 2018-01-31 00:00:00
         * totalDays : 16
         * isUse : 0
         * useTime : 0001-01-01 00:00:00
         * createTime : 2018-01-26 16:15:55
         * productIds : 9,10,11
         * couponType : 0
         * increaseFormula :
         * increaseDay : 0
         * increaseValue : 0
         * isExpire : 0
         * isApply : 0
         * h5URL : https://admin.zhaojinmao.cn/Areas/Product/Html/ProductDetails_9.html
         */

        private int id;
        private String userName;
        private String userRealName;
        private int couponId;
        private String couponName;
        private double couponAmount;
        private int overAmount;
        private int overGold;
        private String bigProductTypeIds;
        private String couponDesc;
        private String useRangeDesc;
        private String startTime;
        private String endTime;
        private int totalDays;
        private int isUse;
        private String useTime;
        private String createTime;
        private String productIds;
        private int couponType;
        private String increaseFormula;
        private int increaseDay;
        private int increaseValue;
        private int isExpire;
        private int isApply;
        private String h5URL;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserRealName() {
            return userRealName;
        }

        public void setUserRealName(String userRealName) {
            this.userRealName = userRealName;
        }

        public int getCouponId() {
            return couponId;
        }

        public void setCouponId(int couponId) {
            this.couponId = couponId;
        }

        public String getCouponName() {
            return couponName;
        }

        public void setCouponName(String couponName) {
            this.couponName = couponName;
        }

        public double getCouponAmount() {
            return couponAmount;
        }

        public void setCouponAmount(double couponAmount) {
            this.couponAmount = couponAmount;
        }

        public int getOverAmount() {
            return overAmount;
        }

        public void setOverAmount(int overAmount) {
            this.overAmount = overAmount;
        }

        public int getOverGold() {
            return overGold;
        }

        public void setOverGold(int overGold) {
            this.overGold = overGold;
        }

        public String getBigProductTypeIds() {
            return bigProductTypeIds;
        }

        public void setBigProductTypeIds(String bigProductTypeIds) {
            this.bigProductTypeIds = bigProductTypeIds;
        }

        public String getCouponDesc() {
            return couponDesc;
        }

        public void setCouponDesc(String couponDesc) {
            this.couponDesc = couponDesc;
        }

        public String getUseRangeDesc() {
            return useRangeDesc;
        }

        public void setUseRangeDesc(String useRangeDesc) {
            this.useRangeDesc = useRangeDesc;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public int getTotalDays() {
            return totalDays;
        }

        public void setTotalDays(int totalDays) {
            this.totalDays = totalDays;
        }

        public int getIsUse() {
            return isUse;
        }

        public void setIsUse(int isUse) {
            this.isUse = isUse;
        }

        public String getUseTime() {
            return useTime;
        }

        public void setUseTime(String useTime) {
            this.useTime = useTime;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getProductIds() {
            return productIds;
        }

        public void setProductIds(String productIds) {
            this.productIds = productIds;
        }

        public int getCouponType() {
            return couponType;
        }

        public void setCouponType(int couponType) {
            this.couponType = couponType;
        }

        public String getIncreaseFormula() {
            return increaseFormula;
        }

        public void setIncreaseFormula(String increaseFormula) {
            this.increaseFormula = increaseFormula;
        }

        public int getIncreaseDay() {
            return increaseDay;
        }

        public void setIncreaseDay(int increaseDay) {
            this.increaseDay = increaseDay;
        }

        public int getIncreaseValue() {
            return increaseValue;
        }

        public void setIncreaseValue(int increaseValue) {
            this.increaseValue = increaseValue;
        }

        public int getIsExpire() {
            return isExpire;
        }

        public void setIsExpire(int isExpire) {
            this.isExpire = isExpire;
        }

        public int getIsApply() {
            return isApply;
        }

        public void setIsApply(int isApply) {
            this.isApply = isApply;
        }

        public String getH5URL() {
            return h5URL;
        }

        public void setH5URL(String h5URL) {
            this.h5URL = h5URL;
        }
    }
}
