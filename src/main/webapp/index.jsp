<%--
  Created by IntelliJ IDEA.
  User: Lin
  Date: 2019/4/17
  Time: 22:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
  <head>
    <title>$Title$</title>
      <script src="${pageContext.request.contextPath}/static/js/jquery-3.3.1.min.js" type="text/javascript"></script>
      <script>
          $(document).ready(function(){
              $.ajax(
                  {
                      url:"init",
                      success:function(data){
                          $("#code").attr("src","data:text/javascript;base64,"+data);

                      }
                  });

              $("#code").click(function () {
                  $.ajax({
                      url:"clickCode",
                      success:function(data){
                          $("#code").attr("src","data:text/javascript;base64,"+data);

                      }
                  });
              });

          });
      </script>
  </head>
  <body>
  <form method="post" action="login">
    学号1：<input name="stuId" type="text"></br>
    密码：<input name="password" type="password"></br>
    验证码：<input name="code" type="text"> <img src="" id="code"></br>
    <input type="submit" value="登录">
  </form>
  </body>
</html>
