var homeConfig = {
		pageSize : 10,
		currentPage : 1
}
const date = new Date();

const year = date.getFullYear();
const month = getMonthName(date.getMonth() + 1);

function getMonthName(monthNumber) {
	 
	  return date.toLocaleString('en-US', {
	    month: 'long',
	  });
	}


window.onload= renderProducts(month,year);


 function renderProducts(month,year) {
	 
	
						$.ajax({
								url : "/admin/api/v1/orders",
								type : "GET",
								data:{
									month:month,
									year:year,
									page:homeConfig.currentPage,
									limit:homeConfig.pageSize
									
								},
								success : function(data) {
									var totalPages = data.totalPage;
									var currentPage = data.currentPage
									 
									var listResult = data.listResult
									var row = document.getElementById("contentTable")
									
									var str = ""
									for (let item of listResult) {
										str+=`<tr>
											<td>${item.id}</td>
											<td>${item.customerName}</td>
											<td>${item.address}</td>
											<td>${item.email}$</td>
											<td>${item.phonenumber}$</td>
											<td>${item.totalPay}$</td>

											<td><a href="#" onclick="viewProduct(${item.id})"
												class="edit"><i class="fa fa-pencil mr-4 ml-2 "></i></a>
												<a href="#" onclick="deleteProduct(${item.id})"><i class="fa fa-trash"></i></a></td>
										</tr>`
									}
									paging(totalPages,currentPage,function(){
										renderProducts()
									});
									
									row.innerHTML = str
									
									

								}
							});
	

				}


function paging(totalPages,currentPage,callback) {
	
		 $('#pagination').twbsPagination({
			totalPages : totalPages,
			visiblePages : 10,
			startPage : currentPage,
			onPageClick : function(event, page) {
				homeConfig.currentPage = page;
				setTimeout(callback, 100);
			}
		});
	
}
$("#inlineFormCustomSelect").on("change", function() {
	var str = "";
	$("select option:selected").each(function() {
		str += $(this).text() + " ";
	});
	const monthAndYear = str.split(" ");
	renderProducts(monthAndYear[0],monthAndYear[1]);
	
	const exportExcelLink = document.getElementById('exportExcelLink');


	exportExcelLink.href = `/admin/api/v1/orders/excel?month=${monthAndYear[0]}&year=${monthAndYear[1]}`;
	
}).trigger("change");









	



