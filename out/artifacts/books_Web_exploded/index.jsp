<%@ page import="Service.BookService" %>
<%@ page import="Model.Book" %>
<%@ page pageEncoding="UTF-8" %>
<%@include file="Component/Header.jsp" %>

<div class="container-fluid">
    <div>
        <button class="btn btn-outline-light border-0" data-bs-toggle="offcanvas" data-bs-target="#staticBackdrop"
                aria-controls="staticBackdrop">
            <i class="fa-solid fa-bars"></i>
        </button>
    </div>
    <div class="d-flex justify-content-center align-items-center">
        <form method="get" action="index.jsp" class="d-flex justify-content-center mb-3 w-100">
            <input type="text" name="searchQuery" class="form-control w-75" placeholder="Search...">
        </form>

    </div>
    <div class="d-flex flex-wrap container">
        <%
            BookService bookService = new BookService();
            String query = request.getParameter("searchQuery");
            if (query != null) {
                for (Book book : bookService.searchBook(query)) {
        %>
        <div class="card m-2" style="width: 19rem;">
            <img src="<%=book.getImageUrl() != null ? book.getImageUrl() : "https://via.placeholder.com/150" %>"
        class="card-img-top img-fluid fixed-height" alt="Book image">
        <div class="card-body">
            <div class="h-60px">
                <h6 class="card-title truncate-1-lines"><%=book.getTitle()%>
                </h6>

            </div>
            <div class="h-60px">
                <p class="card-text fw-bold"><%=book.getAuthor()%>
                </p>
            </div>
            <div class="h-50">
                <p class="card-text truncate-3-lines"><%= book.getDescription() %>
                </p>
            </div>
            <div class="h-25">
                <a href="#" class="btn btn-outline-primary" data-bs-toggle="modal" data-bs-target="#staticBackdrop">Read more</a>
                <!-- Button trigger modal -->
<%--                <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#staticBackdrop">--%>
<%--                    Read more--%>
<%--                </button>--%>
            </div>
        </div>
    </div>

    <%
        }
    } else {
    %>
    <div class="d-flex justify-content-center align-items-center w-100" style="height: 200px;">
        <h3 class="text-light text-center">No Books</h3>
    </div>

    <%}%>

</div>

</div>
<%@ include file="Component/Footer.jsp" %>