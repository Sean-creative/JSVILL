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


/* ****************
 *  새로운 일정 생성
 * ************** */
let newEvent = function (start, end,) {

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
    $('#save-event').unbind();
    $('#save-event').on('click', function () {

        //editRepetitionEnd (반복마감은 데이터 들어가지 않는다)
        let eventData = {
            title: editTitle.val(),
            start: editStart.val(),
            end: editEnd.val(),
            repetition: editRepetition.val(),
            editRepetitionEnd: editRepetitionEnd.val(),
            description: editDesc.val(),
            backgroundColor: editColor.val(),
            textColor: '#ffffff',
            allDay: false
        };

        if (eventData.start > eventData.end) {
            alert('끝나는 날짜가 앞설 수 없습니다.');
            return false;
        }
        let loopDays = [];

        let diffMonths = moment(eventData.editRepetitionEnd).diff(eventData.start, 'months')
        console.log(`diffMonths : ${ diffMonths}`)
        if(eventData.repetition !== "notRepeat" && diffMonths > 35) {
            alert('반복일정은 3년 이내만 가능합니다.');
            return false;
        }

        loopDays.push(eventData.start)
        //시작일에서 종료일까지 repetition에 따라 date를 만들어낸다.
        switch (eventData.repetition) {
            case "notRepeat" :
                loopDays = []
                break;
            case "everyMonth" :
                //종료일에서 시작일까지 한달씩 더해
                for (let i = 0; i < diffMonths; i++) {
                    loopDays.push((moment(loopDays.at(-1)).add(1, 'months').format('YYYY-MM-DD HH:mm')))
                }
                console.log(`loopDays : ${loopDays}`)
                break;
            case "everyYear" :
                for (let i = 0; i < diffMonths/12; i++) {
                    loopDays.push((moment(loopDays.at(-1)).add(1, 'years').format('YYYY-MM-DD HH:mm')))
                }
                break;
            default :
                alert('선택한 값이 없습니다.');
                break;
        }

        if (eventData.title === '') {
            alert('일정명은 필수입니다.');
            return false;
        }

        let realEndDay;

        if (editAllDay.is(':checked')) {
            eventData.start = moment(eventData.start).format('YYYY-MM-DD');
            //render시 날짜표기수정
            eventData.end = moment(eventData.end).add(1, 'days').format('YYYY-MM-DD');
            //DB에 넣을때(선택)
            realEndDay = moment(eventData.end).format('YYYY-MM-DD');
            eventData.allDay = true;
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
                loopDays: loopDays,
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