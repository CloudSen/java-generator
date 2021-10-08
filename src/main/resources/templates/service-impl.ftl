package ${pkgName};

import ${interfacePkgName}
import ${repoPkgName}
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

<#include "fileHeader.ftl">
@Service
@RequiredArgsConstructor
public class ${className} implements ${interfaceClassName} {

    private final ${className}Repository ${className?uncap_first}Repository;
}
