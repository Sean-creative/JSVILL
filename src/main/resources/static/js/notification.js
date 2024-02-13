let lastHeartbeat = Date.now();

$(() => {
    axios({
        method: "get",
        url: "/api/v1/login-status"
    }).then(res => {
        if (res.data===true) {
            console.log("go startSSE(), res.data : ", res.data)
            startSSE();
        }
    })
});

function startSSE() {
    console.log("it's startSSE")
    let sse = new EventSource("/api/sse-connection");

    sse.onopen = function() {
        console.log("SSE Connection opened.");
    };

    sse.onmessage = (event) => {
        console.log("New message:", event);
        if (event.data !== "connected!") {
            console.log(2)
            $("#showNotifications").append('<span class="notification-dot"></span>');
            console.log(3)
        }
    };

    //서버에서 "heartbeat" 이라는 이름으로 보내진 이벤트를 수신할 때 실행될 콜백 함수를 정의하
    sse.addEventListener("heartbeat", (event) => {
        console.log("addEventListener - event : ", event)
        lastHeartbeat = Date.now();
    });

    //서버 연결이 끊어진 경우(onerror 이벤트 발생), EventSource는 기본적으로 몇 초 후에 자동으로 재연결을 시도
    //이는 클라이언트 측에서 추가적인 코드를 작성하지 않아도 EventSource 객체가 내부적으로 처리하는 기능입니다.
    sse.onerror = (event) => {
            console.log("event.target.readyState : ", event.target.readyState)
            console.log("Date.now() - lastHeartbeat : ", Date.now() - lastHeartbeat)
        //어떠한 문제로 닫히던가, 일정시간 동안 새로운 서버로부터 새로운 이벤트가 수신되지 않았다면 해당 연결을 명시적으로 종료
        if (event.target.readyState === EventSource.CLOSED || Date.now() - lastHeartbeat > 180000) {
            console.log("sse.close()")
            sse.close();
        }
    };
}