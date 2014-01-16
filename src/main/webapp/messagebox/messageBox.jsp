<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script data-main="${pageContext.request.contextPath}/js/app/demo.js"
		src="${pageContext.request.contextPath}/js/lib/require.js"></script>
	<%--
    The reason to use a JSP is that it is very easy to obtain server-side configuration
    information (such as the contextPath) and pass it to the JavaScript environment on the client.
    --%>
    <script type="text/javascript">
        var config = {
            contextPath: '${pageContext.request.contextPath}'
        };
    </script>
	<title>Message Box</title>
</head>
<body>
	<input type="text" id="message-to-send" name="msg" />
	<button name="btn" id="send-msg">Greet!</button>
	<div id="messages"></div>
</body>
</html>
