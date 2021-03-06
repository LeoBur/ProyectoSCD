<%@ include file="/common/taglibs.jsp"%>
<c:set var="lista" value="${endocrinologoList}"/>

<head>
<title><fmt:message key="home.title" /></title>
<meta name="menu" content="Endo" />
<link href="<c:url value="/scripts/main.css" />" rel="stylesheet">
<script src="/scripts/jquery.1.10.2.min.js" type="text/javascript"></script>
<script src="/scripts/jquery.autocomplete.min.js" type="text/javascript"></script>
<script>
    $(document).ready(function() {
        $('#endo-input-search').autocomplete({
            serviceUrl: '${ctx}/admin/getTags',
            paramName: "tagName",
            delimiter: "," ,
            transformResult: function(response) {
            return {
            suggestions: $.map($.parseJSON(response), function(item) {
                return {value: item.tagName, data: item.id };
            })
            };
            }
        });

        $('#button-id').click(function(e) {
           var search = $('input[name=endo-input-search]').val();
           window.location.href = "${ctx}/admin/endocrinologoList?search=search&dni="+search;
        });
    });
</script>
</head>

<div class="col-md-2">
  <div class='text-center'>
    <h3>Endocrinologos</h3>
  </div>
</div>

<div class="col-md-9">
    <div class="well">
        <div class="col-md-12">
            <div class="form-group">
                <div class="row">
                    <div class="col-md-6 form-group">
	                    <input type="text" id="endo-input-search" name="endo-input-search" class="form-control">
                    </div>
	                <div id="actions" class="btn-group">
	                    <span>
	                        <button id="button-id" type="button" class="btn btn-primary"><fmt:message key="button.search" /></button>
	                    </span>
	                </div>
	                <div id="actions" class="btn-group">
                        <a class="btn btn-primary" href="<c:url value='/admin/newEndocrinologo'/>">
                        <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
                    </div>
                </div>
            </div>
	    </div>
        <br></br>
        <div class="row">
            <div class="col-md-12">
                <c:if test="${not empty lista}">
                    <table class="table table-condensed table-striped table-hover">
                        <tr>
                            <th style="width: 30%" class="sortable sorted order1">
                                <fmt:message key="user.dni" />
                            </th>
                            <th style="width: 30%" class="sortable sorted order1">
                                <fmt:message key="user.lastName" />
                            </th>
                            <th style="width: 30%" class="sortable sorted order1"><fmt:message key="user.firstName" /></th>
                            <th style="width: 30%" class="sortable sorted order1"><fmt:message key="user.enabled" /></th>
                            <th style="width: 30%" class="sortable sorted order1"><fmt:message key="activeEndos.acciones" /></th>
                        </tr>
                    </table>
                </c:if>
                <c:forEach var="endo" items="${endocrinologoList}" varStatus="index" >
                        <table class="table table-condensed table-striped table-hover">
                            <tr>
                                <td style="width: 28%">
                                    <c:out value="${endo.persona.dni}" />
                                </td>
                                <td style="width: 28%">
                                    <c:out value="${endo.persona.lastName}" />
                                </td>
                                <td style="width: 34%">
                                    <c:out value="${endo.persona.firstName}" />
                                </td>
                                <td style="width: 28%">
                                    <c:choose>
                                        <c:when test="${endo.persona.enabled == 'true'}">
                                            <input type="checkbox" checked="true" disabled="true"/>
                                        </c:when>
                                        <c:when test="${endo.persona.enabled == 'false'}">
                                            <input type="checkbox" disabled="false"/>
                                        </c:when>
                                    </c:choose>
                                </td>
                                <td style="width: 34%">
                                    <a href="${ctx}/admin/newEndocrinologo?search=search&dni=${endo.persona.dni}">Editar</a>
                                </td>
                            </tr>
                        </table>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
</body>
<c:set var="scripts" scope="request">
<v:javascript formName="endoSearch" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value="/scripts/validator.jsp"/>"></script>
</c:set>