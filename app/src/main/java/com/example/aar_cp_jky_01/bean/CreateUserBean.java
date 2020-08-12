package com.example.aar_cp_jky_01.bean;

import java.io.Serializable;
import java.util.List;

public class CreateUserBean implements Serializable {
    /**
     * code : 0
     * message : 用户创建成功
     * server_params : {"batch_code":"146053","cusInfo":{"analysis_location":"36.110.14.218-36.110.14.218-36.110.14.218","batch_code":"146053","batch_time":"20191023125253","begin_date":"","browser":"","browser_title":"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.120 Safari/537.36","create_time":"","current_ip":"36.110.14.218","current_location":"36.110.14.218北京电信110100","current_page":"","customer_Region":"北京","customer_id":"390306","customer_name":"小常","dialoge_mark":"","dialoge_mark_note":"","dialogue_end":"","dialogue_start":"","dialogue_type_id":"","end_date":"","evaluate_include":"","evaluate_item_title":"","explain":"","first_visit_time":"","ip_explain":"110100","language":"","last_customer_set":"","last_visit_time":"","merchant_id":"5","monitor_color":"","open_dialoge_url":"","permanent_identity":"a17bcbe1e209fed4beb8228e033fa6b1","r":"","resolution":"","rownum":"","service_speak_count":"","site_id":"5","source_page":"","source_search_engines":"","source_search_keyword":"","time_zone":"","used_system":"","visit_count":"","visit_page_count":"","visit_time":"20191023125253","visitor_speak_count":""},"customer_id":"390306","offlinemesg":[]}
     * server_code :
     */

    private String code;
    private String message;
    private ServerParamsBean server_params;
    private String server_code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ServerParamsBean getServer_params() {
        return server_params;
    }

    public void setServer_params(ServerParamsBean server_params) {
        this.server_params = server_params;
    }

    public String getServer_code() {
        return server_code;
    }

    public void setServer_code(String server_code) {
        this.server_code = server_code;
    }

    public static class ServerParamsBean  implements Serializable{
        /**
         * batch_code : 146053
         * cusInfo : {"analysis_location":"36.110.14.218-36.110.14.218-36.110.14.218","batch_code":"146053","batch_time":"20191023125253","begin_date":"","browser":"","browser_title":"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.120 Safari/537.36","create_time":"","current_ip":"36.110.14.218","current_location":"36.110.14.218北京电信110100","current_page":"","customer_Region":"北京","customer_id":"390306","customer_name":"小常","dialoge_mark":"","dialoge_mark_note":"","dialogue_end":"","dialogue_start":"","dialogue_type_id":"","end_date":"","evaluate_include":"","evaluate_item_title":"","explain":"","first_visit_time":"","ip_explain":"110100","language":"","last_customer_set":"","last_visit_time":"","merchant_id":"5","monitor_color":"","open_dialoge_url":"","permanent_identity":"a17bcbe1e209fed4beb8228e033fa6b1","r":"","resolution":"","rownum":"","service_speak_count":"","site_id":"5","source_page":"","source_search_engines":"","source_search_keyword":"","time_zone":"","used_system":"","visit_count":"","visit_page_count":"","visit_time":"20191023125253","visitor_speak_count":""}
         * customer_id : 390306
         * offlinemesg : []
         */

        private String batch_code;
        private CusInfoBean cusInfo;
        private String customer_id;
        private List<?> offlinemesg;

        public String getBatch_code() {
            return batch_code;
        }

        public void setBatch_code(String batch_code) {
            this.batch_code = batch_code;
        }

        public CusInfoBean getCusInfo() {
            return cusInfo;
        }

        public void setCusInfo(CusInfoBean cusInfo) {
            this.cusInfo = cusInfo;
        }

        public String getCustomer_id() {
            return customer_id;
        }

        public void setCustomer_id(String customer_id) {
            this.customer_id = customer_id;
        }

        public List<?> getOfflinemesg() {
            return offlinemesg;
        }

        public void setOfflinemesg(List<?> offlinemesg) {
            this.offlinemesg = offlinemesg;
        }

        public static class CusInfoBean  implements Serializable{
            /**
             * analysis_location : 36.110.14.218-36.110.14.218-36.110.14.218
             * batch_code : 146053
             * batch_time : 20191023125253
             * begin_date :
             * browser :
             * browser_title : Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.120 Safari/537.36
             * create_time :
             * current_ip : 36.110.14.218
             * current_location : 36.110.14.218北京电信110100
             * current_page :
             * customer_Region : 北京
             * customer_id : 390306
             * customer_name : 小常
             * dialoge_mark :
             * dialoge_mark_note :
             * dialogue_end :
             * dialogue_start :
             * dialogue_type_id :
             * end_date :
             * evaluate_include :
             * evaluate_item_title :
             * explain :
             * first_visit_time :
             * ip_explain : 110100
             * language :
             * last_customer_set :
             * last_visit_time :
             * merchant_id : 5
             * monitor_color :
             * open_dialoge_url :
             * permanent_identity : a17bcbe1e209fed4beb8228e033fa6b1
             * r :
             * resolution :
             * rownum :
             * service_speak_count :
             * site_id : 5
             * source_page :
             * source_search_engines :
             * source_search_keyword :
             * time_zone :
             * used_system :
             * visit_count :
             * visit_page_count :
             * visit_time : 20191023125253
             * visitor_speak_count :
             */

            private String analysis_location;
            private String batch_code;
            private String batch_time;
            private String begin_date;
            private String browser;
            private String browser_title;
            private String create_time;
            private String current_ip;
            private String current_location;
            private String current_page;
            private String customer_Region;
            private String customer_id;
            private String customer_name;
            private String dialoge_mark;
            private String dialoge_mark_note;
            private String dialogue_end;
            private String dialogue_start;
            private String dialogue_type_id;
            private String end_date;
            private String evaluate_include;
            private String evaluate_item_title;
            private String explain;
            private String first_visit_time;
            private String ip_explain;
            private String language;
            private String last_customer_set;
            private String last_visit_time;
            private String merchant_id;
            private String monitor_color;
            private String open_dialoge_url;
            private String permanent_identity;
            private String r;
            private String resolution;
            private String rownum;
            private String service_speak_count;
            private String site_id;
            private String source_page;
            private String source_search_engines;
            private String source_search_keyword;
            private String time_zone;
            private String used_system;
            private String visit_count;
            private String visit_page_count;
            private String visit_time;
            private String visitor_speak_count;

            public String getAnalysis_location() {
                return analysis_location;
            }

            public void setAnalysis_location(String analysis_location) {
                this.analysis_location = analysis_location;
            }

            public String getBatch_code() {
                return batch_code;
            }

            public void setBatch_code(String batch_code) {
                this.batch_code = batch_code;
            }

            public String getBatch_time() {
                return batch_time;
            }

            public void setBatch_time(String batch_time) {
                this.batch_time = batch_time;
            }

            public String getBegin_date() {
                return begin_date;
            }

            public void setBegin_date(String begin_date) {
                this.begin_date = begin_date;
            }

            public String getBrowser() {
                return browser;
            }

            public void setBrowser(String browser) {
                this.browser = browser;
            }

            public String getBrowser_title() {
                return browser_title;
            }

            public void setBrowser_title(String browser_title) {
                this.browser_title = browser_title;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getCurrent_ip() {
                return current_ip;
            }

            public void setCurrent_ip(String current_ip) {
                this.current_ip = current_ip;
            }

            public String getCurrent_location() {
                return current_location;
            }

            public void setCurrent_location(String current_location) {
                this.current_location = current_location;
            }

            public String getCurrent_page() {
                return current_page;
            }

            public void setCurrent_page(String current_page) {
                this.current_page = current_page;
            }

            public String getCustomer_Region() {
                return customer_Region;
            }

            public void setCustomer_Region(String customer_Region) {
                this.customer_Region = customer_Region;
            }

            public String getCustomer_id() {
                return customer_id;
            }

            public void setCustomer_id(String customer_id) {
                this.customer_id = customer_id;
            }

            public String getCustomer_name() {
                return customer_name;
            }

            public void setCustomer_name(String customer_name) {
                this.customer_name = customer_name;
            }

            public String getDialoge_mark() {
                return dialoge_mark;
            }

            public void setDialoge_mark(String dialoge_mark) {
                this.dialoge_mark = dialoge_mark;
            }

            public String getDialoge_mark_note() {
                return dialoge_mark_note;
            }

            public void setDialoge_mark_note(String dialoge_mark_note) {
                this.dialoge_mark_note = dialoge_mark_note;
            }

            public String getDialogue_end() {
                return dialogue_end;
            }

            public void setDialogue_end(String dialogue_end) {
                this.dialogue_end = dialogue_end;
            }

            public String getDialogue_start() {
                return dialogue_start;
            }

            public void setDialogue_start(String dialogue_start) {
                this.dialogue_start = dialogue_start;
            }

            public String getDialogue_type_id() {
                return dialogue_type_id;
            }

            public void setDialogue_type_id(String dialogue_type_id) {
                this.dialogue_type_id = dialogue_type_id;
            }

            public String getEnd_date() {
                return end_date;
            }

            public void setEnd_date(String end_date) {
                this.end_date = end_date;
            }

            public String getEvaluate_include() {
                return evaluate_include;
            }

            public void setEvaluate_include(String evaluate_include) {
                this.evaluate_include = evaluate_include;
            }

            public String getEvaluate_item_title() {
                return evaluate_item_title;
            }

            public void setEvaluate_item_title(String evaluate_item_title) {
                this.evaluate_item_title = evaluate_item_title;
            }

            public String getExplain() {
                return explain;
            }

            public void setExplain(String explain) {
                this.explain = explain;
            }

            public String getFirst_visit_time() {
                return first_visit_time;
            }

            public void setFirst_visit_time(String first_visit_time) {
                this.first_visit_time = first_visit_time;
            }

            public String getIp_explain() {
                return ip_explain;
            }

            public void setIp_explain(String ip_explain) {
                this.ip_explain = ip_explain;
            }

            public String getLanguage() {
                return language;
            }

            public void setLanguage(String language) {
                this.language = language;
            }

            public String getLast_customer_set() {
                return last_customer_set;
            }

            public void setLast_customer_set(String last_customer_set) {
                this.last_customer_set = last_customer_set;
            }

            public String getLast_visit_time() {
                return last_visit_time;
            }

            public void setLast_visit_time(String last_visit_time) {
                this.last_visit_time = last_visit_time;
            }

            public String getMerchant_id() {
                return merchant_id;
            }

            public void setMerchant_id(String merchant_id) {
                this.merchant_id = merchant_id;
            }

            public String getMonitor_color() {
                return monitor_color;
            }

            public void setMonitor_color(String monitor_color) {
                this.monitor_color = monitor_color;
            }

            public String getOpen_dialoge_url() {
                return open_dialoge_url;
            }

            public void setOpen_dialoge_url(String open_dialoge_url) {
                this.open_dialoge_url = open_dialoge_url;
            }

            public String getPermanent_identity() {
                return permanent_identity;
            }

            public void setPermanent_identity(String permanent_identity) {
                this.permanent_identity = permanent_identity;
            }

            public String getR() {
                return r;
            }

            public void setR(String r) {
                this.r = r;
            }

            public String getResolution() {
                return resolution;
            }

            public void setResolution(String resolution) {
                this.resolution = resolution;
            }

            public String getRownum() {
                return rownum;
            }

            public void setRownum(String rownum) {
                this.rownum = rownum;
            }

            public String getService_speak_count() {
                return service_speak_count;
            }

            public void setService_speak_count(String service_speak_count) {
                this.service_speak_count = service_speak_count;
            }

            public String getSite_id() {
                return site_id;
            }

            public void setSite_id(String site_id) {
                this.site_id = site_id;
            }

            public String getSource_page() {
                return source_page;
            }

            public void setSource_page(String source_page) {
                this.source_page = source_page;
            }

            public String getSource_search_engines() {
                return source_search_engines;
            }

            public void setSource_search_engines(String source_search_engines) {
                this.source_search_engines = source_search_engines;
            }

            public String getSource_search_keyword() {
                return source_search_keyword;
            }

            public void setSource_search_keyword(String source_search_keyword) {
                this.source_search_keyword = source_search_keyword;
            }

            public String getTime_zone() {
                return time_zone;
            }

            public void setTime_zone(String time_zone) {
                this.time_zone = time_zone;
            }

            public String getUsed_system() {
                return used_system;
            }

            public void setUsed_system(String used_system) {
                this.used_system = used_system;
            }

            public String getVisit_count() {
                return visit_count;
            }

            public void setVisit_count(String visit_count) {
                this.visit_count = visit_count;
            }

            public String getVisit_page_count() {
                return visit_page_count;
            }

            public void setVisit_page_count(String visit_page_count) {
                this.visit_page_count = visit_page_count;
            }

            public String getVisit_time() {
                return visit_time;
            }

            public void setVisit_time(String visit_time) {
                this.visit_time = visit_time;
            }

            public String getVisitor_speak_count() {
                return visitor_speak_count;
            }

            public void setVisitor_speak_count(String visitor_speak_count) {
                this.visitor_speak_count = visitor_speak_count;
            }
        }
    }
}
