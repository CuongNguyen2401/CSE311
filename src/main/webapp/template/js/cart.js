function addToCart(id) {

	$.ajax({
		url : "api/cart",
		type : "POST",
		data : {
			idP : id

		},
		success : function(data) {
			console.log(data)
			$('#count_in_cart').html(data.items.length)

		}

	});

}

function minusCountInCart() {
    
    var data = $('.cc_cart_count_child').html();
    
    data--;
    $('.cc_cart_count_child').html(data);
    
}

function renderSumary() {
    
    $.ajax({
        url: "api/cart",
        type: "GET",
        success: function (data) {
            var listItem = data.items
            doRenderSummary(listItem)
            renderTotalPayment(data)
          
        }
    });
    
}
function doRenderSummary(listItem) {
	   let row = document.getElementById("summary-content");
	   if(row!=null){
		   var str=""
				for (let item of listItem) {
					var price =item.quantity * item.productDTO.price
					str+=`<div class="row no-gutters mt-3">
		         <div class="col-md-6 col-6">${item.productDTO.productName}</div>
		         <div class="col-md-3 col-3">${item.quantity}</div>
		         <div class="col-md-3 col-3">${price}</div>
		         </div>`
				}
	   }
	 row.innerHTML = str;
}

function  doFunctionInCartByMode(id, mode) {
    $.ajax({
        url: "api/cart",
        type: "get",
        data: {
            idP: id,
            mode: mode
        },

        success: function (data) {
        	 var listItem = data.items
             doRenderSummary(listItem)
        	 renderTotalPayment(data)
        	 doRenderProduct(listItem)

        }
    });
}

function  renderTotalPayment(data){
	 var content = document.getElementById("totalPay");
    if(content!=null){
    	content.innerHTML = `<h5>$ ${data.totalPay}</h5>`
    }
}




function renderProducts() {
	$.ajax({
		url : "api/cart",
		type : "GET",
	
		success : function(data) {
			var listProduct = data.items
			doRenderProduct(listProduct)
		}
	
	});

}
function doRenderProduct(listProduct) {
	var row = document.getElementById("content");
	if(row !=null){
		var str = ""
		
		for (let item of listProduct) {
			var type ="Food"
				if(item.productDTO.categoryID ===2){
					type ="Drink"
				}
	
			str+=`<div class="row mb-4 d-flex justify-content-between align-items-center customDetail">
            <div class="col-md-2 col-4 col-lg-2  col-xl-2">
                <img src="${item.productDTO.img}" class="mb-3 img-fluid" alt="">
            </div>
            <div class="col-md-3 col-8 col-lg-3  col-xl-3 hrbefore">
                <h6 class="text-muted cart-name">${type}</h6>
                <h6 class="text-black mb-3 cart">${item.productDTO.productName}</h6>
            </div>

            <div class="col-md-3 col-5 col-lg-3 col-xl-2 d-flex">
                <button class="btn btn-link px-2"
                    onclick="this.parentNode.querySelector('input[type=number]').stepDown(); doFunctionInCartByMode(${item.productDTO.id},'minusQuantity')">
                    <i class="fa fa-minus"></i>
                </button>

                <input style="width: 50px;" id="form1" min="0" name="quantity"
                    value="${item.quantity}" type="number" class="form-control form-control-sm" />

                <button class="btn btn-link px-2"
                    onclick="this.parentNode.querySelector('input[type=number]').stepUp(); doFunctionInCartByMode(${item.productDTO.id},'plusQuantity')">
                    <i class="fa fa-plus"></i>
                </button>
            </div>
            <div class="col-md-3 col-4 col-lg-2  col-xl-2 offset-lg-1 ">
                <h6 class="mb-0">$ ${item.productDTO.price}</h6>
            </div>
            <div class="col-md-1 col-3 col-lg-1 col-xl-1 text-end ">
                <a onclick="doFunctionInCartByMode(${item.productDTO.id},'delete'); minusCountInCart()" class="text-muted"><i class="fa fa-trash-o  "></i></a>
            </div>
        </div>
        <hr class="my-4">`
		}
		row.innerHTML = str
		
		
		
	}
	
}



