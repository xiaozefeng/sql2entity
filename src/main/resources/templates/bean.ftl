package ${packageName};

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "${tableName}")
public class ${className}{

    <#list columns as column>
    <#if column.comment != ''>
    /**
    *  ${column.comment}
    */
    </#if>
    @Column(name = "${column.columnName}")
    private ${column.type} ${column.name};

    </#list>
}

