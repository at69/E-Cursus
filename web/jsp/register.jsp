<%-- 
    Document   : register
    Created on : 2 mars 2014, 12:52:22
    Author     : Emmanuel
--%>

<%@ include file="header.jsp" %>
<!-- Register -->
        <div class="wrapper wrapper-style4">
            <article id="register" class="container small">
                <header>
                    <h2>Inscrivez-vous</h2>
                    <span>Remplissez le formulaire ci-dessous</span>
                </header>
                <div>
                    <div class="row">
                        <div class="12u">
                            <form method="post" action="register">
                                <div>
                                   <div class="row half">
                                       <div class="6u">
                                            <input type="text" name="firstname" id="firstname" placeholder="Prénom" />
                                       </div>
				       <div class="6u">
                                            <input type="text" name="lastname" id="lastname" placeholder="Nom" />
                                       </div>
                                   </div>
                                   <div class="row half">
                                        <div class="12u">
                                            <input type="text" name="mail" id="mail" placeholder="E-mail" />
                                        </div>
                                   </div>
				   <div class="row half">
                                       <div class="6u">
                                            <input type="password" name="pass" id="pass" placeholder="Mot de passe" />
                                       </div>
				       <div class="6u">
                                            <input type="password" name="conf" id="conf" placeholder="Confirmation" />
                                       </div>
                                   </div>
                                   <div class="row">
                                        <div class="12u">
					    <input type="submit" class="button form-button-submit" value="M'inscrire" />
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
