package ${pkgName};

<#if typePkgNames?has_content>
    <#list typePkgNames as typePkgName>
        import ${typePkgName};
    </#list>
</#if>

<#include "fileHeader.ftl">
@Data
@Entity
@Accessors(chain = true)
@Table(name="${tableName}")
public class ${className}Entity {
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
