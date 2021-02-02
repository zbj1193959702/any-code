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
    .el-pagination {
        text-align: center;
        margin-top: 10px;
        margin-bottom: 50px
    }

    .el-input-group>.el-input__inner{
        height: 55px;
    }

    #house > div{
        background: -moz-linear-gradient(top, rgba(0, 0, 0, 0.43) 0%, #ffd6ab 100%);
        background: -webkit-gradient(linear, left top, left bottom, color-stop(0%, rgba(242, 216, 96, 0.57)), color-stop(100%, rgba(0, 0, 0, 0.15)));
        background: -webkit-linear-gradient(top, rgba(0, 0, 0, 0.35) 0%, rgba(255, 246, 206, 0.02) 100%);
        background: -o-linear-gradient(top, rgba(0, 0, 0, 0.48) 0%, rgba(255, 242, 206, 0.39) 100%);
        background: -ms-linear-gradient(top, rgba(242, 216, 96, 0.39) 0%, rgba(0, 0, 0, 0) 100%);
        background: linear-gradient(to bottom, rgba(0, 0, 0, 0.45) 0%, rgba(0, 0, 0, 0.19) 100%);
    }
</style>
<body>

<div id="house">
    <template>
        <div style="padding-bottom: 50px;padding-top: 50px">
            <el-row style="padding-top: 20px;padding-bottom: 50px">
                <el-col :span="12" :offset="6">
                    <el-card :body-style="{ padding: '0px' }">
                        <el-input style="height: 55px" placeholder="搜索内容" v-model="keyword">
                            <el-button slot="append" icon="el-icon-search"></el-button>
                        </el-input>
                    </el-card>
                </el-col>
            </el-row>
            <el-row :gutter="20" v-for="item in tableData" style="padding-top: 5px;">
                <el-col :span="16" :offset="4">
                    <el-card class="box-card">
                        <el-container>
                            <el-aside width="200px">
                                <img width="200px" height="160px" :src="item.firstImage"/>
                            </el-aside>
                            <el-container>
                                <el-header style="height: 15px">
                                    <a @click="showDetail(item.detailUrl)">
                                        <span style="font-size: 20px;font-weight: bolder;">{{item.title}}</span>
                                    </a>
                                </el-header>
                                <el-main>
                                    <el-row>
                                        <el-col :offset="18" :span="6">
                                            <span><span style="color: rgb(242 116 96); font-size: 23px"> {{item.price}} </span>元/平米</span>
                                        </el-col>
                                    </el-row>
                                    <el-row>
                                        <el-col :span="20">
                                            {{item.norms}}
                                        </el-col>
                                    </el-row>
                                    <el-row style="padding-top: 30px">
                                        <el-col :span="20">
                                           <i class="el-icon-location-outline"></i>
                                            <span v-if="item.houseType === 1">
                                                {{item.address}}
                                            </span>
                                            <span v-if="item.houseType === 3">
                                                {{item.province}} - {{item.district}}
                                            </span>
                                        </el-col>
                                    </el-row>
                                    <el-row v-if="item.tagList && item.tagList.length > 0">
                                        <el-col :span="20">
                                            <el-tag size="mini" v-for="tag in item.tagList" :key="tag.name" :type="tag.type">
                                                {{tag.name}}
                                            </el-tag>
                                        </el-col>
                                    </el-row>
                                </el-main>
                            </el-container>
                        </el-container>
                    </el-card>
                </el-col>
            </el-row>
            <el-pagination
                    class="fy"
                    :page-size="pageSize"
                    @current-change="currentChange"
                    layout="total,prev, pager, next,jumper"
                    :total="total"
                    :current-page="currentPage"
                    background>
            </el-pagination>
        </div>
    </template>

    <el-dialog title="此人漂亮吗？？？" :visible.sync="bigImageDialog" :before-close="bigImageClose">
        <div style="padding-top: 5px;margin-bottom: 5px">
            <i class="el-icon-zoom-in" @click="imgW=imgW+200"></i>
            <i class="el-icon-zoom-out" @click="imgW=imgW-100"></i>
        </div>
        <div>
            <img src="/static/image/qiubi.jpeg" :style="{height:imgW+'px'}" style="text-align: center;">
        </div>
        <span slot="footer" class="dialog-footer">
            <el-button @click="noBeauty()">不漂亮</el-button>
            <el-button type="primary" @click="bigImageDialog = false">漂 亮</el-button>
        </span>
    </el-dialog>
</div>

<jsp:include page="commonJs.jsp"/>

<script>

    var vm = new Vue({
        el: '#house',
        data: function () {
            return {
                imgW: 200,
                keyword: null,
                tableData: [],
                pageSize: 20,
                currentPage: 1,
                total: 0,
                bigImageDialog: false,
                bigImageUrl: null,
            };
        },
        mounted:function() {
            this.queryList(1);
        },
        methods: {
            noBeauty() {
                vm.$message.error('选择错误');
            },
            bigImageClose() {
                vm.$message.info('请选择');
            },
            showDetail(url) {
                window.open(url);
            },
            currentChange: function (currentPage) {
                vm.currentPage = currentPage;
                this.queryList(currentPage);
            },
            queryList(pageNumber) {
                let query = {
                    pageStart : pageNumber,
                    pageSize : 20,
                };
                $.ajax({
                    type: 'POST',
                    url: _path + '/house/list',
                    data: {
                        json : JSON.stringify(query)
                    },
                    success: function (result) {
                        if (result.code === '0') {
                            vm.tableData = result.data.records;
                            vm.total = result.data.totalRecordCount;
                            for (let i = 0; i < vm.tableData.length; i++) {
                                 if (vm.tableData[i].tags !== null) {
                                     let tagList = [];
                                     for (let j = 0; j < vm.tableData[i].tags.length; j++) {
                                         let type = '';
                                         if (j % 5 === 0) {
                                             type = '';
                                         }
                                         if (j % 5 === 1) {
                                             type = 'success';
                                         }
                                         if (j % 5 === 2) {
                                             type = 'info';
                                         }
                                         if (j % 5 === 3) {
                                             type = 'warning';
                                         }
                                         if (j % 5 === 4) {
                                             type = 'danger';
                                         }
                                         let tagOne = {};
                                         tagOne.name = vm.tableData[i].tags[j];
                                         tagOne.type = type;
                                         tagList.push(tagOne);
                                     }
                                     vm.tableData[i].tagList = tagList;
                                 }
                            }
                        }
                    }
                });
            }
        }
    });

</script>
</body>
</html>
