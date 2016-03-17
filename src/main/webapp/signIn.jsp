<%-- 
    Document   : signIn
    Created on : Mar 12, 2016, 10:10:06 AM
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

        <title>Sign In</title>
    </head>
    <body>
        <h1>This is the sign in page</h1>
        <div class="row card-panel">
            <div class="col s12 m12 16">
                <div class="col">
                    <form action="SignIn" method="POST" class="col">
                        <div class="row">
                            <div class="input-field col s12">
                                <input type="text" name="username" id="username" placeholder="Username" required autofocus>

                            </div>
                        </div>
                        <div class="row">
                            <div class="input-field col s12">
                                <input type="password" name="password" id="password" placeholder="Password" required>
                                <a class="waves-effect waves-light btn"><span><input type="submit" name="signIn" id="signIn" value="Login"></span></a>
                            </div>
                        </div>        
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
