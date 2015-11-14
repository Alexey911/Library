$(document).ready(function () {
    var $deleteId = $('#deleteId');
    var $editId = $('#editId');
    var $name = $('#name');
    var $desc = $('#desc');
    $('.field').on("click", function () {
        var $element = $(this);
        $deleteId.val($element.find('.id').html());
        $editId.val($element.find('.id').html());
        $name.val($element.find('.name').html());
        $desc.val($element.find('.desc').html());
    });
});