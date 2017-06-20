$(document).ready(function() {
    $.ajax({
        url: "/careem/careemcoin/marcos/wallet"
    }).then(function(data) {
       $('.wallet-value').append(data.coins);       
    });
});