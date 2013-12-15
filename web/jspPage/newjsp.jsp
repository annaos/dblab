<%-- 
    Document   : newjsp
    Created on : 13.12.2013, 04:19:02
    Author     : andrey
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns:h="http://java.sun.com/jsf/html" xmlns:f="http://java.sun.com/jsf/core">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <f:view>
        <h:form>
        </h:form>
    </f:view>

    <f:view>
        <h:form>
            <h:dataTable value="123" var="item">
            </h:dataTable>
        </h:form>
    </f:view>

        
        <h1>Hello World!</h1>
    </body>
</html>
