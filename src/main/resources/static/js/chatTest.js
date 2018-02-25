//  /msg/sendcommuser
let stompClient = null;

//传递用户key值

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#connect-status").html("已连接");
        $("#conversation").show();
    }
    else {
        $("#connect-status").html("已断开");
        $("#conversation").hide();
    }
}

function connect() {
    let socket = new SockJS('/webSocketServer');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/user/queue',function(msg){
            addPmsg(msg.body);
            console.log(msg);
        });
    }, function (error) {
        console.log('error: ' + error);
    });
    console.log(stompClient);
}

function disconnect() {
    if (stompClient != null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function addPmsg(msg){
    let msgs=$("#private-msgs");
    let newMsg=$("<p></p>").html(msg);
    msgs.append(newMsg);
}

function chatTest(userId,msg){
    stompClient.send("/app/str",{},JSON.stringify({'receiverId':userId,'msg':msg}));
}


$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $("#connect").click(function () {
        connect();
    });
    $("#disconnect").click(function () {
        disconnect();
    });

});


