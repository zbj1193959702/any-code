<%@ page contentType="text/html;charset=utf-8" %>
<!DOCTYPE html>
<%@ include file="taglibs.jsp" %>
<%@include file="path.jsp"%>
<html>
<head>
    <title>地图</title>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="referrer" content="no-referrer" />
    <link rel="stylesheet" media="screen" href="${webPath}/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="http://outter-common.toodc.cn/static-source/theme-chalk-index_2.13.0.css"/>
</head>
<style>
    #map-3d {
        position: absolute;
        top: 0;
        bottom: 0;
        width: 100%;
    }
</style>
<body>

<div id='map-3d'></div>
<input id="project-id" type="hidden" value="${id}"/>

<jsp:include page="commonJs.jsp"/>

<script src="https://webapi.amap.com/maps?v=1.4.15&key=0b49a5b1f351d9c9e52406e1f4afc858&plugin=Map3D&plugin=AMap.ControlBar,AMap.MapType"></script>
<script src="https://webapi.amap.com/ui/1.1/main.js"></script>

<script>

    let map;
    let floorLineC = [9 / 255, 0 / 255, 0 / 255, 0.99];
    let fc = "rgba(84,183,93,0.46)";
    let object3Dlayer;
    let hasUnAreaColor = [10 / 255, 183 / 255, 168 / 255, 0.66];
    let noUnAreaColor = [131 / 255, 131 / 255, 131 / 255, 0.61];
    let baseH = 30;
    let floorColor = [236 / 255, 245 / 255, 255 / 255, 0.35];

    function addPlugin() {
        AMapUI.loadUI(['control/BasicControl'], function (BasicControl) {
            //图层切换控件
            map.addControl(new BasicControl.LayerSwitcher({
                position: 'rt' //right top，右上角
            }));
        });
    }

    function drawOneBuild(model) {
        let paths = [];
        model.paths.split(";").forEach(lngLat => {
            paths.push([lngLat.split(",")[0], lngLat.split(",")[1]]);
        });
        for (let floor = 0; floor < model.floorCount; floor++) {
            object3Dlayer = new AMap.Object3DLayer();
            map.add(object3Dlayer);
            let bounds = paths.map(function (p) {
                return new AMap.LngLat(p[0], p[1]);
            });

            let startHeight = floor * baseH * model.floorHeight;
            let endHeight = (floor + 1) * baseH * model.floorHeight;

            let baseLine = drawLine(bounds, startHeight, floorLineC, 1);
            object3Dlayer.add(baseLine);

            let baseMesh = drawMiddleLayer(bounds, floorColor, startHeight, startHeight + 2, true);
            object3Dlayer.add(baseMesh);

            let random = Math.ceil(Math.random()*10);
            let currentColor = random > 5 ? hasUnAreaColor : noUnAreaColor;
            let mesh = drawMiddleLayer(bounds, currentColor, startHeight + 2, endHeight, true);
            object3Dlayer.add(mesh);
        }
        drawTextFlag(model.name, (baseH * model.floorCount * model.floorHeight), calculateCenter(paths));
    }

    function calculateArea(p0, p1, p2) {
        let area = p0[0] * p1[1] + p1[0] * p2[1] + p2[0] * p0[1]
            - p1[0] * p0[1] - p2[0] * p1[1] - p0[0] * p2[1];
        return area / 2;
    }

    function calculateCenter(paths) {
        let pointX = 0.0;
        let pointY = 0.0;
        let totalArea = 0.0;
        let p1 = paths[1];
        for (let i = 2; i < paths.length; i++) {
            let p2 = paths[i];
            let area = calculateArea(paths[0], p1, p2);
            totalArea += area;
            pointX += (Number(paths[0][0]) + Number(p1[0]) + Number(p2[0])) * area;
            pointY += (Number(paths[0][1]) + Number(p1[1]) + Number(p2[1])) * area;
            p1 = p2;
        }
        if (totalArea !== 0.0) {
            return [pointX / totalArea / 3, pointY / totalArea / 3];
        }
        return null;
    }

    function drawTextFlag(note, _height, center) {
        let text = new AMap.Text({
            text: note,
            verticalAlign: 'bottom',
            position: center,
            height: _height,
            style: {
                'background-color': 'transparent',
                '-webkit-text-stroke': 'white',
                '-webkit-text-stroke-width': '0.4px',
                'text-align': 'center',
                'border': 'none',
                'color': 'white',
                'font-size': '12px',
            }
        });
        text.setMap(map);
    }

    function drawMap(project) {
        map = new AMap.Map('map-3d', {
            viewMode: '3D', // 开启 3D 模式
            pitch: 45,
            rotation: 175,
            center: [project.lng, project.lat],
            features: ['bg', 'road'],
            zoom: 18,
        });
        addPlugin();

        project.buildingList.forEach(model => {
            drawOneBuild(model)
        });
    }

    function drawMiddleLayer(bounds, color, startH, endH, transparent) {
        let mesh = new AMap.Object3D.Mesh();
        let geometry = mesh.geometry;
        let vertices = geometry.vertices;
        let vertexColors = geometry.vertexColors;
        let faces = geometry.faces;
        let vertexLength = bounds.length * 2;

        let verArr = [];
        bounds.forEach(function (lngLat, index) {
            let g20 = map.lngLatToGeodeticCoord(lngLat);
            verArr.push([g20.x, g20.y]);
            // 构建顶点-底面顶点
            vertices.push(g20.x, g20.y, -startH);
            // 构建顶点-顶面顶点
            vertices.push(g20.x, g20.y, -endH);
            vertexColors.push.apply(vertexColors, color);
            vertexColors.push.apply(vertexColors, color);
            let bottomIndex = index * 2;
            let topIndex = bottomIndex + 1;
            let nextBottomIndex = (bottomIndex + 2) % vertexLength;
            let nextTopIndex = (bottomIndex + 3) % vertexLength;
            //侧面三角形1
            faces.push(bottomIndex, topIndex, nextTopIndex);
            //侧面三角形2
            faces.push(bottomIndex, nextTopIndex, nextBottomIndex);

            drawVerticalBar(lngLat, endH);
        });
        // 物业描边 不使用黑色
        let line = drawLine(bounds, endH, floorLineC, 1);
        object3Dlayer.add(line);

        // 设置顶面，根据顶点拆分三角形
        let triangles = AMap.GeometryUtil.triangulateShape(verArr);
        for (let v = 0; v < triangles.length; v += 3) {
            let a = triangles[v];
            let b = triangles[v + 2];
            let c = triangles[v + 1];
            faces.push(a * 2 + 1, b * 2 + 1, c * 2 + 1);
        }
        mesh.backOrFront = 'both';
        mesh.transparent = transparent;
        return mesh;
    }

    function drawVerticalBar(lngLat, endH) {
        let Line3D = new AMap.Object3D.Line();
        let Lgeometry = Line3D.geometry;
        let origin = map.lngLatToGeodeticCoord(lngLat);
        Lgeometry.vertices.push(origin.x, origin.y, -endH);
        Lgeometry.vertexColors.push(9 / 255, 0 / 255, 0 / 255, 0.99);

        let des = map.lngLatToGeodeticCoord(lngLat);
        Lgeometry.vertices.push(des.x, des.y, 0);
        Lgeometry.vertexColors.push(9 / 255, 0 / 255, 0 / 255, 0.99);
        object3Dlayer.add(Line3D);
    }

    function drawLine(bounds, height, color, lineW) {
        if (bounds == null || bounds.length === 0) {
            return;
        }
        let lineBounds = [];
        let lineHeight = [];
        for (let i = 0; i < bounds.length; i++) {
            lineHeight.push(height);
            lineBounds.push(bounds[i]);
        }
        lineHeight.push(height);
        lineBounds.push(bounds[0]);
        return new AMap.Object3D.MeshLine({
            path: lineBounds,
            height: lineHeight,
            color: color,
            width: lineW
        });
    }

    $(function () {
        $.ajax({
            type: 'GET',
            url: _path + '/project/getMapInfo?id=1',
            success: function (result) {
                drawMap(result.data);
            }
        });
    });













    // let map;
    // let floorLineC = [9 / 255, 0 / 255, 0 / 255, 0.99];
    // let fc = "rgba(84,183,93,0.46)";
    // let object3Dlayer;
    // let hasUnAreaColor = [10 / 255, 183 / 255, 168 / 255, 0.66];
    // let noUnAreaColor = [131 / 255, 131 / 255, 131 / 255, 0.61];
    // let baseH = 320;
    // let floorColor = [236 / 255, 245 / 255, 255 / 255, 0.35];
    //
    // let centerPoints = [];
    //
    // function addPlugin() {
    //     AMapUI.loadUI(['control/BasicControl'], function (BasicControl) {
    //         //图层切换控件
    //         map.addControl(new BasicControl.LayerSwitcher({
    //             position: 'rt' //right top，右上角
    //         }));
    //     });
    // }
    //
    // function drawOneBuild(paths, note) {
    //     for (let floor = 0; floor < 5; floor++) {
    //         object3Dlayer = new AMap.Object3DLayer();
    //         map.add(object3Dlayer);
    //         let bounds = paths.map(function (p) {
    //             return new AMap.LngLat(p[0], p[1]);
    //         });
    //         let baseLine = drawLine(bounds, floor * baseH, floorLineC, 1);
    //         object3Dlayer.add(baseLine);
    //
    //         let baseMesh = drawMiddleLayer(bounds, floorColor, floor * baseH, (floor * baseH) + 3, true);
    //         object3Dlayer.add(baseMesh);
    //
    //         let currentColor = hasUnAreaColor;
    //         if (floor % 2 === 1) {
    //             currentColor = noUnAreaColor;
    //         }
    //         let mesh = drawMiddleLayer(bounds, currentColor, floor * baseH, (floor * baseH) + baseH, true);
    //         object3Dlayer.add(mesh);
    //     }
    //     let cp = {};
    //     cp.flagH = baseH * 5;
    //     cp.flagCenter = calculateCenter(paths);
    //     centerPoints.push(cp);
    //     drawTextFlag(note, cp.flagH, cp.flagCenter);
    // }
    //
    // function calculateArea(p0, p1, p2) {
    //     let area = p0[0] * p1[1] + p1[0] * p2[1] + p2[0] * p0[1]
    //         - p1[0] * p0[1] - p2[0] * p1[1] - p0[0] * p2[1];
    //     return area / 2 ;
    // }
    //
    // function calculateCenter(paths) {
    //     let pointX = 0.0;
    //     let pointY = 0.0;
    //     let totalArea = 0.0;
    //     let p1 = paths[1];
    //     for (let i = 2; i < paths.length; i++) {
    //         let p2 = paths[i];
    //         let area = calculateArea(paths[0], p1, p2);
    //         totalArea += area;
    //         pointX += (paths[0][0] + p1[0] + p2[0]) * area;
    //         pointY += (paths[0][1] + p1[1] + p2[1]) * area;
    //         p1 = p2;
    //     }
    //     if (totalArea !== 0.0) {
    //         return [pointX / totalArea / 3, pointY / totalArea / 3];
    //     }
    //     return null;
    // }
    //
    // function drawTextFlag(note, _height, center) {
    //     let text = new AMap.Text({
    //         text: note,
    //         verticalAlign: 'bottom',
    //         position: center,
    //         height: _height,
    //         style: {
    //             'background-color': 'transparent',
    //             '-webkit-text-stroke': 'white',
    //             '-webkit-text-stroke-width': '0.4px',
    //             'text-align': 'center',
    //             'border': 'none',
    //             'color': 'white',
    //             'font-size': '16px',
    //         }
    //     });
    //     text.setMap(map);
    // }
    //
    // $(function () {
    //     let thisLon = 116.39756;
    //     let thisLat = 39.910215;
    //
    //     // 创建 3D 底图
    //     map = new AMap.Map('map-3d', {
    //         viewMode: '3D', // 开启 3D 模式
    //         pitch: 55,
    //         rotation: 35,
    //         center: [thisLon, thisLat],
    //         features: ['bg', 'road'],
    //         zoom: 15,
    //     });
    //     addPlugin();
    //     let pathList = [
    //         [
    //             [116.395951, 39.907129],
    //             [116.399127, 39.907178],
    //             [116.399534, 39.900413],
    //             [116.396316, 39.900331]
    //         ], [
    //             [116.391531, 39.922912],
    //             [116.401938, 39.923241],
    //             [116.402431, 39.913334],
    //             [116.398354, 39.913103],
    //             [116.398311, 39.912659],
    //             [116.396273, 39.912643],
    //             [116.396101, 39.913108],
    //             [116.392067, 39.912959]
    //         ]
    //     ];
    //     drawOneBuild(pathList[0], '体育馆');
    //     drawOneBuild(pathList[1], '教学楼');
    //
    //
    //     let pointList = [];
    //     let pointH = 0;
    //     centerPoints.forEach(cp => {
    //         pointH = cp.flagH * 3;
    //         drawVerticalBar(new AMap.LngLat(cp.flagCenter[0], cp.flagCenter[1]), cp.flagH * 3);
    //         pointList.push(new AMap.LngLat(cp.flagCenter[0], cp.flagCenter[1]));
    //     });
    //     let baseLine = drawLine(pointList, pointH, floorLineC, 3);
    //     object3Dlayer.add(baseLine);
    //
    // });
    //
    // function drawMiddleLayer(bounds, color, startH, endH, transparent) {
    //     let mesh = new AMap.Object3D.Mesh();
    //     let geometry = mesh.geometry;
    //     let vertices = geometry.vertices;
    //     let vertexColors = geometry.vertexColors;
    //     let faces = geometry.faces;
    //     let vertexLength = bounds.length * 2;
    //
    //     let verArr = [];
    //     bounds.forEach(function (lngLat, index) {
    //         let g20 = map.lngLatToGeodeticCoord(lngLat);
    //         verArr.push([g20.x, g20.y]);
    //         // 构建顶点-底面顶点
    //         vertices.push(g20.x, g20.y, -startH);
    //         // 构建顶点-顶面顶点
    //         vertices.push(g20.x, g20.y, -endH);
    //         vertexColors.push.apply(vertexColors, color);
    //         vertexColors.push.apply(vertexColors, color);
    //         let bottomIndex = index * 2;
    //         let topIndex = bottomIndex + 1;
    //         let nextBottomIndex = (bottomIndex + 2) % vertexLength;
    //         let nextTopIndex = (bottomIndex + 3) % vertexLength;
    //         //侧面三角形1
    //         faces.push(bottomIndex, topIndex, nextTopIndex);
    //         //侧面三角形2
    //         faces.push(bottomIndex, nextTopIndex, nextBottomIndex);
    //
    //         drawVerticalBar(lngLat, endH);
    //     });
    //     // 物业描边 不使用黑色
    //    let line = drawLine(bounds, endH, floorLineC, 1);
    //    object3Dlayer.add(line);
    //
    //     // 设置顶面，根据顶点拆分三角形
    //     let triangles = AMap.GeometryUtil.triangulateShape(verArr);
    //     for (let v = 0; v < triangles.length; v += 3) {
    //         let a = triangles[v];
    //         let b = triangles[v + 2];
    //         let c = triangles[v + 1];
    //         faces.push(a * 2 + 1, b * 2 + 1, c * 2 + 1);
    //     }
    //     mesh.backOrFront = 'both';
    //     mesh.transparent = transparent;
    //     return mesh;
    // }
    //
    // function drawVerticalBar(lngLat, endH) {
    //     let Line3D = new AMap.Object3D.Line();
    //     let Lgeometry = Line3D.geometry;
    //     let origin = map.lngLatToGeodeticCoord(lngLat);
    //     Lgeometry.vertices.push(origin.x, origin.y, -endH);
    //     Lgeometry.vertexColors.push(9 / 255, 0 / 255, 0 / 255, 0.99);
    //
    //     let des = map.lngLatToGeodeticCoord(lngLat);
    //     Lgeometry.vertices.push(des.x, des.y, 0);
    //     Lgeometry.vertexColors.push(9 / 255, 0 / 255, 0 / 255, 0.99);
    //     object3Dlayer.add(Line3D);
    // }
    //
    // function drawLine(bounds, height, color, lineW) {
    //     if (bounds == null || bounds.length === 0) {
    //         return;
    //     }
    //     let lineBounds = [];
    //     let lineHeight = [];
    //     for (let i = 0; i < bounds.length; i++) {
    //         lineHeight.push(height);
    //         lineBounds.push(bounds[i]);
    //     }
    //     lineHeight.push(height);
    //     lineBounds.push(bounds[0]);
    //     return new AMap.Object3D.MeshLine({
    //         path: lineBounds,
    //         height: lineHeight,
    //         color: color,
    //         width: lineW
    //     });
    // }

</script>
</body>
</html>
