package com.yangyunsen.generator.java.converter;

import com.yangyunsen.generator.java.common.model.dto.GeneratorConfig;
import com.yangyunsen.generator.java.converter.model.RepoTemplateData;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @author CloudS3n
 * @date 2021-10-09 17:39
 */
@Getter
@RequiredArgsConstructor
public class RepoConverterContext {

    private final RepoConverterStrategy repoConverterStrategy;

    /**
     * 提取全局配置，生成持久层仓库接口模板数据
     *
     * @param generatorConfig 全局配置
     * @param tablePkTypeMap  主键java类型，key：表名 value：主键对应的java类型
     * @return 控制器模板数据列表
     */
    public List<RepoTemplateData> convertRepo(GeneratorConfig generatorConfig, Map<String, String> tablePkTypeMap) {
        return repoConverterStrategy.convert(generatorConfig, tablePkTypeMap);
    }
}
