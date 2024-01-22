var homeConfig = {
    pageSize: 5,
    currentPage: 1
}

window.onload = loadAllNews();


function formatDateTime(timestamp) {
	  const date = new Date(timestamp);
	  const year = date.getUTCFullYear();
	  const month = String(date.getUTCMonth() + 1).padStart(2, '0');
	  const day = String(date.getUTCDate()).padStart(2, '0');
	  const hours = String(date.getUTCHours()).padStart(2, '0');
	  const minutes = String(date.getUTCMinutes()).padStart(2, '0');
	  const seconds = String(date.getUTCSeconds()).padStart(2, '0');

	  return `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
	}



function loadAllNews() {


    $.ajax({
        url: "/admin/api/v1/news",
        type: "GET",
        data: {
            page: homeConfig.currentPage,
            limit: homeConfig.pageSize
        },

        success: function (data) {

            var totalPages = data.totalPage;
            var currentPage = data.page;
            var listResult = data.listResult;
          console.log(currentPage)
            var str = "";

            const content = document.querySelector("#contentTable");
            var i = 1;
            for (let item of listResult) {
            	
            item.createdDate = formatDateTime(item.createdDate)

                str += `<tr>
                    <th scope="row">${item.id}</th>
                    <td>${item.title}</td>
                    <td>${item.description}</td>
                    
                    <td>${item.createdDate}</td>
                    <td>${item.status}</td>
                    <td><button onclick ="activeAccountByID(${item.id})" type="button" class="btn btn-secondary">Active</button>
                          <button onclick ="disableAccountByID(${item.id})" type="button" class="btn btn-secondary">Inactive</button></td>
                </tr>`
            }
            paging(totalPages, currentPage, function () {
                loadAllNews()
            });
            content.innerHTML = str;

        }

    });
}

function disableAccountByID(id) {
    $.ajax({
        url: "/admin/api/v1/news/" + id,
        type: "delete",
        success: function (data) {
            console.log(data);
            window.onload = loadAllNews();
        }

    });
}
function activeAccountByID(id) {
    $.ajax({
        url: "/admin/api/v1/news/" + id,
        type: "put",
        success: function (data) {
            console.log(data);
            window.onload = loadAllNews();



        }

    });
}



function paging(totalPages, currentPage, callback) {

    $('#pagination').twbsPagination({
        totalPages: totalPages,
        visiblePages: 10,
        startPage: currentPage,
        onPageClick: function (event, page) {
            homeConfig.currentPage = page;
            setTimeout(callback, 100);
        }
    });

}











