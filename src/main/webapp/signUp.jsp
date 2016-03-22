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
        <!-- Compiled and minified CSS -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.5/css/materialize.min.css">

        <!-- Compiled and minified JavaScript -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.5/js/materialize.min.js"></script>
        <title>Sign Up</title>
    </head>
    <body>
        <%@ include file="components/header.jsp" %> 
        <h1>Sign Up</h1>
        <div class="row card-panel">
            <div class="col s12 m12 16">
                <div class="col">
                    <form action="SignUp" method="POST" class="col">
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
                                <a class="waves-effect waves-light btn"><span><input type="submit" name="signUp" id="signIn" value="Sign Up"></span></a>
                            </div>
                        </div>        
                    </form>
                </div>
            </div>
        </div>
        <%@ include file="components/footer.jsp" %> 
    </body>
</html>
