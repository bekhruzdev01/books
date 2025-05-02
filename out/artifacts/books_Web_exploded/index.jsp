<%@ page import="Service.BookService" %>
<%@ page import="Model.Book" %>
<%@include file="Component/Header.jsp"%>
<div class="container m-3 w-100 ">
    <div>
        <button class="btn btn-outline-light border-0" data-bs-toggle="offcanvas" data-bs-target="#staticBackdrop" aria-controls="staticBackdrop">
            <i class="fa-solid fa-bars"></i>
        </button>
    </div>
    <div>
        <%BookService bookService = new BookService();
            for (Book book : bookService.searchBook("Java")) {
        %>
        <div class="card" style="width: 18rem;">
            <img src=<%=book.getImageUrl()%> class="card-img-top" alt="...">
            <div class="card-body">
                <h5 class="card-title"><%=book.getTitle()%></h5>
                <p class="card-text fw-bold"><%=book.getAuthor()%></p>
                <p class="card-text"><%=book.getDescription()%></p>
                <a href="#" class="btn btn-primary">Go somewhere</a>
            </div>
        </div>

        <%}%>

    </div>

</div>
<%@ include file="Component/Footer.jsp"%>