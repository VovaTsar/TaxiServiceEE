<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>

<html lang="${param.lang}">
<jsp:include page="../commonPartsOfPages/headTag.jsp"/>

<body class="home blog">

<div id="wrapper">
    <header id="header">
        <jsp:include page="../commonPartsOfPages/header_white.jsp"/>
        <div class="header_blue">
            <jsp:include page="../commonPartsOfPages/navi_block.jsp"/>

            <section class="slider_home">
                <ul id="main_slider">
                    <%--<li>--%>
                        <%--<img src="../../img/9.jpg" class="desktop-slide" width="1200"--%>
                             <%--height="1200">--%>

                        <%--&lt;%&ndash;<style>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;.banner {&ndash;%&gt;--%>
                                <%--&lt;%&ndash;background: url("../../img/9.jpg") center center /cover;&ndash;%&gt;--%>
                                <%--&lt;%&ndash;background-size: 100%;&ndash;%&gt;--%>
                                <%--&lt;%&ndash;background-repeat: no-repeat;&ndash;%&gt;--%>
                            <%--&lt;%&ndash;}&ndash;%&gt;--%>
                        <%--&lt;%&ndash;</style>&ndash;%&gt;--%>
                    <%--</li>--%>
                </ul>
            </section>
        </div>

    </header>
</div>



<%--<jsp:include page="../commonPartsOfPages/footer.jsp"/>--%>
</body>
</html>