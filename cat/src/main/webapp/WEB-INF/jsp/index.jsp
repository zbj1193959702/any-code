<%@ page contentType="text/html;charset=utf-8" %>
<!DOCTYPE html>
<%@ include file="taglibs.jsp" %>
<%@ include file="path.jsp" %>
<html>
<head>
    <title>旅游</title>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="referrer" content="no-referrer" />
    <link rel="stylesheet" media="screen" href="${webPath}/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="http://outter-common.toodc.cn/static-source/theme-chalk-index_2.13.0.css"/>
</head>
<style>

    .active-text{
        font-size: 35px;
        color: rgba(145, 202, 255, 0.02);
        text-shadow:
                7px -7px 0 rgba(19, 1, 1, 0.03),
                8px -8px 0 rgba(19, 1, 1, 0.5),
                9px -9px 0 rgba(75, 4, 3, 0.03),
                10px -10px 0 rgba(19, 1, 1, 0.05);
    }
</style>
<body style="background-image: url(/static/image/index.jpg);">

<div id="index-el">
    <template>
        <el-row style="padding-top: 40px;">
            <el-col :span="1">
                <span>.</span>
            </el-col>
            <el-col :span="2" v-for="(page, index) in pageList" :key="index">
                <el-button @click="seePage(page.url)" type="text">
                    <span class="active-text">{{page.name}}</span>
                </el-button>
            </el-col>
        </el-row>
    </template>
</div>

<jsp:include page="commonJs.jsp"/>

<script>

    var vm = new Vue({
        el: '#index-el',
        data: function () {
            return {
                pageList:[
                    {
                        name: '2D',
                        url: _path +'/house/map2d',
                    },
                    {
                        name: '3D',
                        url: _path +'/house/map3d',
                    },
                    {
                        name: '房源',
                        url: _path +'/house/table',
                    },
                    {
                        name: '旅游',
                        url: _path +'/scenicSpot/table',
                    },
                    {
                        name: '备忘录',
                        url: _path +'/memorandum/index',
                    },
                    {
                        name: '热搜',
                        url: _path +'/zhiHu/table',
                    },
                ],
            };
        },
        mounted:function() {

        },
        methods: {
            seePage(url) {
                window.open(url);
            }
        }
    });

</script>
</body>
</html>
