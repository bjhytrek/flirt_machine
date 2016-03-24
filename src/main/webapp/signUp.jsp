<%-- 
    Document   : signUp
    Created on : Mar 12, 2016, 12:32:06 PM
    Author     : Roman
--%>

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
        <script>
            function signUp() {
                var displayName = $("#displayName").val();
                var username = $("#username").val();
                var password = $("#password").val();
                var url = "CheckUsername";
                var data = {'displayName': displayName, 'username': username, 'password': password};
                $.post(url, data, function(response) {
                    console.log(response);
                    if (response == "valid") {
                        window.location.replace("SignIn");
                    } 
                    else {
                        $(".credError").show();
                    }
                });
            }
        </script>
        <title>Sign Up</title>
    </head>
    <body>
        <%@ include file="components/header.jsp" %> 
        <div class="row card-panel">
            <div class="col s12 m12 16">
                <div class="col">
                    <div class="col">
                        <div class="row">
                            <div class="input-field col s12">
                                <input type="text" name="displayName" id="displayName" placeholder="Full Name" required autofocus>
                            </div>
                        </div>
                        <div class="row">
                            <div class="input-field col s12">
                                <input type="text" name="username" id="username" placeholder="Username" required>
                            </div>
                        </div>
                        <div class="row">
                            <div class="input-field col s12">
                                <input type="password" name="password" id="password" placeholder="Password" required>
                                <a class="waves-effect waves-light btn purple"><span><input type="button" name="signUp" id="signIn" value="Sign Up" onclick="signUp()"></span></a>
                            </div>
                            <div class="credError" style="display:none;">
                                Unavailable username...
                            </div>
                        </div>        
                    </div>
                </div>
            </div>
        </div>
        <%@ include file="components/footer.jsp" %> 
    </body>
</html>
