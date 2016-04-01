<%-- 
    Document   : myList
    Created on : Mar 16, 2016, 10:52:11 PM
    Author     : Jbeag_000
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link href="css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
        <link href="css/style.css" type="text/css" rel="stylesheet" media="screen,projection"/>
        <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
        <script src="js/materialize.js"></script>
        <script src="js/init.js"></script>
        <script src="http://code.responsivevoice.org/responsivevoice.js"></script>
        <script> function rate(starIndex, pickup_id){
            var data = {'star_index': starIndex, 'pickup_id': pickup_id};
            var url = "Ratings";
            $.post(url, data, function (response) {
                console.log(response);});
        }</script>
    </head>
    <body>
        <%@ include file="components/header.jsp" %>
        <main>

            <div class="container">
                <ul class="collection with-header pickup__list list">
                    <li class="collection-header"><h3>My List</h3></li>
                        <c:forEach items="${pickups}" var="pickup" >
                        <li class="collection-item pickup__list__item">
                            <div><p>${pickup.content}</p></div>
                            <div class="rating">
                                <span onclick="rate(5, ${pickup.id})">☆</span>
                                <span onclick="rate(4, ${pickup.id})">☆</span>
                                <span onclick="rate(3, ${pickup.id})">☆</span>
                                <span onclick="rate(2, ${pickup.id})">☆</span>
                                <span onclick="rate(1, ${pickup.id})">☆</span>
                            </div>
                            <i class="material-icons myIcon Small" onclick="addToList()">volume_up</i>
                        </li>
                    </c:forEach>
                </ul>
            </div>

        </main>
        <%@ include file="components/footer.jsp" %>
    </body>
</html>
