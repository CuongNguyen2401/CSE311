$('.menu-mobile-icon').click(
        function () {
            $('.overlay-mobile-menu').show();
        }
)

$('.close-btn').click(function () {
    $('.overlay-mobile-menu').hide();
})

$('.menu-mobile').on('click', 'li a', function () {
    console.log('hehe')
    $('.overlay-mobile-menu').hide();
})