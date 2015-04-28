<#include "base.ftl">

<#macro page_head>
  <title>Mis lugares favoritos</title>
</#macro>

<#macro page_body>
	<h1>Mis lugares favoritos</h1>
	<ul>
	<#list places as place>
    	<li>${place}</li>
	</#list>
	</ul>

	<a href="/place">Añadir</a>
 </#macro>

<@display_page/>
