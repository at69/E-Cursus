<%@ include file="header.jsp" %>
<div class="wrapper">
    <article id="quizzForCourse${course.id}">
        <header>
            <h2>Quizz: ${course.name}</h2>
            <h3>Catégorie : ${course.category}</h3>
        </header>
        <c:choose>
            <c:when test="${not empty questions}">   
        <div>
            <div class="row">
                <div class="12u">
                    <div>
                        <form method="post"><fieldset>
                        <c:forEach items="${questions}" var="question" varStatus="qStatus"> 
                            <div class="row half">
                                <div class="6u -3u">
                                    <c:out value="${question.wording}"/>
                                </div>
                            </div>      
                            <c:set var="answers" value="${fn:split(question.answersArray, '/')}"/>
                            <c:forEach items="${answers}" var="answer" varStatus="aStatus">
                                <div class="row half">
                                    <div class="6u -3u">
                                        <input type="radio" name="q${qStatus.count}" id="q${qStatus.count}a${aStatus.count}" value="${question.id},${aStatus.count}"/>
                                        <label for="q${qStatus.count}a${aStatus.count}"><c:out value="${answer}" /></label>
                                    </div>
                                </div>
                            </c:forEach>
                            <c:if test="${!qStatus.last}">
                                --------------------------------
                            </c:if>
                        </c:forEach>
                        <div class="row">
                            <div class="12u">
                                <input type="submit" class="button form-button-submit" value="Valider" />
                                <input type="button" formaction="#" class="button button-alt form-button-reset" value="Rafraîchir"/>
                                <c:choose>
                                    <c:when test="${ wrong == true }">
                                        <div id="wrong">${ message }</div>
                                    </c:when>
                                </c:choose>
                            </div>
                        </div>
                        </form></fieldset>
                    </div>
                </div>
            </div>
        </div>
            </c:when>
            <c:otherwise>
                <p>Aucune question disponible pour ce cours.</p>
            </c:otherwise>
        </c:choose>
<footer>
<%@ include file="footer.jsp" %>
