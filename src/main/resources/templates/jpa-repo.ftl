package ${pkgName};

import org.springframework.data.jpa.repository.JpaRepository;
import ${entityPkgName}

<#include "fileHeader.ftl">
public interface ${className} extends JpaRepository<${entityClassName}, ${pkJavaType}> {

}
