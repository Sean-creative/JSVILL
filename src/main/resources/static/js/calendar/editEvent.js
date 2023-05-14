/* ****************
 *  일정 편집
 * ************** */
let editEvent = function (event, element, view) {

    removeConfirmModal.modal('hide');
    modalDeleteTitle.html('반복 삭제 여부');

    $('#onlyDeleteEvent').data('id', event.calendarRowid);
    //삭제는 calendarRowid가 아니라 bundleId로 삭제한다.
    $('#AllDeleteEvent').data('bundleId', event.bundleId);

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

    if (event.allDay === true && event.end !== event.start) {
        editEnd.val(moment(event.end).subtract(1, 'days').format('YYYY-MM-DD HH:mm'))
    } else {
        editEnd.val(event.end.format('YYYY-MM-DD HH:mm'));
    }

    modalTitle.html('일정 수정');
    editTitle.val(event.title);
    editStart.val(event.start.format('YYYY-MM-DD HH:mm'));
    editDesc.val(event.description);
    editColor.val(event.backgroundColor).css('color', event.backgroundColor);

    addBtnContainer.hide();
    modifyBtnContainer.show();
    eventModal.modal('show');

    //업데이트 버튼 클릭시
    $('#updateEvent').unbind();
    $('#updateEvent').on('click', function () {

        if (editStart.val() > editEnd.val()) {
            alert('끝나는 날짜가 앞설 수 없습니다.');
            return false;
        }

        if (editTitle.val() === '') {
            alert('일정명은 필수입니다.')
            return false;
        }

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

        eventModal.modal('hide');

        event.allDay = statusAllDay;
        event.title = editTitle.val();
        event.start = startDate;
        event.end = displayDate;
        event.backgroundColor = editColor.val();
        event.description = editDesc.val();

        $("#calendar").fullCalendar('updateEvent', event);


        console.log("event.calendarRowid : " + event.calendarRowid)
        //일정 업데이트
        $.ajax({
            type: "post",
            url: "/calendar/modify",
            data: {
                groupRowid:groupRowid,
                calendarRowid:event.calendarRowid,
                title:event.title,
                description:event.description,
                start:event.start,
                end:event.end,
                backgroundColor:event.backgroundColor,
                textColor:event.textColor,
                isallday:event.allDay
            },
            success: function (response) {
                alert('수정되었습니다.')
            }
        });
    });
};

// 삭제버튼은 삭제 모달을 켜주기만 한다.
$('#deleteEvent').off().on('click', function () {
    $("#calendar").fullCalendar('removeEvents', $(this).data('bundleId'));
    // 나중에는 bundleId가 하나밖에 없으면 하나만 삭제하도록 구현하기?
    //1. bundleId가 몇개인가?
    //2.
    const clickBundleId = $(this).data('bundleId')
    console.log(`clickBundleId : ${clickBundleId}`)

    const getBundleIdCount = (arr, el) => arr.filter(v => v.bundleId === el).length
    const count = getBundleIdCount(fixedDate, $(this).data('bundleId'))
    console.log(`count : ${count}`)
    removeConfirmModal.modal('show');

});

//해당 일정만 삭제
$('#onlyDeleteEvent').off().on('click', function () {
    $("#calendar").fullCalendar('removeEvents', $(this).data('id'));
    eventModal.modal('hide');
    removeConfirmModal.modal('hide');

    $.ajax({
        type: "post",
        url: "/calendar/remove",
        data: {
            id:$(this).data('id'),
            isAllDelete : false
        },
        success: function (response) {
            alert('해당 일정만 삭제되었습니다.');
            location.reload()
        }
    });
});
//반복 일정 모두 삭제
$('#AllDeleteEvent').off().on('click', function () {
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

