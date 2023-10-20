function oninputPhoneOrPassword(target, isPhone) {
    target.value = target.value.replace(/[^0-9]/g, '')
    if(isPhone) target.value = target.value.replace(/(^01[016789])(\d{4}|\d{3})(\d{4})$/, "$1-$2-$3");
}