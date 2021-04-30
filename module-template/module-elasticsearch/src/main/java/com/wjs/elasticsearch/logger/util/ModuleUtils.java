package com.wjs.elasticsearch.logger.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wenjs
 */
public abstract class ModuleUtils {
    private static final Logger log = LoggerFactory.getLogger(ModuleUtils.class);
    private static final Map<Class, ModuleInfo> classModuleInfoRepository = new ConcurrentHashMap();
    private static final Map<String, ModuleUtils.ModuleInfo> nameModuleInfoRepository = new ConcurrentHashMap();
    private static final ModuleUtils.ModuleInfo noneInfo;

    private ModuleUtils() {
    }

    public static ModuleUtils.ModuleInfo getModuleByClass(Class type) {
        return (ModuleUtils.ModuleInfo)classModuleInfoRepository.computeIfAbsent(type, ModuleUtils::parse);
    }

    public static String getClassPath(Class type) {
        ProtectionDomain domain = type.getProtectionDomain();
        CodeSource codeSource = domain.getCodeSource();
        if (codeSource == null) {
            return getClassPath(type.getResource("").getPath(), type.getPackage().getName());
        } else {
            String path = codeSource.getLocation().toString();
            boolean isJar = path.contains("!/") && path.contains(".jar");
            if (isJar) {
                return path.substring(0, path.lastIndexOf(".jar") + 4);
            } else {
                return path.endsWith("/") ? path.substring(0, path.length() - 1) : path;
            }
        }
    }

    public static String getClassPath(String path, String packages) {
        if (path.endsWith(".jar")) {
            return path;
        } else {
            boolean isJar = path.contains("!/") && path.contains(".jar");
            if (isJar) {
                return path.substring(0, path.lastIndexOf(".jar") + 4);
            } else {
                int pos = path.endsWith("/") ? 2 : 1;
                return path.substring(0, path.length() - packages.length() - pos);
            }
        }
    }

    private static ModuleUtils.ModuleInfo parse(Class type) {
        String classpath = getClassPath(type);
        return (ModuleUtils.ModuleInfo)nameModuleInfoRepository.values().stream().filter((moduleInfo) -> {
            return classpath.equals(moduleInfo.classPath);
        }).findFirst().orElse(noneInfo);
    }

    public static ModuleUtils.ModuleInfo getModule(String id) {
        return (ModuleUtils.ModuleInfo)nameModuleInfoRepository.get(id);
    }

    public static void register(ModuleUtils.ModuleInfo moduleInfo) {
        nameModuleInfoRepository.put(moduleInfo.getId(), moduleInfo);
    }

    static {
        try {
            log.info("init module info");
            Resource[] resources = (new PathMatchingResourcePatternResolver()).getResources("classpath*:/hsweb-module.json");
            Resource[] var1 = resources;
            int var2 = resources.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                Resource resource = var1[var3];
                String classPath = getClassPath(resource.getURL().toString(), "hsweb-module.json");
                ModuleUtils.ModuleInfo moduleInfo = (ModuleUtils.ModuleInfo) JSON.parseObject(resource.getInputStream(), ModuleUtils.ModuleInfo.class, new Feature[0]);
                moduleInfo.setClassPath(classPath);
                register(moduleInfo);
            }
        } catch (Exception var7) {
            log.error(var7.getMessage(), var7);
        }

        noneInfo = new ModuleUtils.ModuleInfo();
    }

    public static class ModuleInfo {
        private String classPath;
        private String id;
        private String groupId;
        private String path;
        private String artifactId;
        private String gitCommitHash;
        private String gitRepository;
        private String comment;
        private String version;

        public ModuleInfo() {
        }

        public String getGitLocation() {
            String gitCommitHash = this.gitCommitHash;
            if (gitCommitHash == null || gitCommitHash.contains("$") || gitCommitHash.contains("@")) {
                gitCommitHash = "master";
            }

            return this.gitRepository + "/blob/" + gitCommitHash + "/" + this.path + "/";
        }

        public String getGitClassLocation(Class clazz) {
            return this.getGitLocation() + "src/main/java/" + ClassUtils.getPackageName(clazz).replace(".", "/") + "/" + clazz.getSimpleName() + ".java";
        }

        public String getGitClassLocation(Class clazz, long line, long lineTo) {
            return this.getGitLocation() + "src/main/java/" + ClassUtils.getPackageName(clazz).replace(".", "/") + "/" + clazz.getSimpleName() + ".java#L" + line + "-L" + lineTo;
        }

        public String getId() {
            if (StringUtils.isEmpty(this.id)) {
                this.id = this.groupId + "/" + this.artifactId;
            }

            return this.id;
        }

        public boolean isNone() {
            return StringUtils.isEmpty(this.classPath);
        }

        public String getClassPath() {
            return this.classPath;
        }

        public String getGroupId() {
            return this.groupId;
        }

        public String getPath() {
            return this.path;
        }

        public String getArtifactId() {
            return this.artifactId;
        }

        public String getGitCommitHash() {
            return this.gitCommitHash;
        }

        public String getGitRepository() {
            return this.gitRepository;
        }

        public String getComment() {
            return this.comment;
        }

        public String getVersion() {
            return this.version;
        }

        public void setClassPath(String classPath) {
            this.classPath = classPath;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setGroupId(String groupId) {
            this.groupId = groupId;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public void setArtifactId(String artifactId) {
            this.artifactId = artifactId;
        }

        public void setGitCommitHash(String gitCommitHash) {
            this.gitCommitHash = gitCommitHash;
        }

        public void setGitRepository(String gitRepository) {
            this.gitRepository = gitRepository;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public void setVersion(String version) {
            this.version = version;
        }
    }
}
