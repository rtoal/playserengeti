<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <title>
            <tiles:insertAttribute name="title" value="${title}"/>
        </title>
        <link rel="stylesheet" type="text/css"
            href="${pageContext.request.contextPath}/styles/serengeti.css"
        />
        <script type="text/javascript"
            src="${pageContext.request.contextPath}/scripts/jquery-1.3.2.min.js">
        </script>
        <script type="text/javascript"
            src="${pageContext.request.contextPath}/scripts/serengeti.js">
        </script>
    </head>

    <body>
        <tiles:insertAttribute name="header"/>

        <%-- Right-floated big logo, linked to home --%>
        <div class="mainlogo">
            <a href="${pageContext.request.contextPath}/"><img
            src="${pageContext.request.contextPath}/images/logo-full-116.png"
            alt="logo" /></a>
        </div>

        <%-- Page title prefixed with small tree linked to home --%>
        <div class="pagetitle">
            <a href="${pageContext.request.contextPath}/"><img
            src="${pageContext.request.contextPath}/images/logo-tree-32.png"
            alt="logo" /></a>
            <tiles:insertAttribute name="title" value="${title}"/>
        </div>
        
        
        <div id="navbar2">
        <ul>
            <li><a href="${pageContext.request.contextPath}/">Home  </a></li>
            <li><a href="link2.html ">Profile</a></li>
            <li><a href="link4.html ">Teams</a></li>
            <li><a href="link5.html ">Help</a></li>
            <li><a href="link6.html ">Settings</a></li>
            <li><a href="link7.html ">Log Out</a></li>
        </ul>
        </div>

        <div class="mainContentArea">
            <tiles:insertAttribute name="login"/>
            <tiles:insertAttribute name="body"/>
        </div>

        <tiles:insertAttribute name="footer"/>
    </body>
</html>