<%-- 
    Document   : Adder
    Created on : Mar 12, 2016, 8:28:58 AM
    Author     : Jbeag_000
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link href="css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
        <link href="css/style.css" type="text/css" rel="stylesheet" media="screen,projection"/>
        <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
        <script src="js/materialize.js"></script>
        <script src="js/init.js"></script>
        <script src="http://code.responsivevoice.org/responsivevoice.js"></script>
        <script>
            $(document).ready(function() {
                    $(".currentMyList").click(function(){                        
                       var line = $(this).text();
                       line = line.trim();
                       console.log(line);
                       $(".inputPickup").val(line);
                    });
                    
            });
            function addToList() {
                var current = $(".inputPickup").val();
                var accent = $("#accent").val();
                console.log(accent);
                if (current.length > 0) {
                    responsiveVoice.speak(current, accent);
                    var url = "Add";
                    var data = {'action': 'try', 'newLine': current};
                    $.post(url, data, function (response) {
                        var content = $("#tries").html();
                        var newContent = "<li class=\"dropBottom purpleHover\"><i class=\"material-icons mySmallIcon\">volume_up</i>&nbsp;&nbsp;" + current + "</li>";
                        if (content.indexOf("No Attempts Yet</li>") !== -1) {
//                            alert("no attemts");
                        } else {
                            var split = content.split("<li");
                            console.log(split.length);
                            if (split.length == 5) {
                                for (var i = 1; i < split.length - 1; i++) {
                                    newContent += "<li " + split[i];
                                }
                            } else {
                                for (var i = 1; i < split.length; i++) {
                                    newContent += "<li " + split[i];
                                }
                            }
                        }
                        $("#tries").html(newContent);
                    });
                }
            }
        </script>
        <title>Add Pickups</title>
    </head>
    <body>
        <main>
            <%@ include file="components/header.jsp" %>
            <div class="container">
                <div class="adderBack">
                    <form action="AddPickup" method="POST">
                        <table class="adderContent">
                            <tr>
                                <td class="adderFirst">
                                    <textarea class="inputPickup" name="pickupLine" rows="4" placeholder="Enter text here..."></textarea>
                                </td>
                                <td class="adderSecond">
                                    <i class="material-icons myIconLarge" onclick="addToList()">volume_up</i>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <select id="accent">
                                        <option value="UK English Female">UK English Female</option>
                                        <option value="UK English Male">UK English Male</option>
                                        <option value="US English Female">US English Female</option>
                                        <option value="Spanish Female">Spanish Female</option>
                                        <option value="French Female">French Female</option>
                                        <option value="Deutsch Female">Deutsch Female</option>
                                        <option value="Italian Female">Italian Female</option>
                                        <option value="Greek Female">Greek Female</option>
                                        <option value="Hungarian Female">Hungarian Female</option>
                                        <option value="Turkish Female">Turkish Female</option>
                                        <option value="Russian Female">Russian Female</option>
                                        <option value="Dutch Female">Dutch Female</option>
                                        <option value="Swedish Female">Swedish Female</option>
                                        <option value="Norwegian Female">Norwegian Female</option>
                                        <option value="Japanese Female">Japanese Female</option>
                                        <option value="Korean Female">Korean Female</option>
                                        <option value="Chinese Female">Chinese Female</option>
                                        <option value="Hindi Female">Hindi Female</option>
                                        <option value="Serbian Male">Serbian Male</option>
                                        <option value="Croatian Male">Croatian Male</option>
                                        <option value="Bosnian Male">Bosnian Male</option>
                                        <option value="Romanian Male">Romanian Male</option>
                                        <option value="Catalan Male">Catalan Male</option>
                                        <option value="Australian Female">Australian Female</option>
                                        <option value="Finnish Female">Finnish Female</option>
                                        <option value="Afrikaans Male">Afrikaans Male</option>
                                        <option value="Albanian Male">Albanian Male</option>
                                        <option value="Arabic Male">Arabic Male</option>
                                        <option value="Armenian Male">Armenian Male</option>
                                        <option value="Czech Female">Czech Female</option>
                                        <option value="Danish Female">Danish Female</option>
                                        <option value="Esperanto Male">Esperanto Male</option>
                                        <option value="Hatian Creole Female">Hatian Creole Female</option>
                                        <option value="Icelandic Male">Icelandic Male</option>
                                        <option value="Indonesian Female">Indonesian Female</option>
                                        <option value="Latin Female">Latin Female</option>
                                        <option value="Latvian Male">Latvian Male</option>
                                        <option value="Macedonian Male">Macedonian Male</option>
                                        <option value="Moldavian Male">Moldavian Male</option>
                                        <option value="Montenegrin Male">Montenegrin Male</option>
                                        <option value="Polish Female">Polish Female</option>
                                        <option value="Brazilian Portuguese Female">Brazilian Portuguese Female</option>
                                        <option value="Portuguese Female">Portuguese Female</option>
                                        <option value="Serbo-Croatian Male">Serbo-Croatian Male</option>
                                        <option value="Slovak Female">Slovak Female</option>
                                        <option value="Spanish Latin American Female">Spanish Latin American Female</option>
                                        <option value="Swahili Male">Swahili Male</option>
                                        <option value="Tamil Male">Tamil Male</option>
                                        <option value="Thai Female">Thai Female</option>
                                        <option value="Vietnamese Male">Vietnamese Male</option>
                                        <option value="Welsh Male">Welsh Male</option>
                                    </select>
                                </td>
                                <td>

                                </td>
                            </tr>
                            <tr>
                                <td class="white-text">
                                    Attempts:
                                    <ul id="tries" class="indented">
                                        <c:if test="${sessionScope.currentTries.size() > 3}">
                                            <li class="dropBottom purpleHover"><i class="material-icons mySmallIcon">volume_up</i>&nbsp;&nbsp;${sessionScope.currentTries[3]}</li>
                                            </c:if>
                                            <c:if test="${sessionScope.currentTries.size() > 2}">
                                            <li class="dropBottom purpleHover"><i class="material-icons mySmallIcon">volume_up</i>&nbsp;&nbsp;${sessionScope.currentTries[2]}</li>
                                            </c:if>
                                            <c:if test="${sessionScope.currentTries.size() > 1}">
                                            <li class="dropBottom purpleHover"><i class="material-icons mySmallIcon">volume_up</i>&nbsp;&nbsp;${sessionScope.currentTries[1]}</li>
                                            </c:if>
                                            <c:if test="${sessionScope.currentTries.size() > 0}">
                                            <li class="dropBottom purpleHover"><i class="material-icons mySmallIcon">volume_up</i>&nbsp;&nbsp;${sessionScope.currentTries[0]}</li>
                                            </c:if>
                                            <c:if test="${!(sessionScope.currentTries.size() > 0)}">
                                            <li class="dropBottom purpleHover">No Attempts Yet</li>
                                            </c:if>
                                    </ul>
                                </td>
                                <td class="adderSecond">
                                    <input type="submit" class="btn-large waves-effect waves-light purple" value="ADD">
                                </td>
                            </tr>
                        </table>
                    </form>
                </div>
                <div class="myList">
                    <div class="innerList">
                        <ul>
                            <c:forEach items="${currentPickups}" var="pickup">
                                <li class="currentMyList">
                                    ${pickup}
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
            </div>
            <%@ include file="components/footer.jsp" %>
        </main>           
    </body>
</html>
