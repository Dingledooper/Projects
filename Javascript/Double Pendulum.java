<!DOCTYPE html>

<html>

<head>

    <meta charset="utf-8">
    <title>Double Pendulum</title>

</head>

<body>

    <style>
        
        body {
            margin: 0;
        }

        canvas {
            background-color: white;
        }

        #l1 {
            position: absolute;
            top: 10px;
            left: 100px;
        }

        #l2 {
            position: absolute;
            top: 60px;
            left: 100px;
        }
        
        #a1 {
            position: absolute;
            top: 110px;
            left: 100px;
        }
        
        #a2 {
            position: absolute;
            top: 160px;
            left: 100px;
        }

        #g {
            position: absolute;
            top: 210px;
            left: 100px;
        }
        
        #f {
            position: absolute;
            top: 260px;
            left: 100px;
        }

        #l1,
        #l2,
        #a1,
        #a2,
        #g,
        #f {
            -webkit-appearance: none;
            width: 150px;
            height: 20px;
            background-color: lightgray;
            outline: none;
            opacity: 0.7;
            transition: opacity 0.2s;
        }

        #l1:hover,
        #l2:hover,
        #a1:hover,
        #a2:hover,
        #g:hover,
        #f:hover {
            opacity: 1;
        }

        #l1::-webkit-slider-thumb,
        #l2::-webkit-slider-thumb,
        #a1::-webkit-slider-thumb,
        #a2::-webkit-slider-thumb,
        #g::-webkit-slider-thumb,
        #f::-webkit-slider-thumb {
            -webkit-appearance: none;
            width: 25px;
            height: 25px;
            background: #4CAF50;
            cursor: pointer;
        }

        #l1show {
            position: absolute;
            top: 10px;
            left: 270px;
            font-size: 20px;
            font-family: Segoe UI;
        }

        #l2show {
            position: absolute;
            top: 60px;
            left: 270px;
            font-size: 20px;
            font-family: Segoe UI;
        }
        
        #a1show {
            position: absolute;
            top: 110px;
            left: 270px;
            font-size: 20px;
            font-family: Segoe UI;
        }
        
        #a2show {
            position: absolute;
            top: 160px;
            left: 270px;
            font-size: 20px;
            font-family: Segoe UI;
        }

        #gshow {
            position: absolute;
            top: 210px;
            left: 270px;
            font-size: 20px;
            font-family: Segoe UI;
        }
        
        #fshow {
            position: absolute;
            top: 260px;
            left: 270px;
            font-size: 20px;
            font-family: Segoe UI;
        }
        
        #l1ins {
            position: absolute;
            top: -5px;
            left: 20px;
            font-family: Segoe UI;
        }
        
        #l2ins {
            position: absolute;
            top: 45px;
            left: 20px;
            font-family: Segoe UI;
        }
        
        #a1ins {
            position: absolute;
            top: 95px;
            left: 20px;
            font-family: Segoe UI;
        }
        
        #a2ins {
            position: absolute;
            top: 145px;
            left: 20px;
            font-family: Segoe UI;
        }
        
        #gins {
            position: absolute;
            top: 195px;
            left: 20px;
            font-family: Segoe UI;
        }
        
        #fins {
            position: absolute;
            top: 245px;
            left: 20px;
            font-family: Segoe UI;
        }
        
        
        #afunc {
            position: absolute;
            top: 112px;
            left: 320px;
            padding: 20px 10px;
            background-color: aqua;
            font-size: 20px;
            font-family: Segoe UI;
            border: 2px solid deepskyblue;
            transition-duration: 0.4s;
        }
        
        #afunc:hover {
            background-color: deepskyblue;
        }

    </style>

    <canvas id="myCanvas" width="1280" height="611"></canvas>
    <input id="l1" type="range" min="1" max="400" value="220">
    <input id="l2" type="range" min="1" max="400" value="220">
    <input id="a1" type="range" min="0" max="360" value="180">
    <input id="a2" type="range" min="0" max="360" value="180">
    <input id="g" type="range" min="0" max="3" value="0.7" step="0.1">
    <input id="f" type="range" min="1" max="1.1" value="1" step="0.01">
    <input id="afunc" type="button" onclick="angles()" value="<= Submit">
    <div id="l1show"></div>
    <div id="l2show"></div>
    <div id="a1show"></div>
    <div id="a2show"></div>
    <div id="gshow"></div>
    <div id="fshow"></div>
    <p id="l1ins">Length 1:</p>
    <p id="l2ins">Length 2:</p>
    <p id="a1ins">Angle 1:</p>
    <p id="a2ins">Angle 2:</p>
    <p id="gins">Gravity:</p>
    <p id="fins">Friction:</p>

    <script>
        
        var canv = document.getElementById("myCanvas");
        var ctx = canv.getContext("2d");

        // mass components
        var m1 = 20;
        var m2 = 20;
        
        // angle components
        var a1 = Math.PI / 360 * document.getElementById("a1").value;
        var a2 = Math.PI / 360 * document.getElementById("a2").value;
        var angles = function() {
            
            a1 = Math.PI / 360 * document.getElementById("a1").value;
            a2 = Math.PI / 360 * document.getElementById("a2").value;
        };
        var a1V = 0;
        var a2V = 0;

        // translate canvas (0, 0) to center
        ctx.translate(canv.width / 2, 150);

        var draw = function() {

            // assign changeable constants
            var l1 = document.getElementById("l1").value;
            document.getElementById("l1show").innerHTML = document.getElementById("l1").value;
            var l2 = document.getElementById("l2").value;
            document.getElementById("l2show").innerHTML = document.getElementById("l2").value;
            document.getElementById("a1show").innerHTML = document.getElementById("a1").value;
            document.getElementById("a2show").innerHTML = document.getElementById("a2").value;
            var g = document.getElementById("g").value;
            document.getElementById("gshow").innerHTML = document.getElementById("g").value;
            var friction = document.getElementById("f").value;
            document.getElementById("fshow").innerHTML = document.getElementById("f").value;

            ctx.clearRect(-canv.width / 2, -150, canv.width, canv.height);
            
            // angular acceleration for a1A
            var el1_1 = -g * (2 * m1 + m2) * Math.sin(a1);
            var el1_2 = m2 * g * Math.sin(a1 - 2 * a2);
            var el1_3 = 2 * Math.sin(a1 - a2) * m2;
            var el1_4 = a2V * a2V * l2 + a1V * a1V * l1 * Math.cos(a1 - a2);
            var el1_5 = l1 * (2 * m1 + m2 - m2 * Math.cos(2 * a1 - 2 * a2));
            var a1A = (el1_1 - el1_2 - el1_3 * el1_4) / el1_5;

            // angular acceleration for a2A
            var el2_1 = 2 * Math.sin(a1 - a2);
            var el2_2 = a1V * a1V * l1 * (m1 + m2);
            var el2_3 = g * (m1 + m2) * Math.cos(a1);
            var el2_4 = a2V * a2V * l2 * m2 * Math.cos(a1 - a2);
            var el2_5 = l2 * (2 * m1 + m2 - m2 * Math.cos(2 * a1 - 2 * a2));
            var a2A = (el2_1 * (el2_2 + el2_3 + el2_4)) / el2_5;

            // set horizontal and vertical position of pendulum mass
            var x1 = l1 * Math.sin(a1);
            var y1 = l1 * Math.cos(a1);
            var x2 = x1 + l2 * Math.sin(a2);
            var y2 = y1 + l2 * Math.cos(a2);

            // first line
            ctx.beginPath();
            ctx.moveTo(0, 0);
            ctx.lineTo(x1, y1);
            ctx.stroke();
            ctx.closePath();

            // first ball
            ctx.beginPath();
            ctx.arc(x1, y1, m1, 0, 2 * Math.PI);
            ctx.fill();
            ctx.closePath();

            // second line
            ctx.beginPath();
            ctx.moveTo(x1, y1);
            ctx.lineTo(x2, y2);
            ctx.stroke();
            ctx.closePath();

            // second ball
            ctx.beginPath();
            ctx.arc(x2, y2, m2, 0, 2 * Math.PI);
            ctx.fill();
            ctx.closePath();

            // change a1, a2, a1V, a2V values
            a1V += a1A;
            a2V += a2A;
            a1 += a1V;
            a2 += a2V;
            a1V *= 1 / friction;
            a2V *= 1 / friction;

            // animate double pendulum
            window.requestAnimationFrame(draw);
        };
        
        window.requestAnimationFrame(draw);
        
    </script>

</body>

</html>
