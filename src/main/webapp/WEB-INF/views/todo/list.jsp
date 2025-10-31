<%--
  Created by IntelliJ IDEA.
  User: a
  Date: 2025-10-29
  Time: 오후 4:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

        <title>Hello, world!</title>
        <style>
            .page-link {
                cursor: pointer;
            }
        </style>
    </head>
    <body>

    <div class="container-fluid">
        <div class="row">
            <nav class="navbar navbar-expand-lg navbar-light bg-light">
                <div class="container-fluid">
                    <a class="navbar-brand" href="#">Navbar</a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                            <li class="nav-item">
                                <a class="nav-link active" aria-current="page" href="#">Home</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#">Link</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link disabled">Disabled</a>
                            </li>
                        </ul>
                        <form class="d-flex">
                            <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                            <button class="btn btn-outline-success" type="submit">Search</button>
                        </form>
                    </div>
                </div>
            </nav>
        </div>

        <div class="row content">
            <div class="col">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Search </h5>
                        <form action="/todo/list" method="get">
<%--                            <input type="hidden" name="size" value="${pageRequestDTO.size}">--%>
<%--                            <div class="mb-3">--%>
<%--                                <input type="checkbox" name="finished" ${pageRequestDTO.finished?"checked":""} >완료여부--%>
<%--                            </div>--%>
<%--                            <div class="mb-3">--%>
<%--                                <input type="checkbox" name="types" value="t" ${pageRequestDTO.checkType("t")?"checked":""}>제목--%>
<%--                                <input type="checkbox" name="types" value="w"  ${pageRequestDTO.checkType("w")?"checked":""}>작성자--%>
<%--                                <input type="text"  name="keyword" class="form-control" value ='<c:out value="${pageRequestDTO.keyword}"/>' >--%>
<%--                            </div>--%>
<%--                            <div class="input-group mb-3 dueDateDiv">--%>
<%--                                <input type="date" name="from" class="form-control" value="${pageRequestDTO.from}">--%>
<%--                                <input type="date" name="to" class="form-control"  value="${pageRequestDTO.to}">--%>
<%--                            </div>--%>
<%--                            <div class="input-group mb-3">--%>
<%--                                <div class="float-end">--%>
<%--                                    <button class="btn btn-primary" type="submit">Search</button>--%>
<%--                                    <button class="btn btn-info clearBtn" type="reset">Clear</button>--%>
<%--                                </div>--%>
<%--                            </div>--%>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <div class="row content">
            <!--  열 방향 채움  -->
            <div class="col">
                <div class="card">
                    <div class="card-header">
                        Featured
                    </div>
                    <div class="card-body">
                        <h5 class="card-title">Special title treatment</h5>

                        <table class="table">
                            <thead>
                            <tr>
                                <th scope="col">번호</th>
                                <th scope="col">제목</th>
                                <th scope="col">작성자</th>
                                <th scope="col">마감날짜</th>
                                <th scope="col">완료여부</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="dto" items="${responseDTO.dtoList}" varStatus="status">
                                <tr>
                                    <%--  tno 대신 전체 게시글 개수를 기준으로 게시글 번호를 출력  --%>
                                    <th scope="row"><c:out value="${dto.tno}"/></th>
                                    <td><a href="/todo/read?tno=${dto.tno}&${pageRequestDTO.link}"><c:out value="${dto.title}"/></a></td>
                                    <td><c:out value="${dto.writer}"/></td>
                                    <td><c:out value="${dto.dueDate}"/></td>
                                    <td><c:out value="${dto.finished ? '완료' : '미완료'}"/></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <div class="float-end">
                            <ul class="pagination flex-wrap">
                                <%--  페이지 번호 리스트 출력  --%>
                                <c:if test="${responseDTO.prev}">
                                    <li class="page-item">
                                        <a class="page-link" data-num="${responseDTO.start-1}" tabindex="-1">Previous</a>
                                    </li>
                                </c:if>
                                <c:forEach begin="${responseDTO.start}" end="${responseDTO.end}" var="num">
                                    <li class="page-item ${responseDTO.page == num ? "active" : ""}">
                                        <a class="page-link" data-num="${num}">${num}</a>
                                    </li>
                                </c:forEach>
                                <c:if test="${responseDTO.next}">
                                    <li class="page-item">
                                        <a class="page-link" data-num="${responseDTO.end+1}">Next</a>
                                    </li>
                                </c:if>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="my-4">
            <div class="float-end">
                <button type="button" class="btn btn-primary">Register</button>
            </div>
        </div>
        <script>
            document.querySelector(".btn-primary").addEventListener("click",function(e) {
                e.preventDefault()
                e.stopPropagation()

                self.location = "/todo/register"
            },false);
        </script>
        <div class="row footer">
            <div class="row fixed-bottom" style="z-index: -100">
                <footer class="py-1 my-1 ">
                    <p class="text-center text-muted">Footer</p>
                </footer>
            </div>
        </div>
    </div>
    <!-- Optional JavaScript; choose one of the two! -->

    <!-- Option 1: Bootstrap Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

    <!-- Option 2: Separate Popper and Bootstrap JS -->
    <!--
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
    -->

    <script>
        <%--  페이지의 특정 번호 클릭 시 해당 페이지로 이동  --%>
        document.querySelector(".pagination").addEventListener("click", (e) => {
            // 클릭 시 발생하는 기본 이벤트가 실행되지 않도록 차단
            e.preventDefault()
            e.stopPropagation()

            const target = e.target

            if (target.tagName !== 'A') {
                return
            }
            const num = target.getAttribute("data-num")
            const formObj = document.querySelector("form")

            formObj.innerHTML += `<input type='hidden' name='page' value='\${num}'>`
            formObj.submit()
        }, false);
    </script>
    </body>
</html>
