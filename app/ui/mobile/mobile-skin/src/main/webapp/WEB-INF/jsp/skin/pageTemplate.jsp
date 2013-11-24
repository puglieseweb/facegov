<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:set var="baseGoogleUrl" value="http://ajax.googleapis.com/ajax/libs"/>

<c:url var="jqueryTablesorterJsUrl" value="/skin/scripts/jquery.tablesorter.min.js"/>

<c:url var="sipCssUrl" value="/skin/main.css"/>
<c:url var="sipJsUrl" value="/skin/scripts/main.js"/>
<c:url var="sipCoverImgUrl" value="/skin/images/sip-cover.png"/>

<!DOCTYPE html>
<html>
<head>
    <title><decorator:title/> - Facegov.com</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://code.jquery.com/mobile/1.0b3/jquery.mobile-1.0b3.min.css"/>
    <script src="http://code.jquery.com/jquery-1.6.3.min.js"></script>
    <script src="http://code.jquery.com/mobile/1.0b3/jquery.mobile-1.0b3.min.js"></script>
    <decorator:head/>
</head>
<body>
<section id="listContactsPage" data-role="page">
    <header data-role="header">
        <div>
            <div>
                <div><c:out value="${initParam.appName}"/></div>
            </div>
            <%@ include file="/WEB-INF/jsp/skin/subhead.jspf" %>
        </div>
    </header>

    <div class="content" data-role="content">
        <div>
            <decorator:body/>
        </div>
    </div>

    <footer data-role="footer">
        <div class="regionInner">
            <div class="yui-gc">
                <div id="aboutRecipe" class="yui-u first">
                    <h2 style="margin-top:0">About <c:out value="${initParam.recipe}"/></h2>

                    <div><c:out value="${initParam.aboutThisRecipe}" escapeXml="false"/></div>
                </div>
                <div id="bookLinks" class="yui-u">
                    <div>
                        <a href="http://www.facegov.com/" title="Facegov Web Application">Facegov Web
                            Application</a><br/>
                        <a href="https://github.com/facegov" title="Facegov Blog">Facegov Blog</a><br/>
                        <a href="https://github.com/facegov" title="Facegov Source code">Facegov Source Code</a><br/>
                    </div>
                </div>
            </div>
            <div id="legal">
                Copyright &copy; 2013 <a href="http://www.puglieseweb.com"
                                         title="Puglieseweb.com - Emanuele Pugliese's web reference">Puglieseweb.com</a>
            </div>
        </div>
    </footer>
</section>

<section id="sitePreferenceSelectionPage" data-role="page">
    <header data-role="header"><h1>Site Preference</h1></header>
    <div class="content" data-role="content">
        <h3>Switch to Normal Site?</h3>

        <div data-role="controlgroup">
            <a href="${currentUrl}?site_preference=normal" data-role="button" rel="external">Yes</a>
            <a href="#" data-rel="back" data-role="button">Cancel</a>
        </div>
    </div>
    <footer data-role="footer"></footer>
</section>
</body>
</html>
