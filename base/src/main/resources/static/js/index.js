console.log('script');

$(document).ready(function () {
    $("#locales").change(function () {
        var selectedOption = $('#locales').val();
        if (selectedOption != '') {
            window.location.replace('/?lang=' + selectedOption);
        }
    });
 });

// const btn_ru = document.getElementById('btn_ru');
// const btn_en = document.getElementById('btn_en');
//
// console.log('script2');
//
// if(btn_ru) {
//     console.log(btn_ru.dataset.lang);
//     // btn_ru.addEventListener('click', () => window.location.replace('/?lang=' + btn_ru.value));
// }
// if(btn_en) {
//     console.log(btn_en.dataset.lang);
//     // btn_en.addEventListener('click', () => window.location.replace('/?lang=' + btn_en.value));
// }
