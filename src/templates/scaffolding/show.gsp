<% import grails.persistence.Event %>
<%=packageName%>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
        <r:require modules="bootstrap"/>
		<g:set var="entityName" value="\${message(code: '${domainClass.propertyName}.label', default: '${className}')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
        <div class="container">
            <div class="navbar">
                <div class="navbar-inner">
                    <ul class="nav">
                        <li><a class="home" href="\${createLink(uri: '/person')}"><g:message code="default.home.label"/></a></li>
                        <li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                        <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
                    </ul>
                </div>
            </div>
            <div id="show-${domainClass.propertyName}" role="main">
                <h1><g:message code="default.show.label" args="[entityName]" /></h1>
                <g:if test="\${flash.message}">
                <div class="message" role="status">\${flash.message}</div>
                </g:if>
                <g:form class="property-list ${domainClass.propertyName}">
                    <fieldset>
                <%  excludedProps = Event.allEvents.toList() << 'id' << 'version'
                    allowedNames = domainClass.persistentProperties*.name << 'dateCreated' << 'lastUpdated'
                    props = domainClass.properties.findAll { allowedNames.contains(it.name) && !excludedProps.contains(it.name) }
                    Collections.sort(props, comparator.constructors[0].newInstance([domainClass] as Object[]))
                    props.each { p -> %>
                    <g:if test="\${${propertyName}?.${p.name}}">
                        <label id="${p.name}-label" class="property-label"><g:message code="${domainClass.propertyName}.${p.name}.label" default="${p.naturalName}" /></label>
                        <%  if (p.isEnum()) { %>
                            <span class="uneditable-input" aria-labelledby="${p.name}-label"><g:fieldValue bean="\${${propertyName}}" field="${p.name}"/></span>
                        <%  } else if (p.oneToMany || p.manyToMany) { %>
                            <g:each in="\${${propertyName}.${p.name}}" var="${p.name[0]}">
                            <span class="uneditable-input" aria-labelledby="${p.name}-label"><g:link controller="${p.referencedDomainClass?.propertyName}" action="show" id="\${${p.name[0]}.id}">\${${p.name[0]}?.encodeAsHTML()}</g:link></span>
                            </g:each>
                        <%  } else if (p.manyToOne || p.oneToOne) { %>
                            <span class="uneditable-input" aria-labelledby="${p.name}-label"><g:link controller="${p.referencedDomainClass?.propertyName}" action="show" id="\${${propertyName}?.${p.name}?.id}">\${${propertyName}?.${p.name}?.encodeAsHTML()}</g:link></span>
                        <%  } else if (p.type == Boolean || p.type == boolean) { %>
                            <span class="uneditable-input" aria-labelledby="${p.name}-label"><g:formatBoolean boolean="\${${propertyName}?.${p.name}}" /></span>
                        <%  } else if (p.type == Date || p.type == java.sql.Date || p.type == java.sql.Time || p.type == Calendar) { %>
                            <span class="uneditable-input" aria-labelledby="${p.name}-label"><g:formatDate date="\${${propertyName}?.${p.name}}" /></span>
                        <%  } else if(!p.type.isArray()) { %>
                            <span class="uneditable-input" aria-labelledby="${p.name}-label"><g:fieldValue bean="\${${propertyName}}" field="${p.name}"/></span>
                        <%  } %>
                    </g:if>
                <%  } %>
                    </fieldset>
                    <footer class="form-actions">
                        <g:hiddenField name="id" value="\${${propertyName}?.id}" />
                        <g:link class="btn btn-primary edit" action="edit" id="\${${propertyName}?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                        <g:actionSubmit class="btn delete" action="delete" value="\${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('\${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                    </footer>
                </g:form>
            </div>
        </div>
	</body>
</html>
