<#include "base.ftl">

<#macro page_head>
  <title>Nuevo lugar</title>
</#macro>

<#macro page_body>
	<h1>Nuevo lugar</h1>
	<form id="place" method="post" action="/place">
 
		<label for="place">Place</label>
		<input type="text" name="place">
 
		<input type="submit" name="submit" id="submit" value="add place" />
	</form>	
</#macro>

<@display_page/>
