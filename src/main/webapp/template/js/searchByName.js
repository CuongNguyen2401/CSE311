
function searchByName(param) {
    var txtName = param.value;
    $.ajax({
        url: "/FastFoodRestaurant/search",
        type: "get",
        data: {
            txt: txtName

        },
        success: function (data) {
            console.log(data)
            data = JSON.parse(data);
            var rowFood = document.getElementById("rowFood");
            var rowDrink = document.getElementById("rowDrink");
            let strFood = "";
            let strDrink = "";

            for (var items of data) {
                if (items.categoryID === 1) {
                    strFood += `<div class="col-md-3 col-12 mt-4">
                                <div class="card h-100 foodCard">
                                    <a href="detail?pid=${items.id}">
                                        <img class="card-img-top" src="${items.img}" alt="Card image cap">
                                    </a>

                                    <div class="card-body">
                                        <h5 class="card-title">${items.productName}</h5>
                                        <p class="card-text">${items.price}$</p>
                                       <button style="width: 90%" onClick="addToCart(${items.id})" class="btn btn-primary addToCart">Add to cart</button>
                                    </div>
                                </div>
                            </div>`
                } else if (items.categoryID === 2) {
                    strDrink += `<div class="col-md-3 col-12 mt-4">
                                <div class="card h-100 drinkCard">
                                    <a href="detail?pid=${items.id}">
                                        <img class="card-img-top" src="${items.img}" alt="Card image cap">
                                    </a>

                                    <div class="card-body">
                                        <h5 class="card-title">${items.productName}</h5>
                                        <p class="card-text">${items.price}$</p>
                                       <button style="width: 90%" onClick="addToCart(${items.id})" class="btn btn-primary addToCart">Add to cart</button>
                                    </div>
                                </div>
                            </div>`
                }

            }

            rowFood.innerHTML = strFood;
            rowDrink.innerHTML = strDrink;
        }

    });


}