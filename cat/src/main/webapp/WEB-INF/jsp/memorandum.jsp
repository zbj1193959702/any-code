<%@ page contentType="text/html;charset=utf-8" %>
<!DOCTYPE html>
<%@ include file="taglibs.jsp" %>
<%@ include file="path.jsp" %>
<html>
<head>
    <title>备忘录</title>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="referrer" content="no-referrer" />
    <link rel="stylesheet" media="screen" href="${webPath}/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="http://outter-common.toodc.cn/static-source/theme-chalk-index_2.13.0.css"/>
</head>
<style>
    body {
        background:  rgba(0, 0, 0, 0.19);
    }

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
</style>
<body>

<div id="memorandum" style="padding-top: 40px;padding-bottom: 40px">
    <template>
        <el-row :gutter="20" v-for="memorandum in memorandumList" style="padding-top: 5px">
            <el-col :offset="5" :span="14">
                <el-card class="box-card">
                    <el-row>
                        <el-col :offset="1" :span="5">
                            <span class="text item" >
                                <el-button type="text" @click="edit(memorandum.id)" style="font-size: 20px">{{memorandum.title}}</el-button>
                            </span>
                        </el-col>
                        <el-col :span="16">
                            <el-row v-for="content in memorandum.contentList">
                                <el-col :span="4">
                                    <span class="text item" style="font-size: 17px">
                                        {{content.desc}}:
                                    </span>
                                 </el-col>
                                 <el-col :span="20">
                                    <span  class="text item" style="font-size: 17px">
                                        {{content.content}}
                                    </span>
                                </el-col>
                            </el-row>
                        </el-col>
                    </el-row>
                </el-card>
            </el-col>
        </el-row>

        <el-dialog title="备忘录" :visible.sync="dialogFormVisible">
            <el-form label-position="right" label-width="20px" :model="memorandum">
                <el-form-item>
                    <el-col :span="8">
                        <el-input v-model="memorandum.title">
                            <template slot="prepend">标 题</template>
                        </el-input>
                    </el-col>
                </el-form-item>
                <template v-for="(detail,index) in memorandum.contentList">
                    <el-form-item>
                        <el-col :span="8">
                            <el-input v-model="detail.desc">
                                <template slot="prepend">{{'描述'+ (index + 1)}}</template>
                            </el-input>
                        </el-col>
                        <el-col :offset="1" :span="14">
                            <el-input v-model="detail.content">
                                <template slot="prepend">{{'内容'+ (index + 1)}}</template>
                            </el-input>
                        </el-col>
                        <el-col :span="1">
                            <el-button type="text" @click="memorandum.contentList.splice(index, 1)"> 移除 </el-button>
                        </el-col>
                    </el-form-item>
                </template>
                <el-form-item >
                    <el-button type="text" @click="addDetail">+增加</el-button>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="saveMemorandum()">确 定</el-button>
            </div>
        </el-dialog>

        <el-button class="top-btn" @click="addMemorandum()">新增</el-button>
    </template>
</div>

<jsp:include page="commonJs.jsp"/>

<script>

    var vm = new Vue({
        el: '#memorandum',
        data: function () {
            return {
                dialogFormVisible: false,
                memorandum: {
                    id: null,
                    title: null,
                    contentList:[
                        {
                            desc: null,
                            content: null
                        }
                    ],
                },
                memorandumList: [],
            };
        },
        mounted:function() {
            this.queryList();
        },
        methods: {
            addMemorandum() {
                this.dialogFormVisible = true;
                this.memorandum =  {
                    id : null,
                    title: null,
                        contentList:[
                        {
                            desc: null,
                            content: null
                        }
                    ],
                };
            },
            edit(id) {
                this.memorandum = null;
                $.ajax({
                    type: 'GET',
                    url: _path + '/memorandum/getById?id='+ id,
                    success: function (result) {
                        if (result.code === '0') {
                            vm.memorandum = result.data;
                            vm.dialogFormVisible = true;
                        }
                    }
                });
            },
            addDetail() {
                this.memorandum.contentList.push({
                    desc: null,
                    content: null
                });
            },
            saveMemorandum() {
                $.ajax({
                    type: 'POST',
                    url: _path + '/memorandum/save',
                    data: {
                        memorandumJson : JSON.stringify(vm.memorandum)
                    },
                    success: function (result) {
                        if (result.code === '0') {
                            vm.dialogFormVisible = false;
                            vm.queryList();
                        }else {
                           vm.dialogFormVisible = true;
                        }
                    }
                });
            },
            queryList() {
                this.memorandumList = [];
                $.ajax({
                    type: 'POST',
                    url: _path + '/memorandum/queryList',
                    success: function (result) {
                        if (result.code === '0') {
                            vm.memorandumList = result.data;
                        }
                    }
                });
            },
        }
    });

</script>
</body>
</html>
