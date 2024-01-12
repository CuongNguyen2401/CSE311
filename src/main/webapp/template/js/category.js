var categoryName = null;
function loadByCategory(categoryName) {

	$.ajax({
		url : "/api/v1/products/category/" + categoryName,
		type : "get",
		
		success : function(data) {
			let res = data.listResult;
			let str = "";
			for (let o of res) {
				str += `<div  style="position: static !important" class="col-md-3 col-12 mt-4 element-item" data-category="food">

					<div class="card h-100 foodCard">
						<a href="detail?pid=${o.id}"> <img class="card-img-top"
							src="http://localhost:8080/${o.listImage[0].imageURL}"
							alt="Card image cap">

						</a>

						<div class="card-body">
							<h5 class="card-title name">${o.productName}</h5>
							<p style="font-size: 16px!important" class="card-text price">$${o.price}</p>
							<button style="width: 90%" onClick="addToCart(${o.id})"
								class="btn btn-primary addToCart">Add to cart</button>
						</div>
					</div>



				</div>
			` 

		
			}
			$("#rowFood").html(str);
			isotope()
		}

	});

}
window.onload = ()=>{
	categoryName = getCategoryNameFromUrl()
	loadByCategory(categoryName)
	loadBreadCrumb()
	loadTypeOfProductTitle()
}

function loadBreadCrumb(){
	$("#breadcrumb-data").html(getCategoryNameFromUrl().charAt(0).toUpperCase() + getCategoryNameFromUrl().slice(1))
}
function loadTypeOfProductTitle(){
	$(".title").html(getCategoryNameFromUrl().charAt(0).toUpperCase() + getCategoryNameFromUrl().slice(1))
}


function getCategoryNameFromUrl(){
	
	const currentUrl = window.location.href;

	const urlParts = currentUrl.split('/');
	const category = urlParts[urlParts.indexOf('category') + 1];

	return category;
}


function isotope(){
	
	var $grid = $('.grid').isotope({
		itemSelector : '.element-item',
		layoutMode : 'fitRows',
		getSortData : {
			name : '.name',
			price : function(itemElem) {
				var priceText = $(itemElem).find('.price').text();
				var match = priceText.match(/\$(\d+)/);

				
				if (match) {
				    var price = parseFloat(match[1]);
				    console.log(price);
				   
				return price;
				}
			
			}
		}
	});
	
	
	$('#sorts').on('change', function () {
        var sortByValue = $(this).find(':selected').data('sort-by');
        $grid.isotope({
            sortBy: sortByValue
        });
       
    });
	
}

