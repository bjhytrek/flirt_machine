<%-- 
    Document   : Adder
    Created on : Mar 12, 2016, 8:28:58 AM
    Author     : Jbeag_000
--%>

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
            function addToList() {
                var current = $(".inputPickup").val();
                
                responsiveVoice.speak(current, "Australian Female");
                
                if (current.length > 0) {
                    var content = $("#tries").html();
                    var split = content.split("<li");
                    var newContent = "<li class=\"dropBottom purpleHover\"><i class=\"material-icons mySmallIcon\">volume_up</i>&nbsp;&nbsp;" + current + "</li>";
                    for (var i = 1; i < split.length - 1; i++) {
                        newContent += "<li " + split[i];
                    }
                    $("#tries").html(newContent);
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
                    <table class="adderContent">
                        <tr>
                            <td class="adderFirst">
                                <textarea class="inputPickup" rows="4" placeholder="Enter text here..."></textarea>
                            </td>
                            <td class="adderSecond">
                                <i class="material-icons myIconLarge" onclick="addToList()">volume_up</i>
                            </td>
                        </tr>
                        <tr>
                            <td class="white-text">
                                Attempts:
                                <ul id="tries" class="indented">
                                    <li class="dropBottom purpleHover"><i class="material-icons mySmallIcon">volume_up</i>&nbsp;&nbsp;Hey there hot stuff...</li>
                                    <li class="dropBottom purpleHover"><i class="material-icons mySmallIcon">volume_up</i>&nbsp;&nbsp;I didn't know angels still came down to earth.</li>
                                    <li class="dropBottom purpleHover"><i class="material-icons mySmallIcon">volume_up</i>&nbsp;&nbsp;What's a girl like you doing in a place like this?</li>
                                    <li class="dropBottom purpleHover"><i class="material-icons mySmallIcon">volume_up</i>&nbsp;&nbsp;Looks like you're suffering from a lack of vitamin Me!</li>
                                </ul>
                            </td>
                            <td class="adderSecond">
                                <input type="button" class="btn-large waves-effect waves-light purple" value="ADD">
                            </td>
                        </tr>
                    </table>
                </div>
                <div class="myList">
                    <div class="innerList">
                        
                    </div>
                </div>
            </div>
            <%@ include file="components/footer.jsp" %>
        </main>           
            </body>
</html>
