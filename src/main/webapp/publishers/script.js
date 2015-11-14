$(document).ready(function () {
    var $idField = $('#fieldForId');
    var $name = $('#name');
    var $desc = $('#address');
    $('.field').on("mousemove", function(){
        var $element = $(this);
        var val = $element.find('.id').html();
        $idField.val(val);
        $name.val($element.find('.name').html());
        $desc.val($element.find('.address').html());
    });
});