<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">  
<html xmlns="http://www.w3.org/1999/xhtml">  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />  
<html>  
<head>  
   <title>login page</title>  
</head>  

<script type="text/javascript" src="${contextPath}/js/jquery/jquery-1.7.2.js"></script>  
 <script type="text/javascript">     
  $(document).ready(function check () {  
            $("#username").blur("click", function () {//  
                var username = $("#username").val(); //获取用户名文本框的值  
                if (username == null || username == "") {//验证是否为空  
                    $("#msg1").html("Username cannot be empty");  
                } else {  
                    $("#msg1").html("");  
                }  
            });  
              
            //判断密码是否为空  
            $("#password").blur("click", function () {//绑定文本框失去焦点事件  
                var password = $("#password").val(); //获取用户名文本框的值  
                if (password == null || password == "") {//验证是否为空  
                    $("#msg2").html("Password cannot be empty");  
                } else {  
                    $("#msg2").html("");  
                }  
            });  
        });  
          
        function check() {    
        $(function() {    
            $.get("login.do",    
                        {  username:  $("#username").val(),    
                            password:  $("#password").val() },    
                            function(data){    
                                $("#info").replaceWith('<div id="info">'+ data + '</div>');    
                        });    
        });  
    } 
</script>  
   <form  method="post">  
   <div id="info"></div>  
  <table style="width: 700px; border: 1px #346086 solid;">  
                                <tr>  
                                        <td style="height: 30px; width: 170px;"> </td>  
                                        <td> </td>  
                                </tr>  
                                <tr>  
                                        <td style="text-align: right; height: 30px;"><label  
                                                style="color: #ff0000"> * </label>username:</td>  
                                        <td><input type="text" name="username" id="username" style="width: 200px;" /></td>  
                                        <td width="400" id="msg1"  style="color: #ff0000"></td>  
                                </tr>  
                                <tr>  
                                        <td style="text-align: right; height: 30px;"><label  
                                                style="color: #ff0000"> * </label>password:</td>  
                                        <td><input type="text" name="password" id="password" style="width: 200px;" /></td>  
                                        <td width="400" id="msg2"  style="color: #ff0000"></td>  
                                </tr>  
                                <tr>  
                                        <td> </td>  
                                        <td><input type="button" id="button" value="check" onclick="check()"  /></td>  
                                        <td ></td>  
                                </tr>  
                                <tr>  
                                        <td style="height: 30px;"> </td>  
                                        <td> </td>  
                                </tr>  
                        </table>  
  
   </form>  
 <script type="text/javascript">     
      
</script>    
</body>  
</html> 