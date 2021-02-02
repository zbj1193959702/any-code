<%@ page contentType="text/html;charset=utf-8" %>
<!DOCTYPE html>
<%@ include file="taglibs.jsp" %>
<%@ include file="path.jsp" %>
<html>
<head>
    <title>知乎</title>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="referrer" content="no-referrer" />
    <link rel="stylesheet" media="screen" href="${webPath}/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="http://outter-common.toodc.cn/static-source/theme-chalk-index_2.13.0.css"/>
</head>
<style>

    .top-btn {
        color: #fff;
        right: 20px;
        bottom: 700px;
        position:fixed;
        width: 56px;
        height: 56px;
        border-radius: 50%;
        z-index: 1500;
        overflow: hidden;
        -webkit-transition-duration: 300ms;
        transition-duration: 300ms;
        box-shadow: 0 10px 20px rgba(0, 0, 0, .19), 0 6px 6px rgba(0, 0, 0, .23);
        display: -webkit-box;
        display: -ms-flexbox;
        display: -webkit-flex;
        display: flex;
        -webkit-box-align: center;
        -ms-flex-align: center;
        -webkit-align-items: center;
        align-items: center;
        -webkit-box-pack: center;
        -ms-flex-pack: center;
        -webkit-justify-content: center;
        justify-content: center;
        background-color: #2196f3;
    }

    .el-card__body{
        padding: 8px;
    }
</style>
<body>

<div id="zhiHuHot">
    <template>
        <el-row :gutter="16" v-for="hot in hotList" style="padding-top: 5px">
            <el-col :span="20" :offset="2">
                <el-card class="box-card" >
                    <el-row>
                        <el-col :span="20">
                            <el-row>
                                <el-col :span="1">
                                    <span style="color: #f1403c;font-size: 17px">{{getRanking(hot.ranking)}}</span>
                                </el-col>
                                <el-col :span="22">
                                    <span style="font-size:18px;margin-bottom:4px;text-overflow: ellipsis;overflow: hidden;">{{hot.title}}</span>
                                </el-col>
                            </el-row>
                            <el-row>
                                <el-col :span="20" :offset="1">
                                    <div style="padding-bottom: 10px"></div>
                                    <span style="color: #535353">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{{subContent(hot.content)}}</span>
                                </el-col>
                            </el-row>
                        </el-col>
                        <el-col :span="4" v-if="hot.image">
                            <el-aside>
                                <img width="120px" height="120px" :src="hot.image"/>
                            </el-aside>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-row>
                            <el-col :span="4" :offset="1">
                                <span style="font-size: 16px;color: #999">{{hot.hot}}</span>
                            </el-col>
                        </el-row>
                    </el-row>
                </el-card>
            </el-col>
        </el-row>

        <el-button class="top-btn" @click="start">更新</el-button>
    </template>
</div>
<jsp:include page="commonJs.jsp"/>
<script>

    let vm = new Vue({
        el: '#zhiHuHot',
        data: function () {
            return {
                hotList: [{
                    title: null,
                    content: null,
                    hot: null,
                    ranking: null,
                    image: null
                }],
            };
        },
        mounted:function() {
            this.queryList();
            this.initSocket();
        },
        methods: {
            getRanking(ranking) {
                if (ranking == null || ranking === '') {
                    return null;
                }
                if (ranking > 9) {
                    return ranking;
                }
                return "0" + ranking;
            },
            subContent(content) {
                if (content == null || content === '') {
                    return null;
                }
                if (content.length > 200) {
                    return content.substring(0, 200) + "...";
                }
                return content;
            },
            start() {
                $.ajax({
                    type: 'POST',
                    url: _path + '/zhiHu/execute',
                    data: {

                    },
                    success: function (result) {
                        if (result.code === '0') {

                        }
                    }
                });
            },
            queryList() {
                $.ajax({
                    type: 'POST',
                    url: _path + '/zhiHu/list',
                    success: function (result) {
                        if (result.code === '0') {
                            vm.hotList = result.data;
                        }
                    }
                });
            },
            initSocket() {
                let _this = this;
                let socket;
                if (typeof (WebSocket) == "undefined") {
                    console.log("您的浏览器不支持WebSocket");
                } else {
                    //实现化WebSocket对象，指定要连接的服务器地址与端口  建立连接
                    socket = new WebSocket("ws://localhost:8008/websocket");
                    //打开事件
                    socket.onopen = function () {
                        // socket.send("这是来自客户端的消息" + location.href + new Date());
                    };
                    //获得消息事件
                    socket.onmessage = function (msg) {
                        if (msg != null && msg.data != null && msg.data !== 'success') {
                            let hotOne = null;
                            try {
                                hotOne = JSON.parse(msg.data);
                            }catch (e) {

                            }
                            if (hotOne == null || hotOne.ranking == null) {
                                return;
                            }
                            let ranking = Number(hotOne.ranking);
                            let len = _this.hotList.length;

                            if (document.querySelector('#zhiHuHot > div:nth-child('+(ranking)+')') != null) {
                                document.querySelector('#zhiHuHot > div:nth-child('+(ranking)+')').scrollIntoView()
                            }
                            if (len === 0 || Number(_this.hotList[0].ranking) > Number(ranking)) {
                                _this.hotList.splice(0, 0, hotOne);
                            }else {
                                if (_this.hotList[ranking - 1].title !== hotOne.title) {
                                    vm.$message.success('排行榜第' + ranking + '更新');
                                }
                                _this.hotList.splice(ranking - 1, 1, hotOne)
                            }
                        }
                    };
                    //关闭事件
                    socket.onclose = function () {
                        console.log("Socket已关闭");
                    };
                    //发生了错误事件
                    socket.onerror = function () {
                        alert("socket connect error");
                    }
                }
            }
        }
    });

</script>
</body>
</html>
