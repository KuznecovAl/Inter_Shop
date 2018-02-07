$(document).ready(function(){
    $('#addPerson').click(function () {
        addPerson();
    });
    $('#deletePerson').click(function () {
        deletePerson();
    });
    $('#productAdd').click(function () {
        addProduct();
    });
    $('#productUpdate').click(function () {
        updateProduct();
    });
    $('#productDelete').click(function () {
        deleteProduct();
    });
});


function addPerson() {
    $('#personForm').prop('action', 'add.form');
    $('#personButton').prop('value', 'Add person');
    $('#personForm').show();
}

function deletePerson() {
    $('#personForm').prop('action', 'delete.form');
    $('#personButton').prop('value', 'Delete person');
    $('#personForm').show();
}

function addProduct() {
    var name = $("#name").val();
    var model = $("#model").val();
    var price = $("#price").val();
    if (name && model && price) {
        var product = {
            name: name,
            model: model,
            price: price
        };
        $.ajax({
            headers:"Accept: application/json",
            data:product,
            dataType: "json",
            type:'post',
            url: restUrl
        }).done(function(data) {
            var el = '<div id="' + data.id + '">'+ data.name +'    '+data.model+'    '+data.price+'</div>';
            $(".productTable").append(el);
        }).fail(function(data){
            if ( console && console.log ) {
                console.log( "Error data:", data);
            }
        });
    }
}

function deleteProduct() {
    var id = $("#id").val();
    var url = restUrl+ "/" + id;

    $.ajax({
        headers:"Accept: application/json",
        type:'delete',
        url: url
    }).done(function(data) {
        $("#"+id).remove();
    }).fail(function(data){
        if ( console && console.log ) {
            console.log( "Error data:", data);
        }
    });
}

