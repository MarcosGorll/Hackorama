var customerJson = null;
var selectedCustomerOnTable = null;

var productJson = null;
var selectedProductOnTable = null;

function loadUsers() {
	$.ajax({
		"url" : "/careem/careemcoin/users",
		"crossDomain" : false,
		"dataType" : "json",
		"contentType" : "application/json",
		success : function(result) {
			customerJson = result;
			fillCustomerTableData(result);
		}
	});
}

function loadProducts() {
	$.ajax({
		"url" : "/careem/careemcoin/products",
		"crossDomain" : false,
		"dataType" : "json",
		"contentType" : "application/json",
		success : function(result) {
			productJson = result;
			fillProductTableData(result);
		}
	});
}

function fillCustomerTableData(result) {
	var customers = [];
	for (var i = 0, len = result.length; i < len; i++) {
		var desc = result[i].name;
		customers.push([ result[i].name, result[i].wallet.coins ]);
	}

	$('#customers').bootstrapTable('destroy');
	$('#customers').bootstrapTable(
			{
				data : customers,
				onClickRow : function(row, $element) {
					selectedCustomerOnTable = customerJson[$element.index()];
					$("#selected_customer_name").text(
							customerJson[$element.index()].name);

				}
			});
}

function fillProductTableData(result) {
	var products = [];
	for (var i = 0, len = result.length; i < len; i++) {
		var desc = result[i].name;
		products.push([ result[i].name, result[i].price ]);
	}

	$('#products').bootstrapTable('destroy');
	$('#products').bootstrapTable(
			{
				data : products,
				onClickRow : function(row, $element) {
					selectedProductOnTable = productJson[$element.index()];
					$("#selected_product_name").text(
							productJson[$element.index()].name);
				}
			});
}

function endTrip() {
	var paymentType = $("#payment_type").prop('selectedIndex') + 1;
	var tripValue = $("#trip_value").val();

	$
			.ajax({
				"url" : "/careem/careemcoin/" + selectedCustomerOnTable.id
						+ "/endtrip",
				"method" : "POST",
				"data" : '{"paymentTypeCode" : ' + paymentType + ', "value" : '
						+ tripValue + '}',
				"crossDomain" : false,
				"dataType" : "json",
				"contentType" : "application/json",
				success : function(result) {
					loadUsers();
				}
			});
}

function transferCoins() {
	var targetUser = $("#transfer_customer_target").val();
	var amount = $("#amout_transfer").val();

	$.ajax({
		"url" : "/careem/careemcoin/" + selectedCustomerOnTable.id + "/"
				+ targetUser + "/transfer",
		"method" : "POST",
		"data" : '{"amount" : ' + amount + '}',
		"crossDomain" : false,
		"dataType" : "json",
		"contentType" : "application/json",
		success : function(result) {
			loadUsers();
		}
	});
}

function buyProduct() {
	$.ajax({
		"url" : "/careem/careemcoin/" + selectedCustomerOnTable.id + "/"
				+ selectedProductOnTable.id + "/buy",
		"method" : "POST",
		"crossDomain" : false,
		"dataType" : "json",
		"contentType" : "application/json",
		success : function(result) {
			loadUsers();
		}
	});
}

function payTripWithCoins() {
	var tripValue = $("#trip_pay_value").val();
	var percentage = $("#trip_pay_percentage").val();

	$
			.ajax({
				"url" : "/careem/careemcoin/" + selectedCustomerOnTable.id
						+ "/paytrip",
				"method" : "POST",
				"crossDomain" : false,
				"data" : '{"value" : ' + tripValue + ', "percentage" : '
						+ percentage + '}',
				"dataType" : "json",
				"contentType" : "application/json",
				success : function(result) {
					loadUsers();
				}
			});
}

$(document).ready(function() {

	$("#end_trip_button").click(function() {
		endTrip();
	});

	$("#transfer_button").click(function() {
		transferCoins();
	});

	$("#buy_product_button").click(function() {
		buyProduct();
	});
	
	$("#pay_button").click(function() {
		payTripWithCoins();
	});
	
	// load the users
	loadUsers();

	// load the products
	loadProducts();
});