var homeConfig = {
    pageSize: 10,
    currentPage: 1
}


function loadAllNews() {

    var str = "";
    $.ajax({
        url: "/admin/api/v1/news/",
        type: "get",
        data: {
            page: homeConfig.currentPage,
            limit: homeConfig.pageSize
        },

        success: function (data) {

            var totalPages = data.totalPage;
            var currentPage = data.currentPage;
            var listResult = data.listResult;

            const content = document.querySelector("#contentTable");
            var i = 1;
            for (let item of listResult) {


                if (item.status === 1) {
                    var status = "active"
                } else {
                    var status = "inactive"
                }
                str += `<tr>
                    <th scope="row">${item.id}</th>
                    <td>${item.title}</td>
                    <td>${item.description}</td>
                    <td>${item.createDate}</td>
                    <td>${status}</td>
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
            window.onload = loadAllCategories();
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
window.onload = loadAllNews();


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











