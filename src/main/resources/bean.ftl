package ${packageName};

@Data
public class ${className}{

    <#list columns as column>
    <#if column.comment != ''>
    /**
    *  ${column.comment}
    */
    </#if>
    private ${column.type} ${column.name};

    </#list>
}

