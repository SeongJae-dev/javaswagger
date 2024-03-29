<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>검색</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css"
          integrity="sha384-Smlep5jCw/wG7hdkwQ/Z5nLIefveQRIY9nfy6xoR1uRYBtpZgI6339F5dgvm/e9B"
          crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script
            src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/js/bootstrap.min.js"
            integrity="sha384-o+RDsa0aLu++PJvFqy8fFScvbHFLtbvScb8AjopnFD+iEQ7wo/CG0xlczd+2O/em"
            crossorigin="anonymous"></script>
    <script
            src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
            integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
            crossorigin="anonymous"></script>
    <!--부트 스트랩 CDN  -->
    <script type="text/javascript">
        $(function () {
            <%String keyword = request.getParameter("keyword");%>//검색어

            $("#btnUserProfile").click(function () {
                location.href = "profile/userProfile?user_ID=${user_ID}";
            })

            $(".btn-outline-secondary").click(function () {
                var keyword = $("#keyword").val();
                if (keyword.indexOf("#") >= 0) {
                    var key = keyword.substr(1, keyword.length);
                    $("#keyword").val(key);
                    $("#F").attr("action", "hashtag");
                } else {
                    $("#F").attr("action", "search");
                }
            })

            $.ajax({
                url: 'hashtagPost?keyword=<%=keyword%>',
                success: function (data) {
                    list = eval("(" + data + ")");
                    alert(list)
                    $.each(list, function (idx, p) {
                        var fname = p.post_fname

                        if (fname != null) //파일이 없으면 이미지 생성 x
                        {
                            var img = $("<img id='pImg'/>").attr({
                                src: "resources/image/" + fname
                            })
                        }

                        var detail_a = $("<a></a>").attr({
                            href: "#",
                            no: p.post_no
                        })

                        $(detail_a).append(img)
                        $(".item").append(detail_a);

                        $(detail_a).click(function () {
                            no = $(this).attr("no");
                            $("#col_comment_content").empty();
                            $.ajax({
                                url: "detailPost?post_no=" + no, success: function (data) { //게시글 상세
                                    detail = eval("(" + data + ")")
                                    //alert(data)
                                    $('#post_no').val(detail.post_no);
                                    $('#detail_Img').attr("src", "resources/image/" + detail.post_fname);
                                    $('#h3_detail_userID').html(detail.user_ID);
                                    $('#small_detail_content').html(detail.post_hash);
                                    $.ajax({ //댓글 리스트
                                        url: "listComment.do?post_no=" + detail.post_no,
                                        success: function (data) {
                                            var arr = eval("(" + data + ")")
                                            //alert(arr)
                                            $.each(arr, function (i, p) {
                                                var h6 = $("<h6></h6>").html(p.user_ID + " ");
                                                var small = $("<small></small>").html(p.comment_content);
                                                $(h6).append(small);
                                                $("#col_comment_content").append(h6);

                                            })
                                        }
                                    })

                                }
                            })
                            $('#detail_Dialog').modal('show')
                        })

                        $imgs = $(".item").imagesLoaded(function () {
                            $imgs.masonry({
                                itemSelector: 'img', // img 태그를 대상으로 masonry 적용
                                fitWidth: true // 내용물을 가운데 정렬하기, CSS margin:0 auto; 설정이 필요함
                            });
                        });
                    })
                }
            })//ajax end
        })
    </script>
</head>
<body>
<nav class="navbar">
    <div class="container">
        <div class="col-4">
            <div class="navbar-header navbar-left">
                <h1><a class="navbar-brand" href="timeLine">Eden</a></h1>
            </div>
        </div>
        <div class="col-4">
            <form class="navbar-form navbar-center" id="F">
                <div class="input-group">
                    <input type="text" class="form-control" placeholder="Search" id="keyword" name="keyword">
                    <div class="input-group-append">
                        <button class="btn btn-outline-secondary" type="submit">
                            <img src="resources/icon/search2.png" width="18" height="18">
                        </button>
                    </div>
                </div>
            </form>
        </div>
        <div class="col-4 d-flex justify-content-end align-items-center">
            <div class="btn-group">
                <button type="button" class="btn btn btn-outline-primary" id="btnUserProfile">${user_ID }</button>
                <button type="button" class="btn btn btn-outline-primary btn-sm dropdown-toggle" data-toggle="dropdown"
                        aria-expanded="false">
                </button>
                <div class="dropdown-menu">
                    <a class="dropdown-item" href="profile/editProfile">프로필 설정</a>
                    <a class="dropdown-item" href="logout">로그아웃</a>
                </div>
            </div>
        </div>
    </div> <!-- 컨테이너  -->
</nav>
<!-- 게시글  -->
<div class="container">
    <div class="row">
        <div class="col-md-12" id="masonry_col">
            <div class="item small"></div>
        </div>
    </div>
</div>
<!-- detail modal -->
<div class="modal modal-center fade" id="detail_Dialog" role="dialog" tabindex="-1">
    <div class="modal-dialog modal-dialog-center" role="document">
        <div class="modal-content h-100 d-flex no-gutters" id="content">
            <div class="container-fluid no-gutters" id="detailModalContainer">
                <div class="row d-flex no-gutters">
                    <div class="col-md-8 box-shadow h-100">
                        <img id="detail_Img" class="img-fluid d-inline-block">
                    </div>
                    <div class="col-md-4">
                        <div class="modal-header">
                            <h3 id="h3_detail_userID"></h3>
                        </div>
                        <div class="modal-body" style="overflow:auto">
                            <div class="row" id="detail_content">
                                <div class="col-sm-12">
                                    <h6 id="h6_detail_userID"><small id="small_detail_content">내용</small></h6>
                                </div>
                            </div>
                            <div class="row" id="row_comment_content">
                                <div class="col-sm-12" id="col_comment_content">
                                    <h6><small></small></h6>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <div class="row">
                                <div class="col-sm-12">
                                    <form action="insertComment.do" method="post">
                                        <div class="form-row align-items-left">
                                            <div class="col-auto">
                                                <input type="hidden" name="user_ID" id="user_ID" value=${user_ID }>
                                            </div>
                                            <div class="col-auto">
                                                <input type="hidden" name="post_no" id="post_no">
                                            </div>
                                            <div class="col-sm-10">
                                                <div class="input-group mb-3">
                                                    <input type="text" class="form-control" id="comment_content"
                                                           name="comment_content" placeholder="댓글 달기">
                                                    <div class="input-group-append">
                                                        <button type="submit" class="btn btn-primary">등록</button>
                                                    </div>
                                                </div>
                                            </div>

                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>