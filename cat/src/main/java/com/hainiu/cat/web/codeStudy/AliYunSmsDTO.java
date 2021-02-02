package com.hainiu.cat.web.codeStudy;

import java.io.Serializable;

/**
 * create by biji.zhao on 2020/12/9
 */
public class AliYunSmsDTO implements Serializable {
    private String templateCode;

    private String templateParam;

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

    public String getTemplateParam() {
        return templateParam;
    }

    public void setTemplateParam(String templateParam) {
        this.templateParam = templateParam;
    }

    @Override
    public String toString() {
        return "AliYunSmsDTO{" +
                "templateCode='" + templateCode + '\'' +
                ", templateParam='" + templateParam + '\'' +
                '}';
    }
}
