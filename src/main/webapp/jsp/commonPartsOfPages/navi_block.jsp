<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>

<nav id="navi_block">
    <div class="center">
        <div id="navmobile-btn"><fmt:message key="label.header.menu"/></div>
        <ul id="menu-main-menu" class="main_menu ">
            <li id="menu-item-431" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-431"><a
                    href="${pageContext.request.contextPath}/taxi-Kyiv/makeOrder">
                <fmt:message key="label.header.menu.for.make.order"/></a></li>

        </ul>
    </div>
</nav>
