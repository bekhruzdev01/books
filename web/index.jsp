<%@ page import="Service.BookService" %>
<%@ page import="Model.Book" %>
<%@ page pageEncoding="UTF-8" %>
<%@include file="Component/Header.jsp"%>

<div class="container-fluid">
    <div>
        <button class="btn btn-outline-light border-0" data-bs-toggle="offcanvas" data-bs-target="#staticBackdrop" aria-controls="staticBackdrop">
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
            if (query != null){
            for (Book book : bookService.searchBook(query)) {
        %>
        <div class="card m-3" style="width: 18rem;">
            <img src="<%= book.getImageUrl() != null ? book.getImageUrl() : "https://via.placeholder.com/150" %>"
                 class="card-img-top img-fluid fixed-height" alt="Book image">
            <div class="card-body">
                <div class="h-">
                    <h5 class="card-title"><%=book.getTitle()%></h5>

                </div>
                <p class="card-text fw-bold"><%=book.getAuthor()%></p>
                <p class="card-text truncate-3-lines"><%= book.getDescription() %></p>
                <a href="#" class="btn btn-outline-primary">Read more</a>
            </div>
        </div>

        <%}
            }else {%>
        <div class="text-center d-flex justify-content-center align-items-center m-3 text-light">
            No Books
        </div>

        <%}%>

    </div>

</div>
<%@ include file="Component/Footer.jsp"%>