$(document).ready(function () {
    $('.addProductBtn').click(function () {
        addProduct($(this));
    });
});

function addProduct(element) {
    var productId = $(element).attr('id');
    var json = JSON.stringify(productId);
    console.log(json);
    $.ajax({
        type: 'get',
        // data:{productId:productId, command:"addProduct"},
        url: contextUrl + '/frontController?command=addProduct&productId=' + productId
    }).done(function (data) {
        var productId = JSON.parse(data);
        $('#count'+productId).text(productId);
    }).fail(function (data) {
        if (console && console.log) {
            console.log("Error data:", data);
        }
    });
}
