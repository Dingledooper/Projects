<!DOCTYPE html>

<html>

<head>

    <meta charset="utf-8">
    <title>Chaos Game</title>

</head>

<body>

    <style>
        canvas {
            background-color: black;
        }

        body {
            margin: 0;
        }

        #triangle {
            position: absolute;
            left: 10px;
            background-color: orange;
            border: none;
            padding: 10px 20px;
            font-size: 30px;
            font-family: Segoe UI;
            border-radius: 10px;
            transition-duration: 0.4s;
        }

        #triangle:hover {
            background-color: orangered;
        }

        #square {
            position: absolute;
            left: 160px;
            background-color: greenyellow;
            border: none;
            padding: 10px 20px;
            font-size: 30px;
            font-family: Segoe UI;
            border-radius: 10px;
            transition-duration: 0.4s;
        }

        #square:hover {
            background-color: green
        }

        #pentagon {
            position: absolute;
            left: 298px;
            background-color: deepskyblue;
            border: none;
            padding: 10px 20px;
            font-size: 30px;
            font-family: Segoe UI;
            border-radius: 10px;
            transition-duration: 0.4s;
        }

        #pentagon:hover {
            background-color: blue;
        }

        #hexagon {
            position: absolute;
            left: 470px;
            background-color: mediumpurple;
            border: none;
            padding: 10px 20px;
            font-size: 30px;
            font-family: Segoe UI;
            border-radius: 10px;
            transition-duration: 0.4s;
        }

        #hexagon:hover {
            background-color: purple;
        }

        #xway {
            position: absolute;
            left: 800px;
            width: 75px;
            height: 50px;
            font-size: 30px;
        }

        #show {
            position: absolute;
            left: 650px;
            width: 140px;
            font-size: 12px;
            font-family: Segoe UI;
        }

        #reset {
            position: absolute;
            left: 890px;
            width: 100px;
            height: 30px;
            background-color: gainsboro;
            padding: 0px 0px;
            font-size: 20px;
            font-family: Segoe UI;
            border-color: grey;
            border-radius: 10px;
            transition-duration: 0.4s;
        }

        #reset:hover {
            background-color: slategray;
            color: white;
        }

    </style>

    <canvas id="myCanvas" width="1280" ; height="550"></canvas>
    <input id="triangle" type="button" onclick="triangle(); anTri()" value="Triangle">
    <input id="square" type="button" onclick="square(); anSqr()" value="Square">
    <input id="pentagon" type="button" onclick="pentagon(); anPen()" value="Pentagon">
    <input id="hexagon" type="button" onclick="hexagon(); anHex()" value="Hexagon">

    <input id="xway" type="number" step="0.01" min="0" max="1" value="0.5">
    <div id="show">Fraction of distance between point and vertex:</div>
    <input id="reset" type="button" onclick="history.go(0)" value="reset">

    <script>
        var canv = document.getElementById("myCanvas");
        var ctx = canv.getContext("2d");

        // canvas shortcuts
        var cw = canv.width;
        var ch = canv.height;

        // point coordinates
        var x = cw / 2;
        var y = ch / 2;

        // x fraction of the way to a vertex
        var way;
        var waySelect = function() {
            way = document.getElementById("xway").value;
        };

        // shapes coordinates
        var triX = [cw / 2, cw / 2 - Math.sqrt(270000) / 2, cw / 2 + Math.sqrt(270000) / 2];
        var triY = [50, ch - 50, ch - 50];
        var sqrX = [cw / 2 - 225, cw / 2 - 225, cw / 2 + 225, cw / 2 + 225];
        var sqrY = [50, ch - 50, ch - 50, 50];
        var penX = [Math.cos(-1 / 2 * Math.PI) * 225 + cw / 2, Math.cos(-9 / 10 * Math.PI) * 225 + cw / 2, Math.cos(-13 / 10 * Math.PI) * 225 + cw / 2, Math.cos(-17 / 10 * Math.PI) * 225 + cw / 2, Math.cos(-21 / 10 * Math.PI) * 225 + cw / 2];
        var penY = [Math.sin(-1 / 2 * Math.PI) * 225 + ch / 2, Math.sin(-9 / 10 * Math.PI) * 225 + ch / 2, Math.sin(-13 / 10 * Math.PI) * 225 + ch / 2, Math.sin(-17 / 10 * Math.PI) * 225 + ch / 2, Math.sin(-21 / 10 * Math.PI) * 225 + ch / 2];
        var hexX = [Math.cos(1 / 3 * Math.PI) * 225 + cw / 2, Math.cos(2 / 3 * Math.PI) * 225 + cw / 2, Math.cos(Math.PI) * 225 + cw / 2, Math.cos(4 / 3 * Math.PI) * 225 + cw / 2, Math.cos(5 / 3 * Math.PI) * 225 + cw / 2, Math.cos(2 * Math.PI) * 225 + cw / 2];
        var hexY = [Math.sin(1 / 3 * Math.PI) * 225 + ch / 2, Math.sin(2 / 3 * Math.PI) * 225 + ch / 2, Math.sin(Math.PI) * 225 + ch / 2, Math.sin(4 / 3 * Math.PI) * 225 + ch / 2, Math.sin(5 / 3 * Math.PI) * 225 + ch / 2, Math.sin(2 * Math.PI) * 225 + ch / 2];

        var triangle = function() {
            // draw triangle
            ctx.beginPath();
            ctx.moveTo(triX[0], triY[0]);
            ctx.lineTo(triX[1], triY[1]);
            ctx.lineTo(triX[2], triY[2]);
            ctx.lineTo(triX[0], triY[0]);
            // stroke of triangle to white
            ctx.strokeStyle = "white";
            ctx.stroke();
            ctx.closePath();
        };

        var square = function() {
            // draw square
            ctx.beginPath();
            ctx.moveTo(sqrX[0], sqrY[0]);
            ctx.lineTo(sqrX[1], sqrY[1]);
            ctx.lineTo(sqrX[2], sqrY[2]);
            ctx.lineTo(sqrX[3], sqrY[3]);
            ctx.lineTo(sqrX[0], sqrY[0]);
            // stroke of square to white
            ctx.strokeStyle = "white";
            ctx.stroke();
            ctx.closePath();
        };

        var pentagon = function() {
            // draw pentagon
            ctx.beginPath();
            ctx.moveTo(penX[0], penY[0]);
            ctx.lineTo(penX[1], penY[1]);
            ctx.lineTo(penX[2], penY[2]);
            ctx.lineTo(penX[3], penY[3]);
            ctx.lineTo(penX[4], penY[4]);
            ctx.lineTo(penX[0], penY[0]);
            // stroke of pentagon to white
            ctx.strokeStyle = "white";
            ctx.stroke();
            ctx.closePath();
        };

        var hexagon = function() {
            // draw hexagon
            ctx.beginPath();
            ctx.moveTo(hexX[0], hexY[0]);
            ctx.lineTo(hexX[1], hexY[1]);
            ctx.lineTo(hexX[2], hexY[2]);
            ctx.lineTo(hexX[3], hexY[3]);
            ctx.lineTo(hexX[4], hexY[4]);
            ctx.lineTo(hexX[5], hexY[5]);
            ctx.lineTo(hexX[0], hexY[0]);
            // stroke of hexagon to white
            ctx.strokeStyle = "white";
            ctx.stroke();
            ctx.closePath();
        };

        var dot = function(x, y) {
            // draw dot
            ctx.beginPath();
            ctx.arc(x, y, 0.5, 0, 2 * Math.PI);
            ctx.fillStyle = "white";
            ctx.fill();
            ctx.closePath();
        };

        var chaosTri = function() {
            // create triangle fractal 
            var vert3 = Math.floor(Math.random() * 3);
            dot(x, y);
            x += (triX[vert3] - x) * way;
            y += (triY[vert3] - y) * way;
        };

        var chaosSqr = function() {
            // create square fractal
            var vert4 = Math.floor(Math.random() * 4);
            dot(x, y);
            x += (sqrX[vert4] - x) * way;
            y += (sqrY[vert4] - y) * way;
        };

        var chaosPen = function() {
            // create pentagonal fractal
            var vert5 = Math.floor(Math.random() * 5);
            dot(x, y);
            x += (penX[vert5] - x) * way;
            y += (penY[vert5] - y) * way;
        };

        var chaosHex = function() {
            // create hexagonal fractal
            var vert6 = Math.floor(Math.random() * 6);
            dot(x, y);
            x += (hexX[vert6] - x) * way;
            y += (hexY[vert6] - y) * way;
        };

        var anTri = function() {
            waySelect();
            setInterval(function() {
                setInterval(chaosTri, 1);
            }, 1);
        };
        var anSqr = function() {
            waySelect();
            setInterval(function() {
                setInterval(chaosSqr, 1);
            }, 1);
        };
        var anPen = function() {
            waySelect();
            setInterval(function() {
                setInterval(chaosPen, 1);
            }, 1);
        };
        var anHex = function() {
            waySelect();
            setInterval(function() {
                setInterval(chaosHex, 1);
            }, 1);
        };

    </script>

</body>

</html>
