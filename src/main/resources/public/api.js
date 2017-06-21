$(document).ready(function() {
    $.ajax({
        url: "/careem/careemcoin/marcos/wallet"
    }).then(function(data) {
       $('.wallet-value').append(data.coins);       
    });

    var users;
    var products;

    $.ajax({
        url: "/careem/careemcoin/users"
    }).then(function(data) {
        users = data;
    });

    $.ajax({
        url: "/careem/careemcoin/products"
    }).then(function(data) {
        products = data;
    });

    $('.users').append(
        $.map(users, function(user, i) {
            return '<tr><td>' + user.name + '</td><td>' + user.tripCount + '</td><td>' + user.wallet.coins + '</td>';
        }).join()
    );

    $('.users-options').append(
        $.map(users, function(user, i) {
            return '<option value="' + user.name + '">' + user.name + '</option>';
        }).join()
    );

});