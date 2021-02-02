<%@ page contentType="text/html;charset=utf-8" %>
<!DOCTYPE html>
<%@ include file="taglibs.jsp" %>
<%@include file="path.jsp"%>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
    <style>
        html,
        body,
        #container {
            width: 100%;
            height: 100%;
        }
    </style>
    <title>椭圆的绘制和编辑</title>
    <link rel="stylesheet" href="https://a.amap.com/jsapi_demos/static/demo-center/css/demo-center.css" />
    <script src="https://webapi.amap.com/maps?v=1.4.15&key=0b49a5b1f351d9c9e52406e1f4afc858&plugin=AMap.MouseTool&plugin=AMap.MapType&plugin=AMap.PolyEditor"></script>
    <script src="https://a.amap.com/jsapi_demos/static/demo-center/js/demoutils.js"></script>
</head>
<body>

<div id='container'></div>

<div class="input-card" style="width: 200px">
    <button class="btn" onclick="drawPolygon()" style="margin-bottom: 5px">绘制多边形</button>
<%--    <button class="btn" onclick="polyEditor.open()" style="margin-bottom: 5px">开始编辑</button>--%>
</div>

<jsp:include page="commonJs.jsp"/>

<script>
    let map = new AMap.Map("container", {
        center: [121.317175, 31.229079],
        zoom: 20
    });

    let pathList = [];

    map.addControl(new AMap.MapType({
        defaultType:1 //0代表默认，1代表卫星
    }));

    let mouseTool = new AMap.MouseTool(map);

    // let polygon = new AMap.Polygon({
    //     path: path,
    //     strokeColor: "#FF33FF",
    //     strokeWeight: 6,
    //     strokeOpacity: 0.2,
    //     fillOpacity: 0.4,
    //     fillColor: '#1791fc',
    //     zIndex: 50,
    // });
    //
    // let polyEditor = new AMap.PolyEditor(map, polygon);

    function drawPolygon () {
        mouseTool.polygon({
            strokeColor: "rgba(255,12,11,0.99)",
            strokeOpacity: 1,
            strokeWeight: 6,
            strokeOpacity: 0.5,
            fillColor: 'rgba(250,240,9,0.83)',
            fillOpacity: 0.4,
            strokeStyle: "solid",
        })
    }

    mouseTool.on('draw', function(event) {
        let paths = [];
        event.obj.w.path.forEach(p => {
            paths.push(p.lng + "," + p.lat);
        });
        let building = {};
        building.name = '五角大楼';
        building.paths = paths.join(";");
        saveBuilding(building);
    });


    function saveBuilding(building) {
        let projectId = 1;
        let name = building.name;
        let paths = building.paths;
        let floorCount = 6;
        let floorHeight = 4;
        $.ajax({
            type: 'POST',
            url: _path + '/project/saveBuilding',
            data: {
                projectId: projectId,
                name: name,
                paths: paths,
                floorCount: floorCount,
                floorHeight: floorHeight
            },
            success: function (result) {
                if (result.code === '0') {
                    console.log('成功');
                }else {
                    console.log('失败');
                }
            }
        });
    }
</script>

</body>
</html>
