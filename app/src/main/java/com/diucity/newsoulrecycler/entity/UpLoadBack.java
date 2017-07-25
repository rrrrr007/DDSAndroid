package com.diucity.newsoulrecycler.entity;

import java.util.List;

/**
 * By：CH  on 2017/7/13 0013
 * <p>
 * email：576748006@qq.com
 */
public class UpLoadBack {

    /**
     * result : {"code":5601,"message":"回收人员编号:a 不存在或未审核通过"}
     * failure : 0.0
     * codes : []
     */

    private ResultBean result;
    private int failure;
    private List<Integer> codes;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getFailure() {
        return failure;
    }

    public void setFailure(int failure) {
        this.failure = failure;
    }

    public List<?> getCodes() {
        return codes;
    }

    public void setCodes(List<Integer> codes) {
        this.codes = codes;
    }

    public static class ResultBean {
        /**
         * code : 5601.0
         * message : 回收人员编号:a 不存在或未审核通过
         */

        private int code;
        private String message;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
