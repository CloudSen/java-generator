package ${pkgName};

import ${importServicePkgName};
import cn.uni.starter.autoconfigure.annotation.mvc.RestValidController;
import lombok.RequiredArgsConstructor;

<#include "fileHeader.ftl">
@RequiredArgsConstructor
@RestValidController("/${controllerUrl}")
public class ${className} {

    private final ${className}Service ${className?uncap_first}Service;


}
