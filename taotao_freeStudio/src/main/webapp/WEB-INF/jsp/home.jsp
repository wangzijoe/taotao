<html>
<head>
    <link rel="shortcut icon" href="#"/>
</head>
<body>
<h2>Hello World!</h2>
<script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/sockjs-client/1.3.0/sockjs.js"></script>
<script>



    openWebSocket();
    var ws = null;

    function openWebSocket() {
        //判断当前浏览器是否支持WebSocket
        if ('WebSocket' in window) {
            ws = new WebSocket("ws://" + window.location.host + "/webSocket/tWebSocket.action?jspSessionId=123");
        } else {
            ws = new SockJS("http://" + window.location.host + "/webSocket/sockJs/tWebSocket/info?jspSessionId=123");
        }
        ws.onopen = function () {
            console.log("WS connect ok!")

            $.ajax({
                type:"get",
                url:"http://localhost:8089/consumer_producer/buildTable.html?jspSessionId=123",
                dataType : "jsonp",//数据类型为jsonp
                success : function(data){
                    console.log(data);
                },
                error:function(){
                    alert('fail');
                }
            });
        };
        //这个事件是接受后端传过来的数据
        ws.onmessage = function (event) {
            //根据业务逻辑解析数据
            console.log(event)
        };
        ws.onclose = function (event) {
            $.ajax({
                type:"get",
                url:"http://localhost:8089/consumer_producer/closeProducer.html?jspSessionId=123",
                dataType : "jsonp",//数据类型为jsonp
                success : function(data){
                    console.log(data);
                },
                error:function(){
                    alert('fail');
                }
            });
        };
    }
</script>
</body>
</html>
