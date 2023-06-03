
const getBundleIdCount = (arr, el) => arr.filter(v => v.bundleId === el).length
let bundleIdCount = 0
let editEvent = function (event, element, view) {

    removeConfirmModal.modal('hide');
    modifyConfirmModal.modal('hide');
    modalDeleteTitle.html('반복 삭제 여부');

    bundleIdCount = getBundleIdCount(fixedDate, event.bundleId)
    let isRepetition = event.repetition !== "notRepeat"

    $('#deleteEvent').data('bundleId', event.bundleId);
    $('#deleteEvent').data('id', event.calendarRowid);
    //삭제는 calendarRowid가 아니라 bundleId로 삭제한다.
    $('#deleteAllEvent').data('bundleId', event.bundleId);

    $('.popover.fade.top').remove();
    $(element).popover("hide");

    if (event.allDay === true) {
        editAllDay.prop('checked', true);
    } else {
        editAllDay.prop('checked', false);
    }
    if (event.end === null) {
        event.end = event.start;
    }

    //변경전 반복일정 기록해놓기
    const originalRepetition = event.repetition



    //폼에 데이터 넣기
    modalTitle.html('일정 수정');
    editTitle.val(event.title);
    editStart.val(event.start.format('YYYY-MM-DD HH:mm'));
    if (event.allDay === true && event.end !== event.start) {
        editEnd.val(moment(event.end).subtract(1, 'days').format('YYYY-MM-DD HH:mm'))
    } else {
        editEnd.val(event.end.format('YYYY-MM-DD HH:mm'));
    }
    editDesc.val(event.description);
    editRepetition.val(event.repetition);
    editColor.val(event.backgroundColor).css('color', event.backgroundColor);

    if(isRepetition) editRepetitionEnd.val(moment(event.repetitionEnd).format('YYYY-MM-DD HH:mm'))
    repetitionEndChange(isRepetition)

    addBtnContainer.hide();
    modifyBtnContainer.show();
    eventModal.modal('show');



    //업데이트 버튼 클릭시************************
    $('#updateEvent').off().on('click', function () {

        let statusAllDay;
        let startDate;
        let endDate;
        let displayDate;

        if (editAllDay.is(':checked')) {
            statusAllDay = true;
            startDate = moment(editStart.val()).format('YYYY-MM-DD');
            endDate = moment(editEnd.val()).format('YYYY-MM-DD');
            displayDate = moment(editEnd.val()).add(1, 'days').format('YYYY-MM-DD');
        } else {
            statusAllDay = false;
            startDate = editStart.val();
            endDate = editEnd.val();
            displayDate = endDate;
        }

        event.allDay = statusAllDay;
        event.title = editTitle.val();
        event.start = startDate;
        event.end = displayDate;
        event.repetition = editRepetition.val();
        event.repetitionEnd = editRepetitionEnd.val();
        event.backgroundColor = editColor.val();
        event.description = editDesc.val();
        isRepetition = event.repetition !== "notRepeat" //업데이트 버튼 클릭 시 변경되었을 수도 있음

        //벨리데이션 --------------------------------
        if (editStart.val() > editEnd.val()) {
            alert('끝나는 날짜가 앞설 수 없습니다.');
            return false;
        }
        if (editTitle.val() === '') {
            alert('일정명은 필수입니다.')
            return false;
        }
        //'반복 안 함'이 아니라면 반복 마감 체크를 해야함
        if(isRepetition) {
            if(editRepetitionEnd.val() === '') {
                alert('반복을 선택했다면 반복 마감은 필수입니다.')
                return false;
            }
            if (editEnd.val() > editRepetitionEnd.val()) {
                alert('반복 마감은 끝나는 날짜 이후여야 합니다.');
                return false;
            }
        }

/* ****************
 *  일정 편집, type 번호가 된다.
 *  1. 원래 반복일정 x - 수정 후 반복일정이 x -> 해당 bundleId로 추가(bundleId가 1개임)
 *  2. 원래 반복일정 x - 수정 후 반복일정이 o -> 해당 bundleId로 추가(bundleId가 1개임)
 *  3. 원래 반복일정 o - 수정 후 반복일정이 x -> alert 생성 "해당 일정을 제외하고 반복일정은 사라집니다."   삭제버튼 누르면 나머지 반복일정은 삭제됨
 *  4. 원래 반복일정 o - 수정 후 반복일정이 o -> 해당 bundleId로 수정
 *  4.1 A B C D 중 C에서 반복일정을 수정해도 전체 반복일정이 영향을 받으면 된다. (연대책임으로 ㄱㄱ)
 *
 * StartLoopDays 배열 사이즈에 따라  레코드가 늘어난다.
 * bundleIdCount
 * isRepetition
 * */
        let typeNo = 0;
        if (originalRepetition === "notRepeat") typeNo = event.repetition === "notRepeat" ? 1 : 2;
        else typeNo = event.repetition === "notRepeat" ? 3 : 4;

        console.log(`typeNo : ${typeNo}`)

        let startLoopDays = [];
        let endLoopDays = [];

        let diffMonths = moment(event.repetitionEnd).diff(event.start, 'months')
        if(event.repetition !== "notRepeat" && diffMonths > 35) {
            alert('반복일정은 3년 이내만 가능합니다.');
            return false;
        }
        //반복일정 o -> 반복일정 x 가 되는 상황이면 해당일정은 제외하고 나머지 반복일정은 사라짐
        if(typeNo===3) {
            const result = confirm("해당 일정을 제외하고 반복일정은 사라집니다.\n수정할까요?")
            if(!result) {
                alert("수정을 다시 입력해주세요.");
                eventModal.modal('hide');
                return false
            }
        }

        //loopDays init - 반복이 아니더라고 초기값은 들어가있다.
        startLoopDays.push(event.start)
        endLoopDays.push(event.end)
        let dateFormat;
        if(event.allDay) dateFormat = "YYYY-MM-DD"; else dateFormat = "YYYY-MM-DD HH:mm";
        //시작일에서 종료일까지 repetition에 따라 date를 만들어낸다.
        switch (event.repetition) {
            case "notRepeat" :
                break;
            case "everyMonth" :
                //종료일에서 시작일까지 한달씩 더해
                for (let i = 0; i < diffMonths; i++) {
                    startLoopDays.push((moment(startLoopDays.at(-1)).add(1, 'months').format(dateFormat)))
                    endLoopDays.push((moment(endLoopDays.at(-1)).add(1, 'months').format(dateFormat)))
                }
                break;
            case "everyYear" :
                for (let i = 0; i < diffMonths/12; i++) {
                    startLoopDays.push((moment(startLoopDays.at(-1)).add(1, 'years').format(dateFormat)))
                    endLoopDays.push((moment(endLoopDays.at(-1)).add(1, 'years').format(dateFormat)))
                }
                break;
            default :
                alert('선택한 값이 없습니다.');
                break;
        }

        eventModal.modal('hide');
        console.log("event.calendarRowid : " + event.calendarRowid)
        $("#calendar").fullCalendar('updateEvent', event);
        //일정 업데이트
        $.ajax({
            type: "post",
            url: "/calendar/modify",
            data: {
                groupRowid:groupRowid,
                calendarRowid:event.calendarRowid,
                bundleId:event.bundleId,
                title:event.title,
                description:event.description,
                start:event.start,
                end:event.end,
                repetition:event.repetition,
                startLoopDays: startLoopDays,
                endLoopDays: endLoopDays,
                repetitionEnd:event.repetitionEnd,
                backgroundColor:event.backgroundColor,
                textColor:event.textColor,
                isallday:event.allDay,
                typeNo:typeNo,
            },
            success: function (response) {
                if(typeNo===4) alert('반복일정이 모두 수정되었습니다.')
                else alert('수정되었습니다.')
                location.reload()
            }
        });
    });
};




// 삭제버튼은 삭제 모달을 켜주기만 한다.
$('#deleteEvent').off().on('click', function () {
    $("#calendar").fullCalendar('removeEvents', $(this).data('bundleId'));
    $("#calendar").fullCalendar('removeEvents', $(this).data('id'));

    //중복된 bundleId가 하나이면 바로 삭제하고, 두개 이상이면 모달을 열자
    if(bundleIdCount<2) deleteOneEvent($(this).data('id')); else removeConfirmModal.modal('show');
});

//해당 일정만 삭제
$('#deleteOneEvent').off().on('click', function () {
    removeConfirmModal.modal('hide');
    deleteOneEvent();

});
//반복 일정 모두 삭제
$('#deleteAllEvent').off().on('click', function () {
    $("#calendar").fullCalendar('removeEvents', $(this).data('bundleId'));
    eventModal.modal('hide');
    removeConfirmModal.modal('hide');

    $.ajax({
        type: "post",
        url: "/calendar/remove",
        data: {
            id:$(this).data('bundleId'),
            isAllDelete : true
        },
        success: function (response) {
            alert('반복 일정이 모두 삭제되었습니다.');
            location.reload()
        }
    });
});

function deleteOneEvent(rowId) {
    eventModal.modal('hide');

    $.ajax({
        type: "post",
        url: "/calendar/remove",
        data: {
            id:rowId,
            isAllDelete : false
        },
        success: function (response) {
            alert('해당 일정이 삭제되었습니다.');
            location.reload()
        }
    });
}