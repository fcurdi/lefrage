$(function() {
	$("#btn-previous").hide();	
	$("#btn-next").hide();
	$("#btn-favourite").hide();	
});

function searchItems(offset, item, favouriteUrl) {
	var search = $.get("https://api.mercadolibre.com/sites/MLA/search",
						{q: item,
						offset: offset,
						limit: 51,
						condition: "new",
						buying_mode: "buy_it_now"});
	search.done(function(data){
		showResults(data, favouriteUrl, item)
	});
	search.fail(showError);
};

function showResults(data, favouriteUrl, item) {
	$("#search_result").empty();
	var super_promise = fetchItemData(data);
	super_promise.done(function() {
		if (data.paging.offset > 0) {
			$("#btn-previous").show();
		} else {
			$("#btn-previous").hide();	
		}

		if (data.paging.offset > data.paging.total - 51) {
			$("#btn-next").hide();
		} else {
			$("#btn-next").show();
		}
		
		$("#btn-favourite").show();
	});

	
	$("#btn-next").off("click").click(function() {
		searchItems(data.paging.offset + 51, data.query);
	});

	$("#btn-previous").off("click").click(function() {
		searchItems(data.paging.offset - 51, data.query);
	});

	$("#btn-favourite").off("click").click(function() {
		//TODO hacer que se guarden los stats en el controller
		var info = { "query": item};
		var infoStr = JSON.stringify(info);
		$.ajax({
			method:"POST",
			url: favouriteUrl,
			data: {"infoStr": infoStr},
			success: function(){
				if(!favourited){
					$("#btn-favourite").addClass("next-selected");
					favourited=true;
					alert("Favoriteado!");
				}
				else{
					$("#btn-favourite").removeClass("next-selected");
					favourited=false;
					alert("Desfavoriteado");
				}
			}
		});
	});
};

function fetchItemData(data) {
	var promises = [];
	$.each(data.results, function(index, item) {
		var promise = addResults(index, item);
		promises.push(promise);
	});

	var super_promise = $.when.apply(this, promises);
	return super_promise;
};
			
function addResults (index, item) {
	var search_item = $.get("https://api.mercadolibre.com/items/"+item.id);
	search_item.done(function(res) {
		console.log(res.pictures)
		var content = $("#search_item_template").html();
		content = content.replace(/#itemTitle/g, res.title);
		content = content.replace(/#itemPrice/g, res.price);
		if(res.pictures[0])
			content = content.replace(/#urlImg/g, res.pictures[0].url);
		$("#search_result").append(content);
	});
	search_item.fail(showError);
	return search_item;
};

			
function showError() {
	$("#search_result").empty();
	$("#search_result").append($("#error_search"));
};
