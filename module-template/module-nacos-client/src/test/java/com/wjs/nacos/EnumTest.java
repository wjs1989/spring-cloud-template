package com.wjs.nacos;

public class EnumTest {

    public static void main(String[] args) {
        RegistryType file = RegistryType.getType("File");

        System.out.println(file);
    }


    enum RegistryType {
        File,
        ZK,
        Redis,
        Nacos,
        Eureka,
        Consul,
        Etcd3,
        Sofa,
        Custom;

        private RegistryType() {
        }

        public static RegistryType getType(String name) {
            RegistryType[] var1 = values();
            int var2 = var1.length;

            for (int var3 = 0; var3 < var2; ++var3) {
                RegistryType registryType = var1[var3];
                if (registryType.name().equalsIgnoreCase(name)) {
                    return registryType;
                }
            }

            throw new IllegalArgumentException("not support registry type: " + name);
        }
    }
}
