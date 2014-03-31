<%@ include file="header.jsp" %>
<div class="wrapper">
    <article id="cours${course.id}">
        <header>
            <h2>${course.name}</h2>
            <h3>Catégorie : ${course.category}</h3>
        </header>
        <c:choose>
            <c:when test="${ user != null }">
                <div class="row">
                    <div class="12u">
                        <a href="quizz?courseId=${course.id}&user=${user}" class="button">Passer le Quizz</a>
                        <c:choose>
                            <c:when test="${ certificate != null }">
                                <a href="#" class="button">Imprimer le certificat</a>
                            </c:when>    
                        </c:choose>
                        
                    </div>
                </div>
            </c:when>
        </c:choose>
        <p>${content}</p>
<footer>
<%@ include file="footer.jsp" %>