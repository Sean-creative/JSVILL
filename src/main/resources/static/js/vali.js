$(function () {
    $('.check').on('change', function () {
        const scriptTag = /[~^&()|<>?]/;
        // 핸드폰 밸리
        // const regExp_tel = /^[0-9]{2,3}-[0-9]{3,4}-[0-9]{4}$/;
        let spaceCheck = /\s/g; // 공백 체크 정규표현식 - 탭, 스페이스
        const thisVal = $(this).val();
        console.log("thisVal.length", thisVal.length)
        console.log("thisVal.value", thisVal)
        console.log("spaceCheck.test(thisVal)", spaceCheck.test(thisVal))
        let checkText = "";
        switch ($(this).attr("name")) {
            case "detailAddr":
                if (thisVal.length == 0) {
                    checkText = "호수를 입력해주세요.";
                } else if (scriptTag.test(thisVal)) {
                    checkText = "스크립트 태그는 들어갈 수 없습니다.";
                } else if (spaceCheck.test(thisVal)) {
                    checkText = "공백이 들어갈 수 없습니다.";
                } else if (thisVal.length > 10) {
                    checkText = "10글자 이내로 입력해주세요.";
                }
                break;
            case "deposit":
                if (thisVal.length == 0) {
                    checkText = "보증금을 입력해주세요.";
                } else if (scriptTag.test(thisVal)) {
                    checkText = "스크립트 태그는 들어갈 수 없습니다.";
                } else if (Space_Check.test(thisVal)) {
                    checkText = "공백이 들어갈 수 없습니다.";
                } else if (thisVal.length > 10) {
                    checkText = "10글자 이내로 입력해주세요.";
                }
                break;
        }
        console.log("checkText : " + checkText)
        if (checkText != "") {
            $(this).siblings("p").html(checkText);
            $(this).siblings("p").addClass("vali");
            $(this).siblings("p").slideDown();
        } else {
            $(this).siblings("p").slideUp(function () {
                $(this).removeClass("vali");
            });
        }
    })
})