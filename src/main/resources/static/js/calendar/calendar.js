let draggedEventIsAllDay;
let activeInactiveWeekends = true;
let fixedDate;

let calendar = $('#calendar').fullCalendar({

    /** ******************
     *  OPTIONS
     * *******************/
    locale: 'ko',
    timezone: "local",
    nextDayThreshold: "09:00:00",
    allDaySlot: true,
    displayEventTime: true,
    displayEventEnd: true,
    firstDay: 0, //월요일이 먼저 오게 하려면 1
    weekNumbers: false,
    selectable: true,
    weekNumberCalculation: "ISO",
    eventLimit: true,
    views: {
        month: {eventLimit: 12} // 한 날짜에 최대 이벤트 12개, 나머지는 + 처리됨
    },
    eventLimitClick: 'week', //popover
    navLinks: true,
    defaultDate: moment().format('YYYY-MM-DD'), //실제 사용시 현재 날짜로 수정
    timeFormat: 'HH:mm',
    defaultTimedEventDuration: '01:00:00',
    editable: true,
    minTime: '00:00:00',
    maxTime: '24:00:00',
    slotLabelFormat: 'HH:mm',
    weekends: true,
    nowIndicator: true,
    dayPopoverFormat: 'MM/DD dddd',
    longPressDelay: 0,
    eventLongPressDelay: 0,
    selectLongPressDelay: 0,
    header: {
        left: 'today, prevYear, nextYear, viewWeekends',
        center: 'prev, title, next',
        right: 'month, agendaWeek, agendaDay, listWeek'
    },
    views: {
        month: {
            columnFormat: 'dddd'
        },
        agendaWeek: {
            columnFormat: 'M/D ddd',
            titleFormat: 'YYYY년 M월 D일',
            eventLimit: false
        },
        agendaDay: {
            columnFormat: 'dddd',
            eventLimit: false
        },
        listWeek: {
            columnFormat: ''
        }
    },
    // customButtons: { //주말 숨기기 & 보이기 버튼
    //     viewWeekends: {
    //         text: '주말',
    //         click: function () {
    //             activeInactiveWeekends ? activeInactiveWeekends = false : activeInactiveWeekends = true;
    //             $('#calendar').fullCalendar('option', {
    //                 weekends: activeInactiveWeekends
    //             });
    //         }
    //     }
    // },


    eventRender: function (event, element, view) {

        //일정에 hover시 요약
        element.popover({
            title: $('<div />', {
                class: 'popoverTitleCalendar',
                text: event.title
            }).css({
                'background': event.backgroundColor,
                'color': event.textColor
            }),
            content: $('<div />', {
                class: 'popoverInfoCalendar'
            })
                .append('<p><strong>시간:</strong> ' + getDisplayEventDate(event) + '</p>')
                .append('<div class="popoverDescCalendar"><strong>설명:</strong> ' + event.description + '</div>'),
            delay: {
                show: "800",
                hide: "50"
            },
            trigger: 'hover',
            placement: 'top',
            html: true,
            container: 'body'
        });

        return true;

    },

    /* ****************
     *  일정 받아옴
     * ************** */
    events: function (start, end, timezone, callback) {
        $.ajax({
            type: "GET",
            url: "/calendar/read",
            data: {
                // 화면이 바뀌면 Date 객체인 start, end 가 들어옴
                groupRowid: groupRowid
                //startDate : moment(start).format('YYYY-MM-DD'),
                //endDate   : moment(end).format('YYYY-MM-DD')
            },
            success: function (response) {
                fixedDate = response.map(function (array) {
                    if (array.allDay && array.start !== array.end) {
                        array.end = moment(array.end).add(1, 'days'); // 이틀 이상 AllDay 일정인 경우 달력에 표기시 하루를 더해야 정상출력
                    }
                    console.log("array : " + JSON.stringify(array))
                    return array;
                });
                const calendarTitleSet = new Set();
                const now = moment()
                fixedDate.forEach(function (date) {
                    if(date.repetition==="notRepeat"&&now.isAfter(moment(date.end))) return
                    else if(date.repetition!=="notRepeat"&&now.isAfter(moment(date.repetitionEnd))) return
                    calendarTitleSet.add(date.title)
                });

                $('#calendarTitleSetDiv').empty()
                calendarTitleSet.forEach(function (value) {
                    $('#calendarTitleSetDiv').append(`<div class="fc-event badge" style="display:block;width:fit-content;margin-top:15px;font-size:16px;font-weight:normal">${value}</div>`)
                })

                callback(fixedDate);
            }
        });
    },

    eventAfterAllRender: function (view) {
        if (view.name == "month") $(".fc-content").css('height', 'auto');
    },

    //일정 리사이즈
    eventResize: function (event, delta, revertFunc, jsEvent, ui, view) {
        console.log("일정 리사이즈")
        $('.popover.fade.top').remove();

        /** 리사이즈시 수정된 날짜반영
         * 하루를 빼야 정상적으로 반영됨. */
        let newDates = calDateWhenResize(event);

        //리사이즈한 일정 업데이트
        $.ajax({
            type: "get",
            url: "",
            data: {
                //id: event._id,
                //....
            },
            success: function (response) {
                alert('수정: ' + newDates.startDate + ' ~ ' + newDates.endDate);
            }
        });

    },

    eventDragStart: function (event, jsEvent, ui, view) {
        draggedEventIsAllDay = event.allDay;
    },

    //일정 드래그앤드롭
    eventDrop: function (event, delta, revertFunc, jsEvent, ui, view) {
        console.log("일정 드래그앤드롭")
        $('.popover.fade.top').remove();

        //주,일 view일때 종일 <-> 시간 변경불가
        if (view.type === 'agendaWeek' || view.type === 'agendaDay') {
            if (draggedEventIsAllDay !== event.allDay) {
                alert('드래그앤드롭으로 종일<->시간 변경은 불가합니다.');
                location.reload();
                return false;
            }
        }

        // 드랍시 수정된 날짜반영
        let newDates = calDateWhenDragnDrop(event);

        //드롭한 일정 업데이트
        $.ajax({
            type: "post",
            url: "/calendar/modify",
            data: {
                groupRowid: groupRowid,
                calendarRowid: event.calendarRowid,
                title: event.title,
                description: event.description,
                start: newDates.startDate,
                end: newDates.endDate,
                backgroundColor: event.backgroundColor,
                textColor: event.textColor,
                isallday: event.allDay
            },
            success: function (response) {
                alert('반복일정 중 해당일정만 수정됩니다.\n수정 후: ' + newDates.startDate + ' ~ ' + newDates.endDate);
            }
        });

    },

    select: function (startDate, endDate, jsEvent, view) {

        let today = moment();
        if (view.name == "month") {
            startDate.set({
                hours: 8,
                minute: 0
            });
            startDate = moment(startDate).format('YYYY-MM-DD HH:mm');
            endDate = moment(endDate).subtract(1, 'days');

            endDate.set({
                hours: 9,
                minute: 0
            });
            endDate = moment(endDate).format('YYYY-MM-DD HH:mm');
        } else {
            startDate = moment(startDate).format('YYYY-MM-DD HH:mm');
            endDate = moment(endDate).format('YYYY-MM-DD HH:mm');
        }
        newEvent(startDate, endDate);
    },

    //이벤트 클릭시 수정이벤트
    eventClick: function (event, jsEvent, view) {
        editEvent(event);
    }
});

function getDisplayEventDate(event) {

    let displayEventDate;

    if (event.allDay == false) {
        let startTimeEventInfo = moment(event.start).format('HH:mm');
        let endTimeEventInfo = moment(event.end).format('HH:mm');
        displayEventDate = startTimeEventInfo + " - " + endTimeEventInfo;
    } else {
        displayEventDate = "하루종일";
    }

    return displayEventDate;
}

function calDateWhenResize(event) {

    let newDates = {
        startDate: '',
        endDate: ''
    };

    if (event.allDay) {
        newDates.startDate = moment(event.start._d).format('YYYY-MM-DD');
        newDates.endDate = moment(event.end._d).subtract(1, 'days').format('YYYY-MM-DD');
    } else {
        newDates.startDate = moment(event.start._d).format('YYYY-MM-DD HH:mm');
        newDates.endDate = moment(event.end._d).format('YYYY-MM-DD HH:mm');
    }

    return newDates;
}

function calDateWhenDragnDrop(event) {
    // 드랍시 수정된 날짜반영
    let newDates = {
        startDate: '',
        endDate: ''
    }

    // 날짜 & 시간이 모두 같은 경우
    if (!event.end) {
        event.end = event.start;
    }

    //하루짜리 all day
    if (event.allDay && event.end === event.start) {
        newDates.startDate = moment(event.start._d).format('YYYY-MM-DD');
        newDates.endDate = newDates.startDate;
    }

    //2일이상 all day
    else if (event.allDay && event.end !== null) {
        newDates.startDate = moment(event.start._d).format('YYYY-MM-DD');
        newDates.endDate = moment(event.end._d).subtract(1, 'days').format('YYYY-MM-DD');
    }

    //all day가 아님
    else if (!event.allDay) {
        newDates.startDate = moment(event.start._d).format('YYYY-MM-DD HH:mm');
        newDates.endDate = moment(event.end._d).format('YYYY-MM-DD HH:mm');
    }

    return newDates;
}