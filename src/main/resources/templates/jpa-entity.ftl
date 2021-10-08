package ${pkgName};

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import javax.persistence.*;
import java.time.LocalDateTime;
<#if importPkgNames?has_content>
    <#list importPkgNames as typePkgName>
        import ${typePkgName};
    </#list>
</#if>

<#include "fileHeader.ftl">
@Data
@Entity
@Accessors(chain = true)
@Table(name="${tableName}")
public class ${className} {
<#list fields as field>

    <#if field.pkFlg!false>
        @Id
        @Column(name = "UNIQUEID")
        @GeneratedValue(generator = "seq${className}", strategy = GenerationType.SEQUENCE)
        @GenericGenerator(
        name = "seq${className}",
        strategy = "cn.uni.starter.jpa.utils.StringSequenceIdentifier",
        parameters = @org.hibernate.annotations.Parameter(name = "sequence", value = "SEQ_${tableName}")
        )
        private ${field.javaType} id;
    <#else>
        @Basic
        @Column(name = "${field.dbName}")
        private ${field.javaType} ${field.javaName};
    </#if>
</#list>
}
