<#ftl output_format="HTML"/>
<#-- @ftlvariable name="model" type="org.springframework.ui.Model" -->

<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Sector selection</title>
    <link rel="stylesheet" href="normalize.css" type="text/css">
    <link rel="stylesheet" href="skeleton.css" type="text/css">
    <link rel="stylesheet" href="custom.css" type="text/css">
</head>
<body>
<p>Please enter your name and pick the Sectors you are currently involved in.</p>
<form>
    <div class="row">
        <label for="name">Name:</label>
        <input id="name" type="text" required>
    </div>

    <div class="row">
        <label for="sector">Sectors:</label>
        <select id="sector" class="sector-selector" multiple="multiple" size="5" required>
            <#function repeat x n>
                <#return ''?left_pad(n * 2, x)>
            </#function>

            <#macro listSectors collection identLevel>
                <#list collection as sector>
                    <#noautoesc>
                        <option value="${sector.value}">${repeat("_", identLevel)?replace("_", "&nbsp;")}${sector.name}</option>
                    </#noautoesc>
                    <#if sector.subSectors?? && sector.subSectors?has_content>
                        <#local count = identLevel + 1 />
                        <@listSectors sector.subSectors count />
                    </#if>
                </#list>
            </#macro>

            <@listSectors sectors 0 />
        </select>
    </div>
    <label>
        <input type="checkbox" required>
        <span class="label-body">Agree to terms</span>
    </label>
    <input class="button-primary" type="submit" value="Save">
</form>
</body>
</html>
