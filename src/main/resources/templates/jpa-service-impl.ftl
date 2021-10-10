package ${pkgName};

import ${servicePkgName};
import ${repoPkgName};
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

<#include "fileHeader.ftl">
@Service
@RequiredArgsConstructor
public class ${className} implements ${serviceClassName} {

private final ${repoClassName} ${repoClassName?uncap_first};
}
