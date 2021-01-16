<#ftl output_format="HTML"/>
<#-- @ftlvariable name="errors" type="org.springframework.validation.BindingResult" -->

<#import "/spring.ftl" as spring/>

<#macro errorField text >
    <div class="error-field">${text}</div>
</#macro>

<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Sector selection</title>
    <link rel="stylesheet" href="normalize.css" type="text/css">
    <link rel="stylesheet" href="skeleton.css" type="text/css">
    <link rel="stylesheet" href="custom.css" type="text/css">
</head>
<body>
<form class="form" method="post" action="/submit">
    <h5>Please enter your name and pick the Sectors you are currently involved in.</h5>
    <div class="row">
        <#if errors?? && errors.hasFieldErrors("name")>
            <#assign errorMessage =  errors.getFieldError("name").getDefaultMessage() />
            <@errorField errorMessage />
        </#if>
        <label for="name">Name:</label>
        <@spring.formInput "sectorFormDto.name" "" "text" />
    </div>

    <div class="row">
        <#if errors?? && errors.hasFieldErrors("selectedSectors")>
            <#assign errorMessage =  errors.getFieldError("selectedSectors").getDefaultMessage() />
            <@errorField errorMessage />
        </#if>
        <label for="sectors">Sectors:</label>
        <@spring.bind "sectorFormDto.selectedSectors"/>
        <select id="sectors" class="sector-selector" multiple="multiple" size="5"
                id="${spring.status.expression?replace('[','')?replace(']','')}" name="${spring.status.expression}">
            <#function repeat x n>
                <#return ''?left_pad(n * 2, x)>
            </#function>

            <#macro listSectors collection identLevel>
                <#list collection as sector>
                    <#noautoesc>
                        <#assign isSelected = sectorFormDto.selectedSectors?? && sectorFormDto.selectedSectors?seq_contains(sector.value) />
                        <option value="${sector.value}"
                                <#if isSelected>selected</#if>>${repeat("_", identLevel)?replace("_", "&nbsp;")}${sector.name}</option>
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
        <#if errors?? && errors.hasFieldErrors("agreedToTerms")>
            <#assign errorMessage =  errors.getFieldError("agreedToTerms").getDefaultMessage() />
            <@errorField errorMessage />
        </#if>
        <@spring.formCheckbox "sectorFormDto.agreedToTerms" />
        <span class="label-body">Agree to terms</span>
    </label>
    <input class="button-primary" type="submit" value="Save">
</form>
</body>
</html>
