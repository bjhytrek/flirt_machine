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
        <!-- Compiled and minified CSS -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.5/css/materialize.min.css">

        <!-- Compiled and minified JavaScript -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.5/js/materialize.min.js"></script>

        <title>Sign In</title>
    </head>
    <body>
        <h1>This is the sign in page</h1>
        <div class="row card-panel">
            <div class="col s12 m12 16">
                <div class="col">
                    <form action="#" method="POST" class="col">
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
