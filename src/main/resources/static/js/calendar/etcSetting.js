//SELECT 반복 변경
$('edit-repetition').change(function () {
    $(this).css('repetition', $(this).val());
});

//SELECT 색상 변경
$('#edit-color').change(function () {
    $(this).css('color', $(this).val());
});

//datetimepicker
$("#edit-start, #edit-end").datetimepicker({
    format: 'YYYY-MM-DD HH:mm'
});