let eventModal = $('#eventModal');

let modalTitle = $('.modal-title');
let editAllDay = $('#edit-allDay');
let editTitle = $('#edit-title');
let editStart = $('#edit-start');
let editEnd = $('#edit-end');
let editRepetition = $('#edit-repetition');
let editRepetitionEnd = $('#edit-repetition-end');
let editColor = $('#edit-color');
let editDesc = $('#edit-desc');

let addBtnContainer = $('.modalBtnContainer-addEvent');
let modifyBtnContainer = $('.modalBtnContainer-modifyEvent');

let removeConfirmModal = $('#removeConfirmModal');
let modifyConfirmModal = $('#modifyConfirmModal');
let modalDeleteTitle = $('.modal-delete-title');


/* ****************
 *  새로운 일정 생성
 * ************** */
let newEvent = function (start, end) {

    //초기화
    modalTitle.html('새로운 일정!');
    editTitle.val('');
    editStart.val(start);
    editEnd.val(end);
    editRepetition.val("notRepeat");
    editRepetitionEnd.val("");
    editRepetitionEnd.attr("disabled", true);
    editColor.val("#D25565");
    editColor.css("color","#D25565");
    editDesc.val('');

    addBtnContainer.show();
    modifyBtnContainer.hide();
    eventModal.modal('show');


    //새로운 일정 저장버튼 클릭
    $('#save-event').off().on('click', function () {
        let eventData = {
            title: editTitle.val(),
            start: editStart.val(),
            end: editEnd.val(),
            repetition: editRepetition.val(),
            repetitionEnd: editRepetitionEnd.val(),
            description: editDesc.val(),
            backgroundColor: editColor.val(),
            textColor: '#ffffff',
            allDay: false
        };
        let isRepetition = eventData.repetition !== "notRepeat"

        if (eventData.start > eventData.end) {
            alert('끝나는 날짜가 앞설 수 없습니다.');
            return false;
        }
        if (eventData.title === '') {
            alert('일정명은 필수입니다.');
            return false;
        }
        //'반복 안 함'이 아니라면 반복 마감 체크를 해야함
        if(isRepetition) {
            if(editRepetitionEnd.val() === '') {
                alert('반복을 선택했다면 반복 마감은 필수입니다.2')
                return false;
            }
            if (editEnd.val() > editRepetitionEnd.val()) {
                alert('반복 마감은 끝나는 날짜 이후여야 합니다.');
                return false;
            }
        }


        let realEndDay; //필요할까?
        if (editAllDay.is(':checked')) {
            eventData.start = moment(eventData.start).format('YYYY-MM-DD');
            //render시 날짜표기수정
            eventData.end = moment(eventData.end).add(1, 'days').format('YYYY-MM-DD');
            //DB에 넣을때(선택)
            realEndDay = moment(eventData.end).format('YYYY-MM-DD');
            eventData.allDay = true;
        }
        let startLoopDays = [];
        let endLoopDays = [];

        let diffMonths = moment(eventData.repetitionEnd).diff(eventData.start, 'months')
        if(eventData.repetition !== "notRepeat" && diffMonths > 35) {
            alert('반복일정은 3년 이내만 가능합니다.');
            return false;
        }

        //loopDays init - 반복이 아니더라고 초기값은 들어가있다.
        startLoopDays.push(eventData.start)
        endLoopDays.push(eventData.end)
        let dateFormat;
        if(eventData.allDay) dateFormat = "YYYY-MM-DD"; else dateFormat = "YYYY-MM-DD HH:mm";
        //시작일에서 종료일까지 repetition에 따라 date를 만들어낸다.
        switch (eventData.repetition) {
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


        $("#calendar").fullCalendar('renderEvent', eventData, true);
        eventModal.find('input, textarea').val('');
        editAllDay.prop('checked', false);
        eventModal.modal('hide');

        //새로운 일정 저장
        $.ajax({
            type: "post",
            url: "/calendar/register",
            data: {
                groupRowid:groupRowid,
                bundleId : 1 + Math.floor(Math.random() * 100000000),
                title:eventData.title,
                description:eventData.description,
                start:eventData.start,
                end:eventData.end,
                repetition:eventData.repetition,
                startLoopDays: startLoopDays,
                endLoopDays: endLoopDays,
                repetitionEnd:eventData.repetitionEnd,
                backgroundColor:eventData.backgroundColor,
                textColor:eventData.textColor,
                isallday:eventData.allDay
            },
            success: function (response) {
                //DB연동시 중복이벤트 방지를 위한
                $('#calendar').fullCalendar('removeEvents');
                $('#calendar').fullCalendar('refetchEvents');
            }
        });
    });
};