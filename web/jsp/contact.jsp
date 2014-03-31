<%-- 
    Document   : contact
    Created on : 3 mars 2014, 14:51:42
    Author     : Crocell
--%>

<%@ include file="header.jsp" %>
<!-- Contact -->
        <div class="wrapper wrapper-style4">
            <article id="contact" class="container small">
                <header>
                    <h2>Contactez-nous</h2>
                    <span>Remplissez le formulaire ci-dessous</span>
                </header>
                <div>
                    <div class="row">
                        <div class="12u">
                            <form method="post" action="#">
                                <div>
                                   <div class="row half">
                                       <div class="6u">
                                            <input type="text" name="name" id="name" placeholder="Nom" />
                                       </div>
                                       <div class="6u">
                                            <input type="text" name="email" id="email" placeholder="Email" />
                                       </div>
                                   </div>
                                   <div class="row half">
                                        <div class="12u">
                                            <input type="text" name="subject" id="subject" placeholder="Sujet" />
                                        </div>
                                   </div>
                                   <div class="row half">
                                        <div class="12u">
                                            <textarea name="message" id="message" placeholder="Message"></textarea>
                                        </div>
                                   </div>
                                   <div class="row">
                                        <div class="12u">
                                            <a href="#" class="button form-button-submit">Envoyer</a>
                                            <a href="#" class="button button-alt form-button-reset">Rafraîchir</a>
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