package com.hainiu.cat.web.controller;

import com.alibaba.fastjson.JSON;
import com.hainiu.cat.service.ZhiHuHotService;
import com.hainiu.cat.service.dto.ZhiHuHotDTO;
import com.hainiu.cat.util.Result;
import com.hainiu.cat.web.vo.JsonReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * create by biji.zhao on 2020/11/12
 */
@RequestMapping("/zhiHu")
@Controller
public class ZhiHuHotController {

    @Autowired
    private ZhiHuHotService zhiHuHotService;

    @Autowired
    private WebSocketServer webSocketServer;

    @RequestMapping("save")
    @ResponseBody
    public JsonReturn saveOne(ZhiHuHotDTO huHotDTO) {
        Result<ZhiHuHotDTO> result = zhiHuHotService.saveOne(huHotDTO);
        if (result.isSuccess()) {
            try {
                webSocketServer.sendInfo(JSON.toJSONString(result.getData()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return JsonReturn.successInstance();
    }

    @RequestMapping("table")
    public String customerPage() {
        return "zhiHuHot";
    }

    @RequestMapping(value = "list")
    @ResponseBody
    public JsonReturn queryList() {
        return JsonReturn.successInstance(zhiHuHotService.queryList());
    }

    @RequestMapping("execute")
    @ResponseBody
    public JsonReturn execute() {
        String command = "cmd /k \"cd /d D:\\myself\\cat\\src\\pupeteer&&node src/ZhiHuExecutor.js";
        //cmd /c为执行完命令后关闭cmd窗口，若将c改成k，则为执行完命令后cmd窗口不关闭！
        try {
            Runtime.getRuntime().exec(command);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JsonReturn.successInstance();
    }

}
