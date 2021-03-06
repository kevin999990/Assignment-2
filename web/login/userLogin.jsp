<%@page import="model.Member"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <style type="text/css">
            body{
                margin: 0px;
                padding: 0px;
                background: brown;
            }
            #body{
                position: absolute;
                top:-20px;
                bottom: -20px;
                right: -20px;
                left:-20px;
                width: auto;
                height: auto;
                background-image: url("/EuYanSang/img/background.jpg");
                background-size: 1500px 900px;
                background-repeat: no-repeat;
                -webkit-filter: blur(2px);
                z-index: -999;
            }
            #font1 {
                font-family: 'Exo',sans-serif;
                font-size: 30px;
            }
            #container {
                width: 800px;
                margin: 0px auto;
                z-index: 999;
            }
            #loginform {
                width: 250px;
                height: 180px;
                margin: 200px auto 0px auto;
            }
            #login input[type=text]{
                width: 230px;
                height: 30px;
                border-radius: 2px;
                font-family: 'Exo',sans-serif;
                font-size: 16px;
                font-weight: 400;
                color: black;
                background: transparent;
                outline: none;
                border: 1px solid rgba(255,255,255,0.9);
                padding: 2px;
            }
            #login input[type=password]{
                width: 230px;
                height: 30px;
                border-radius: 2px;
                font-family: 'Exo',sans-serif;
                font-size: 16px;
                font-weight: 400;
                color: black;
                background: transparent;
                outline: none;
                border: 1px solid rgba(255,255,255,0.9);
                padding: 2px;
            }
            ::-webkit-input-placeholder{
                color: rgba(255,255,255,0.6);
            }
            ::-webkit-input{
                color: rgba(255,255,255,0.6);
            }
            .button1 {
                font-family: 'Exo',sans-serif;
                width: 110px;
                height: 30px;
                clear: both;
                position: absolute;
                margin-left: 120px;
            }
            .button2 {
                font-family: 'Exo',sans-serif;
                width: 110px;
                height: 30px;
                clear: both;
                position: absolute;
                margin-left: 0px;
            }
            #login {
                color: #fff;
            }
        </style>

        <title>Login</title>
    </head>
    <body>
        <% Member member = (Member) session.getAttribute("loginMember");%>
        <div id="body"></div>
        <div id="container">
            <div id="loginform">
                <form method="POST" action="j_security_check">
                    <fieldset id="login">

                        <legend>Welcome <%=member.getMemberName()%>, Press OK to login</legend>
                        <p><input type="password" placeholder="ID" name="j_username" value="<%=session.getAttribute("username")%>" readonly="readonly"/></p>
                        <p><input type="password" placeholder="password" name="j_password" value="<%=session.getAttribute("password")%>" readonly="readonly"/></p>

                        <input class="button2" type="submit" value="OK"/>
                        <br/><br/>


                    </fieldset>

            </div>
        </div>
    </body>
</html>
