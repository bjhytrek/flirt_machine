<%-- 
    Document   : popular
    Created on : Apr 4, 2016, 12:44:03 PM
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
        <script>
            function playLine(myLine) {
                var accent = "UK English Female";
                responsiveVoice.speak(myLine, accent);
            }
            function addPickupToMe(otherLine) {
                //alert("working");
                var current = otherLine;
                var url = "AddPickup";
                var data = {'pickupLine': current};
                $.post(url, data, function (response) {
                    window.location.href = "GetList";
                });
                
            }
        </script>
    </head>
    <body>
        <%@ include file="components/header.jsp" %>
        <main>

            <div class="container">
                <ul class="collection with-header pickup__list list">
                    <li class="collection-header"><h3>Popular</h3></li>
                        <c:forEach items="${pickups}" var="pickup" >
                        <li class="collection-item pickup__list__item">
                            <div><p>${pickup.content}</p></div>
                            <c:if test="${pickup.isRated == true}">
                                <div class="cRating">
                                    <c:if test="${pickup.rating == 1}">
                                        <span>☆</span>
                                        <span>☆</span>
                                        <span>☆</span>
                                        <span>☆</span>
                                        <span class="chosenRating">"☆"</span>
                                    </c:if>
                                    <c:if test="${pickup.rating == 2}">
                                        <span>☆</span>
                                        <span>☆</span>
                                        <span>☆</span>
                                        <span class="chosenRating">☆</span>
                                        <span class="chosenRating">☆</span>
                                    </c:if>
                                    <c:if test="${pickup.rating == 3}">
                                        <span>☆</span>
                                        <span>☆</span>
                                        <span class="chosenRating">☆</span>
                                        <span class="chosenRating">☆</span>
                                        <span class="chosenRating">☆</span>
                                    </c:if>
                                    <c:if test="${pickup.rating == 4}">
                                        <span>☆</span>
                                        <span class="chosenRating">☆</span>
                                        <span class="chosenRating">☆</span>
                                        <span class="chosenRating">☆</span>
                                        <span class="chosenRating">☆</span>
                                    </c:if>
                                    <c:if test="${pickup.rating == 5}">
                                        <span class="chosenRating">☆</span>
                                        <span class="chosenRating">☆</span>
                                        <span class="chosenRating">☆</span>
                                        <span class="chosenRating">☆</span>
                                        <span class="chosenRating">☆</span>
                                    </c:if>
                                </div>
                            </c:if>
                            <c:if test="${pickup.isRated == false}">
                                <div class="cRating">
                                    <span>☆</span>
                                    <span>☆</span>
                                    <span>☆</span>
                                    <span>☆</span>
                                    <span>☆</span>
                                </div>
                            </c:if>                            
                            <i class="material-icons myIcon Small" onclick="playLine('${pickup.content}')">volume_up</i>
                            &nbsp;<span class="addPickupButton" onclick="addPickupToMe('${pickup.content}')">+</span>
                        </li>
                    </c:forEach>
                </ul>
            </div>

        </main>
        <%@ include file="components/footer.jsp" %>
    </body>
</html>

