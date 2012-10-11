<%=packageName%>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
        <r:require modules="bootstrap"/>
		<g:set var="entityName" value="\${message(code: '${domainClass.propertyName}.label', default: '${className}')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
	</head>
	<body>
        <div class="container">
            <div class="navbar">
                <div class="navbar-inner">
                    <ul class="nav">
                        <li><a href="\${createLink(uri: '/person')}"><g:message code="default.home.label"/></a></li>
                        <li><g:link action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                        <li><g:link action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
                    </ul>
                </div>
            </div>
            <div id="edit-${domainClass.propertyName}">
                <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
                <g:if test="\${flash.message}">
                <div class="message" role="status">\${flash.message}</div>
                </g:if>
                <g:hasErrors bean="\${${propertyName}}">
                <ul class="errors" role="alert">
                    <g:eachError bean="\${${propertyName}}" var="error">
                    <li <g:if test="\${error in org.springframework.validation.FieldError}">data-field-id="\${error.field}"</g:if>><g:message error="\${error}"/></li>
                    </g:eachError>
                </ul>
                </g:hasErrors>
                <g:form method="post" <%= multiPart ? ' enctype="multipart/form-data"' : '' %>>
                    <g:hiddenField name="id" value="\${${propertyName}?.id}" />
                    <g:hiddenField name="version" value="\${${propertyName}?.version}" />
                    <fieldset class="form">
                        <g:render template="form"/>
                    </fieldset>
                    <fieldset class="form-actions">
                        <g:actionSubmit class="btn btn-primary save" action="update" value="\${message(code: 'default.button.update.label', default: 'Update')}" />
                        <g:actionSubmit class="btn delete" action="delete" value="\${message(code: 'default.button.delete.label', default: 'Delete')}" formnovalidate="" onclick="return confirm('\${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                    </fieldset>
                </g:form>
            </div>
        </div>
	</body>
</html>
