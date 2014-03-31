<%-- 
    Document   : login.jsp
    Created on : Mar 3, 2014, 9:38:55 AM
    Author     : Emmanuel
--%>

<%@ include file="header.jsp" %>
<!-- Login -->
        <div class="wrapper wrapper-style4">
            <article id="login" class="container small">
                <header>
                    <h2>Connectez-vous</h2>
                    <span>Remplissez le formulaire ci-dessous</span>
                </header>
                <div>
                    <div class="row">
                        <div class="12u">
                            <form method="post">
                                <div>
                                   <div class="row half">
                                       <div class="6u -3u">
                                            <input type="text" name="email" id="email" placeholder="E-mail" />
                                       </div>
                                   </div>
                                   <div class="row half">
                                        <div class="6u -3u">
                                            <input type="password" name="password" id="password" placeholder="Mot de passe" />
                                        </div>
                                   </div>
                                   <div class="row">
                                        <div class="12u">
					    <input type="submit" class="button form-button-submit" value="Se connecter" />
                                            <input type="button" formaction="#" class="button button-alt form-button-reset" value="Rafraîchir"/>
                                            <c:choose>
                                                <c:when test="${ wrong == true }">
                                                    <div id="wrong">${ message }</div>
                                                </c:when>
                                            </c:choose>
                                            <ul id="copyright">
                                                <li>&copy; 2014 E-Cursus</li>
                                            </ul>
                                        </div>
                                   </div>
                                 </div>
                                </form>
                            </div>
                        </div>
                </div>
            </article>
        </div>
    </body>
</html>
