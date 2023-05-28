//SELECT 반복 변경
// $('edit-repetition').change(function () {
//     $(this).css('repetition', $(this).val());
// });

//SELECT 색상 변경
$('#edit-color').change(function () {
    $(this).css('color', $(this).val());
});

//datetimepicker
$("#edit-start, #edit-end, #edit-repetition-end").datetimepicker({
    format: 'YYYY-MM-DD HH:mm'
});

$('#edit-repetition').change(function () {
    repetitionEndChange($(this).val()!=="notRepeat")
});

function repetitionEndChange(isRepetition) {
    if(isRepetition) $("#edit-repetition-end").removeAttr("disabled");
    else {
        $("#edit-repetition-end").attr("disabled", true).val('');
    }
}