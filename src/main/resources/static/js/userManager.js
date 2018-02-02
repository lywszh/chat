$(function () {
        /**
         * 重新搜索 页面更新
         * @param page
         */
        let loadUserList = function (page) {
            console.log("reloadUserList");
            let name = $("#search-name").val().trim();
            let cellPhone = $("#search-cellPhone").val().trim();
            let email = $("#search-email").val().trim();
            $.ajax({
                    url: "/back/listUser",
                    type: "POST",
                    data: {name: name, cellPhone: cellPhone, email: email, page: page, size: 10},
                    success: function (res) {
                        if (res.result) {
                            let dataObj = res.dataObj;
                            console.log(dataObj);
                            loadPersonData(dataObj.content);
                            loadTotalData(dataObj.totalElements);
                            loadPaging(dataObj);
                        } else {
                            layer.msg(res.message);
                        }
                    },
                    error: function (res) {
                    }
                }
            );

        };
        /**
         * 重新加载用户数据
         */
        let loadPersonData = function (data) {
            let tbody = $("#userlist-tbody");
            tbody.empty();
            for (let value of data) {
                let tr = `<tr>
                    <td>${value.userId}</td>
                    <td>${value.name}</td>
                    <td>${value.cellPhone}</td>
                    <td>${value.email}</td>
                    <td>${value.personNote}</td>
                    <td>
                        <button class="btn btn-success " type="button">允许登陆</button>
                    </td>
                </tr>`;
                $(tr).appendTo(tbody);
            }

        };

        /**
         * 重新加载总人数
         */
        let loadTotalData = function (data) {
            $("#totalPersonNum").html(data);
        };

        /**
         * 重新分页
         */
        let loadPaging = function (data) {
            let page = data.number + 1;
            let totalPage = data.totalPages;

            /**
             * 实力不够了，以后再来重构这里的JS...
             *
             */
            function liSwitch(index, page) {
                return `<li ${page === index ? 'class="active"' : ''}><a class="reLoadByPaging" data-index="${index}">${index}</a></li>`;
            }

            function liT(totalPage, page) {
                let str = "";
                for (let value = page; value <= totalPage && value < (page + 5); value++) {
                    str = str + liSwitch(value, page);
                }
                return str;
            }

            let lis = `<li ${data.first ? 'class="disabled"' : ''}><a class="reLoadByPaging" data-index="1" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
             ${liT(totalPage, page)}
             <li ${data.last ? 'class="disabled"' : ''}><a class="reLoadByPaging" data-index="${totalPage}" aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>`;
            $("#userlist-paging").empty().append(lis);
        };
        /**
         * 进入页面，自动加载数据
         */
        loadUserList(1);
        /**
         * 点击按钮，重新加载数据
         */
        $("body").on("click", " #reSearch", function () {
            console.log("reSearch");
            loadUserList(1);
        });
        /**
         * 点击页码的时候加载
         */
        $("body").on("click", ".reLoadByPaging", function () {
            let currPage =$(this).data("index");
            loadUserList(currPage);
        });

    }
);