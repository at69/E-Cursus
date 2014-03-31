<%@ include file="header.jsp" %>
<!-- Courses -->
<div class="wrapper wrapper-style3">
        <article id="cours">
        <c:choose>
            <c:when test="${not empty courses}">
                        <header>
                            <h2>Les cours en ligne</h2>
                            <span>E-Cursus propose un vaste choix de cours portant sur l'ensemble de l'informatique et des nouvelles technologies.</span>
			</header>
                        <select name="Categories">  
                            <c:forEach items="${categories}" var="category">
                                <option value="${category}"><c:out value="${category}" /></option>  
                            </c:forEach>
                        </select>  
                        <br /><br />
                        <div class="container">
                            <c:forEach items="${courses}" var="course" varStatus="status">
                                <c:if test="${status.count == 1}">
                                    <div class="row">
                                </c:if>
                                <div class="4u">
                                    <article class="box box-style2">
                                        <c:choose>
                                            <c:when test="${user != null && user != ''}">
                                               <c:set value="course?id=${course.id}&user=${user}" var="href"/> 
                                            </c:when>
                                            <c:otherwise>
                                               <c:set value="course?id=${course.id}" var="href"/> 
                                            </c:otherwise>
                                        </c:choose>                                        
					<a href="${href}" class="image image-full"><img src="${course.image}" alt="${course.name}" /></a>
                                        <h3><a href="${href}"><c:out value="${course.name}"/></a></h3>
					<p></p>
                                    </article>
				</div>
                                <c:if test="${status.count % 3 == 0}">
                                    </div>  
                                    <div class="row">
                                </c:if> 
                                <c:if test="${status.last}">
                                    </div>  
                                </c:if>          
                            </c:forEach>       
                        </div>                   
            </c:when>
            <c:otherwise>
                <p>Aucun cours disponible.</p>
            </c:otherwise>
        </c:choose>
        <footer>
<%@ include file="footer.jsp" %>
