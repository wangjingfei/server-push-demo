<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script data-main="${pageContext.request.contextPath}/js/app/stock.js"
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
	<title>Stock Prices</title>
</head>
<body>
	<select id="stock-code">
		<option value="*">All</option>
		<option value="ORCL">Oracle</option>
		<option value="MSFT">Microsoft</option>
		<option value="GOOG">Google</option>
		<option value="YHOO">Yahoo!</option>
		<option value="FB">Facebook</option>
	</select>
	<button name="btn" id="subscribe" disabled>Subscribe!</button>
	<div id="status"></div>
	<div id="stock-prices"></div>
</body>
</html>
