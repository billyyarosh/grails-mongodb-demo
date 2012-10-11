<html>
<head>
	<meta name='layout' content='main'/>
    <r:require modules="bootstrap"/>
	<title><g:message code="springSecurity.login.title"/></title>
</head>

<body>
<div class='container'>

		<g:if test='${flash.message}'>
			<div class='alert alert-error'>${flash.message}</div>
		</g:if>
        <div class="row span12">
            <legend><g:message code="springSecurity.login.header"/></legend>
            <div class="span6">
                <form action='${postUrl}' method='POST' id='loginForm' class='form-horizontal' autocomplete='off'>
                    <div class="control-group">
                        <label class="control-label" for='username'><g:message code="springSecurity.login.username.label"/>:</label>
                        <div class="controls">
                            <input type='text' class='text_' name='j_username' id='username'/>
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label" for='password'><g:message code="springSecurity.login.password.label"/>:</label>
                        <div class="controls">
                            <input type='password' class='text_' name='j_password' id='password'/>
                        </div>
                    </div>

                    <div class="control-group" id="remember_me_holder">
                        <div class="controls">
                            <label class="checkbox" for='remember_me'><g:message code="springSecurity.login.remember.me.label"/>
                                <input type='checkbox' name='${rememberMeParameter}' id='remember_me'
                                       <g:if test='${hasCookie}'>checked='checked'</g:if>/>
                            </label>
                            <input class="btn btn-primary" type='submit' id="submit" value='${message(code: "springSecurity.login.button")}'/>
                        </div>
                    </div>
                </form>
            </div>
            <div class="span4">
                <div class="">
                    <dl class="dl-horizontal">
                        <dt>Admin User (u/p):</dt>
                        <dd>admin/admin</dd>
                    </dl>
                    <dl class="dl-horizontal">
                        <dt>Guest User (u/p):</dt>
                        <dd>guest/guest</dd>
                    </dl>
                </div>
            </div>
        </div>
</div>
<script type='text/javascript'>
	<!--
	(function() {
		document.forms['loginForm'].elements['j_username'].focus();
	})();
	// -->
</script>
</body>
</html>
