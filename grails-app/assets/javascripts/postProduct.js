function completePostTemplate(urlImg, itemTitle, itemPrice) {
	
	$("#post_result").empty();
	var template = $("#post_item_template").html();
	template = template.replace("#urlImg", urlImg);
	template = template.replace("#itemTitle", itemTitle);
	template = template.replace("#itemPrice", itemPrice);
	$("#post_result").append(template);
};

function postProduct() {
	$("#postContent").empty();

	var jsProdContent = {
		text:  $("#postContent").val(),
		title: $("#post_result h3").text(),
		price: $("#post_result p").text(),
		image: $("#post_result img").attr("src"),
		usernameDestination: $('#combobox option:selected').val()
	};

	jsProdContent = JSON.stringify(jsProdContent)

	$.post(urlController, {"jsProdContent": jsProdContent}).done(function() {
		console.log("in postProduct");
		$("#postModal").modal('hide');
	});
	

};
