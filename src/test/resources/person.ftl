package ${packageName};

public class ${className}{

    <#list columns as column>
    private ${column.type} ${column.name};
    </#list>

}

