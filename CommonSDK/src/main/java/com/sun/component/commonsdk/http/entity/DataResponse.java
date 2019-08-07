package com.sun.component.commonsdk.http.entity;

/**
 * Created by ChenYuYun on 2018/8/10.
 * 数据返回基类
 */
public class DataResponse<T, T2> {
    public T data;
    public T2 data2;
    /**
     * result : {"msg":"wrong token","code":909,"success":false}
     */

    public ResultBean result;
    public int totalCount;

    public DataResponse(T data, T2 data2) {
        this.data = data;
        this.data2 = data2;
    }

    @Override
    public String toString() {
        return "DataResponseExt{" +
                "data=" + data +
                ", data2=" + data2 +
                ", result=" + result +
                ", totalCount=" + totalCount +
                '}';
    }

    /**
     * 获取总页数
     *
     * @return
     */
    public int getTotalPage(int pageSize) {
        return (int) Math.ceil(totalCount*1.0 / pageSize);
    }

    public static class ResultBean {
        /**
         * msg : wrong token
         * code : 909
         * success : false
         */

        private String msg;
        private int code;
        private boolean success;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        @Override
        public String toString() {
            return "ResultBean{" +
                    "msg='" + msg + '\'' +
                    ", code=" + code +
                    ", success=" + success +
                    '}';
        }
    }
}
